package cn.minqi.consumer.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Afff  extends Thread{

    @Override
    public void run() {
        try {
            log.debug("开始执行方法");
            Thread.sleep(1000);
            log.debug("执行方法结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


