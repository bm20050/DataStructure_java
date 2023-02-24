package ch05;

import java.util.Arrays;
import java.util.Stack;

class MyPoint {
    private int x;
    private int y;
    private Point direction;

    public MyPoint(int x, int y, Point direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getDirection() {
        return direction;
    }
}

public class Maze {
    static int maze[][] = new int[100][100];
    static int mark[][] = new int[100][100];

    public static void path(int m, int n) {
        mark[1][1] = 2;
        Stack<MyPoint> s = new Stack<>();

        Point N = new Point(-1, 0);
        Point NE = new Point(-1, 1);
        Point E = new Point(0, 1);
        Point SE = new Point(1, 1);
        Point S = new Point(1, 0);
        Point SW = new Point(1, -1);
        Point W = new Point(0, -1);
        Point NW = new Point(-1, -1);

        Point[] directions = {N, NE, E, SE, S, SW, W, NW};

        MyPoint p = new MyPoint(1, 1, E);

        s.push(p);

        while (!s.isEmpty()) {
            p = s.pop();
            int i = p.getX();
            int j = p.getY();
            Point d = p.getDirection();
            int idx = Arrays.asList(directions).indexOf(d);

            while (idx < directions.length) {
                int g = i + d.getX();
                int h = j + d.getY();
                if (g == m && h == n) {
                    mark[g][h] = 2;
                    System.out.println("the term near the exit: " + i + j);
                    System.out.println("exit: " + m + " " + n);
                    return;
                }
                if (maze[g][h] == 0 && mark[g][h] == 0) {
                    mark[g][h] = 2;
                    s.push(new MyPoint(i, j, directions[idx + 1]));
                    i = g;
                    j = h;
                    d = directions[0];
                    idx = 0;
                } else {
                    if (idx + 1 >= directions.length) {
                        mark[i][j] = 1;
                        break;
                    }

                    d = directions[++idx];
                }

            }

        }
        System.out.println("NO Path");
    }

    public static void main(String[] args) {
        int[][] input = {
                {0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
                {1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0},
                {1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1},
                {0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1},
                {0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
        };

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 17; j++) {
                if ((i == 0) || (j == 0) || (i == 13) || (j == 16))
                    maze[i][j] = 1;
                else
                    maze[i][j] = input[i - 1][j - 1];

               // mark[i][j] = 0;
            }
        }

        for (int i = 0; i <= 13; i++) {
            for (int j = 0; j <= 16; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        path(12, 15);

        System.out.println();
        // 지나온 경로 출력 되돌아 온 경로 1, 최종 경로 2
        for (int i = 0; i <= 13; i++) {
            for (int j = 0; j <= 16; j++) {
                System.out.print(mark[i][j] + " ");
            }
            System.out.println();
        }
    }
}
