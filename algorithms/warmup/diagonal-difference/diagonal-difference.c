#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

static int M[100][100];

int solve(int N)
{
	int fd = 0;
	int sd = 0;
	for (int i = 0; i < N; i++) {
		fd += M[i][i];
		sd += M[i][N -1 -i];
	}
	return abs(fd - sd);
}

void load(FILE * in)
{
	int N;
	fscanf(in, "%d\n", &N);
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < N; x++) {
			fscanf(in, "%d ", &M[y][x]);
		}
	}
	printf("%d\n", solve(N));
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("diagonal-difference.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

