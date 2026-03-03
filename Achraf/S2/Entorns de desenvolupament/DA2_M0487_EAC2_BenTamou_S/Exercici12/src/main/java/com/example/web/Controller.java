package com.example.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String index() {
        return "<html><body><h1>Calcula un quart del valor</h1>"
             + "<form action='resultat' method='GET'>"
             + "<h3>Introdueix el valor:</h3>"
             + "<input type='text' name='valor'/>"
             + "<input type='submit' value='Calcular'/>"
             + "</form></body></html>";
    }

    @GetMapping("/resultat")
    public String resultat(@RequestParam String valor) {
        try {
            int num = Integer.parseInt(valor);
            int quart = num / 4;
            String dni = "49876193A";
            return "<html><body>"
                   + "<h1>Calcula un quart del valor</h1>"
                   + "<h3>DNI: " + dni + "</h3>"
                   + "<p>Resultat: <strong>" + quart + "</strong></p>"
                   + "</body></html>";
        } catch (NumberFormatException e) {
            return "<html><body><h1>Error</h1><p>El valor introduit no es un enter</p></body></html>";
        }
    }
}
