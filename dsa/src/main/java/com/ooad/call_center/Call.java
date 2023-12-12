package com.ooad.call_center;

public class Call {

    private int id;
    private String callerName;
    private String issue;
    private STATUS status= STATUS.WAITING;
    private LEVEL level=LEVEL.RESPONDENT;

    public LEVEL getLevel() {
        return level;
    }

    public void setLevel(LEVEL level) {
        this.level = level;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
