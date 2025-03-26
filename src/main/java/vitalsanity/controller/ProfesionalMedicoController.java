package vitalsanity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfesionalMedicoController {

    @GetMapping("/api/profesional-medico/pacientes/{idPaciente}/informes/nuevo")
    public String crearNuevoInforme(@PathVariable(value="idPaciente") Long idPaciente,
                                                    Model model) {
        return "profesional-medico/profesional-medico-crear-nuevo-informe";
    }

    @GetMapping("/api/profesional-medico/pacientes/{idPaciente}/informes")
    public String verDetallesInformePaciente(@PathVariable(value="idPaciente") Long idPaciente,
                                                    Model model) {
        return "profesional-medico/profesional-medico-informes-del-paciente";
    }

}



