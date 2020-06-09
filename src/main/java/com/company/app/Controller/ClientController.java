package com.company.app.Controller;

import com.company.app.Model.Dao.IPersonDaoImpl;
import com.company.app.Model.Entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.GregorianCalendar;

@Controller
//@SessionAttributes("person") //necesario para poder editar , porque usamos el ultimo en ID en session para poder editar
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IPersonDaoImpl personDao;

    @GetMapping("/list")
    public String listAll(Model model){

        GregorianCalendar calendar = new GregorianCalendar();
        model.addAttribute("title","LISTA DE CLIENTES -"+ calendar.getWeekYear());
        model.addAttribute("person", personDao.findAll());
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
    public String save(Person person, Model model){
        personDao.save(person);
    //  sessionStatus.setComplete(); SessionStatus sessionStatus
        return "redirect:/client/list";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(name = "id")Long id,Model model){
        Person person = null;
        if(id>0){
            person = personDao.find(id);
        }
        model.addAttribute("person",person);
        return "client/form";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        if(id > 0){
            personDao.delete(id);
        }
        return "redirect:/client/list";
    }

}
