package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/11505)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N, M, K int
	segTree []int
	mod     int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M, K = atoi(st[0]), atoi(st[1]), atoi(st[2])
	segTree = make([]int, 4*N)
	mod = 1000000007
	for i := 1; i <= N; i++ {
		num := nextInt()
		Update(1, 1, N, i, num)
	}
	for i := 0; i < M+K; i++ {
		st := stringTokenizer(" ")
		a, b, c := atoi(st[0]), atoi(st[1]), atoi(st[2])
		if a == 1 {
			Update(1, 1, N, b, c)
		} else {
			fmt.Fprintln(writer, Query(1, 1, N, b, c))
		}
	}
}

func Query(idx, l, r, tl, tr int) int {
	if l > tr || r < tl {
		return 1
	}
	if tl <= l && r <= tr {
		return segTree[idx]
	}
	m := (l + r) / 2
	return (Query(2*idx, l, m, tl, tr) * Query(2*idx+1, m+1, r, tl, tr)) % mod
}

func Update(idx, l, r, tar, val int) int {
	if l == r {
		segTree[idx] = val % mod
		return segTree[idx]
	}
	m := (l + r) / 2
	if tar <= m {
		segTree[idx] = (Update(2*idx, l, m, tar, val) * segTree[2*idx+1]) % mod
	} else {
		segTree[idx] = (segTree[2*idx] * Update(2*idx+1, m+1, r, tar, val)) % mod
	}
	return segTree[idx]
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
