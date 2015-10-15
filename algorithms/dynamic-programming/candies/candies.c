#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#define MAX_CHILDREN 102345
static long long R[MAX_CHILDREN]; // ratings

long long solve(int N)
{
	long long l = 1; // length of subarray without eq elements
	long long b = 0; // base shift
	long long m = 0; // min base in subarray
	long long s = 0; // unshifted sum in subarray
	long long total = 0; // total sum
	for (int i = 1; i < N; i++) {
		if (R[i - 1] < R[i]) {
			l += 1;
			b += 1;
			s += b;
		} else if (R[i - 1] > R[i]) {
			l += 1;
			b -= 1;
			s += b;
			if (b < m) {
				m = b;
			}
		} else {
			total += s + l * (-m);
			l = 1;
			b = 0;
			m = 0;
			s = 0;
		}
		printf("l=%lld, b=%lld, m=%lld, s=%lld, total=%lld\n", l, b, m, s, total);
	}
	total += s + l * (-m);
	return N + total;
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

