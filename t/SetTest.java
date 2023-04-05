package t;

public class SetTest {
    static class Sets {
        private int heapSize;
        private int n;
        private int[] parent;
        public Sets(int heapSize) {
            n = heapSize;
            parent = new int[heapSize + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = -1;
            }
        }

        public void simpleUnion(int i, int j) {
            parent[j] = i;
        }

        public int simpleFind(int i) {
            while (parent[i] > 0) {
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
            for (int i = 1; i <= n; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            System.out.print("display: value= ");
            for (int i = 1; i <= n; i++) {
                System.out.print(parent[i] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Sets s = new Sets(8);
        s.display();

        System.out.println("find 5: " + s.collapsingFind(5));
        s.weightedUnion(1, 2);
        s.weightedUnion(3,4);
        s.weightedUnion(5, 6);
        s.weightedUnion(7, 8);
        s.display();

        System.out.println("find 5: " + s.collapsingFind(5));
        System.out.println("find 6: " + s.collapsingFind(6));
        s.weightedUnion(1, 3);
        s.weightedUnion(5, 7);
        s.display();

        System.out.println("find 5: " + s.collapsingFind(5));
        System.out.println("find 6: " + s.collapsingFind(6));
        System.out.println("find 7: " + s.collapsingFind(7));
        System.out.println("find 8: " + s.collapsingFind(8));
        s.weightedUnion(1, 5);
        s.display();

        System.out.println("find 1: " + s.collapsingFind(1));
        System.out.println("find 2: " + s.collapsingFind(2));
        System.out.println("find 3: " + s.collapsingFind(3));
        System.out.println("find 4: " + s.collapsingFind(4));
        System.out.println("find 5: " + s.collapsingFind(5));
        System.out.println("find 6: " + s.collapsingFind(6));
        System.out.println("find 7: " + s.collapsingFind(7));
        System.out.println("find 8: " + s.collapsingFind(8));
        s.display();
    }
}
