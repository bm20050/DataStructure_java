package t;

import java.util.ArrayList;
import java.util.Scanner;

class Element {
    private int key;

    public int getKey () {
        return key;
    }
}

interface MaxPQ {
    public void insert(Element o);
    public Element deleteMax(Element o);
}

class MaxHeap implements MaxPQ{
    private Element[] heap;
    private int n;
    private int maxSize;
    public MaxHeap(int sz) {
        maxSize = sz;
    }

    public void heapEmpty() {
        System.out.println("Heap Empty");
    }

    public void heapFull() {
        System.out.println("Heap Full");
    }

    public void display() {
        System.out.print("MaxHeap:: (i, heap[i].key):");
        for (int i = 1; i <= n; i++) {
            System.out.println("(" + i + ", " + heap[i].getKey() + ")");
        }
    }

    public void insert(Element x) {
        if (n == maxSize) {
            heapFull();
            return;
        }
        n++;
        int i;
        for (i = n; i != 1 ; i /= 2) {
            if (x.getKey() <= heap[i / 2].getKey())
                break;
            heap[i] = heap[i / 2];
        }
        heap[i] = x;
    }

    public Element deleteMax(Element x) {
        int i, j;
        if (n == 0) {
            heapEmpty();
            return null;
        }
        x = heap[1];
        Element k = heap[n--];
        for (i = 1, j = 2; j <= n; i = j, j *= 2) {
            if (j < n && heap[j].getKey() < heap[j + 1].getKey())
                j++;
            if (k.getKey() >= heap[j].getKey())
                break;
            heap[i] = heap[j];
        }
        heap[i] = k;
        return x;
    }
}

class Sets {
    private int[] parent;
    private int n;

    public Sets(int sz) {
        n = sz;
        parent = new int[sz + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = -1;
        }
    }

    public void simpleUnion(int i, int j) {
        parent[j] = i;
    }

    public int simpleFind(int i) {
        while(parent[i] > 0) {
            i = parent[i];
        }
        return i;
    }

    public void weightedUnion(int i, int j) {
        int temp = parent[i] + parent[j];
        if (parent[i] > parent[j]) {
            parent[i] = j;
            parent[j] = temp;
        }
        else {
            parent[j] = i;
            parent[i] = temp;
        }
    }

    public int collapsingFind(int i) {
        int r;
        for (r = i; parent[r] > 0; r = parent[r]);

        while (i != r) {
            int s = parent[i];
            parent[i] = r;
            i = s;
        }

        return r;
    }

    public void display() {
        System.out.print("display: index= ");
        for (int i = 1; i <= n; i++)
            System.out.print(i + " ");
        System.out.println();

        System.out.print("dispaly: index= ");
        for (int i = 1; i <= n; i++)
            System.out.print(parent[i] + " ");
        System.out.println();
    }
}

class ListNode {
    private int data;
    ListNode link;

    public ListNode(int data) {
        this.data = data;
        link = null;
    }
    public int getData() {
        return data;
    }
}

class List {
    ListNode first;

    public void insert(int k) {
        ListNode newnode = new ListNode(k);
        newnode.link = first;
        first = newnode;
    }

    public void delete(int k) {
        ListNode previous = null;
        ListNode current;
        for (current = first; current.getData() != k && current != null;
                    previous = current, current = current.link);
        if (current != null) {
            if (previous != null)
                previous.link = current.link;
            else
                first = first.link;
        }
    }
}

class ListIterator {
    private List list;
    ListNode current;

    public ListIterator(List l) {
        current = l.first;
        ListNode current;
    }

    public int first() {
        if (current != null)
            return current.getData();
        else
            return 0;
    }

    public int next() {
        current = current.link;
        return current.getData();
    }

    public boolean notNull() {
        if (current != null)
            return true;
        else
            return false;
    }

    public boolean nextNotNull() {
        if (current.link != null)
            return true;
        else
            return false;
    }
}



class QueueNode {
    int data;
    QueueNode link;

    public QueueNode() {
        data = 0;
        link = null;
    }
    public QueueNode(int def, QueueNode l) {
        data = def;
        link = l;
    }

}

class Queue {
    private QueueNode front;
    private QueueNode rear;

    public Queue() {
        front = rear = null;
    }

    public void insert(int y) {
        if (front == null)
            front = rear = new QueueNode(y, null);
        else {
            rear.link = new QueueNode();
            rear = rear.link;
        }
    }

    public int delete(int retvalue) {
        if (front == 0) {
            queueEmpty();
            return 0;
        }
        QueueNode x = front;
        retvalue = front.data;
        front = x.link;
        if (front == null)
            rear = null;
        // delete x;
        return retvalue;
    }

    public boolean isEmpty() {
        if (front == null)
            return true;
        else
            return false;
    }
}

class Graph {
    List headNodes;
    private int n;
    boolean[] visited;

    public Graph(int vertices) {
        headNodes = new List();
    }
    public void _DFS(int v) {

    }
}

public class MinimalSpanningTree {
    public static void main(String[] args) {
        int select = 0, n, start, end, weight;
        int startBFSNode = 100;

        System.out.println("Input the total node number: ");
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        Graph graph(n);
        Graph[] SpanningTree = null;
        while (select != 0) {
            System.out.print("Select command 1: Add edges and Weight, 2: Display Adjacency Lists, 3: spanningTree, 4: Quit => ");
            select = sc.nextInt();

        }
    }
}
