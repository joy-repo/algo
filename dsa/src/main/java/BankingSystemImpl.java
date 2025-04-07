import java.util.*;

import junit.framework.TestCase;

class BankingSystemImpl {


    public static void main(String[] args) {

        BankingSystemImpl system = new BankingSystemImpl();
        //TestCase Assertions = new TestCase();
        system.createAccount(1, "account1");
        system.createAccount(2, "account2");
        system.createAccount(3, "account3");
     system.deposit(4, "account1", 1000);
         system.deposit(5, "account2", 1000);
        system.deposit(6, "account3", 1000);
        system.pay(7, "account2", 100);
         system.pay(8, "account2", 100);
         system.pay(9, "account3", 100);
        List<String> expected = new ArrayList<>(List.of("account2(1200)", "account3(1100)", "account1(1000)"));
        system.topActivity(10, 3);
    }

//
//    public static void main(String[] args) {
//        BankingSystemImpl b = new BankingSystemImpl();
//        b.createAccount(1, "A");
//        b.createAccount(2, "B");
//        b.createAccount(3, "C");
//        b.createAccount(4, "D");
//        b.createAccount(5, "E");
//
//        System.out.println(b.deposit(1, "A", 100));
//        System.out.println(b.deposit(2, "B", 200));
//        System.out.println(b.deposit(3, "C", 300));
//        System.out.println(b.deposit(4, "D", 400));
//        System.out.println(b.deposit(5, "E", 500));
//        b.deposit(6, "E", 1500);
//
//        System.out.println(b.pay(6, "A", 50));
//        System.out.println(b.pay(7, "B", 150));
//        System.out.println(b.pay(8, "C", 250));
//        System.out.println(b.pay(9, "D", 350));
//        System.out.println(b.pay(10, "E", 450));
//
//        System.out.println(b.topActivity(11, 3));
//    }

    private HashMap<String, Integer> acountBalance ;
    private HashMap<String, Integer> transactionMap;

    public BankingSystemImpl() {
        acountBalance = new HashMap<>();
        transactionMap = new HashMap<>();
    }

    public boolean createAccount(int timestamp, String accountId) {
        if(!acountBalance.containsKey(accountId)){
            acountBalance.put(accountId, 0);
            transactionMap.put(accountId, 0);
            return true;
        }
        return false;
    }


    public Optional<Integer> deposit(int timestamp, String accountId, int amount) {

        if(acountBalance.containsKey(accountId)){
      //      transactionMap.putIfAbsent(accountId,0);
            transactionMap.put(accountId, transactionMap.get(accountId)+1);
            System.out.print("deposit :" + transactionMap);
            acountBalance.put(accountId, acountBalance.get(accountId)+amount);
            return Optional.of(acountBalance.get(accountId));
        }

        return Optional.empty();
    }

    public  Optional<Integer> pay(int timestamp, String accountId, int amount) {


        if(!acountBalance.containsKey(accountId))
            return Optional.empty();

        if(acountBalance.get(accountId) < amount){
            return Optional.empty();
        }
    //    transactionMap.putIfAbsent(accountId,0);
        transactionMap.put(accountId, transactionMap.get(accountId)+1);
        System.out.print("pay :" + transactionMap);


        acountBalance.put(accountId, acountBalance.get(accountId)-amount);

        return Optional.of(acountBalance.get(accountId));

    }

    public List<String> topActivity(int timestamp, int n) {
        List<Map.Entry<String,Integer>> trnList = new ArrayList<>(transactionMap.entrySet());

        //System.out.print("topActivity :" + transactionMap);

        trnList.sort(Map.Entry.comparingByValue());
        Collections.reverse(trnList);

        int end = n<trnList.size() ? n : trnList.size();
        List<String> res = new ArrayList<>();
        for(Map.Entry<String,Integer> entr : trnList){
            res.add(entr.getKey() + "(" + acountBalance.get(entr.getKey()) +")");
        }
        System.out.println();

        System.out.println("topActivity :" + res);
        return res.subList(0, end);
    }

}

