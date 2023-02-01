package ch02;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

//10장 Collection, Test01, Test02를 사용

//string 정렬, binary search 구현
//1단계: string, 2단계: string 객체,  Person 객체들의 list\
//file1: 서울,북경,상해,서울,도쿄, 뉴욕,부산
//file2: 런던, 로마,방콕, 도쿄,서울,부산
// file > string split() > 배열 > ArrayList > sort > iterator 사용하여 merge > 중복 제거 > string 배열 > file에 저장

public class StringArray {

    static int binSearch(String[] s, int n, String key) { // 자료구조 책 페이지 115 코드 사용한다.
        int first = 0;
        int last = n - 1;

        do {
            int middle = (first + last) / 2;
            if (s[middle].compareTo(key) == 0)
                return middle;
            else if (s[middle].compareTo(key) < 0)
                first = middle + 1;
            else
                last = middle - 1;
        } while (first <= last);

        return -1;
    }

    public static void main(String[] args) {
        try {
            Path input1 = Paths.get("file1.txt");
            Path input2 = Paths.get("file2.txt");
            byte[] bytes1 = Files.readAllBytes(input1);
            byte[] bytes2 = Files.readAllBytes(input2);
            String s1 = new String(bytes1);
            String s2 = new String(bytes2);
            String[] sarray1 = new String[20];
            String[] sarray2 = new String[20];
            sarray1 = s1.split(",|\n");
            sarray2 = s2.split(",|\n");

            // file1에서 read하여 list1.add()한다.
            // 배열을 list로 만드는 방법
            // 방법1:
//			ArrayList<String> arrayList = new ArrayList<>();
//			for (String temp : sarray1) {
//				arrayList.add(temp);
//			}
//			for (String temp : sarray2) {
//				arrayList.add(temp);
//			}
            // 방법2
            ArrayList<String> list1 = new ArrayList<>(Arrays.asList(sarray1));

            ArrayList<String> list2 = new ArrayList<String>();
            // file2에서 read하여 list1.add()한다.
            for (String temp : sarray2) {
                list2.add(temp);
            }
            //
            System.out.println("collection.sort()::");
            Collections.sort(list1);
            System.out.println("list1::");
            for (String city : list1)
                System.out.print(city + " ");
            System.out.println();
            System.out.println("list2::");
            Collections.sort(list2);
            for (String city : list2)
                System.out.print(city + " ");
            ArrayList<String> list3 = new ArrayList<String>();

            Iterator<String> iter1 = list1.iterator();
            Iterator<String> iter2 = list2.iterator();
            // iterator를 사용하여 merge한다
            String data1 = iter1.next();
            String data2 = iter2.next();

            // ArrayList를 merge하는 코드 구현 list3 = list1 + list2(merge)
            while (iter1.hasNext() && iter2.hasNext()) {
                if (data1.compareTo(data2) < 0) {
                    list3.add(data1);
                    data1 = iter1.next();
                } else if (data1.compareTo(data2) > 0) {
                    list3.add(data2);
                    data2 = iter2.next();
                }
                else {
                    list3.add(data1);
                    list3.add(data2);
                    data1 = iter1.next();
                    data2 = iter2.next();
                }
            }
            int a = 0;
            while (iter1.hasNext()) {
                list3.add(data1);
                data1 = iter1.next();
                a = 1;
            }

            while (iter2.hasNext()) {
                list3.add(data2);
                data2 = iter2.next();
                a = 2;
            }
            if (a == 1) list3.add(data1);
            else list3.add(data2);

            System.out.println();
            System.out.println("merge:: ");
            for (String city : list3)
                System.out.print(city + " ");
            // System.out.println();

            // 중복제거
            Iterator<String> iter3 = list3.iterator();
            String temp = iter3.next();
            while (iter3.hasNext()) {
                if (temp.compareTo(temp = iter3.next()) == 0)
                    iter3.remove();
            }
            // binary search 구현
            // 방법1:
            // Arrays.binarySearch(null, null, null);// ArrayList에는 binarySearch()가 없다.
            // 방법2:
            // int result = Collections.binarySearch(list3, newFruit, cc);

            // 방법3:

            System.out.println();
            System.out.println("중복제거:: ");
            for (String city : list3)
                System.out.print(city + " ");
            System.out.println();
            // ArrayList를 배열로 전환
            String[] st = list3.toArray(new String[list3.size()]);
            // binary search 구현
            int n = binSearch(st, st.length, "서울");
            System.out.println(n + "번째");
            // 정렬된 list3을 file에 출력하는 코드 완성
            int bufferSize = 10240;
            String b = " ";
            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
            for (String sx : list3) {
                System.out.println(" " + sx);
                buffer.put(sx.getBytes());
                buffer.put(b.getBytes());
            }
            buffer.flip();
            FileOutputStream file = new FileOutputStream("c.txt");
            FileChannel channel = file.getChannel();
            channel.write(buffer);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}