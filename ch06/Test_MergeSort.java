package ch06;
// PhyscData 클래스를 Comparable로 수정해야 한다.

import java.util.Arrays;

public class Test_MergeSort {

    //MergeSort() 구현 소스 코드를 사용하여 객체들의 정렬 소스코드로 수정한다.

    // --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void merge(PhyscExamSort.PhyscData[] a, int lefta, int righta, int leftb, int rightb) {
        PhyscExamSort.PhyscData[] temp = new PhyscExamSort.PhyscData[righta - lefta + 1 + rightb - leftb + 1];
        int idx = 0;
        int start = lefta;
        while (lefta <= righta && leftb <= rightb) {
            int n = a[lefta].compareTo(a[leftb]);
            if (n < 0)
                temp[idx++] = a[lefta++];
            else if (n > 0)
                temp[idx++] = a[leftb++];
            else {
                temp[idx++] = a[lefta++];
                leftb++;
            }
        }
        while (lefta <= righta)
            temp[idx++] = a[lefta++];
        while (leftb <= rightb)
            temp[idx++] = a[leftb++];

        for (int i = 0; i < idx; i++)
            a[start++] = temp[i];
    }

    // --- 퀵 정렬(비재귀 버전)---//
    static void MergeSort(PhyscExamSort.PhyscData[] a, int left, int right) {
        int mid = (left + right) / 2;
        if (left == right) return;
        MergeSort(a, left, mid);
        MergeSort(a, mid + 1, right);
        merge(a, left, mid, mid + 1, right);
        return;
    }

    public static void main(String[] args) {
        PhyscExamSort.PhyscData[] x = {
                new PhyscExamSort.PhyscData("장경오", 171, 1.2),
                new PhyscExamSort.PhyscData("황지안", 169, 0.8),
                new PhyscExamSort.PhyscData("강민하", 162, 0.3),
                new PhyscExamSort.PhyscData("김찬우", 173, 0.7),
                new PhyscExamSort.PhyscData("이수연", 168, 0.4),
                new PhyscExamSort.PhyscData("유서범", 171, 1.5),
                new PhyscExamSort.PhyscData("박준서", 171, 2.0),
        };
        int nx = x.length;

        MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬
        System.out.println("오름차순으로 정렬했습니다.");
        System.out.println("■ 신체검사 리스트 ■");
        System.out.println(" 이름     키  시력");
        System.out.println("------------------");
        for (int i = 0; i < x.length; i++)
            System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);

    }

}
