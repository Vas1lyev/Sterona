package ru.vasilyev.dbp.Sterona.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vasilyev.dbp.Sterona.dao.BoardDAO;
import ru.vasilyev.dbp.Sterona.models.Board;

@Controller
@RequestMapping("/boards")
public class BoardsController {
    private BoardDAO boardDAO;

    @Autowired
    public BoardsController(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("boards", boardDAO.findAll());
        return "boards/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("board", boardDAO.findById(id));
        return "boards/show";
    }

    @GetMapping("/new")
    public String newBoard(@ModelAttribute("board") Board board) {
        return "boards/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("board") @Valid Board board, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "boards/new";
        }
        boardDAO.save(board);
        return "redirect:/boards";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("board", boardDAO.findById(id));
        return "boards/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("boards") @Valid Board board, BindingResult bindingResult, @PathVariable("id") int id) {
//        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "boards/new";
        }
        boardDAO.update(id, board);
        return "redirect:/boards";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        boardDAO.delete(id);
        return "redirect:/boards";
    }

}