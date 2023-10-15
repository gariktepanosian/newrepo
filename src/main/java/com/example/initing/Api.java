package com.example.initing;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Api {

    private final int requestLimit;
    private final long interval;
    private final ReentrantLock lock = new ReentrantLock();
    private long lastResetTime;
    private final AtomicInteger requestCount;

    public Api(TimeUnit timeUnit, int requestLimit) {
        this.requestLimit = requestLimit;
        this.interval = timeUnit.toMillis(1); // Convert timeUnit to milliseconds
        this.requestCount = new AtomicInteger(0);
        this.lastResetTime = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Api crptApi = new Api(TimeUnit.SECONDS, 5); // Example: 5 requests per second

        for (int i = 0; i < 10; i++) {
            if (crptApi.makeRequest()) {
                // Perform your API request here
            }
        }
    }

    public boolean makeRequest() {
        lock.lock();
        try {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastResetTime >= interval) {
                // If the interval has passed, reset the request count
                requestCount.set(0);
                lastResetTime = currentTime;
            }

            if (requestCount.get() < requestLimit) {
                requestCount.incrementAndGet();
                // Replace this with actual API request logic
                System.out.println("API request made");
                return true;
            } else {
                // Request limit exceeded
                System.out.println("API request limit exceeded");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }
}
