package implementations;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char toBeReplaced;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.toBeReplaced = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {
        fillMatrix(this.startRow, this.startCol);
    }

    private void fillMatrix(int row, int col) {
        if (isOutOfBounds(row, col) || this.matrix[row][col] != this.toBeReplaced) {
            return;
        }

        this.matrix[row][col] = this.fillChar;

        this.fillMatrix(row + 1, col);
        this.fillMatrix(row, col + 1);
        this.fillMatrix(row - 1, col);
        this.fillMatrix(row, col - 1);
    }

    private boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= this.matrix.length || col < 0 || col >= this.matrix[row].length;
    }

    public String toOutputString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= this.matrix.length - 1; i++) {
            for (int j = 0; j <= this.matrix[i].length - 1; j++) {
                sb.append(this.matrix[i][j]);
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}