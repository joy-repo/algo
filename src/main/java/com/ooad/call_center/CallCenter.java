package com.ooad.call_center;

import java.util.ArrayList;

import java.util.List;

import java.util.concurrent.*;

public class CallCenter {

    List<Employee> employees;
    BlockingQueue<Call> calls;
    CallHandler callHandler;

    public static int RESPONDENTS=15;
    public static int MANAGER=7;
    public static int DIRECTOR=3;

    public CallCenter() {
        createEmployees();
        initializeCallQueue();
        createCallHandler();
    }

    private void createCallHandler() {
         callHandler= new CallHandler();
    }

    private void startCallConsumer() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(()-> {
            try {
                callHandler.handleCall();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    public void recieveCall(Call newCall){
        calls.offer(newCall);
    }

    public void initializeCallQueue() {
        calls= new LinkedBlockingQueue<Call>();
    }


    private void createEmployees() {
        // create Respondents, Mangers, Directors
        employees=new ArrayList<>();
    }


}
