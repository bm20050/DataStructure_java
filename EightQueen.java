package EightQueens;
class Point {
    private int x;
    private int y;

    public Point (int _x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
class MyStack {
    private int top;
    private Point[] data;

    public MyStack() {
        top = 0;
        data = new Point[100];
    }
    public void push(Point p) {
        data[top++] = p;
    }
    public Point pop() {
        return data[--top];
    }
}
public class EightQueen {


    public static boolean checkMove(int x, int y, int[][] array) {
        if(!(checkRow(x, array))) return false;
        if(!(checkCol(y, array))) return false;
        if(!(checkDiagSW(x, y, array))) return false;
        if(!(checkDiagSE(x, y, array))) return false;

        return true;
     }

    private static boolean checkDiagSE(int x, int y, int[][] array) {
        int dx = x;
        int dy = y;
        while (dx >= 0 && dx < array[y].length && dy >= 0 && dy < array[x].length) {
            if(array[dx][dy] == 1)
                return false;
            dx++;
            dy--;
        }
        dx = x;
        dy = y;
        while (dx >= 0 && dx < array[y].length && dy >= 0 && dy < array[x].length) {
            if(array[dx][dy] == 1)
                return false;
            dx--;
            dy++;
        }
        return true;

    }


    private static boolean checkDiagSW(int x, int y, int[][] array) {
        int dx = x;
        int dy = y;
        while (dx >= 0 && dx < array[y].length && dy >= 0 && dy < array[x].length) {
            if(array[dx][dy] == 1)
                return false;
            dx++;
            dy++;
        }
        dx = x;
        dy = y;
        while (dx >= 0 && dx < array[y].length && dy >= 0 && dy < array[x].length) {
            if(array[dx][dy] == 1)
                return false;
            dx--;
            dy--;
        }
        return true;
    }

    private static boolean checkCol(int y, int[][] array) {
        for (int i = 0; i < array[y].length; i++) {
            if (array[i][y] == 1)
                return false;
        }
        return true;
    }

    private static boolean checkRow(int x, int[][] array) {
        for (int i = 0; i < array[x].length; i++) {
            if (array[x][i] == 1)
                return false;
        }
        return true;
    }

    public static void solveQueen (int row, int col, int[][] array) {
        MyStack s = new MyStack();
        Point p = new Point(0, 0);
        int x = p.getX();
        int y = p.getY();

        while (x < row) {
            while (y < col) {
                if (checkMove(x, y, array)) {
                    array[x][y] = 1;
                    s.push(new Point(x, y));
                    y = 0;
                    break;
                }
                y++;
            }
            x++;
        }




    }
    public static void main(String[] args) {
        int row = 4;
        int col = 4;
        int[][] array = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                array[i][j] = 0;
            }
        }
        solveQueen(row, col, array);
    }
}
