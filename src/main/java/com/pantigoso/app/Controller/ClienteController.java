package com.pantigoso.app.Controller;

import com.pantigoso.app.Model.Entity.Cliente;
import com.pantigoso.app.Model.Service.IClienteServiceImpl;
import com.pantigoso.app.Util.Paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String listar(@RequestParam(name = "page", defaultValue = "0")int page, Map<String, Object> model){
        //aca le decimos cuantos registros por pagina se mostrara
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Cliente> clientes = service.findAll(pageRequest);
        PageRender<Cliente> pageRender= new PageRender<>("/client/list", clientes);

        String titulo = "Listado de usuarios";
        model.put("title",titulo);
        model.put("client",clientes);
        model.put("page",pageRender);
        //model.put("client", service.findAll());
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
