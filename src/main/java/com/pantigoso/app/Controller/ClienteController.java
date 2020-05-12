package com.pantigoso.app.Controller;

import com.pantigoso.app.Model.Entity.Cliente;
import com.pantigoso.app.Model.Service.IClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private IClienteServiceImpl service;
    @GetMapping("/list")
    public String listar(Map<String, Object> model){
        String titulo = "Listado de usuarios";
        model.put("title",titulo);
        model.put("client", service.findAll());
        return "clientes/lista";
    }

    @GetMapping("/form")
    public String crear(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("cl",cliente);
        model.addAttribute("title","Formulario de cliente");
        return "clientes/formulario";
    }

    @PostMapping("/form")
    public String guardar(Cliente cliente, Model mode){

        service.save(cliente);
        mode.addAttribute("title","Formulario de cliente");
        return "redirect:/client/list";
    }
}
