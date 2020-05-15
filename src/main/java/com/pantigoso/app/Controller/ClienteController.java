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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public String guardar(Cliente cliente, Model mode, @RequestParam("file")MultipartFile foto){

        service.save(cliente);
        if(!foto.isEmpty()){

            Path directorioRecursos = Paths.get("src/main/resources/static/upload");
            String rootPath = directorioRecursos.toFile().getAbsolutePath();

            try{
                byte[] bytes = foto.getBytes();
                Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
                Files.write(rutaCompleta,bytes);
                //flash.addFlashAttribute("inf","Has subido correctamente '"+ foto.getOriginalFilename() + "'");

                cliente.setFoto(foto.getOriginalFilename());
            }catch (IOException e){
                e.printStackTrace();
            }

        }
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
