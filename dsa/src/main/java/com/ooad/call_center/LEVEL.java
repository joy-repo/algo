package com.ooad.call_center;

public enum LEVEL {

    DIRECTOR,MANAGER,RESPONDENT;

    public LEVEL getNext() {
        if(this==RESPONDENT) return MANAGER;
        if(this==MANAGER) return DIRECTOR;
        return null;
    }
}
