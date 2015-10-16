#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#define N 1023
static int A[N];

// a binary search
int solve(int n, int v)
{
	int l = 0;
	int r = n - 1;
	while (l <= r) {
		int m = ((unsigned int)l + (unsigned int)r) >> 1;
		if (A[m] < v) {
			l = m + 1;
		} else if (A[m] > v) {
			r = m - 1;
		} else {
			return m;
		}
	}
	return -(l + 1);
}

void load(FILE * in)
{
	int V;
	int n;
	fscanf(in, "%d\n", &V);
	fscanf(in, "%d\n", &n);
	for (int i = 0; i < n; i++) {
		fscanf(in, "%d", &A[i]);
	}
	printf("%d\n", solve(n, V));
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("intro.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

