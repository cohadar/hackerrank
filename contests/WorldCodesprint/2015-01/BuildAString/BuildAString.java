import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class BuildAString {

	static final int INF = Integer.MAX_VALUE / 2;

	final int n;
	final int a;
	final int b;
	final String s;
	final char[] S;
	final int[] C;
	final SegmentTree T;
	
	BuildAString(int n, int a, int b, String s) {
		this.n = n;
		this.a = a;
		this.b = b;
		this.s = s;
		this.S = s.toCharArray();
		this.C = new int[1 + n];
		this.T = new SegmentTree(C);
	}

	boolean exists(int p, int k) {
		if (k == n) {
			return false;
		}
		int k2 = k % 2;
		int l = k - p + 1;
		String tl = s.substring(0, l);
		String tr = s.substring(l, k + 1);
		return tl.contains(tr);
	}

	public int upperBound(int left, int right, int k) {
		right--;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			boolean midValue = exists(mid, k);
			if (midValue == false) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	int minInRange_linear(int l, int r) {
		int min = INF;
		for (int i = l; i <= r; i++) {
			min = Math.min(min, C[i]);
		}
		return min;
	}

	int minInRange(int l, int r) {
		if (l > r) { return INF; };
		return T.RMinQ(l, r);
	}	

	int solve() {
		for (int k = 1; k <= n; k++) {
			C[k] = a + C[k - 1];
			int rp = (k + 1) / 2;
			int ff = upperBound(1, rp + 1, k);
			int min = Math.min(C[k] - b, minInRange(k - (ff - 1), k - 1));
			C[k] = Math.min(C[k], b + min);
			T.update(k, k, C[k]);
		}
		return C[n];
	}

	static BuildAString load(Scanner scanner) {
		String[] _nab = scanner.nextLine().split(" ");
		int n = Integer.valueOf(_nab[0]);
		int a = Integer.valueOf(_nab[1]);
		int b = Integer.valueOf(_nab[2]);
		assert 1 <= n && n <= 3e4 : "out of range, n: " + n;
		assert 1 <= a && a <= 1e4 : "out of range, a: " + a;
		assert 1 <= b && b <= 1e4 : "out of range, b: " + b;
		String s = scanner.nextLine();
		assert n == s.length();
		return new BuildAString(n, a, b, s);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 3 : "out of range, t: " + t;
		while (t-->0) {
			BuildAString o = BuildAString.load(scanner);
			System.out.println(o.solve());
		}
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

/**
 * The <tt>SegmentTree</tt> class is an structure for efficient search of cummulative data.
 * It performs  Range Minimum Query and Range Sum Query in O(log(n)) time.
 * It can be easily customizable to support Range Max Query, Range Multiplication Query etc.
 * <p/>
 * Also it has been develop with  <tt>LazyPropagation</tt> for range updates, which means
 * when you perform update operations over a range, the update process affects the least nodes as possible
 * so that the bigger the range you want to update the less time it consumes to update it. Eventually those changes will be propagated
 * to the children and the whole array will be up to date.
 * <p/>
 * <p/>
 * <p/>
 * Example:
 * <p/>
 * SegmentTreeHeap st = new SegmentTreeHeap(new Integer[]{1,3,4,2,1, -2, 4});
 * st.update(0,3, 1)
 * In the above case only the node that represents the range [0,3] will be updated (and not their children) so in this case
 * the update task will be less than n*log(n)
 *
 * Memory usage:  O(n)
 *
 * @author Ricardo Pacheco 
 * <p/>
 */
class SegmentTree {

    private Node[] heap;
    private int[] array;
    private int size;

    /**
     * Time-Complexity:  O(n*log(n))
     *
     * @param array the Initialization array
     */
    public SegmentTree(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        //The max size of this array is about 2 * 2 ^ log2(n) + 1
        size = (int) (2 * Math.pow(2.0, Math.floor((Math.log((double) array.length) / Math.log(2.0)) + 1)));
        heap = new Node[size];
        build(1, 0, array.length);
    }


    public int size() {
        return array.length;
    }

    //Initialize the Nodes of the Segment tree
    private void build(int v, int from, int size) {
        heap[v] = new Node();
        heap[v].from = from;
        heap[v].to = from + size - 1;

        if (size == 1) {
            heap[v].sum = array[from];
            heap[v].min = array[from];
        } else {
            //Build childs
            build(2 * v, from, size / 2);
            build(2 * v + 1, from + size / 2, size - size / 2);

            heap[v].sum = heap[2 * v].sum + heap[2 * v + 1].sum;
            //min = min of the children
            heap[v].min = Math.min(heap[2 * v].min, heap[2 * v + 1].min);
        }
    }

    /**
     * Range Sum Query
     *
     * Time-Complexity: O(log(n))
     */
    public int RSQ(int from, int to) {
        return RSQ(1, from, to);
    }

    private int RSQ(int v, int from, int to) {
        Node n = heap[v];

        //If you did a range update that contained this node, you can infer the Sum without going down the tree
        if (n.pendingVal != null && contains(n.from, n.to, from, to)) {
            return (to - from + 1) * n.pendingVal;
        }

        if (contains(from, to, n.from, n.to)) {
            return heap[v].sum;
        }

        if (intersects(from, to, n.from, n.to)) {
            propagate(v);
            int leftSum = RSQ(2 * v, from, to);
            int rightSum = RSQ(2 * v + 1, from, to);

            return leftSum + rightSum;
        }

        return 0;
    }

    /**
     * Range Min Query
     * 
     * Time-Complexity: O(log(n))
     */
    public int RMinQ(int from, int to) {
        return RMinQ(1, from, to);
    }

    private int RMinQ(int v, int from, int to) {
        Node n = heap[v];


        //If you did a range update that contained this node, you can infer the Min value without going down the tree
        if (n.pendingVal != null && contains(n.from, n.to, from, to)) {
            return n.pendingVal;
        }

        if (contains(from, to, n.from, n.to)) {
            return heap[v].min;
        }

        if (intersects(from, to, n.from, n.to)) {
            propagate(v);
            int leftMin = RMinQ(2 * v, from, to);
            int rightMin = RMinQ(2 * v + 1, from, to);

            return Math.min(leftMin, rightMin);
        }

        return Integer.MAX_VALUE;
    }


    /**
     * Range Update Operation.
     * With this operation you can update either one position or a range of positions with a given number.
     * The update operations will update the less it can to update the whole range (Lazy Propagation).
     * The values will be propagated lazily from top to bottom of the segment tree.
     * This behavior is really useful for updates on portions of the array
     * <p/>
     * Time-Complexity: O(log(n))
     *
     * @param from
     * @param to
     * @param value
     */
    public void update(int from, int to, int value) {
        update(1, from, to, value);
    }

    private void update(int v, int from, int to, int value) {

        //The Node of the heap tree represents a range of the array with bounds: [n.from, n.to]
        Node n = heap[v];

        /**
         * If the updating-range contains the portion of the current Node  We lazily update it.
         * This means We do NOT update each position of the vector, but update only some temporal
         * values into the Node; such values into the Node will be propagated down to its children only when they need to.
         */
        if (contains(from, to, n.from, n.to)) {
            change(n, value);
        }

        if (n.size() == 1) return;

        if (intersects(from, to, n.from, n.to)) {
            /**
             * Before keeping going down to the tree We need to propagate the
             * the values that have been temporally/lazily saved into this Node to its children
             * So that when We visit them the values  are properly updated
             */
            propagate(v);

            update(2 * v, from, to, value);
            update(2 * v + 1, from, to, value);

            n.sum = heap[2 * v].sum + heap[2 * v + 1].sum;
            n.min = Math.min(heap[2 * v].min, heap[2 * v + 1].min);
        }
    }

    //Propagate temporal values to children
    private void propagate(int v) {
        Node n = heap[v];

        if (n.pendingVal != null) {
            change(heap[2 * v], n.pendingVal);
            change(heap[2 * v + 1], n.pendingVal);
            n.pendingVal = null; //unset the pending propagation value
        }
    }

    //Save the temporal values that will be propagated lazily
    private void change(Node n, int value) {
        n.pendingVal = value;
        n.sum = n.size() * value;
        n.min = value;
        array[n.from] = value;

    }

    //Test if the range1 contains range2
    private boolean contains(int from1, int to1, int from2, int to2) {
        return from2 >= from1 && to2 <= to1;
    }

    //check inclusive intersection, test if range1[from1, to1] intersects range2[from2, to2]
    private boolean intersects(int from1, int to1, int from2, int to2) {
        return from1 <= from2 && to1 >= from2   //  (.[..)..] or (.[...]..)
                || from1 >= from2 && from1 <= to2; // [.(..]..) or [..(..)..
    }

    //The Node class represents a partition range of the array.
    static class Node {
        int sum;
        int min;
        //Here We store the value that will be propagated lazily
        Integer pendingVal = null;
        int from;
        int to;

        int size() {
            return to - from + 1;
        }

    }

}



