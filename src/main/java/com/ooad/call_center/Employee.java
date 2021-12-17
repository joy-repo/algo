package com.ooad.call_center;

import java.util.Random;

public abstract class Employee {

    protected String name;
    protected String id;
    protected String phnopneNum;
    protected LEVEL level;
    protected CallHandler callHandler;
    protected boolean isAvailable = true;

    public abstract void setLevel();

    public LEVEL getLevel() {
        return this.level;
    }

    public Call handleCalls(Call call) {
        // Handle Calls for 10 secs
        this.isAvailable = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        //simulate 1 in 5 needs escalation
        if (new Random().nextInt(10) % 5 == 0) {
            LEVEL nextLevel = this.level.getNext();
            if (nextLevel == null) {
                this.isAvailable = true;
                call.setStatus(STATUS.COMPLETED);
                return call;
            }
            call.setStatus(STATUS.FORWARDED);
            call.setLevel(nextLevel);
            return call;
        }

        isAvailable = true;
        call.setStatus(STATUS.COMPLETED);
        return call;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhnopneNum() {
        return phnopneNum;
    }

    public void setPhnopneNum(String phnopneNum) {
        this.phnopneNum = phnopneNum;
    }
}
