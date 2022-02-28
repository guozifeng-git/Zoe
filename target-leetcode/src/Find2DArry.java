/**
 * @author admin
 */
public class Find2DArry {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null && matrix.length == 0 && matrix[0].length == 0){
            return false;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int row = 0;
        int column = columns - 1;
        while (row < rows && column >= 0) {
            int number = matrix[row][column];
            if (number == target) {
                return true;
            } else if (number > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }
}
