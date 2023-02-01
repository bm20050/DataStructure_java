template <class T>
class Queue
{
public:
	Queue(int queueCapacity = 10);
	bool IsEmpty();
	T& Front();
	T& Rear();
	void Push(T const& x);
	void Pop();
private:
	T* queue;
	int front;
	int rear;
	int capacity;
};

template <class T>
Queue<T>::Queue(int queueCapacity) : capacity(queueCapacity)
{
	queue = new T[capacity];
	front = rear = -1;
}

template <class T>
inline bool  Queue<T>::IsEmpty() { return front == rear; }

template <class T>
void Queue<T>::Push(T const& x)
{
	if ((rear + 1) % capacity == front)
	{
	   //throws
	}

	rear = (rear + 1) % capacity;
	queue[rear] = x;
}

template <class T>
void Queue<T>::Pop()
{
	if (IsEmpty()) throw "Queue is empty. Cannot delelte.";
	front = (front + 1) % capacity;
	queue[front].~T();
}
