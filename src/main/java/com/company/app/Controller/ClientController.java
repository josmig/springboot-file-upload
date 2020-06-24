package com.company.app.Controller;

import com.company.app.Model.Entity.Person;
import com.company.app.Model.Services.PersonServiceImpl;
import com.company.app.Util.Paginator.PageRender;
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
import java.util.GregorianCalendar;

@Controller
//@SessionAttributes("person") //necesario para poder editar , porque usamos el ultimo en ID en session para poder editar
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/list")
    public String listAll(@RequestParam(value = "page" ,defaultValue = "0")int page, Model model){
        //aca le decimos cuantos registros por pagina se mostrara
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Person> person = personService.findAll(pageRequest);
        PageRender<Person> pageRender= new PageRender<>("/client/list", person);

        GregorianCalendar calendar = new GregorianCalendar();
        model.addAttribute("title","LISTA DE CLIENTES -"+ calendar.getWeekYear());
        model.addAttribute("person",person);
        model.addAttribute("page",pageRender);
        return  "client/list";
    }
    @GetMapping("/form")
    public String create(Model model){
        Person person = new Person();
        model.addAttribute("person",person);
        model.addAttribute("title","Formulario de Persona");
        return "client/form";
    }
    @PostMapping("/form")
    public String save(Person person, Model model, @RequestParam("file")MultipartFile foto){

        if(!foto.isEmpty()){
            Path carpetaUpload = Paths.get("src//main//resources//static/uploads");
            String rootPath = carpetaUpload.toFile().getAbsolutePath();
            try{
                byte[] bytes = foto.getBytes();
                Path ruta_completa = Paths.get(rootPath + "//" + foto.getOriginalFilename());
                Files.write(ruta_completa,bytes);
                person.setFoto(foto.getOriginalFilename());

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        personService.save(person);
    //  sessionStatus.setComplete(); SessionStatus sessionStatus
        return "redirect:/client/list";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(name = "id")Long id,Model model){
        Person person = null;
        if(id>0){
            person = personService.findOne(id);
        }
        model.addAttribute("person",person);
        return "client/form";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        if(id > 0){
            personService.delete(id);
        }
        return "redirect:/client/list";
    }

}
