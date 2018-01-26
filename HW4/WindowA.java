// Jillian Fliedner
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class WindowA {
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

    findWindowMedian(window, numbers);

  }

  public static void findWindowMedian(int window, ArrayList<Integer> numbers) {
    int start = 0;
    boolean finished = false;
    ArrayList<Integer>  windowNums = new ArrayList<Integer>();
    while (!finished) {
      for (int i = start; i < start + window; i++) {
        windowNums.add(numbers.get(i));
      }

      start++;
      Collections.sort(windowNums);
      if (windowNums.size() % 2 == 0) {
        int median = Math.max(windowNums.get(windowNums.size() / 2),
            windowNums.get((windowNums.size() / 2) - 1));
        System.out.println(median);
        windowNums.clear();
        if ((start + window) > numbers.size()) {
          finished = true;
        }
      }
      else {
        int median = windowNums.get(windowNums.size() / 2);
        System.out.println(median);
        windowNums.clear();
        if ((start + window) > numbers.size()) {
          finished = true;
        }
      }
    }

  }
}
