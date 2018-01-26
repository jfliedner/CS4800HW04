// Jillian Fliedner
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class WindowB {
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
    
    findMedian(window, numbers);
  }
  
  public static void findMedian(int window, ArrayList<Integer> numbers) {
    int start = 0;
    boolean finished = false;
    ArrayList<Integer> windowNums = new ArrayList<Integer>();
    
    while (!finished) {
      for (int i = start; i < start + window; i++) {
        windowNums.add(numbers.get(i));
      }
      
      start++;
      
      int kth = windowNums.size() / 2;
      int median = select(windowNums, kth);
      System.out.println(median);
      windowNums.clear();
      if ((start + window) > numbers.size()) {
        finished = true;
      }
  }
  }
  
  public static int select(ArrayList<Integer> windowNums, int k) {
    int splitterIdx = new Random().nextInt(windowNums.size());
    int splitter = windowNums.get(splitterIdx);
    ArrayList<Integer> sless = new ArrayList<Integer>();
    ArrayList<Integer> smore = new ArrayList<Integer>();

    for (int j = 0; j < windowNums.size(); j++) {
      if (j == splitterIdx) {
        continue;
      }
      else if (windowNums.get(j) < splitter) {
        sless.add(windowNums.get(j));
      }
      else {
        smore.add(windowNums.get(j));
      }
    }
    
    if (sless.size() == k) {
      return splitter;
    }
    else if (sless.size() > k) {
      return select(sless, k);
    }
    else {
      return select(smore, k - (1 + sless.size()));
    }
  }
}
