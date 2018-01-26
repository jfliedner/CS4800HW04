// Jillian Fliedner
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class WindowC {
  public static void main(String[] args) {
    Scanner myScanner = new Scanner(System.in);

    int window = 0;
    String win = myScanner.nextLine();
    String inputWindow = win.substring(7);
    window = Integer.parseInt(inputWindow);

    ArrayList<Integer> numbers = new ArrayList<Integer>();
    while (myScanner.hasNextLine()) {
      String intToAdd = myScanner.nextLine();
      numbers.add(Integer.parseInt(intToAdd));
    }
    int[] inputNumbers = new int[numbers.size()];
    for (int i = 0; i < numbers.size(); i++) {
      inputNumbers[i] = numbers.get(i);
    }

    int start = 0;
    boolean finished = false;
    while (!finished) {
      int[] aCopy = new int[window];
      int idx = 0;
      for (int i = start; i < start + window; i++) {
        aCopy[idx] = inputNumbers[i];
        idx++;
      }
      idx = 0;
       int median = select(aCopy, 0, window - 1, window / 2);
       System.out.println(median);
       start++;
       if ((start + window) > numbers.size()) {
         finished = true;
       }
    }

  }

  public static int selectHelp(int[] a, int min, int max, int pivotIdx) {
    int split = a[pivotIdx];
    swap(a, pivotIdx, max);
    int idx = min;
    for (int i = min; i < max; i++) {
      if (a[i] < split) {
        swap(a, idx, i);
        idx++;
      }
    }

    swap(a, max, idx);
    return idx;
  }

  public static int select(int[] a, int min, int max, int k) {
    if (min == max) {
      return a[min];
    }

    int pivotIdx = new Random().nextInt(max - min) + min;
    pivotIdx = selectHelp(a, min, max, pivotIdx);
    if (k == pivotIdx) {
      return a[k];
    }
    else if (k < pivotIdx) {
      return select(a, min, pivotIdx - 1, k);
    }
    else {
      return select(a, pivotIdx + 1, max, k);
    }
  }

  public static void swap(int[] a, int i1, int i2) {
    int temp = a[i1];
    a[i1] = a[i2];
    a[i2] = temp;
  }
}


