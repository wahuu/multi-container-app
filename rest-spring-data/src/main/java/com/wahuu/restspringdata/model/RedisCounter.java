package com.wahuu.restspringdata.model;

import java.io.Serializable;

public class RedisCounter implements Serializable {
    private Long counter;

    public RedisCounter(Long counter) {
        this.counter = counter;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public RedisCounter increment() {
        this.counter++;
        return this;
    }

    @Override
    public String toString() {
        return "RedisCounter{" +
                "counter=" + counter +
                '}';
    }
}
