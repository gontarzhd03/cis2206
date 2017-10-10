package quicksort;

/**
 *
 * @author hgontarz
 */
public class QuickSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] x = { 11, 231, 9, 20, 6, 10, 14, 8, 60 };
        int first = 0;
        int last = x.length - 1;

        quickSort(x, first, last);

        for(int i = 0; i <= last; i++) {
           System.out.println(x[i]);
        }
    }
    
   /** Method that uses recursion after splitting the array.
    * @param anArray array of integers, first integer, last integer
    * @exception exceptions No exceptions thrown
   */
    static void quickSort(int[] anArray, int first, int last) {
        int splitPoint = 0;
                
        if(first < last) {
            int splitVal = anArray[first];
            int xRight = first + 1;
            int xLeft = last;
            while(xRight <= xLeft) {
                String onCorrectSide = "Yes";
                while(onCorrectSide == "Yes") {
                    if(anArray[xRight] <= splitVal) {
                        xRight = xRight + 1;
                        if(xRight > xLeft) {
                            onCorrectSide = "No";
                        }
                    }
                    else {
                        onCorrectSide = "No";
                    }
                }
                if(xRight <= xLeft) {
                    onCorrectSide = "Yes";
                }
                while(onCorrectSide == "Yes") {
                    if(anArray[xLeft] > splitVal) {
                        xLeft = xLeft - 1;
                        if(xRight > xLeft) {
                            onCorrectSide = "No";
                        }
                    }
                    else {
                        onCorrectSide = "No";
                    }
                }
                if(xRight < xLeft) {
                   swap(anArray, xRight, xLeft);
                   xRight = xRight + 1;
                   xLeft = xLeft - 1;
                }
            }
            splitPoint = xLeft;
            swap(anArray, first, splitPoint);
            quickSort(anArray, first, splitPoint - 1);
            quickSort(anArray, splitPoint + 1, last);
        }
    }
   /** Method that swaps elements in the array.
    * @param anArray array of integers, here integer, there integer
    * @exception exceptions No exceptions thrown
   */
    static void swap(int[] anArray, int here, int there) {
        int temp = anArray[here];
        anArray[here] = anArray[there];
        anArray[there] = temp;
    }
}
