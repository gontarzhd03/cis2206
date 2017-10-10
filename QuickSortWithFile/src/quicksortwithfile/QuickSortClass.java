package quicksortwithfile;

/**
 *
 * @author hgontarz
 */
public class QuickSortClass {
/**
 * 
 * @param anArray
 * @param left
 * @param right 
 */
    static void quickSort(double[] anArray, int left, int right) {
        if(right - left <= 0) {
            return;
        }
        else {
            double pivot = anArray[right];
            int partition = partitionlt(anArray, left, right, pivot);
            quickSort(anArray, left, partition - 1);
            quickSort(anArray, partition + 1, right);
        }
    }
/**
 * 
 * @param anArray
 * @param left
 * @param right
 * @param pivot
 * @return 
 */
    static int partitionlt(double[] anArray, int left, int right, double pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while(true) {
            while(anArray[++leftPtr] < pivot) {
            }
            while(rightPtr> 0 && anArray[--rightPtr] > pivot) {
            }
            if(leftPtr >= rightPtr) {
                break;
            }
            else {
                SwapArrayElementsClass.swap(anArray, leftPtr, rightPtr);
            }
        }
        SwapArrayElementsClass.swap(anArray, leftPtr, right);
        return leftPtr;
    }
}
