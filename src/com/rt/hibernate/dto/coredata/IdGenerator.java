package com.rt.hibernate.dto.coredata;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private final AtomicInteger count = new AtomicInteger(100);

    public int next(){
        return count.incrementAndGet();
    }
}
