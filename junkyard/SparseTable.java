static class SparseTable
{
	int[][] st;
	public SparseTable(int[] h){
		int n=h.length;
		int MAX_LOG=(int)Math.ceil(Math.log(n)/Math.log(2))+1;
		st=new int[n][MAX_LOG];
		for (int i = 0; i < n; i++)
			st[i][0] = h[i];
		for (int j = 1; (1 << j) <= n; j++)
		{
			for (int i = 0;( i + (1 << j) - 1 )< n; i++)
			{
				st[i][j]=Math.max(st[i][j-1],st[i+(1<<(j-1))][j-1]);
			}
		}
	}
	public int query(int l,int r)
	{
		int x=(int )Math.floor((Math.log(r-l+1)/Math.log(2)));
		int ans=Math.max(st[l][x],st[r-(1<<x)+1][x]);
		return ans;
	}

}
