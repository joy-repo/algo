package lld.callcenter;

import java.awt.event.WindowStateListener;

public class CallHandler {


    private CallHandler(){}

    private static CallHandler instance;

    public static CallHandler getInstance(){
        if(instance==null){
            instance= new CallHandler();
        }
        return instance;
    }

    public static void main(String[] args) {
        CallHandler callHandler = getInstance();
        callHandler.handleCall(new Caller("Mohan"));

    }


    public void handleCall(Caller caller){
        Employee employee =new Employee();
        if(employee==null){
            System.out.println("Nobody to handle the call");
        }

        System.out.println("Employee handling the call: "+ employee);

    }
}
