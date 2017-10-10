package quicksortwithfile;

/**
 *
 * @author hgontarz
 */
public class SwapArrayElementsClass {
   /** Method that swaps elements in the array.
    * @param anArray array of integers, here integer, there integer
    * @exception exceptions No exceptions thrown
   */
    static void swap(double[] anArray, int here, int there) {
        double temp = anArray[here];
        anArray[here] = anArray[there];
        anArray[there] = temp;
    }
}
