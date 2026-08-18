package com.nimanotes.controller;

import com.nimanotes.model.Note;
import com.nimanotes.model.User;
import com.nimanotes.repository.NoteRepository;
import com.nimanotes.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NoteController {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        User user = currentUser();
        List<Note> notes = noteRepository.findByUser(user);
        model.addAttribute("notes", notes);
        model.addAttribute("newNote", new Note());
        return "index";
    }

    @PostMapping("/notes")
    public String createNote(@ModelAttribute("newNote") Note note) {
        User user = currentUser();
        note.setUser(user);
        noteRepository.save(note);
        return "redirect:/";
    }

    @PostMapping("/notes/{id}/update")
    public String updateNote(@PathVariable Long id, @ModelAttribute Note note) {
        User user = currentUser();
        Note existing = noteRepository.findById(id).orElseThrow();
        if (existing.getUser().getId().equals(user.getId())) {
            existing.setTitle(note.getTitle());
            existing.setContent(note.getContent());
            noteRepository.save(existing);
        }

        return "redirect:/";
    }

    @PostMapping("/notes/{id}/delete")
    public String deleteNote(@PathVariable Long id) {
        User user = currentUser();
        Note existing = noteRepository.findById(id).orElseThrow();
        if (existing.getUser().getId().equals(user.getId())) {
            noteRepository.delete(existing);
        }
        return "redirect:/";
    }

    private User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));
    }
}
