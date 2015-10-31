static class UF {

	private int[] parent;  // parent[i] = parent of i
	private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
	private int count;   
	int[] seg;
	int max;
	// number of components
	public UF(int N) {
		if (N < 0) throw new IllegalArgumentException();
		count = N;
		parent = new int[N];
		rank = new byte[N];
		seg=new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
			rank[i] = 0;
			seg[i]=1;
		}
		max=1;
	}

	public int find(int p) {
		if (p < 0 || p >= parent.length) throw new IndexOutOfBoundsException();
		while (p != parent[p]) {
			parent[p] = parent[parent[p]];	// path compression by halving
			p = parent[p];
		}
		return p;
	}
	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public boolean union(int p, int q) {
		//	System.out.println("uf "+p+" "+q);
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ) return false;

		// make root of smaller rank point to root of larger rank
		if	  (rank[rootP] < rank[rootQ])
		{
			parent[rootP] = rootQ;
			seg[rootQ]+=seg[rootP];
			max=Math.max(seg[rootQ],max);
		}
		else if (rank[rootP] > rank[rootQ])
		{
			parent[rootQ] = rootP;
			seg[rootP]+=seg[rootQ];
			max=Math.max(seg[rootP],max);
		}
		else {
			parent[rootQ] = rootP;
			rank[rootP]++;
			seg[rootP]+=seg[rootQ];
			max=Math.max(seg[rootP],max);
		}
		count--;
		return true;
	}

}
