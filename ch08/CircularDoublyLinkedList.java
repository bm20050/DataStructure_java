//package ch08;
//
//import java.util.Scanner;
//
//class DoubleLinkNode {
//    private int data;
//    DoubleLinkNode llink;
//    DoubleLinkNode rlink;
//
//    public DoubleLinkNode(int data) {
//        this.data = data;
//        llink = null;
//        rlink = null;
//    }
//}
//
//class DoubleLinkedList {
//    DoubleLinkNode head;
//
//    public DoubleLinkedList() {
//        head = null;
//    }
//
//
//    public void Show() {
//    }
//
//    public void Add(int data) {
//        DoubleLinkNode newNode = new DoubleLinkNode(data);
//        if (head == null) {
//            head = newNode;
//            newNode.llink = newNode;
//            newNode.rlink = newNode;
//            return;
//        }
//
//
//
//
//    }
//
////    public boolean Delete(int num) {
////    }
////
////    public boolean Search(int n) {
////    }
//}
//
//public class CircularDoublyLinkedList {
//    enum Menu {
//        Add("삽입"),
//        Delete("삭제"),
//        Show("인쇄"),
//        Search("검색"),
//        Exit("종료");
//
//        private final String message;                // 표시할 문자열
//
//        static Chap8_Test_SimpleList.Menu MenuAt(int idx) {                // 순서가 idx번째인 열거를 반환
//            for (Chap8_Test_SimpleList.Menu m : Chap8_Test_SimpleList.Menu.values())
//                if (m.ordinal() == idx)
//                    return m;
//            return null;
//        }
//
//        Menu(String string) {                        // 생성자(constructor)
//            message = string;
//        }
//
//        String getMessage() {                        // 표시할 문자열을 반환
//            return message;
//        }
//    }
//
//    //--- 메뉴 선택 ---//
//    static Chap8_Test_SimpleList.Menu SelectMenu() {
//        Scanner sc = new Scanner(System.in);
//        int key;
//        do {
//            for (Chap8_Test_SimpleList.Menu m : Chap8_Test_SimpleList.Menu.values()) {
//                System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
//                if ((m.ordinal() % 3) == 2 &&
//                        m.ordinal() != Chap8_Test_SimpleList.Menu.Exit.ordinal())
//                    System.out.println();
//            }
//            System.out.print(" : ");
//            key = sc.nextInt();
//        } while (key < Chap8_Test_SimpleList.Menu.Add.ordinal() ||
//                key > Chap8_Test_SimpleList.Menu.Exit.ordinal());
//        return Chap8_Test_SimpleList.Menu.MenuAt(key);
//    }
//
//    public static void main(String[] args) {
//        Chap8_Test_SimpleList.Menu menu;                                // 메뉴
//        System.out.println("Linked List");
//        DoubleLinkedList l = new DoubleLinkedList();
//        Scanner sc = new Scanner(System.in);
//        int data = 0;
//        System.out.println("inserted");
//        l.Show();
//        do {
//            switch (menu = SelectMenu()) {
//                case Add:                           // 머리노드 삽입
//                    double d = Math.random();
//                    data = (int) (d * 50);
//                    l.Add(data);
//                    break;
//                case Delete:                          // 머리 노드 삭제
//                    int num = sc.nextInt();
//                    if (l.Delete(num))
//                        System.out.println("삭제된 데이터는 " + num);
//                    else
//                        System.out.println("데이터 없음");
//                    break;
//                case Show:                           // 꼬리 노드 삭제
//                    l.Show();
//                    break;
//                case Search:                           // 회원 번호 검색
//                    int n = sc.nextInt();
//                    if (l.Search(n))
//                        System.out.println("검색 값 = " + n + " 데이터가 존재합니다.");
//                    else
//                        System.out.println("검색 값 = " + n + " 데이터가 없습니다.");
//                    break;
//                case Exit:                           // 꼬리 노드 삭제
//                    break;
//            }
//        } while (menu != Chap8_Test_SimpleList.Menu.Exit);
//    }
//}
