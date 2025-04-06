package vitalsanity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vitalsanity.dto.ActualizarContrasenyaData;
import vitalsanity.service.UsuarioService;

@Controller
public class GeneralUserController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/api/general/usuarios/{id}/contrasenya")
    public String actualizarContrasenya(@PathVariable(value="id") Long idUsuario,
                                        Model model) {
        model.addAttribute("actualizarContrasenyaData", new ActualizarContrasenyaData());
        return "general-user/actualizar-contrasenya";
    }

    @PostMapping("/api/general/usuarios/{id}/contrasenya")
    public String actualizarContrasenya(@PathVariable("id") Long id,
                                        @ModelAttribute("actualizarContrasenyaData") ActualizarContrasenyaData actualizarContrasenyaData,
                                        Model model) {
        try {
            usuarioService.actualizarContrasenya(id, actualizarContrasenyaData);
            return "redirect:/api/centro-medico/check";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "general-user/actualizar-contrasenya";
        }
    }

}
