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
    public void thatMainRunsWithKnightsShortArgument() {
        underTest.run(new String[]{"-k", "12"});
        assertThat(underTest.getExitHelper().getExitStatus(), is(0));
    }

    @Test
    public void thatMainRunsHandlesKnightsLongArgument() {
        underTest.run(new String[]{"--knights", "15"});
        assertThat(underTest.getExitHelper().getExitStatus(), is(0));
    }

    @Test
    public void thatMainRunsWithDaysShortArgument() {
        underTest.run(new String[]{"-d", "1"});
        assertThat(underTest.getExitHelper().getExitStatus(), is(0));
    }

    @Test
    public void thatMainRunsWithDaysLongArgument() {
        underTest.run(new String[]{"--days", "1"});
        assertThat(underTest.getExitHelper().getExitStatus(), is(0));
    }

    @Test
    public void thatMainRunsWithDaysMoreThanOneDay() {
        underTest.run(new String[]{"-d", "2"});
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
    public void thatMainRunsHandlesBadArgumentValue() {
        underTest.run(new String[]{"-k", "BLAHBLAH"});
        assertThat(underTest.getExitHelper().getExitStatus(), is(1));
    }

    @Test
    public void thatMainRunsHandlesBadArgument() {
        underTest.run(new String[]{"-q", "BLAHBLAH"});
        assertThat(underTest.getExitHelper().getExitStatus(), is(1));
    }


}
