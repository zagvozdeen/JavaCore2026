package lab2;

public class SnakeMatrixFill {
    public static void main(String[] args) {
        int rows = 4;
        int cols = 5;
        int[][] matrix = new int[rows][cols];

        int value = 1;

        for (int row = 0; row < rows; row++) {
            if (row % 2 == 0) {
                for (int col = 0; col < cols; col++) {
                    matrix[row][col] = value++;
                }
            } else {
                for (int col = cols - 1; col >= 0; col--) {
                    matrix[row][col] = value++;
                }
            }
        }

        System.out.println("Матрица, заполненная змейкой:");
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.printf("%4d", element);
            }
            System.out.println();
        }
    }
}
