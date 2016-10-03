package com.mishkurov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class posTest {

    private Pos pos;

    @Before
    public void setUp() throws Exception {
        pos = new Pos();
    }


    @Test
    public void acceptCoin() {
        pos.acceptCoin(5);
        assertThat(pos.getDeposit(), is(5));
    }

    @Test(expected = IllegalArgumentException.class)
    @DataPoint
    public void negativeCoinTest() {
        pos.acceptCoin(-1);
    }
}
