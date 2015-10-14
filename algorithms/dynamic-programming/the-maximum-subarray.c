#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stdbool.h>

#define MAX_LEN 100000
static int A[MAX_LEN];
static int S[2][MAX_LEN + 1];
static int M[2][MAX_LEN + 1];

int solve_non_contiguous(int n)
{
	int ncont = 0;
	int max = A[0];
	bool hasone = false;
	for (int i = 0; i < n; i++) {
		max = (A[i] > max) ? A[i] : max;
		if (ncont + A[i] > ncont) {
			ncont = ncont + A[i];
			hasone = true;
		}
	}
	if (!hasone) {
		ncont = max;
	}
	return ncont;
}

int max2(int a, int b)
{
	return (a >= b) ? a : b;
}

int max3(int a, int b, int c)
{
	return max2(max2(a, b), c);
}

int solve_contiguous(int n)
{
	for (int r = 0; r < n; r++) {
		int ir = r % 2;
		S[ir][r] = A[r];
		M[ir][r] = A[r];
		for (int l = r - 1; l >= 0; l--) {
			S[ir][l] = S[ir][l + 1] + A[l];
			M[ir][l] = max3(S[ir][l], M[ir][l + 1], M[1 - ir][l]);
		}
	}
	return M[(n-1)%2][0];
}

void solve(FILE *in)
{
	int T;
	int N;

	fscanf(in, "%d\n", &T);
	for (int i = 0; i < T; i++) {
		fscanf(in, "%d\n", &N);
		if (N > 0) {
			for (int i = 0; i < N; i++) {
				fscanf(in, "%d", A + i);
			}
			memset(M, 0, (MAX_LEN + 1) * sizeof(**M) * 2);
			memset(S, 0, (MAX_LEN + 1) * sizeof(**S) * 2);
			int cont = solve_contiguous(N);
			int ncont = solve_non_contiguous(N);
			printf("%d %d\n", cont, ncont);
		}
	}
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("the-maximum-subarray.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	solve(in);
	return 0;
}
