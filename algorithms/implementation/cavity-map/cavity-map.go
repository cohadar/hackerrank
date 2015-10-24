package main

import (
	"os"
	"io"
	"fmt"
	"log"
)

type Point struct {
	x, y int
}

var D = [...]Point {
	          {-1, 0},
	{ 0, -1},          { 0, +1},
	          {+1, 0},
}

func solve(M [][]int) {
	ny := len(M)
	nx := len(M[0])
	for y := 0; y < ny; y++ {
		for x := 0; x < nx; x++ {
			cavity := true
			for _, r := range D {
				if x == 0 || x == nx - 1 || y == 0 || y == ny - 1 {
					cavity = false
					break
				}
				if (M[y + r.y][x + r.x] >= M[y][x]) {
					cavity = false
					break
				}
			}
			if cavity {
				fmt.Print("X")
			} else {
				fmt.Print(M[y][x])
			}
		}
		fmt.Println()
	}
}

func load(stdin io.Reader) {
	var n int
	fmt.Fscanln(stdin, &n)
	M := make([][]int, n)
	for y := 0; y < len(M); y++ {
		M[y] = make([]int, n)
		s := make([]byte, n)
		fmt.Fscanln(stdin, &s)
		for x := 0; x < len(M[y]); x++ {
			M[y][x] = int(s[x] - '0')
		}
	}
	solve(M)
}

func main() {
	if len(os.Args) == 2 && os.Args[1] == "COHADAR" {
		f, err := os.Open("cavity-map.in")
		if err != nil {
			log.Fatal(err)
		}
		defer f.Close()
		load(f)
	} else {
		load(os.Stdin)
	}
}
