package main

import (
	"os"
	"io"
	"fmt"
	"log"
)

func solve(s string, k int) string {
	r := make([]byte, len(s))
	for i := 0; i < len(s); i++ {
		switch {
		case 'a' <= s[i] && s[i] <= 'z':
			r[i] = byte((int(s[i]) - 'a' + k) % 26 + 'a')
		case 'A' <= s[i] && s[i] <= 'Z':
			r[i] = byte((int(s[i]) - 'A' + k) % 26 + 'A')
		default:
			r[i] = s[i]
		}
	}
	return string(r)
}

func load(stdin io.Reader) {
	var n int
	fmt.Fscanln(stdin, &n)
	var s string
	fmt.Fscanln(stdin, &s)
	s = s[0:n] // just in case string is overlong
	var k int
	fmt.Fscan(stdin, &k)
	fmt.Println(solve(s, k))
}

func main() {
	if len(os.Args) == 2 && os.Args[1] == "COHADAR" {
		f, err := os.Open("ceasar-cipher.in")
		if err != nil {
			log.Fatal(err)
		}
		defer f.Close()
		load(f)
	} else {
		load(os.Stdin)
	}
}
