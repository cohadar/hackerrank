package main

import (
	"os"
	"io"
	"fmt"
	"log"
)

func solve(A []int) int {
	sum := 0
	for i := 0; i < len(A); i++ {
		sum += A[i]
	}
	return sum
}

func load(stdin io.Reader) {
	var n int
	fmt.Fscan(stdin, &n)
	A := make([]int, n)
	for i := 0; i < len(A); i++ {
		fmt.Fscan(stdin, &A[i])
	}
	fmt.Println(solve(A))
}

func main() {
	if len(os.Args) == 2 && os.Args[1] == "COHADAR" {
		f, err := os.Open("simple-array-sum.in")
		if err != nil {
			log.Fatal(err)
		}
		defer f.Close()
		load(f)
	} else {
		load(os.Stdin)
	}
}