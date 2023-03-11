package EightQueens.ch10_1;

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject {

    String sno; // 회원번호
    String sname; // 이름

    public SimpleObject(String sno, String sname) {
        this.sno = sno;
        this.sname = sname;
    }
    public Integer keyCode() {
        return Integer.parseInt(sno);
    }
    public String keyValue() {
        return sname;
    }
    // --- 문자열 표현을 반환 ---//
    public String toString() {
        return "(" + sno + ") " + sname;
    }

    // --- 회원번호로 순서를 매기는 comparator ---//
    public static final Comparator<SimpleObject> NO_ORDER = new NoOrderComparator();

    private static class NoOrderComparator implements Comparator<SimpleObject> {
        public int compare(SimpleObject d1, SimpleObject d2) {
            return (d1.sno.compareTo(d2.sno) > 0) ? 1 : ((d1.sno.compareTo(d2.sno) < 0)) ? -1 : NAME_ORDER.compare(d1, d2);
        }
    }

    // --- 이름으로 순서를 매기는 comparator ---//
    public static final Comparator<SimpleObject> NAME_ORDER = new NameOrderComparator();

    private static class NameOrderComparator implements Comparator<SimpleObject> {
        public int compare(SimpleObject d1, SimpleObject d2) {
            return (d1.sname.compareTo(d2.sname) > 0) ? 1 : ((d1.sname.compareTo(d2.sname) < 0)) ? -1 : 0;
        }
    }
}

class ChainHash2 {
    //--- 해시를 구성하는 노드 ---//
    class Node2 {
        private SimpleObject data;                 // 키값
        private Node2 next;        // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

        //--- 생성자(constructor) ---//
        public Node2(SimpleObject s) {
            this.data = s;
            this.next = null;
        }

        Node2(SimpleObject s, Node2 p) {
            this.data = s;
            this.next = p;

        }

        Node2() {
            this.data = null;
            this.next = null;
        }

        SimpleObject getData() {
            return data;
        }
        //--- 키값을 반환 ---//
        Integer getKey() {
            return data.keyCode();
        }
        String getValue() {
            return data.keyValue();
        }

        //--- 키의 해시값을 반환 ---//
        public int hashCode() {
            int hash = 11;
            hash = 31 * hash * getKey();
            hash = hash * hash;
            return hash;
        }
    }

    private int size;              // 해시 테이블의 크기
    private Node2[] table;        // 해시 테이블

    //--- 생성자(constructor) ---//
    public ChainHash2(int capacity) {
        try {
            table = new Node2[capacity];
            this.size = capacity;
        } catch (OutOfMemoryError e) {        // 테이블을 생성할 수 없음
            this.size = 0;
        }
    }

    //--- 해시값을 구함 ---//
    public int hashValue(Object key) {
        int hash = 1;
        hash = 31 * hash * (int) key;
        hash = hash * hash;
        return hash;
    }

    //--- 키값이 key인 요소를 검색(데이터를 반환) ---//
    public boolean search(SimpleObject s, Comparator<? super SimpleObject> c) {
        int hash = hashValue(s.keyCode()) % 11;            // 검색할 데이터의 해시값
        Node2 p = table[hash];            // 선택 노드

        while (p != null) {
            if (c.compare(p.getData(), s) == 0)
                return true;                // 검색 성공
            p = p.next;                             // 다음 노드를 선택
        }
        return false;                                // 검색 실패
    }

    //--- 키값이 key인 데이터를 data의 요소로 추가 ---//
    public boolean add(SimpleObject s, Comparator<? super SimpleObject> c) {
        int hash = hashValue(s.keyCode()) % 11;            // 추가할 데이터의 해시값
        Node2 p = table[hash];            // 선택 노드
//구현 필요함
        while (p != null) {
            if (c.compare(p.getData(), s) == 0)
                return false;
            p = p.next;
        }
        Node2 temp = new Node2(s, table[hash]);
        table[hash] = temp;
        return true;
    }

    //--- 키값이 key인 요소를 삭제 ---//
    public boolean remove(SimpleObject s, Comparator<? super SimpleObject> c) {
        int hash = hashValue(s.keyCode()) % 11;            // 삭제할 데이터의 해시값
        Node2 p = table[hash];            // 선택 노드
        Node2 pp = null;                  // 바로 앞의 선택 노드
        //구현실습
        while (p != null) {
            if (c.compare(p.getData(), s) == 0) {
                if (pp == null)
                    table[hash] = p.next;
                else
                    pp.next = p.next;
                return true;
            }
            pp = p;
            p = p.next;
        }

        return false;                             // 찾는 키값이 없음
    }

    //--- 해시 테이블을 덤프(dump) ---//
    public void dump() {
        for (int i = 0; i < size; i++) {
            Node2 p = table[i];
            System.out.printf("%02d  ", i);
            while (p != null) {
                System.out.printf("→ %s ", p.getKey());
                p = p.next;
            }
            System.out.println();
        }
    }
}


public class Chap11_Test_ChainHash {
    static Scanner stdIn = new Scanner(System.in);

    public static void main(String[] args) {
        ChainHash2 hash = new ChainHash2(11);
        SimpleObject data;
        int select = 0;
        final int count = 3;
        while (select != 6) {
            System.out.println(
                    "SimpleChainHash. Select 1:Add, 2. Delete, 3:Search, 4. PrintDump, 5. Quit =>");

            select = stdIn.nextInt();
            switch (select) {
                case 1:
                    SimpleObject[] input = new SimpleObject[count];
                    String sno = null;
                    String sname = null;
                    for (int ix = 0; ix < count; ix++) {
                        System.out.println("입력 데이터(sno, sname):: ");

                        System.out.print("번호: ");
                        sno = stdIn.next();

                        System.out.print("이름: ");
                        sname = stdIn.next();

                        System.out.println("sno =  " + sno);
                        input[ix] = new SimpleObject(sno, sname);
                        if (!hash.add(input[ix], SimpleObject.NO_ORDER))
                            System.out.println("입력한 데이터가 이미 존재합니다.");
                        else
                            System.out.println("입력한 데이터 " + input[ix] + " 가 입력되었습니다.");
                    }
                    break;
                case 2:
                    System.out.println("삭제할 데이터(sno, sname):: ");
                    System.out.print("번호: ");
                    sno = stdIn.next();
                    System.out.print("이름: ");
                    sname = stdIn.next();

                    if (hash.remove(new SimpleObject(sno, sname), SimpleObject.NO_ORDER))
                        System.out.println("삭제되었습니다.");
                    else
                        System.out.println("삭제할 데이터가 없습니다.");
                    // Delete
                    break;
                case 3:
                    System.out.println("Search Value:: ");
                    System.out.print("번호: ");
                    sno = stdIn.next();
                    System.out.print("이름: ");
                    sname = stdIn.next();
                    SimpleObject s = new SimpleObject(sno, sname);
                    if (hash.search(s, SimpleObject.NO_ORDER))
                        System.out.println("검색한 데이터 " + s + " 가 존재합니다. ");
                    else
                        System.out.println("데이터가 없습니다.");
                    break;
                case 4:
                    hash.dump();
                    break;
                case 5:
                    System.out.println("Quit");
                    break;

                default:
                    System.out.println("WRONG INPUT  ");
                    System.out.println("Re-Enter");
                    break;
            }
        }
    }
}
