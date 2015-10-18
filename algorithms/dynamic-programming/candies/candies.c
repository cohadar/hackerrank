#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stdbool.h>
#include <stdint.h>

#define MIN(a,b) ((a)<(b)?(a):(b))

#define MAX_CHILDREN 102345

typedef long long ll;

typedef struct {
	int len;
	bool peak;
} Segment;

void Segment_init(Segment *o)
{
	o->len = 1;
	o->peak = false;
}

static ll R[MAX_CHILDREN]; // ratings
static Segment S[MAX_CHILDREN]; // segments

int scan_segments(int N)
{
	assert(N > 1);
	int n_segments = 0;
	Segment segment;
	Segment_init(&segment);
	for (int i = 1; i < N; i++) {
		if (R[i - 1] < R[i]) {
			segment.len++;
		} else if (R[i - 1] > R[i]) {
			segment.peak = true;
			S[n_segments++] = segment;
			Segment_init(&segment);
		} else {
			segment.peak = false;
			S[n_segments++] = segment;
			Segment_init(&segment);
		}
	}
	if (segment.len == 1) {
		S[n_segments++] = segment;
	}
	return n_segments;
}

ll solve(int N)
{
	assert(N > 0);
	if (N == 1) {
		return 1;
	}
	ll total = 0;
	int n_segments = scan_segments(N);
	for (int i = 0; i < n_segments; i++) {
		total += S[i].sum;
	}
	return total + N;
}

void load_single(FILE *in, bool cohadar, int test_case)
{
	int N;
	fscanf(in, "%d\n", &N);
	for (int i = 0; i < N; i++) {
		fscanf(in, "%lld", &R[i]);
	}
	printf("%lld\n", solve(N));
}

void load_multi(FILE *in, bool cohadar) {
	int T;
	fscanf(in, "%d\n", &T);
	for (int t = 1; t <= T; t++) {
		load_single(in, cohadar, t);
	}
}

int main(int argc, char const *argv[])
{
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		FILE *in = fopen("candies.in", "r");
		assert(in);
		load_multi(in, true);
	} else {
		load_single(stdin, false, 0);
	}
	return 0;
}

