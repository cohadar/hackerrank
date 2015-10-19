#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stdbool.h>
#include <stdint.h>

#define MIN(a,b) ((a)<(b)?(a):(b))

#define MAX_CHILDREN 102345

#define PRINT 0

typedef struct {
	int sum;
	int len;
	int delta;
	int min;
} Segment;

void Segment_init(Segment *o)
{
	o->sum = 0;
	o->len = 1;
	o->delta = 0;
	o->min = 0;
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

void Segment_shift(Segment *o, int shift)
{
	o->sum += o->len * shift;
	o->delta += shift;
	o->min += shift;
}

void Segment_add(Segment *a, Segment *b)
{
	Segment_shift(a, -a->min);
	int b_left = -b->min;
	Segment_shift(b, -b->min);
	if (a->delta <= b_left) {
		Segment_shift(a, b_left + 1 - a->delta);
	}
	a->sum += b->sum;
	a->len += b->len;
	a->delta = b->delta;
	a->min = 0;
}

void Segment_print(Segment s)
{
	printf("[l=%d, s=%d, m=%d, d=%d]", s.len, s.sum, s.min, s.delta);
}

static int R[MAX_CHILDREN]; // ratings
static Segment S[MAX_CHILDREN]; // segments

int scan_segments(int a, int b)
{
	assert(a < b);
	int n_segments = 0;
	Segment segment;
	Segment_init(&segment);
	bool asc = false;
	bool one_left = false;
	for (int i = a + 1; i <= b; i++) {
		one_left = true;
		if (R[i - 1] == R[i]) {
			assert(0);
		}
		if (asc) {
			if (R[i - 1] < R[i]) {
				// continue ascending
				Segment_delta(&segment, +1);
			} else {
				// peak
				S[n_segments++] = segment;
				Segment_init(&segment);
				asc = false;
				one_left = false;
			}
		} else {
			if (R[i - 1] > R[i]) {
				// continue descending
				Segment_delta(&segment, -1);
				if (PRINT) printf("%d > %d, len=%d, i=%d\n", R[i - 1], R[i], segment.len, i);
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

int solve_slope(int a, int b)
{
	assert(a <= b);
	if (a == b) {
		return 0;
	}
	int n_segments = scan_segments(a, b);
	if (PRINT) print_segments(n_segments);
	if (n_segments == 0) {
		return 0;
	}
	Segment njak = S[0];
	for (int i = 1; i < n_segments; i++) {
		Segment_add(&njak, &S[i]);
	}
	Segment_shift(&njak, -njak.min);
	return njak.sum;
}

static int B[MAX_CHILDREN]; // brute force ratings

void fixback(int r) {
	for (int i = r; i > 0; i--) {
		if (R[i - 1] > R[i] && B[i - 1] <= B[i]) {
			B[i - 1] = B[i] + 1;
		} else if (R[i - 1] < R[i] && B[i - 1] >= B[i]) {
			B[i - 1] = B[i] - 1;
			if (B[i - 1] < 1) {
				int delta = 1 - B[i - 1];
				for (int j = i - 1; j <= r; j++) {
				 	B[j] += delta;
				}
			}
		}
	}
}

int barbarian(int N)
{
	assert(N > 0);
	if (N == 1) {
		return 1;
	}
	B[0] = 1;
	for (int i = 1; i < N; i++) {
		if (R[i - 1] < R[i]) {
			B[i] = B[i - 1] + 1;
		} else {
			B[i] = 1;
		}
		fixback(i);
	}
	int total = 0;
	for (int i = 0; i < N; i++) {
		total += B[i];
	}
	return total;
}

int solve(int N)
{
	assert(N > 0);
	if (N == 1) {
		return 1;
	}
	int total = 0;
	int a = 0, b = 0;
	for (int i = 1; i < N; i++) {
		if (R[i - 1] == R[i]) {
			total += solve_slope(a, b);
			a = i;
			b = i;
		} else {
			b++;
		}
	}
	total += solve_slope(a, b);
	return total + N;
}

void load_single(FILE *in, bool cohadar, int test_case)
{
	int N;
	fscanf(in, "%d\n", &N);
	if (PRINT) printf("N=%d\n[", N);
	for (int i = 0; i < N; i++) {
		fscanf(in, "%d", &R[i]);
		if (PRINT) printf("%d ", R[i]);
	}
	if (PRINT) printf("\n");
	printf("%d\n", barbarian(N));
}

void load_multi(FILE *in, bool cohadar) {
	int T;
	fscanf(in, "%d\n", &T);
	if (PRINT) printf("T=%d\n", T);
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


