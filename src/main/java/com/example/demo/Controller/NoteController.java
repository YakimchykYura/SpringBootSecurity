package com.example.demo.Controller;

import com.example.demo.Entity.Note;


import com.example.demo.NoteService.NoteService;
import com.example.demo.exception.NoteNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public ModelAndView allNotes() {
        ModelAndView result = new ModelAndView("/list");
        result.addObject("notes", noteService.getAll());
        return result;
    }

    @PostMapping("/create")
    public ModelAndView createNotes(@ModelAttribute Note note) {
        noteService.addNote(note);
        return allNotes();
    }


    @GetMapping("/update")
    public String updateNote(@RequestParam(name = "id") Long id, Model model) {
        try {
            model.addAttribute("note", noteService.getById(id));
        } catch (NoteNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "update";
    }

    @PostMapping("/update")
    public ModelAndView updateNotes(@RequestBody Note updatedNote) {
        try {
            noteService.updateNote(updatedNote);
        } catch (NoteNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allNotes();
    }

    @PostMapping("/delete")
    public ModelAndView deleteNotes(@RequestParam(value = "id") long id) {
        try {
            noteService.deleteById(id);
        } catch (NoteNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allNotes();
    }
}
