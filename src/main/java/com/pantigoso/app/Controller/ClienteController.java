package com.pantigoso.app.Controller;

import com.pantigoso.app.Model.Entity.Cliente;
import com.pantigoso.app.Model.Service.IClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("client",cliente);
        model.addAttribute("title","Formulario de cliente");
        return "clientes/formulario";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id")Long id, Model model){
        Cliente cliente = null;
        if(id > 0){
            cliente = service.findOne(id);
        }
        model.addAttribute("title","Editar usuario");
        model.addAttribute("client",cliente);

        return "clientes/formulario";
    }

    @PostMapping("/form")
    public String guardar(Cliente cliente, Model mode){

        service.save(cliente);
        mode.addAttribute("title","Formulario de cliente");
        return "redirect:/client/list";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable(name = "id")Long id){

        if(id > 0){
            service.delete(id);
        }

        return "redirect:/client/list";
    }


}
