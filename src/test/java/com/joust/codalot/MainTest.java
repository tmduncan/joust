package com.joust.codalot;

import com.joust.codalot.util.ExitHelper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MainTest {

    ExitHelper exitHelper = new ExitHelper() {
        int status;
        @Override
        public void exit(int status) {
            this.status = status;
        }

        @Override
        public int getExitStatus() {
            return status;
        }
    };
    Main underTest = new Main(exitHelper);

    @Test
    public void thatMainRunsWithoutException() {
        underTest.run(new String[]{});
        assertThat(underTest.getExitHelper().getExitStatus(), is(0));
    }

    @Test
    public void thatMainRunsWithShortArgument() {
        underTest.run(new String[]{"-k", "12"});
        assertThat(underTest.getExitHelper().getExitStatus(), is(0));
    }

    @Test
    public void thatMainRunsHandlesLongArgument() {
        underTest.run(new String[]{"--knights", "15"});
        assertThat(underTest.getExitHelper().getExitStatus(), is(0));
    }

    @Test
    public void thatMainRunsHandlesMissingArgument() {
        underTest.run(new String[]{"-k"});
        assertThat(underTest.getExitHelper().getExitStatus(), is(1));
    }

    @Test
    public void thatMainRunsWithLessThanMinimumKnights() {
        underTest.run(new String[]{"-k", "10"});
        assertThat(1, is(underTest.getExitHelper().getExitStatus()));
    }

    @Test
    public void thatMainRunsHandlesBadArgument() {
        underTest.run(new String[]{"-k", "BLAHBLAH"});
        assertThat(underTest.getExitHelper().getExitStatus(), is(1));
    }


}
