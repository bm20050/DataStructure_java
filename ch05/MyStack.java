package ch05;

class MyStack {
    private int top;
    Point[] data;

    public MyStack() {
        top = 0;
        data = new Point[1000];
    }

    public boolean isEmpty() {
        if (top == 0)
            return true;
        else
            return false;
    }

    public void push(Point p) {
        data[top++] = p;
    }

    public Point pop() {
        if (top <= 0) {
            System.out.println("-1");
        }
        return data[--top];
    }

}
