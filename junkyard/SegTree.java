// https://en.wikipedia.org/wiki/Segment_tree
static class SegTree
{
	static int seg[];
	static int dp[];
	int n;
	int d;
	public SegTree(int[] arr,int defaul)
	{
		this.dp=arr;
		n=dp.length;
		seg=new int[4*arr.length];
		build(1,0,n);
		this.d=defaul;
	}

	public void build(int id,int l,int r){
		if(r-l<2){
			seg[id]=dp[l];
			return;
		}
		int mid=(l+r)/2;
		build(2*id,l,mid);
		build(2*id+1,mid,r);
		seg[id]=merge(seg[2*id],seg[2*id+1]);
	}

	public int query(int l,int r)
	{
		return givemin(1,l,r,0,n);
	}

	public  int givemin(int id,int x,int y,int l,int r){
		if(x<=l && y>=r){
			return seg[id];
		}
		if(y<=l || r<=x){
			return d;
		}
		int mid=(l+r)/2;
		return merge(givemin(2*id, x, y, l, mid),givemin(2*id+1, x, y, mid, r));
	}

	public int upd(int id,int value)
	{
		return update(1,id,value,0,n);
	}
	public  int update(int id,int p,int value,int l,int r){
		if(r-l<2){
			dp[p]=value;
			seg[id]=value;
			return value;
		}
		int mid=(l+r)/2;
		if(p<mid){
			update(2*id, p, value, l, mid);
			seg[id]=merge(seg[2*id], seg[2*id+1]);
			return seg[id];
		}
		else{
			update(2*id+1, p, value, mid,r);
			seg[id]=merge(seg[2*id+1], seg[2*id]);
			return seg[id];

		}
	}

	public int merge(int l,int r)
	{

		//set initial value
		return l+r;
	}
}
