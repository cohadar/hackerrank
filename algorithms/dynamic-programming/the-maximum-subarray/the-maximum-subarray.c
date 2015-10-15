#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stdbool.h>

#define MAX_LEN 100000
static int A[MAX_LEN];

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
	int m = A[0];
	int r = max2(0, A[0]);
	for (int i = 1; i < n; i++) {
		int temp = r + A[i];
		r = max2(A[i], temp);
		m = max3(A[i], m, temp);
	}
	return m;
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
