package ch08;

//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계
import java.util.Scanner;

class ObjectPhyscData implements Comparable<ObjectPhyscData> {
    String name;              // 이름
    int height;            // 키
    double vision;            // 시력

    //--- 생성자(constructor) ---//
    ObjectPhyscData(String name, int height, double vision) {
        this.name = name;
        this.height = height;
        this.vision = vision;
    }

    //--- 신체검사 데이터를 문자열로 반환 --//
    public String toString() {
        return "이름: " + name + ", " + "키: " +  height + ", " + "시력: " +  vision + "\n";
    }

    @Override
    public int compareTo(ObjectPhyscData o) {
        if (this.name.compareTo(o.name) < 0)
            return -1;
        else if (this.name.compareTo(o.name) > 0)
            return 1;
        else {
            if (this.height < o.height)
                return -1;
            else if (this.height > o.height)
                return 1;
            else {
                if (this.vision < o.vision)
                    return -1;
                else if (this.vision > o.vision)
                    return 1;
                else
                    return 0;
            }
        }
    }
}
class ObjectNode {
    ObjectPhyscData data;
    ObjectNode link;

    public ObjectNode(ObjectPhyscData pd) {
        data = pd;
        link = null;
    }
}

class ObjectLinkedList {
    ObjectNode first;

    public ObjectLinkedList() {
        first = null;
    }

    public boolean Delete(String name) //delete the element
    {
        ObjectNode p = first, q = null;
        if (p == null)
            return false;

        while (p != null) {
            if (p.data.name.compareTo(name) == 0) {
                if (q == null)
                    first = p.link;
                else
                    q.link = p.link;
                return true;
            } else {
                q = p;
                p = p.link;
                if (p == null && q.data.name.compareTo(name) == 0) {
                    q.link = null;
                    return true;
                }
            }
        }
        return false;
    }

    public void Show() { // 전체 리스트를 순서대로 출력한다.
        for (ObjectNode p = first; p != null; p = p.link) {
            System.out.print(p.data);
        }
        System.out.println();
    }

    public void Add(ObjectPhyscData pd) //임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
    {
        ObjectNode newNode = new ObjectNode(pd);
        ObjectNode p = first, q = null;
        if (p == null) {
            first = newNode;
            return;
        }

        while (p != null) {
            if (p.data.compareTo(pd) > 0) {
                newNode.link = p;
                if (q == null)
                    first = newNode;
                else
                    q.link = newNode;
                break;
            } else {
                q = p;
                p = p.link;
                if (p == null) {
                    q.link = newNode;
                    break;
                }
            }
        }
    }

    public boolean Search(String name) { // 전체 리스트를 순서대로 출력한다.
        for (ObjectNode p = first; p != null; p = p.link) {
            if (p.data.name.compareTo(name) == 0)
                return true;
        }
        return false;
    }
}

public class Chap8_Test_SimpleObjectList {
    enum Menu {
        Add("삽입"),
        Delete("삭제"),
        Show("인쇄"),
        Search("검색"),
        Exit("종료");

        private final String message;                // 표시할 문자열

        static Menu MenuAt(int idx) {                // 순서가 idx번째인 열거를 반환
            for (Menu m : Menu.values())
                if (m.ordinal() == idx)
                    return m;
            return null;
        }

        Menu(String string) {                        // 생성자(constructor)
            message = string;
        }

        String getMessage() {                        // 표시할 문자열을 반환
            return message;
        }
    }

    //--- 메뉴 선택 ---//
    static Menu SelectMenu() {
        Scanner sc = new Scanner(System.in);
        int key;
        do {
            for (Menu m : Menu.values()) {
                System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
                if ((m.ordinal() % 3) == 2 &&
                        m.ordinal() != Menu.Exit.ordinal())
                    System.out.println();
            }
            System.out.print(" : ");
            key = sc.nextInt();
        } while (key < Menu.Add.ordinal() ||
                key > Menu.Exit.ordinal());
        return Menu.MenuAt(key);
    }

    public static void main(String[] args) {
        Menu menu;                                // 메뉴
        System.out.println("Linked List");
        ObjectLinkedList l = new ObjectLinkedList();
        Scanner sc = new Scanner(System.in);
        int data = 0;
        System.out.println("inserted");
        l.Show();
        do {
            switch (menu = SelectMenu()) {
                case Add:
                    System.out.print("이름을 입력하세요. : ");
                    String name = sc.next();
                    System.out.print("키를 입력하세요. : ");
                    int height = sc.nextInt();
                    System.out.print("시력을 입력하세요. : ");
                    double vision =  sc.nextDouble();
                    l.Add(new ObjectPhyscData(name, height, vision));
                    break;
                case Delete:
                    System.out.println("삭제할 데이터의 이름을 입력하세요. ");
                    name = sc.next();
                    if (l.Delete(name))
                        System.out.println("삭제된 데이터는 " + name);
                    else
                        System.out.println("데이터 없음");
                    break;
                case Show:
                    l.Show();
                    break;
                case Search:
                    System.out.println("검색할 데이터의 이름을 입력하세요. ");
                    name = sc.next();
                    if (l.Search(name))
                        System.out.println("검색 값 = " + name + " 데이터가 존재합니다.");
                    else
                        System.out.println("검색 값 = " + name + " 데이터가 없습니다.");
                    break;
                case Exit:
                    break;
            }
        } while (menu != Menu.Exit);
    }
}
