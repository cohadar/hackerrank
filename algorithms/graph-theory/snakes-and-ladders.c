#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

static int D[100][100];

void init_D()
{
	for (int i = 1; i <= 100; i++) {
	for (int j = 1; j <= 100; j++) {
		D[i - 1][j - 1] = 0;
		if (i <= j && (j - i) <= 6) {
			D[i - 1][j - 1] = j - i;
		}
	}
	}
}

int solve()
{
	return 0;
}

void load(FILE * in)
{
	int T, NL, NS;
	fscanf(in, "%d\n", &T);
	for (int i = 0; i < T; i++) {
		init_D();
		fscanf(in, "%d\n", &NL);
		for (int l = 0; l < NL; l++) {
			int a, b;
			fscanf(in, "%d %d\n", &a, &b);
			D[a - 1][b - 1] = 0;
		}
		fscanf(in, "%d\n", &NS);
		for (int s = 0; s < NS; s++) {
			int a, b;
			fscanf(in, "%d %d\n", &a, &b);
			D[a - 1][b - 1] = 0;
		}
		printf("%d\n", solve());
	}
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("snakes-and-ladders.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

