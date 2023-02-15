package ch05;


import ch04.GenericStack;

public class EightQueens {
    static int row = 8;
    static int col = 8;
    static int[][] array = new int[row][col];
    static int[] result = new int[10];
    static MyStack s = new MyStack();

    public static void nextMove(int[] move, int row) {

    }

    public static boolean checkMove(int x, int y) {
        if (!checkRow(x)) return false;
        if (!checkCol(y)) return false;
        if (!checkDiagSW(x, y)) return false; //x++, y-- or x--, y++ where 0<= x,y <= 7
        if (!checkDiagSE(x, y)) return false; //x++, y++ or x--, y--

        return true;
    }

    public static boolean checkRow(int x) {
        for (int i = 0; i < row; i++) {
            if (array[x][i] == 1)
                return false;
        }
        return true;
    }

    public static boolean checkCol(int y) {
        for (int i = 0; i < col; i++) {
            if (array[i][y] == 1)
                return false;
        }
        return true;
    }

    public static boolean checkDiagSW(int x, int y) {
        int dx = x;
        int dy = y;
        while (dx >= 0 && dx < row && dy >= 0 && dy < col) {
            if (dx == x && dy == y) {
                dx++;
                dy--;
                continue;
            }
            if (array[dx][dy] == 1)
                return false;
            dx++;
            dy--;
        }
        dx = x;
        dy = y;
        while (dx >= 0 && dx < row && dy >= 0 && dy < col) {
            if (dx == x && dy == y) {
                dx--;
                dy++;
                continue;
            }
            if (array[dx][dy] == 1)
                return false;
            dx--;
            dy++;
        }
        return true;
    }

    public static boolean checkDiagSE(int x, int y) {
        int dx = x;
        int dy = y;
        while (dx >= 0 && dx < row && dy >= 0 && dy < col) {
            if (dx == x && dy == y) {
                dx++;
                dy++;
                continue;
            }
            if (array[dx][dy] == 1)
                return false;
            dx++;
            dy++;
        }
        dx = x;
        dy = y;
        while (dx >= 0 && dx < row && dy >= 0 && dy < col) {
            if (dx == x && dy == y) {
                dx--;
                dy--;
                continue;
            }
            if (array[dx][dy] == 1)
                return false;
            dx--;
            dy--;
        }
        return true;
    }

    public static void solveQueen(int[][] data) {
        Point p = new Point(0, 0);
        int x = p.getX();
        int y = p.getY();
        for(int i = 0; i < col; i++) {
            while (x < row) {
                while (y < col) {
                    if (checkMove(x, y)) {
                        array[x][y] = 1;
                        s.push(new Point(x, y));
                        y = 0;
                        break;
                    }
                    y++;
                }
                x++;
                if (y >= col) {
                    p = s.pop();
                    x = p.getX();
                    y = p.getY();
                    array[x][y] = 0;
                    y += 1;
                    continue;
                }
            }
            print();
            for (int j = 0; j < row; j++) {
                p = s.pop();
                x = p.getX();
                y = p.getY();
                array[x][y] = 0;
            }
            y++;
        }
    }
    public static void print() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        solveQueen(array);
    }
}
