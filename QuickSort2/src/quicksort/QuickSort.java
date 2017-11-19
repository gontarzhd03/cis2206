package quicksort;

/**
 *
 * @author hgontarz
 */
public class QuickSort {
    public static int Level;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] x = { 11, 231, 9, 20, 6, 10, 14, 8, 60 };
        int first = 0;
        int last = x.length - 1;

        Level = 0;
        quickSort(0, x, first, last);
    }
    
   /** Method that uses recursion after splitting the array.
    * @param anArray array of integers, first integer, last integer
    * @exception exceptions No exceptions thrown
   */
    static void quickSort(int side, int[] anArray, int first, int last) {
        boolean Crossed;
        int splitPoint = 0;

        if(first < last) {
            switch(side) {
                case 0:
                    System.out.print("All; Entering Level: " + ++Level);
                    break;
                case 1:
                    System.out.print("Left; Entering Level: " + ++Level);
                    break;
                case 2:
                    System.out.print("Right; Entering Level: " + ++Level);
                    break;
            }
            System.out.print(" [");
            for(int i = first; i <= last; i++) {
               System.out.printf("%d ", anArray[i]);
            }
            System.out.print("] ");

            int splitVal = anArray[first];
            int moveRight = first + 1;
            int moveLeft = last;
            while(moveRight <= moveLeft) {                 //while not Crossed
                do {
                    if(anArray[moveRight] <= splitVal) {   //examine left side: if leftValue <= splitVal; skip leftValue
                        moveRight++;
                        if(moveRight > moveLeft) break;    //if Crossed break
                    }
                    else if(moveRight <= moveLeft) {       //if leftValue > splitVal && not Crossed; examine right side
                        do {
                            if(anArray[moveLeft] > splitVal) {   //if rightValue > splitVal; ignore it
                                moveLeft--;
                                if(moveRight > moveLeft) break;  //if Crossed break
                            }
                            else break;                          //if rightValue <= splitVal break
                        } while(true);
                        break;
                    }
                    else break;                            //leftValue > splitVal && Crossed
                } while(true);
                if(moveRight < moveLeft) {                 //if not Crossed; swap leftValue with rightValue; skip each
                   swap(0, anArray, moveRight, moveLeft);
                   moveRight++;
                   moveLeft--;
                }
            }
            splitPoint = moveLeft;
            swap(1, anArray, first, splitPoint);              //swap splitVal with 
            quickSort(1, anArray, first, splitPoint - 1);     //first half; do again
            quickSort(2, anArray, splitPoint + 1, last);      //second half; do again
            switch(side) {
                case 0:
                    System.out.print("All; Leaving Level: " + Level--);
                    break;
                case 1:
                    System.out.print("Left; Leaving Level: " + Level--);
                    break;
                case 2:
                    System.out.print("Right; Leaving Level: " + Level--);
                    break;
            }
            System.out.print(" [");
            for(int i = first; i <= last; i++) {
               System.out.printf("%d ", anArray[i]);
            }
            System.out.println("] ");
        }
        else {
//            System.out.println("");
            switch(side) {
                case 0:
                    System.out.println("All; Entering & Leaving Level: " + (Level+1));
                    break;
                case 1:
                    System.out.println("Left; Entering & Leaving Level: " + (Level+1));
                    break;
                case 2:
                    System.out.println("Right; Entering & Leaving Level: " + (Level+1));
                    break;
            }
        }
    }
    
   /** Method that swaps elements in the array.
    * @param anArray array of integers, here integer, there integer
    * @exception exceptions No exceptions thrown
   */
    static void swap(int mode, int[] anArray, int here, int there) {
        if(mode == 0) {
            System.out.print("Swap(" + anArray[here] + ", " + anArray[there] + ") ");
        }
        else if(mode == 1) {
            System.out.println("SwapSplit(" + anArray[here] + ", " + anArray[there] + ") ");
        }
        int temp = anArray[here];
        anArray[here] = anArray[there];
        anArray[there] = temp;
    }
}
