#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stdbool.h>

#define MAX_CHILDREN 102345
static long long R[MAX_CHILDREN]; // ratings

enum Stage {CONTINUE_ASC = 3, CONTINUE_DESC = 0, BOTTOM = 1, PEAK_BREAK = 2};

long long solve(int N)
{
	long long total = 0;
	long long partial = 0;
	bool asc = false;
	long long d = 0;
	long long m = 0;
	long long len = 1;
	for (int i = 1; i < N; i++) {
		if (R[i - 1] == R[i]) {
			total += partial + len * (-m);
			d = 0;
			partial = 0;
			m = 0;
			len = 1;
			asc = false;
			continue;
		}
		enum Stage stage = (asc << 1) + (R[i - 1] < R[i]);
		switch (stage) {
			case CONTINUE_ASC:
				d += 1;
				partial += d;
				len++;
				break;
			case CONTINUE_DESC:
				d -= 1;
				partial += d;
				len++;
				if (d < m) { m = d; }
				break;
			case BOTTOM:
				d += 1;
				partial += d;
				len++;
				asc = true;
				break;
			case PEAK_BREAK:
				if (m >= 0) {
					total += partial + len * (-m);
				} else {
					total += partial + i * (-m);
				}
				d = d - m - 1;
				partial = d;
				m = d;
				len = 1;
				asc = false;
				break;
			default:
				assert(0);
		}
	}
	if (len > 0) {
		total += partial + len * (-m);
	}
	return total + N;
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

