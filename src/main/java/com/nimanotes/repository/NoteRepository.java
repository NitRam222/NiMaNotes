package com.nimanotes.repository;

import com.nimanotes.model.Note;
import com.nimanotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
}
