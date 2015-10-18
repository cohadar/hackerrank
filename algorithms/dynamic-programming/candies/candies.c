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
	ll sum;
	ll len;
	ll delta;
	ll min;
	bool peak;
} Segment;

void Segment_init(Segment *o)
{
	o->sum = 0;
	o->len = 1;
	o->delta = 0;
	o->min = 0;
	o->peak = false;
}

void Segment_delta(Segment *o, int delta)
{
	o->delta += delta;
	o->sum += o->delta;
	o->len++;
	if (o->delta < o->min) {
		o->min = o->delta;
	}
}

void Segment_shift(Segment *o, ll shift)
{
	o->sum += o->len * shift;
	o->delta += shift;
	o->min += shift;
}

void Segment_print(Segment s)
{
	printf("[l=%lld, s=%lld, m=%lld, d=%lld, %c]", s.len, s.sum, s.min, s.delta, (s.peak) ? '^' : '=');
}

static ll R[MAX_CHILDREN]; // ratings
static Segment S[MAX_CHILDREN]; // segments

int scan_segments(int N)
{
	assert(N > 1);
	int n_segments = 0;
	Segment segment;
	Segment_init(&segment);
	bool asc = false;
	bool one_left = false;
	for (int i = 1; i <= N; i++) {
		bool one_left = false;
		if (R[i - 1] == R[i]) {
			segment.peak = false;
			S[n_segments++] = segment;
			Segment_init(&segment);
			asc = false;
			one_left = true;
			continue;
		}
		if (asc) {
			if (R[i - 1] < R[i]) {
				// continue ascending
				Segment_delta(&segment, +1);
			} else {
				// peak
				segment.peak = true;
				S[n_segments++] = segment;
				Segment_init(&segment);
				asc = false;
				one_left = true;
			}
		} else {
			if (R[i - 1] > R[i]) {
				// continue descending
				Segment_delta(&segment, -1);
			} else {
				// curve bottom
				Segment_delta(&segment, +1);
				asc = true;
			}
		}
	}
	S[n_segments++] = segment;
	return n_segments;
}

void print_segments(n_segments)
{
	for (int i = 0; i < n_segments; i++) {
		Segment_print(S[i]);
	}
	printf("\n");
}

ll solve(int N)
{
	for (int i = 0; i < N; i++) {
		printf("%lld ", R[N]);
	}
	printf("\n");
	assert(N > 0);
	if (N == 1) {
		return 1;
	}
	ll total = 0;
	int n_segments = scan_segments(N);
	print_segments(n_segments);
	bool prev_peak = false;
	int zlen = 0;
	ll zdelta = 0;
	for (int i = 0; i < n_segments; i++) {
		if (prev_peak) {
			Segment_shift(&S[i], zdelta - 1);
			if (S[i].min >= 0) {
				total += S[i].sum;
			} else {
				total += S[i].sum;
				total += zlen * -S[i].min;
			}
		} else {
			Segment_shift(&S[i], -S[i].min);
			total += S[i].sum;
		}
		zlen = S[i].len;
		zdelta = S[i].delta;
		prev_peak = S[i].peak;
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

