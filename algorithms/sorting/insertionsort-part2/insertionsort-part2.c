#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

static int A[1023];

void print_array(int n)
{
	for (int i = 0; i < n; i++) {
		printf("%d ", A[i]);
	}
	printf("\n");
}

void solve(int n)
{
	for (int i = 1; i < n; i++) {
		int v = A[i];
		int j = i;
		while (j > 0 && A[j - 1] > v) {
			A[j] = A[j - 1];
			j--;
		}
		A[j] = v;
		print_array(n);
	}
}

void load(FILE * in)
{
	int s;
	fscanf(in, "%d\n", &s);
	for (int i = 0; i < s; i++) {
		fscanf(in, "%d", &A[i]);
	}
	solve(s);
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("insertionsort-part2.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

