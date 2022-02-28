/**
 * @author admin
 */
public class PrintNum {
    //输入: n = 1
    //输出: [1,2,3,4,5,6,7,8,9]
    public int[] printNumbers(int n) {
        int border = 1;
        for (int i = 0; i < n; i++) {
            border *= 10;
        }
        int[] ints = new int[border - 1];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i + 1;
        }
        return ints;
    }
}
