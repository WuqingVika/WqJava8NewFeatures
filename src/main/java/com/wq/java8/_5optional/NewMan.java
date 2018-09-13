package com.wq.java8._5optional;

import java.util.Optional;

/**
 * 新时代的男人
 * Created by qwu on 2018/8/10.
 */
public class NewMan {
    private Optional<Goddess> goddess=Optional.empty();//默认是null的话就没意义了

    public NewMan(Optional<Goddess> goddess) {
        this.goddess = goddess;
    }

    public NewMan() {
    }

    public Optional<Goddess> getGoddess() {
        return goddess;
    }

    public void setGoddess(Optional<Goddess> goddess) {
        this.goddess = goddess;
    }
}
