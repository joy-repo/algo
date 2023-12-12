package com.ooad.call_center;

import java.util.Random;

public class Respondent extends Employee{


    @Override
    public void setLevel() {
        this.level=LEVEL.RESPONDENT;
    }
}
