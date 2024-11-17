package ru.vasilyev.dbp.Sterona.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vasilyev.dbp.Sterona.dao.BoardDAO;
import ru.vasilyev.dbp.Sterona.dao.StatusDAO;
import ru.vasilyev.dbp.Sterona.dao.TaskDAO;
import ru.vasilyev.dbp.Sterona.models.Board;
import ru.vasilyev.dbp.Sterona.models.Status;
import ru.vasilyev.dbp.Sterona.models.Task;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private TaskDAO taskDAO;
    private BoardDAO boardDAO;
    private StatusDAO statusDAO;

    @Autowired
    public TasksController(TaskDAO taskDAO, BoardDAO boardDAO, StatusDAO statusDAO) {
        this.taskDAO = taskDAO;
        this.boardDAO = boardDAO;
        this.statusDAO = statusDAO;
    }

    @GetMapping()
    public String index(Model model) {
        // получим всех из дао и отдадим во вью
        model.addAttribute("tasks", taskDAO.findAll());
        return "tasks/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("task", taskDAO.findById(id));
        model.addAttribute("boards", boardDAO.findAll());
        model.addAttribute("statuses", statusDAO.findAll());
        return "tasks/show";
    }

//    @GetMapping("/new")
//    public String newPerson(Model model) {
//        model.addAttribute("person", new Person());
//        return "tasks/new";
//    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task") Task task,
                          Model model) {
        model.addAttribute("statuses", statusDAO.findAll());
        model.addAttribute("boards", boardDAO.findAll());
        return "tasks/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("task") @Valid Task task,
                         BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "tasks/new";
        }
        taskDAO.save(task);
        return "redirect:/tasks";
    }

    /*
        @GetMapping("/new")
    public String newTask(@ModelAttribute("task") Task task,
                          Model model) {
        model.addAttribute("statuses", statusDAO.findAll());
        model.addAttribute("boards", boardDAO.findAll());
        return "tasks/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult, Model model) {
//        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", statusDAO.findAll());
            model.addAttribute("boards", boardDAO.findAll());
            return "tasks/new";
        }
        taskDAO.save(task);
        return "redirect:/tasks";
    }
     */

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", taskDAO.findById(id));
        model.addAttribute("statuses", statusDAO.findAll());
        model.addAttribute("boards", boardDAO.findAll());
        return "tasks/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult, @PathVariable("id") int id) {
//        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "tasks/new";
        }
        taskDAO.update(id, task);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        taskDAO.delete(id);
        return "redirect:/tasks";
    }

}