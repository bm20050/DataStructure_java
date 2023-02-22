package ch06;


class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}
}
class MyStack3 {
	private Point[] data;
	private int top;
	public MyStack3(int x) {
		top = 0;
		data = new Point[x];
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
	public boolean isEmpty() {
		if (top == 0)
			return true;
		else
			return false;
	}


}
public class Test_QuickSort {


//퀵 정렬(비재귀 버전)

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void quickSort(int[] a, int left, int right) {
		MyStack3 s = new MyStack3(10);
		Point p = new Point(left, right);
		s.push(p);

		while (!s.isEmpty()) {
			p = s.pop();
			int l = left = p.getX();
			int r = right = p.getY();
			int pivot = a[(l + r) / 2];
			do {
				while (a[l] < pivot) {
					l++;
				}
				while (a[r] > pivot) {
					r--;
				}
				if (l <= r){
					swap(a, l, r);
					l++;
					r--;
				}

			} while (l <= r);

			if (left < r)
				s.push(new Point(left, r));

			if (l < right)
				s.push(new Point(l, right));
		}
	}

	public static void main(String[] args) {
		int nx = 10;
//		int[] x = {5,7,1,4,6,2,3,9,8,0};
		int[] x = new int[10];
		for (int ix = 0; ix < 10; ix++) {
			double d = Math.random();
			x[ix] = (int) (d * 20);
		}
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
		System.out.println();

		quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("오름차순으로 정렬했습니다.");

		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
	}
}
