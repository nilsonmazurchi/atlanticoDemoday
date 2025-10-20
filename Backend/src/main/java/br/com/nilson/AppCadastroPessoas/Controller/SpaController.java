package br.com.nilson.AppCadastroPessoas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {
    
    /**
     * Redireciona todas as rotas do Angular para o index.html
     * O Angular vai cuidar do roteamento no client-side
     */
    @RequestMapping(value = {
        "/",
        "/home",
        "/cadastro", 
        "/pessoas",
        "/{path:^(?!api|static|h2-console|.*\\..*).*$}/**"
    })
    public String redirect() {
        // Forward para index.html - NÃO usar forward:/ que cria loop
        return "forward:/index.html";
    }
}