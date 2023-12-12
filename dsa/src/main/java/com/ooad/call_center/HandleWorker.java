package com.ooad.call_center;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class HandleWorker implements Supplier<Call> {

    private Employee emp;
    private Call call;

    public HandleWorker(Employee emp, Call call){
        this.emp=emp;
        this.call=call;
    }


    @Override
    public Call get() {
        return emp.handleCalls(call);
    }
}
