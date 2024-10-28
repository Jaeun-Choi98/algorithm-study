package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/14517)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	d      [][]int
	mod    int
)

func main() {
	defer writer.Flush()
	data := []rune(nextLine())
	mod = 10007
	leng := len(data)
	d = make([][]int, leng)
	for i := 0; i < leng; i++ {
		d[i] = make([]int, leng)
		d[i][i] = 1
		if i != leng-1 {
			if data[i] == data[i+1] {
				d[i][i+1] = 3
			} else {
				d[i][i+1] = 2
			}
		}
	}

	for l := 2; l < leng; l++ {
		for i := 0; i < leng; i++ {
			s, e := i, i+l
			if e >= leng {
				continue
			}
			if data[s] == data[e] {
				d[s][e] = (d[s+1][e] + d[s][e-1] + 1) % mod
			} else {
				d[s][e] = (d[s+1][e] + d[s][e-1] - d[s+1][e-1] + mod) % mod
			}
		}
	}
	fmt.Fprintln(writer, d[0][leng-1])
}

func nextLine() string {
	line, _ := reader.ReadString('\n')
	return strings.TrimSpace(line)
}
