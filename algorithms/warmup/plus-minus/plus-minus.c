#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

void load(FILE * in)
{
	int N;
	int p = 0;
	int n = 0;
	int z = 0;
	fscanf(in, "%d\n", &N);
	for (int i = 0; i < N; i++) {
		int a;
		fscanf(in, "%d", &a);
		if (a > 0) {
			p++;
		} else if (a < 0) {
			n++;
		} else {
			z++;
		}
	}
	printf("%.3lf\n", 1.0 * p / N);
	printf("%.3lf\n", 1.0 * n / N);
	printf("%.3lf\n", 1.0 * z / N);
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("plus-minus.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

