package com.lyx;

import java.util.Date;
import java.util.TimerTask;

/**
 * @ClassName TimerTaskTest
 * @Descrition TODO()
 * @Author LYX
 * @Date 2018-11-03 15:03
 * @Version 1.0
 */
public class TimerTaskTest extends TimerTask {

    @Override
    public void run() {
        Date date = new Date(this.scheduledExecutionTime());
        System.out.println("本次执行该线程的时间为：" + date);
    }
}
