package com.jboss.windup.test;

import java.util.concurrent.Executor;

class MainExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    }
}
