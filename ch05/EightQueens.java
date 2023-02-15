package ch05;

public class EightQueens {
    static int row = 8;
    static int col = 8;
    static int[][] array = new int[row][col];
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

    public static void solveQueen() {
        Point p = new Point(0, 0);
        int x = p.getX();
        int y = p.getY();
        int flag = 0;
        while (!(x == 0  && y >= col)) {
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
                    if (s.getTop() != 0){
                        p = s.pop();
                        x = p.getX();
                        y = p.getY();
                        array[x][y] = 0;
                        y += 1;
                    }
                    else {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) break;
            }
            if (flag == 1) break;
            print();
            if (x < row - 1) {
                while (y < col - 1) {
                    p = s.pop();
                    x = p.getX();
                    y = p.getY();
                    array[x][y] = 0;
                }
            }
            else {

                p = s.pop();
                x = p.getX();
                y = p.getY();
                array[x][y] = 0;
            }

            y++;
        }
    }
    static int num = 0;
    public static void print() {
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
        solveQueen();
    }
}