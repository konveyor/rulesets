package org.konveyor;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SettableListenableFuture;

public class ListenableFutureTest {

    public static void main(String[] args) {
        SettableListenableFuture<String> future = new SettableListenableFuture<>();

        future.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("Success: " + result);
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failure: " + ex.getMessage());
            }
        });

        // Simulate asynchronous operation
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                future.set("Hello, World!");
            } catch (InterruptedException e) {
                future.setException(e);
            }
        }).start();
    }
}
