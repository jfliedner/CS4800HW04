// Jillian Fliedner

// custom resizing array list
public class CustomResize<T> {
  public static void main(String[] args) {
    int insert = new Integer(args[0]);
    CustomResize<Object> custom = new CustomResize<Object>(insert);
    custom.printAverages();
  }

  // fields for keeping track of timing
  long insertNoResize = 0;
  int addedA = 0;
  long insertWResize = 0;
  int addedB = 0;
  long deleteNoResize = 0;
  int addedC = 0;
  long deleteWResize = 0;
  int addedD = 0;
  int insert = 0; // number of elements to be inserted and deleted
  T[] initialArray; // the array
  int trueSize; // the actual size and the capacity
  int capacity;
  CustomResize(int insert) {
    this.insert = insert;
    initialArray = (T[]) new Object[10];
    trueSize = 0;
    capacity = 10;
    int inserted = 0;
    int removed = 0; // while there's still elements to add, add them
    while (inserted != insert) {
      this.add(3);
      inserted++;
    } // while there's still elements to delete, delete them
    while (removed != insert) {
      this.deleteLast();
      removed++;
    }
  }

  // to add the passed in Object
  public void add(Object toAdd) {
    if (trueSize == capacity) { // capacity is too small, double it
      long startTime = System.nanoTime(); // start time for this operation
      T[] copy = (T[]) new Object[capacity * 2];
      capacity = capacity * 2;
      for (int i = 0; i < trueSize; i++) {
        copy[i] = initialArray[i];
      }

      copy[trueSize] = (T) toAdd;
      trueSize++;
      initialArray = copy;
      long endTime = System.nanoTime(); // end time for this operation
      insertWResize = insertWResize + (endTime - startTime);
      addedB++;
    }
    else { 
      long startTime = System.nanoTime(); // start
      initialArray[trueSize] = (T) toAdd;
      trueSize++;
      long endTime = System.nanoTime(); // end
      insertNoResize = insertNoResize + (endTime - startTime);
      addedA++;
    }

  }

  // delete the last element
  public void deleteLast() {
    if (trueSize <= (int) Math.ceil(capacity / 4.0)) { // capacity too big for the true size, reduce by half
      long startTime = System.nanoTime(); // start
      T[] copy = (T[]) new Object[capacity / 2];
      for (int i = 0; i < trueSize - 1 && i < copy.length; i++) {
        copy[i] = initialArray[i];
      }
      trueSize--;
      capacity = capacity / 2;
      initialArray = copy;
      long endTime = System.nanoTime(); // end
      deleteWResize = deleteWResize + (endTime - startTime);
      addedD++;
    }
    else {
      long startTime = System.nanoTime(); // start
      initialArray[trueSize - 1] = null;
      trueSize--;
      long endTime = System.nanoTime(); // end
      deleteNoResize = deleteNoResize + (endTime - startTime);
      addedC++;
    }
  }

  // prints out the average times
  public void printAverages() {

    long totalTimeAdd = insertNoResize + insertWResize;
    long totalAddAvg = totalTimeAdd / insert;
    System.out.println("Total avg to add: " + totalAddAvg); // the overall average for all operations
    long totalTimeDel = deleteNoResize + deleteWResize;
    long totalDelAvg = totalTimeDel / insert;
    System.out.println("Total avg to delete: " + totalDelAvg);
    
    long insertNoSumAvg = insertNoResize / addedA; // average for inserting with no resizing
    System.out.println("Average time of insert when not resizing: " + insertNoSumAvg);


    long insertWSumAvg = insertWResize / addedB; // average for inserting with resizing
    System.out.println("Average time of insert when resizing: " + insertWSumAvg);


    long deleteNoSumAvg = deleteNoResize / addedC; // average for deleting when not resizing
    System.out.println("Average time of delete when not resizing: " + deleteNoSumAvg);

    long deleteWSumAvg = deleteWResize / addedD; // average for deleting with resizing
    System.out.println("Average time of delete when resizing: " + deleteWSumAvg);


  }
}
