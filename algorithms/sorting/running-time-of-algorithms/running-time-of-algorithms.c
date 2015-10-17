#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

static int A[1023];

int solve(int n)
{
	int shifts = 0;
	for (int i = 1; i < n; i++) {
		int v = A[i];
		int j = i;
		while (j > 0 && A[j - 1] > v) {
			A[j] = A[j - 1];
			j--;
			shifts++;
		}
		A[j] = v;
	}
	return shifts;
}

void load(FILE * in)
{
	int s;
	fscanf(in, "%d\n", &s);
	for (int i = 0; i < s; i++) {
		fscanf(in, "%d", &A[i]);
	}
	printf("%d\n", solve(s));
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("running-time-of-algorithms.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

