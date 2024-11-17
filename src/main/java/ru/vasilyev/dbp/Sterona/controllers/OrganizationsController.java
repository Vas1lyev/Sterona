package ru.vasilyev.dbp.Sterona.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vasilyev.dbp.Sterona.dao.OrganizationDAO;
import ru.vasilyev.dbp.Sterona.models.Organization;

@Controller
@RequestMapping("/organizations")
public class OrganizationsController {
    private OrganizationDAO organizationDAO;

    @Autowired
    public OrganizationsController(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    @GetMapping()
    public String index(Model model) {
        // получим всех из дао и отдадим во вью
        model.addAttribute("organizations", organizationDAO.findAll());
        return "organizations/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("organization", organizationDAO.findById(id));
        return "organizations/show";
    }

//    @GetMapping("/new")
//    public String newPerson(Model model) {
//        model.addAttribute("person", new Person());
//        return "people/new";
//    }

    @GetMapping("/new")
    public String newOrganization(@ModelAttribute("organization") Organization organization) {
        return "organizations/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("organization") @Valid Organization organization, BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "organizations/new";
        }
        organizationDAO.save(organization);
        return "redirect:/organizations";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("organization", organizationDAO.findById(id));
        return "organizations/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("organization") @Valid Organization organization, BindingResult bindingResult, @PathVariable("id") int id) {
//        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "organizations/new";
        }
        organizationDAO.update(id, organization);
        return "redirect:/organizations";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        organizationDAO.delete(id);
        return "redirect:/organizations";
    }

}