package ch05;

public class EightQueens_1 {


    public static int nextMove(int[][] move, int row) {
        for (int i = 0; i < move[row].length; i++) {
            if (checkMove(row, i, move)) {
                move[row][i] = 1;
                return i;
            }
        }
        return -1;
    }

    public static boolean checkMove(int x, int y, int[][] array) {
        if (!checkRow(x, array)) return false;
        if (!checkCol(y, array)) return false;
        if (!checkDiagSW(x, y, array)) return false; //x++, y-- or x--, y++ where 0<= x,y <= 7
        if (!checkDiagSE(x, y, array)) return false; //x++, y++ or x--, y--

        return true;
    }

    public static boolean checkRow(int x, int[][]array) {
        for (int i = 0; i < array.length; i++) {
            if (array[x][i] == 1)
                return false;
        }
        return true;
    }

    public static boolean checkCol(int y, int[][]array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][y] == 1)
                return false;
        }
        return true;
    }

    public static boolean checkDiagSW(int x, int y, int[][]array) {
        int dx = x;
        int dy = y;
        while (dx >= 0 && dx < array.length && dy >= 0 && dy < array.length) {
            if (array[dx][dy] == 1)
                return false;
            dx++;
            dy--;
        }
        dx = x;
        dy = y;
        while (dx >= 0 && dx < array.length && dy >= 0 && dy < array.length) {
            if (array[dx][dy] == 1)
                return false;
            dx--;
            dy++;
        }
        return true;
    }

    public static boolean checkDiagSE(int x, int y, int[][]array) {
        int dx = x;
        int dy = y;
        while (dx >= 0 && dx < array.length && dy >= 0 && dy < array.length) {
            if (array[dx][dy] == 1)
                return false;
            dx++;
            dy++;
        }
        dx = x;
        dy = y;
        while (dx >= 0 && dx < array.length && dy >= 0 && dy < array.length) {
            if (array[dx][dy] == 1)
                return false;
            dx--;
            dy--;
        }
        return true;
    }

    public static void solveQueen(int row, int col, int[][] array) {
        Point p = new Point(0, 0);
        MyStack s = new MyStack();
        int x = p.getX();
        int y = p.getY();


        while (x < row) {
            while (y < col) {
                int t = nextMove(array, x);
                if (t != -1) {
                    s.push(new Point(x, t));
                    y = 0;
                    break;
                } else {
                    p = s.pop();
                    x = p.getX();
                    y = p.getY();
                    array[x][y] = 0;
                    y++;
                }
            }
            x++;
            if (y >= col) {
                if (!s.isEmpty()) {
                    p = s.pop();
                    x = p.getX();
                    y = p.getY();
                    array[x][y] = 0;
                    y++;
                }
            }
        }
    }


    static int num = 0;

    public static void print(int row, int col, int[][] array) {
        System.out.println(++num);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
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
        print(row, col, array);
    }
}
