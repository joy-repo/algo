package com.heap;

import java.util.ArrayList;
import java.util.List;

// 0 1 2 3 4 5
// 9 4 6 7 8 3
//

public class MyPriorityQueue_MAXHEAP {

    public List<Integer> list = new ArrayList<>();

    public void insert(Integer i) {
        list.add(i);
        boolean br = false;
        int l = list.size() - 1;
        while ((l - 1) / 2 > 0) {

            if (list.get(l) > list.get((l - 1) / 2)) {
                int t = list.get(l);
                list.set(l, list.get((l - 1) / 2));
                list.set(((l - 1) / 2), t);

            }
            l = ((l - 1) / 2);
          //  System.out.println(list);
        }
        if (list.get(l) > list.get((l - 1) / 2) && ((l - 1) / 2)==0) {
            int t = list.get(l);
            list.set(l, list.get((l - 1) / 2));
            list.set(((l - 1) / 2), t);

        }

      //  System.out.println(list);
    }


    public Integer peek(){
        return list.get(0);
    }

    public Integer poll(){

        int ans = list.get(0);
        list.set(0,list.get(list.size()-1));
        list.remove(list.size()-1);
        int l = list.size()-1;

        for(int i =0; i<=l;){
            if(l<2*(i+1)-1) break;
            if(l<2*(i+1)){
                int left = list.get(2*(i+1)-1);
                if(left<list.get(i)){
                    int t =list.get(i);
                    list.set(i,left);
                    list.set(2*(i+1)-1,t);
                    break;
                }
            }
            int right = list.get(2*(i+1));
            int left = list.get(2*(i+1)-1);
            if(Math.max(right,left)<list.get(i)) break;
            if(left>right){
                int t =list.get(i);
                list.set(i,left);
                list.set(2*(i+1)-1,t);
                i=2*(i+1)-1;
                ;
            } else {
                int t =list.get(i);
                list.set(i,right);
                list.set(2*(i+1),t);
                i=2*(i+1);
                ;
            }

        }
        return ans;
    }


    public static void main(String[] args) {
        MyPriorityQueue_MAXHEAP maxheap= new MyPriorityQueue_MAXHEAP();
        maxheap.insert(50);
        maxheap.insert(30);
        maxheap.insert(20);
        maxheap.insert(15);
        maxheap.insert(10);
        maxheap.insert(8);
        maxheap.insert(16);
        System.out.println(maxheap.list);
        maxheap.poll();
        System.out.println(maxheap.list);

    }
}
