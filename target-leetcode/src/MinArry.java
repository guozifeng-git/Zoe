/**
 * @author admin
 */
public class MinArry {
    // 3,4,5,1,2
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length-1;
        while (left<right){
            int mid = (left+right)/2;
            if (numbers[mid] > numbers[right]){
                left = mid+1;
            }else if (numbers[mid] < numbers[right]){
                right = mid;
            }else {//3 1 2 2 2
                right --;
            }
        }
        return numbers[left];
    }
}
