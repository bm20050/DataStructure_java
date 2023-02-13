package ch04;// int형 고정 길이 큐의 사용 예

import java.util.Scanner;

class QueueTester {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Queue s = new Queue(2);    // 최대 64개를 인큐할 수 있는 큐

        while (true) {
        	System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
            System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity() - 1);
            System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");

            int menu = stdIn.nextInt();
            if (menu == 0) break;

            int x, y;
            Point p = null;
            switch (menu) {
             case 1:                                // 인큐
                System.out.print("데이터: ");
                x = stdIn.nextInt();
                y = stdIn.nextInt();
                try {
                    s.push(new Point(x, y));
                 } catch (Queue.OverflowQueueException e) {
                    System.out.println("큐가 가득 찼습니다.");
                }
                break;

             case 2:                                // 디큐
                try {
                    p = s.pop();
                    System.out.println("디큐한 데이터는 " + p + "입니다.");
                 } catch (Queue.EmptyQueueException e) {
                    System.out.println("큐가 비어 있습니다.");
                }
                break;

             case 3:                                // 피크
                try {
                     p = s.peek();
                    System.out.println("피크한 데이터는 " + p + "입니다.");
                 } catch (Queue.EmptyQueueException e) {
                    System.out.println("큐가 비어 있습니다.");
                }
                break;

             case 4:                                // 덤프
                s.dump();
                break;
            }
        }
    }
}