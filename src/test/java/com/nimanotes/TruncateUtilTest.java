package com.nimanotes;

import com.nimanotes.util.TruncateUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TruncateUtilTest {

    @Test
    void truncateWorks() {
        String s = "abcdefgh";
        assertThat(TruncateUtil.truncate(s, 5)).isEqualTo("abcde");
        assertThat(TruncateUtil.truncate(s, 8)).isEqualTo(s);
    }
}
