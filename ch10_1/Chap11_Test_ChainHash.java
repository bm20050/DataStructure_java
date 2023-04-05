package ch10_1;

import java.util.Comparator;
//hash node가 student 객체일 때를 구현하는 과제
//체인법에 의한 해시
import java.util.Scanner;

class SimpleObject {

    String sno; // 회원번호
    String sname; // 이름

    public SimpleObject(String sno, String sname) {
        this.sno = sno;
        this.sname = sname;
    }
    public int keyCode() {
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
    public static final Comparator<SimpleObject2> NO_ORDER = new NoOrderComparator();

    private static class NoOrderComparator implements Comparator<SimpleObject2> {
        public int compare(SimpleObject2 d1, SimpleObject2 d2) {
            return (d1.sno.compareTo(d2.sno) > 0) ? 1 : ((d1.sno.compareTo(d2.sno) < 0)) ? -1 : 0;
        }
    }

    // --- 이름으로 순서를 매기는 comparator ---//
    public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

    private static class NameOrderComparator implements Comparator<SimpleObject2> {
        public int compare(SimpleObject2 d1, SimpleObject2 d2) {
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
            this.data = new SimpleObject(s);
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
        int hash = 11;
        hash = 31 * hash * (int) key;
        hash = hash * hash;
        return hash;


    }

    //--- 키값이 key인 요소를 검색(데이터를 반환) ---//
    public String search(int key) {
        int hash = hashValue(key);            // 검색할 데이터의 해시값
        Node2 p = table[hash];            // 선택 노드

        while (p != null) {
            if (p.getKey() == key)
                return p.getValue();                // 검색 성공
            p = p.next;                             // 다음 노드를 선택
        }
        return null;                                // 검색 실패
    }

    //--- 키값이 key인 데이터를 data의 요소로 추가 ---//
    public int add(SimpleObject st) {
        int hash = hashValue(st.keyCode()) % 9;            // 추가할 데이터의 해시값
        Node2 p = table[hash];            // 선택 노드
//구현 필요함
        while (p != null) {
            if (p.getKey() == Integer.parseInt(st.sno))
                return 1;
            p = p.next;
        }
        Node2 temp = new Node2(st, table[hash]);
        table[hash] = temp;
        return 0;
    }

    //--- 키값이 key인 요소를 삭제 ---//
    public int remove(int key) {
        int hash = hashValue(key);            // 삭제할 데이터의 해시값
        Node2 p = table[hash];            // 선택 노드
        Node2 pp = null;                  // 바로 앞의 선택 노드
        //구현실습


        return 1;                             // 찾는 키값이 없음
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
            System.out.println(1);
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
                        System.out.print("sno =  " + sno);
                        input[ix] = new SimpleObject(sno, sname);
                        hash.add(input[ix]);

                        System.out.print(" " + input[ix]);
                    }
                    break;
                case 2:
                    // Delete
                    break;
                case 3:
                    System.out.println("Search Value:: ");
                    int val = stdIn.nextInt();
                    String v = hash.search(val);
                    if (v != null)
                        System.out.println(v);
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
