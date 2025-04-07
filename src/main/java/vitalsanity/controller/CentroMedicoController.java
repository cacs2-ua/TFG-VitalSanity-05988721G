package vitalsanity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vitalsanity.authentication.ManagerUserSession;
import vitalsanity.dto.RegistroProfesionalesMedicosData;
import vitalsanity.dto.UsuarioData;
import vitalsanity.model.CentroMedico;
import vitalsanity.repository.CentroMedicoRepository;
import vitalsanity.service.UsuarioService;

import java.util.List;

@Controller
public class CentroMedicoController {

    @Autowired
    private CentroMedicoRepository centroMedicoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ManagerUserSession managerUserSession;

    @GetMapping("/api/centro-medico/profesionales-medicos")
    public String mostrarFormularioRegistroProfesionalesMedicos(Model model) {
        model.addAttribute("registroProfesionalesMedicosData", new vitalsanity.dto.RegistroProfesionalesMedicosData());
        return "centro-medico/registro-profesionales-medicos";
    }

    @PostMapping("/api/centro-medico/profesionales-medicos")
    public String registrarProfesionalesMedicos(@ModelAttribute("registroProfesionalesMedicosData") vitalsanity.dto.RegistroProfesionalesMedicosData data,
                                                Model model) {
        // Obtener el id del usuario centro medico logueado
        Long idUsuario = managerUserSession.usuarioLogeado();
        // Obtener el centro medico asociado al usuario logueado
        CentroMedico centroMedico = centroMedicoRepository.findByUsuarioId(idUsuario)
                .orElseThrow(() -> new IllegalStateException("Centro medico no encontrado para el usuario logueado"));
        try {
            List<UsuarioData> registrados = usuarioService.registrarProfesionalesMedicos(data.getCsvFile(), centroMedico);
            model.addAttribute("mensaje", "Registro completado con exito para " + registrados.size() + " profesionales medicos.");
            return "centro-medico/registro-profesionales-medicos-success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "centro-medico/registro-profesionales-medicos";
        }
    }
}
