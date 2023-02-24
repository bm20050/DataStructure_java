package ch06MinHeap;

import java.util.Scanner;

class Element {
    public int key;

    public int getElement() {
        return key;
    }

    public Element(int key) {
        this.key = key;
    }
}

interface MinPQ {
    public void Insert(Element x);

    public Element DeleteMin(Element x);
}

class MinHeap implements MinPQ {
    final int heapSize = 1000;

    public MinHeap(int sz) {
        MaxSize = sz;
        n = 0;
        heap = new Element[MaxSize + 1]; // Don't want to use heap[0]
    }

    public void display() {
        int i;
        for (i = 1; i <= n; i++)
            System.out.print("  (" + i + ", " + heap[i].key + ") ");
        System.out.println();
    }

    public void Insert(Element x) {
        int i;
        if (n == MaxSize) {
            HeapFull();
            return;
        }
        n++;
        for (i = n; i >= 1;  i /= 2) {
            if (i == 1)
                break; // at root
            if (x.key >= heap[i / 2].key)
                break;// 자바에서 generic array 사용 안됨
            // move from parent to i
            heap[i] = heap[i / 2];
        }
        heap[i] = x;

    }

    public Element DeleteMin(Element x) {

        int i, j;
        if (n == 0) {
            HeapEmpty();
            Element elm = new Element(0);
            return elm;
        }
        x = heap[1];
        Element k = heap[n];
        n--;

        for (i = 1, j = 2; j <= n; i = j, j *= 2) {
            if (j < n)
                if (heap[j].key > heap[j + 1].key)
                    j++;
            // j points to the larger child
            if (k.key <= heap[j].key)
                break;
            heap[i] = heap[j];
        }
        heap[i] = k;
        return x;
    }

    private Element[] heap;
    private int n; // current size of MaxHeap
    private int MaxSize; // Maximum allowable size of MaxHeap

    private void HeapEmpty() {
        System.out.println("Heap Empty");
    }

    private void HeapFull() {
        System.out.println("Heap Full");
    }
}

public class MinHeapSort {

    public static void main(String[] args) {
        int select = 0;
        Scanner stdIn = new Scanner(System.in);
        MinHeap heap = new MinHeap(20);
        Element ele = null;
        final int count = 5;
        int[] x = new int[count];


        Element deletedEle = null;

        do {
            System.out.println("Min Tree. Select: 1 insert, 2 display, 3 delete,  4 sort, 5 exit => ");
            select = stdIn.nextInt();
            switch (select) {
                case 1:
                    for (int i = 0; i < count; i++) {
                        x[i] = (int) (Math.random() * 30);
                        heap.Insert(new Element(x[i]));
                    }
                    break;
                case 2:
                    heap.display();
                    break;
                case 3:
                    deletedEle = heap.DeleteMin(ele);
                    if (deletedEle != null) {
                        System.out.println("deleted element: " + deletedEle.key);
                    }
                    System.out.println("current min heap: ");
                    heap.display();
                    break;
                case 4:
                    for (int j = 0; j < count; j++) {
                        deletedEle = heap.DeleteMin(ele);
                        x[j] = deletedEle.getElement();
                    }
                    for (int num : x)
                        System.out.println(" " + num);
                case 5:
                    return;

            }
        } while (select < 5);

        return;
    }
}
