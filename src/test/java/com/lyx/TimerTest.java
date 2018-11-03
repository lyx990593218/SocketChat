package com.lyx;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName TimerTest
 * @Descrition TODO()
 * @Author LYX
 * @Date 2018-11-03 15:01
 * @Version 1.0
 */
public class TimerTest {
    Timer timer;

    public TimerTest() {
        timer = new Timer();
        timer.schedule(new TimerTaskTest(), 1000, 2000);
    }

    public static void main(String[] args) {
        new TimerTest();
    }
}
