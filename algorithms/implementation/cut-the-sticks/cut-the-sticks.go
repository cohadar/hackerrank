package main

import (
	"os"
	"io"
	"fmt"
	"log"
)

func min(A []int) int {
	m := A[0]
	for i := 1; i < len(A); i++ {
		if A[i] < m {
			m = A[i]
		}
	}
	return m
}

func solve(A []int) {
	B := make([]int, len(A))
	for len(A) > 0 {
		B = B[0:0]
		m := min(A)
		fmt.Println(len(A))
		for i := 0; i < len(A); i++ {
			A[i] -= m
			if A[i] != 0 {
				B = append(B, A[i])
			}
		}
		A, B = B, A
	}
}

func load(stdin io.Reader) {
	var n int
	fmt.Fscan(stdin, &n)
	A := make([]int, n)
	for i := 0; i < len(A); i++ {
		fmt.Fscan(stdin, &A[i])
	}
	solve(A)
}

func main() {
	if len(os.Args) == 2 && os.Args[1] == "COHADAR" {
		f, err := os.Open("cut-the-sticks.in")
		if err != nil {
			log.Fatal(err)
		}
		defer f.Close()
		load(f)
	} else {
		load(os.Stdin)
	}
}