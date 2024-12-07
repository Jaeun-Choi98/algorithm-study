package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/2673)
*/

type Line struct {
	l, r int
}

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N      int
	d      [][]int
	lines  []Line
)

func main() {
	defer writer.Flush()
	N = nextInt()
	lines = make([]Line, N)
	d = make([][]int, 101)
	for i := 0; i <= 100; i++ {
		d[i] = make([]int, 101)
		for j := 0; j <= 100; j++ {
			d[i][j] = -1
		}
	}
	for i := 0; i < N; i++ {
		st := stringTokenizer(" ")
		l, r := atoi(st[0]), atoi(st[1])
		if l > r {
			l, r = r, l
		}
		lines[i] = Line{l, r}
	}
	ans := search(1, 100)
	fmt.Fprintln(writer, ans)
}

func search(l, r int) int {
	if r < 1 || l > 100 {
		return 0
	}

	if d[l][r] != -1 {
		return d[l][r]
	}
	d[l][r] = 0

	for _, line := range lines {
		if line.l < l || r < line.r {
			continue
		}
		d[l][r] = max(d[l][r], search(l, line.l-1)+search(line.l+1, line.r-1)+search(line.r+1, r)+1)
	}

	return d[l][r]
}

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
