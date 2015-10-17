#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

static int A[1023];

void printA(int n)
{
	for (int i = 0; i < n; i++) {
		printf("%d ", A[i]);
	}
	printf("\n");
}

void swap(int a, int b) {
	int temp = A[a];
	A[a] = A[b];
	A[b] = temp;
}

// Lomuto partitioning scheme
void solve(int n)
{
	int piv = A[0];
	int a = 1;
	for (int b = 1; b < n; b++) {
		if (A[b] < piv) {
			swap(a, b);
			a++;
		}
	}
	swap(0, a - 1);
	printA(n);
}

void load(FILE * in)
{
	int n;
	fscanf(in, "%d\n", &n);
	for (int i = 0; i < n; i++) {
		fscanf(in, "%d", &A[i]);
	}
	solve(n);
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("quicksort1-partition.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}
