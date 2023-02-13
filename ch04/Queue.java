package ch04;// int형 고정 길이 큐

public class Queue {
    private Point[] data;            // 큐용 배열
    private int capacity;         // 큐의 크기
    private int front;            // 맨 처음 요소 커서
    private int rear;             // 맨 끝 요소 커서
    private boolean mode;

    //--- 실행시 예외: 큐가 비어있음 ---//
    public class EmptyQueueException extends RuntimeException {
        public EmptyQueueException() { }
    }

    //--- 실행시 예외: 큐가 가득 찼음 ---//
    public class OverflowQueueException extends RuntimeException {
        public OverflowQueueException() { }
    }

    //--- 생성자(constructor) ---//
    public Queue(int maxlen) {
        front = 0;
        rear = 0;
        capacity = maxlen;
        mode = false;
        try {
            data = new Point[capacity];          // 큐 본체용 배열을 생성
        } catch (OutOfMemoryError e) {        // 생성할 수 없음
            capacity = 0;
        }
    }

    
    //--- 큐에 데이터를 인큐 ---//
    public Point push(Point p) throws OverflowQueueException {
        if ((rear + 1) % capacity == front)  // 큐가 가득 찼음
            throw new OverflowQueueException();

        rear = (rear + 1) % capacity;
        data[rear] = p;
        return p;
    }

    //--- 큐에서 데이터를 디큐 ---//
    public Point pop() throws EmptyQueueException {
        if (rear - front <= 0)
            throw new EmptyQueueException();            // 큐가 비어있음

        front = (front + 1) % capacity;
        Point p = data[front];

        return p;
    }

    //--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
    public Point peek() throws EmptyQueueException {
        if (rear - front <= 0)
            throw new EmptyQueueException();            // 큐가 비어있음
        return data[front];
    }

    //--- 큐를 비움 ---//
    public void clear() {
        front = rear = 0;
    }

    //--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
    public int indexOf(Point x) {
        for (int i = 0; i < rear - front; i++) {
            int idx = (i + front) % capacity;
            if (data[idx].equals(x))                // 검색 성공
                return idx;
        }
        return -1;                            // 검색 실패
    }

    //--- 큐의 크기를 반환 ---//
    public int getCapacity() {
        return capacity;
    }

    //--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
    public int size() {
        if (rear == front)
            return 0;
        else if (rear >= front)
            return rear - front;
        else
            return front - rear;
    }

    //--- 큐가 비어있는가? ---//
    public boolean isEmpty() {
        return rear - front <= 0;
    }

    //--- 큐가 가득 찼는가? ---//
    public boolean isFull() {
        return rear - front >= capacity;
    }

    //--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
    public void dump() {
        if (rear - front <= 0)
            System.out.println("큐가 비어있습니다.");
        else {
            for (int i = 0; i < rear - front; i++)
                System.out.print(data[(i + front) % capacity] + " ");
            System.out.println();
        }
    }
}