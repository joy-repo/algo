package com.divide_conquer;


//https://www.techiedelight.com/find-floor-ceil-number-sorted-array/
public class FloorCiel {

    static int[] ARR = {1, 4, 6, 8, 9};

    public static void main(String[] args) {
        sol_Ciel(3);
        sol_Floor(3);
    }

    private static void sol_Floor(int n) {

        int strt = 0;
        int end = ARR.length - 1;

        int floor = 0;

        while (end >= strt) {
            int mid = (strt + end) / 2;
            if (ARR[mid] == n) {
                System.out.println(n);
                return;
            }

            if (ARR[mid] < n) {
                strt = mid + 1;
            }
            if (ARR[mid] > n) {
                end = mid - 1;
                floor = ARR[mid];
            }
        }
        System.out.println(floor);

    }


    private static void sol_Ciel(int n) {

        int strt = 0;
        int end = ARR.length - 1;

        int ceil = 0;

        while (end >= strt) {
            int mid = (strt + end) / 2;
            if (ARR[mid] == n) {
                System.out.println(n);
                return;
            }

            if (ARR[mid] < n) {
                strt = mid + 1;
                ceil = ARR[mid];
            }
            if (ARR[mid] > n) {
                end = mid - 1;
            }
        }
        System.out.println(ceil);

    }
}
