package blink75.dp;

//https://www.youtube.com/watch?v=xlgZ10436ks&list=PL-Jc9J83PIiEZvXCn-c5UIBvfT8dA-8EG&index=79

public class StoneGame {

  static int[] STONES = { 5, 3, 1, 4, 2 };

  static int aliceScore = 0;
  static int bobScore = 0;

  public static void main(String[] args) {
    sol();
  }

  private static int sol() {

    boolean isAlice = true;
    int aliceScore = 0;
    int bobScore = 0;

    int start = 0;
    int last = STONES.length - 1;

    while (start < last) {

      int sum = sum(start, last);
      if (STONES[start] > STONES[last]) {
        if (isAlice)
          aliceScore += sum - STONES[last];
        else
          bobScore += sum - STONES[last];
        last--;
      } else {
        if (isAlice)
          aliceScore += sum - STONES[start];
        else
          bobScore += sum - STONES[start];
        start++;
      }
      isAlice = !isAlice;

    }

    System.out.println(aliceScore);
    System.out.println(bobScore);
    return aliceScore - bobScore;

  }

  private static int sum(int strt, int lst) {
    int sum = 0;
    for (int i = strt; i <= lst; i++) {
      sum += STONES[i];
    }
    return sum;
  }
}
