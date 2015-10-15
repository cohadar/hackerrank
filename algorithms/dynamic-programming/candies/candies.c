#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#define MAX_CHILDREN 102345
static long long R[MAX_CHILDREN]; // ratings

long long solve(int N)
{
	long long s = 0;
	long long b = 0;
	for (int i = 1; i < N; i++) {
		if (R[i - 1] < R[i]) {
			if (b <= 0) {
				b = 1;
			} else {
				b += 1;
			}
		} else if (R[i - 1] > R[i]) {
			if (b > 0) {
				b = 0;
			} else {
				b -= 1;
			}
		} else {
			b = 0;
		}
		if (b >= 0) {
			s += b;
		} else {
			s += -b;
		}
	}
	return N + s;
}

void load(FILE * in)
{
	int N;
	fscanf(in, "%d\n", &N);
	for (int i = 0; i < N; i++) {
		fscanf(in, "%lld\n", &R[i]);
	}
	printf("%lld\n", solve(N));
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("candies.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

