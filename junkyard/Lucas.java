
// https://en.wikipedia.org/wiki/Lucas'_theorem
static class nck
{
	public static long ncr[][];
	
	public nck(long z,long p)//z==maxvalue  p==mod
	{
		generateNCR(z, p);
	}
	
	private static void generateNCR( long z, long p )
	{
		ncr = new long[(int)z+1][(int)z+1];
		ncr[0][0] = 1;
		for(int i=1; i<=z; i++)
		{
			ncr[i][0] = 1;
			ncr[0][i] = 0;
		}
		for(int i=1; i<=z; i++)
		{
			for(int j=1; j<=z; j++)
			{
				ncr[i][j] = (ncr[i-1][j] + ncr[i-1][j-1]) % p;
			}
		}
	}

	private static long lucas(long n, long k, long p) 
	{
	
		long ans = 1;
		
		while(n>0)
		{
			int N = (int)(n%p);
			int K = (int)(k%p);
			if(K>N)
				return 0;
			ans *= (ncr[N][K] );
			n /= p;
			k /= p;
		}
		return ans % p;
	}
}