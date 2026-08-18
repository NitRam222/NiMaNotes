package com.nimanotes.service;

import com.nimanotes.model.Note;
import com.nimanotes.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private static final int DEFAULT_LIMIT = 5;

    public boolean canCreateNote(User user, List<Note> currentNotes, boolean isPremium) {
        if (user == null) return false;
        if (currentNotes == null) return true;
        if (isPremium) return true;
        return currentNotes.size() < DEFAULT_LIMIT;
    }

    public List<Note> filterByKeyword(List<Note> notes, String keyword) {
        if (notes == null || keyword == null) return List.of();
        return notes.stream()
            .filter(n -> n.getTitle().contains(keyword) || n.getContent().contains(keyword))
            .collect(Collectors.toList());
    }
}
