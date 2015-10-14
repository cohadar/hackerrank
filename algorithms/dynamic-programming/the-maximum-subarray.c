#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stdbool.h>

int solve_non_contiguous(int *arr, int n)
{
	int ncont = 0;
	int max = arr[0];
	bool hasone = false;
	for (int i = 0; i < n; i++) {
		max = (arr[i] > max) ? arr[i] : max;
		if (ncont + arr[i] > ncont) {
			ncont = ncont + arr[i];
			hasone = true;
		}
	}
	if (!hasone) {
		ncont = max;
	}
	return ncont;
}

int solve_contiguous(int *arr, int n)
{
	return 0;
}

void solve(FILE *in)
{
	int T;
	int N;
	int *arr;

	fscanf(in, "%d\n", &T);
	for (int i = 0; i < T; i++) {
		fscanf(in, "%d\n", &N);
		if (N > 0) {
			arr = malloc(N * sizeof(*arr));
			for (int i = 0; i < N; i++) {
				fscanf(in, "%d", arr + i);
			}
			int cont = solve_contiguous(arr, N);
			int ncont = solve_non_contiguous(arr, N);
			printf("%d %d\n", cont, ncont);
			free(arr);
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
