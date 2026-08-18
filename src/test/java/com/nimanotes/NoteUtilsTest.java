package com.nimanotes;

import com.nimanotes.util.NoteUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NoteUtilsTest {

    @Test
    void titleLengthBoundaries() {
        assertThat(NoteUtils.isTitleLengthValid("ab")).isFalse(); // n-1
        assertThat(NoteUtils.isTitleLengthValid("abc")).isTrue(); // n
        assertThat(NoteUtils.isTitleLengthValid("abcd")).isTrue(); // n+1
    }

    @Test
    void computeRatioWithTolerance() {
        double value = NoteUtils.computeRatio(22.0, 7.0);
        assertThat(value).isCloseTo(3.142857, org.assertj.core.data.Offset.offset(0.001));
    }

    @Test
    void regexPattern() {
        String email = "test@example.com";
        String bad = "not-an-email";
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        assertThat(NoteUtils.matchesPattern(email, regex)).isTrue();
        assertThat(NoteUtils.matchesPattern(bad, regex)).isFalse();
    }

    @Test
    void divisionByZeroThrows() {
        assertThatThrownBy(() -> NoteUtils.computeRatio(1.0, 0.0)).isInstanceOf(IllegalArgumentException.class);
    }
}
