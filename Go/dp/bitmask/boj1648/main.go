package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N, M   int
	d      [][]int
	mod    int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi(st[0]), atoi(st[1])
	d = make([][]int, N*M+1)
	for i := 0; i < N*M+1; i++ {
		d[i] = make([]int, 1<<M)
		for j := 0; j < (1 << M); j++ {
			d[i][j] = -1
		}
	}
	mod = 9901
	ans := search(0, 0)
	fmt.Fprintln(writer, ans)
}

func search(idx, status int) int {
	if idx == N*M && status == 0 {
		return 1
	}

	if idx >= N*M {
		return 0
	}

	if d[idx][status] != -1 {
		return d[idx][status]
	}

	ret := 0

	if (status & 1) > 0 {
		ret += search(idx+1, status>>1)
	} else {
		if (idx%M != M-1) && (status&2) == 0 {
			ret += search(idx+2, status>>2)
		}
		ret += search(idx+1, (status|1<<M)>>1)
	}

	ret %= mod
	d[idx][status] = ret
	return ret
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
