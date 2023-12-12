package com.ooad.call_center;

import java.util.*;
import java.util.concurrent.*;

public class CallHandler {

    BlockingQueue<Call> calls = new LinkedBlockingQueue<>();

    Map<LEVEL, BlockingQueue<Employee>> availableEmployee = new ConcurrentHashMap<>();
    Map<Call, List<Employee>> handeledCallRecord = new ConcurrentHashMap<>();


    public void handleCall() throws InterruptedException {

        Call call = calls.take();
        Employee emp = availableEmployee.get(call.getLevel()).take();
        call.setStatus(STATUS.INPROGRESS);

        CompletableFuture.supplyAsync(new HandleWorker(emp, call)).thenAccept(c -> updateAfterCall(c, emp));

    }

    private void updateAfterCall(Call call, Employee emp) {
        try {
            availableEmployee.get(emp.level).put(emp);
            handeledCallRecord.putIfAbsent(call, new ArrayList<>());
            handeledCallRecord.get(call).add(emp);
            if (call.getStatus() != STATUS.COMPLETED) calls.put(call);
        } catch (InterruptedException e) {
        }

    }


}
