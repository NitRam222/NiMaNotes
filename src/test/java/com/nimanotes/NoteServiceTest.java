package com.nimanotes;

import com.nimanotes.model.Note;
import com.nimanotes.model.User;
import com.nimanotes.service.NoteService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NoteServiceTest {

    private final NoteService noteService = new NoteService();

    @Test
    void combinatorialCanCreateNote() {
        User user = new User("u", "p");
        List<Note> notes = List.of(new Note(), new Note(), new Note(), new Note(), new Note());
        // non-premium with 5 notes -> cannot create
        assertThat(noteService.canCreateNote(user, notes, false)).isFalse();
        // premium -> can
        assertThat(noteService.canCreateNote(user, notes, true)).isTrue();
        // null notes -> allowed
        assertThat(noteService.canCreateNote(user, null, false)).isTrue();
    }

    @Test
    void collectionsFilterByKeyword() {
        User user = new User("u", "p");
        Note a = new Note("hello", "world", user);
        Note b = new Note("foo", "bar", user);
        List<Note> filtered = noteService.filterByKeyword(List.of(a,b), "hello");
        assertThat(filtered).hasSize(1).contains(a);
    }
}
