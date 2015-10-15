#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

int solve(int a, int b)
{
	return a + b;
}

void load(FILE * in)
{
	int T;
	int a, b;
	fscanf(in, "%d\n", &T);
	for (int i = 0; i < T; i++) {
		fscanf(in, "%d %d\n", &a, &b);
		printf("%d\n", solve(a, b));
	}
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("solve-me-second.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

