package fhnw.mada.other;

public final class Prime {

  public static boolean[] siebDesEratostenes(int ceiling) {
    boolean[] nums = new boolean[ceiling];

    int i = 2;
    int sqrt = (int) Math.ceil(Math.sqrt(ceiling));
    while (i < sqrt) {
      if (!nums[i]) {
        int j = i * i;
        while (j < ceiling) {
          nums[j] = true;
          j += i;
        }
      }
      i++;
    }

    return nums;
  }
}
