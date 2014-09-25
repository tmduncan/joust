package com.joust.codalot;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MainTest {

    @Test
    public void thatMainRunsWithoutException(){
        Main.main(new String[] {});
    }

    @Test
    public void thatMainRunsWithShortArgument(){
        int result = Main.main(new String[] {"-k", "12"});
        assertThat(0, is(result));
    }

    @Test
    public void thatMainRunsHandlesLongArgument(){
        int result = Main.main(new String[] {"--knights", "15"});
        assertThat(0, is(result));
    }

    @Test
    public void thatMainRunsHandlesMissingArgument(){
        int result = Main.main(new String[] {"-k"});
        assertThat(1, is(result));
    }

    @Test
    public void thatMainRunsHandlesBadArgument(){
        int result = Main.main(new String[] {"-k", "BLAHBLAH"});
        assertThat(1, is(result));
    }


}
