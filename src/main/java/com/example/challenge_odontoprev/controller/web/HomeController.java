package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.model.Usuario;
import com.example.challenge_odontoprev.security.UsuarioDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UsuarioDetails usuarioDetails, Model model) {
        if (usuarioDetails != null && usuarioDetails.getUsuario() != null) {
            model.addAttribute("usuarioLogado", usuarioDetails.getUsuario());
        }
        return "home";
    }

}
