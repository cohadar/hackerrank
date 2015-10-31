import java.util.*;
import java.io.*;

public class CountLuck {

	static final char HERMIONE = 'M';
	static final char GRASS = '.';
	static final char TREE = 'X';
	static final char DEAD_END = '@';
	static final char EXIT = '*';
	static final char BREADCRUMB = '-';

	static enum Direction { RIGHT, DOWN, LEFT, UP }

	static List<Direction> directions = Arrays.asList(
											Direction.RIGHT, 
											Direction.DOWN, 
											Direction.LEFT, 
											Direction.UP
										);

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
		debug("YES", d, next);
		return next;
	}

	boolean isExit(Point current) {
		return F[current.y][current.x] == EXIT;
	}

	boolean moreThanOneWay(Point current) {
		int ways = 0;
		for (Direction d : directions) {
			if (lookAhead(current, d) != null) {
				ways++;
			}
		}
		return ways > 1;
	}

	boolean isImpressed(int k) {
		Point current = findStartPoint();
		if (moreThanOneWay(current)) { k--; }
		search:
		while (isExit(current) == false) {
			for (Direction d : directions) {
				Point next = tryDirection(current, d);
				if (next != null) {
					current = next;
					if (moreThanOneWay(current)) { k--; }
					continue search;
				}
			}
			// backtrack
			F[current.y][current.x] = DEAD_END;
			debug("DEAD_END", current);
			current = path.pop();
			F[current.y][current.x] = GRASS;
			debug("BACK", current);
		}
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

	static void debug(Object...os) {
		// System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

