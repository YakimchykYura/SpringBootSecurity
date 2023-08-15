package com.example.demo.NoteService;

import com.example.demo.Entity.Note;
import com.example.demo.Entity.NoteRepository;
import com.example.demo.exception.NoteNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;


@Slf4j
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public Note addNote(Note note) {
        return noteRepository.save(note);
    }


    public Note getById(long id) throws NoteNotFoundException {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            return optionalNote.get();
        } else {
            throw new NoteNotFoundException(id);
        }
    }

    public void updateNote(Note note) throws NoteNotFoundException {
        Note newNote = getById(note.getId());
        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());
        noteRepository.save(newNote);
    }

    public void deleteById(Long id) throws NoteNotFoundException {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            noteRepository.deleteById(id);
        } else {
            throw new NoteNotFoundException(id);
        }

    }
}
