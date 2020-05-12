package com.pantigoso.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model){
        String titulo = "Home";
        model.addAttribute("title", titulo);
        return "index";
    }

}
