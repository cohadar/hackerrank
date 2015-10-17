#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

static int N, M;
static int C[50 + 1];
static int V[50 + 1][250 + 1]; // V[coin][value]

int solve()
{
	for (int ic = 0; ic <= M; ic++) {
		V[ic][0] = 1;
	}
	for (int ic = 1; ic <= M; ic++) {
		for (int iv = 0; iv <= N; iv++) {
			V[ic][iv] = V[ic-1][iv];
			int p = iv - C[ic];
			if (p >= 0) {
				V[ic][iv] += V[ic][p];
			}
		}
	}
	return V[M][N];
}

void load(FILE * in)
{
	fscanf(in, "%d %d\n", &N, &M);
	for (int i = 1; i <= M; i++) {
		fscanf(in, "%d", &C[i]);
	}
	printf("%d\n", solve());
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("the-coin-change-problem.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}
