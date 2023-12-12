package com.ttt;

import java.util.ArrayList;
import java.util.List;

public class Max {

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        List<Integer> list = new ArrayList<Integer>();

        for(int i=1;i<=5;i++){
            list.add(i);
        }

        int count=1;
        int i=-1;
        while(true){
            if(count==list.size()){
                System.out.println("Final --" +list); return;
            }
            i=i+2;
            if(i>=list.size()) i = i-list.size();

            while(list.get(i)==-1){
                i++;
                if(i==list.size()) i=0;
            }
            list.set(i, -1);
            System.out.println(list);
            count++;
        }
    }
}
