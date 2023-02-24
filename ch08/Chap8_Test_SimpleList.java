package ch08;

//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Scanner;

class Node {
    int data;
    Node link;

    public Node(int element) {
        data = element;
        link = null;
    }
}

class LinkedList {
    Node first;
    Node p;
    public LinkedList() {
        first = null;
        p = null;
    }

    public void Delete(int element) //delete the element
    {
        for (Node i = first; i != null; i = i.link) {
            if (element == i.link.data){
                i.link = i.link.link;
                break;
            }
        }
    }

    public void Show() {
        for (Node i = first; i != null; i = i.link) {
            System.out.print(i.data + " ");
        }
        System.out.println();
    }

    public void Add(int element) //add a new element
    {
        Node newNode = new Node(element);

        if (first == null) //insert into empty list
        {
            first = newNode;
            p = first;
        }
        //기존 노드들이 있으면 순서가 유지하도록 삽입할 노드 위치를 찾는다
        else {
            p.link = newNode;
            p = p.link;
        }


    }
}

public class Chap8_Test_SimpleList {

    public static void main(String[] args) {
        System.out.println("Linked List");
        LinkedList l = new LinkedList();

        int nx = 10;
        int data = 0;
        for (int i = 0; i < nx; i++) {
            double d = Math.random();
            data = (int) (d * 50);
            System.out.print(" " + data);
            l.Add(data);
        }
        System.out.println();
        System.out.println("inserted");
        l.Show();
        System.out.print("delete : ");
        Scanner stdIn = new Scanner(System.in);
        int n = stdIn.nextInt();
        l.Delete(n);
        l.Show();
        System.out.println("insert : ");
        n = stdIn.nextInt();
        l.Add(n);
        l.Show();
    }
}
