import java.util.*;
import java.io.*;

public class CountLuck {

	static final char HERMIONE = 'M';
	static final char GRASS = '.';
	static final char TREE = 'X';
	static final char DEAD_END = '@';
	static final char EXIT = '*';
	static final char BREADCRUMB = '-';

	static enum Direction { 
		START, RIGHT, DOWN, LEFT, UP, END;
		private static Direction[] vals = values();
		public Direction next()
		{
			return vals[(this.ordinal() + 1)];
		}		
	}

	static class Point {
		final int x;
		final int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			return String.format("(x=%d, y=%d)", x, y);
		}	
	}	

	static Point nextInDirection(Point p, Direction d) {
		switch(d) {
			case UP: 
				return new Point(p.x, p.y - 1);
			case DOWN:
				return new Point(p.x, p.y + 1);
			case LEFT: 
				return new Point(p.x - 1, p.y);
			case RIGHT:
				return new Point(p.x + 1, p.y);
			default: 
				throw new IllegalArgumentException(d.toString());
		}		
	}

	// forest
	final char[][] F;
	final int nX;
	final int nY;
	final Deque<Point> path = new ArrayDeque<>();
	final Deque<Direction> state = new ArrayDeque<>();

	public CountLuck(char[][] F) {
		this.F = F;
		this.nY = F.length;
		this.nX = F[0].length;
	}

	Point findStartPoint() {
		for (int y = 0; y < nY; y++) {
			for (int x = 0; x < nX; x++) {
				if (F[y][x] == HERMIONE) {
					return new Point(x, y);
				}
			}
		}
		throw new IllegalArgumentException("Where is Hermione?");
	}

	boolean inBounds(Point p) {
		if (p.x < 0 || p.y < 0 || p.x >= nX || p.y >= nY) {
			return false;
		}
		return true;
	}

	Point lookAhead(Point current, Direction d) {
		Point next = nextInDirection(current, d);
		if (inBounds(next)) { 
			char field = F[next.y][next.x];
			if ((field == GRASS) || (field == EXIT)) {
				return next;
			}
		}
		return null;
	}

	Point tryDirection(Point current, Direction d) {
		Point next = lookAhead(current, d);
		if (next == null) {
			debug("NO", d);
			return null;
		}
		F[current.y][current.x] = BREADCRUMB;
		path.push(current);
		state.push(d);
		debug("YES", d, next);
		return next;
	}

	boolean isExit(Point current) {
		return F[current.y][current.x] == EXIT;
	}

	boolean moreThanOneWay(Point current) {
		int ways = 0;
		for (Direction d = Direction.START.next(); d != Direction.END; d = d.next()) {
			if (lookAhead(current, d) != null) {
				ways++;
			}
		}
		return ways > 1;
	}

	void findPath() {
		Point current = findStartPoint();
		Direction d = Direction.START.next();
		search:
		while (isExit(current) == false) {
			for (; d != Direction.END; d = d.next()) {
				Point next = tryDirection(current, d);
				if (next != null) {
					current = next;
					d = Direction.START.next();
					continue search;
				}
			}
			// backtrack
			F[current.y][current.x] = DEAD_END;
			debug("DEAD_END", current);
			current = path.pop();
			d = state.pop();
			F[current.y][current.x] = GRASS;
			debug("BACK", current);
		}		
	}

	void cleanForest() {
		for (int y = 0; y < F.length; y++) {
			for (int x = 0; x < F[0].length; x++) {
				if (F[y][x] == BREADCRUMB || F[y][x] == DEAD_END) {
					F[y][x] = GRASS;
				}
			}
		}
	}

	boolean isImpressed(int k) {
		findPath();
		cleanForest();
		debugMatrix(F);
		Iterator<Point> it = path.descendingIterator();
		Point p = null;
		char c = '0';
		while (it.hasNext()) {
			p = it.next();
			F[p.y][p.x] = BREADCRUMB;
			if (moreThanOneWay(p)) { 
				k--; 
				F[p.y][p.x] = '#';
			}
		}
		debugMatrix(F);
		return k == 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String[] _nm = scanner.nextLine().split(" ");
			int n = Integer.valueOf(_nm[0]);
			int m = Integer.valueOf(_nm[1]);
			char F[][] = new char[n][m];
			for (int i = 0; i < n; i++) {
				F[i] = scanner.nextLine().toCharArray();
				assert F[i].length == m;
			}
			int k = Integer.valueOf(scanner.nextLine());
			CountLuck o = new CountLuck(F);
			System.out.println((o.isImpressed(k)) ? "Impressed" : "Oops!");
		}
	}

	static boolean DEBUG = false;

	static void debugMatrix(char[][] M) {
		if (!DEBUG) { return; }
		for (int y = 0; y < M.length; y++) {
			for (int x = 0; x < M[0].length; x++) {
				System.err.print(M[y][x]);
			}
			System.err.println();
		}
		System.err.println();
	}

	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

