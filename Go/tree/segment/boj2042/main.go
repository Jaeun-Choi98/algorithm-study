package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/2042)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	segTree []int64
	data    []int64
	N, M, K int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M, K = atoi(st[0]), atoi(st[1]), atoi(st[2])
	data = make([]int64, N)
	for i := 0; i < N; i++ {
		data[i] = nextInt64()
	}
	h := 0
	for (1 << h) < N {
		h++
	}
	segTree = make([]int64, 1<<(h+1))
	InitSegTree(1, 0, N-1)
	tc := M + K
	for tc > 0 {
		st := stringTokenizer(" ")
		a := atoi(st[0])
		if a == 1 {
			b, c := atoi(st[1]), atoi64(st[2])
			Update(1, 0, N-1, b-1, c)
		} else if a == 2 {
			b, c := atoi(st[1]), atoi(st[2])
			fmt.Fprintln(writer, Query(1, 0, N-1, b-1, c-1))
		}
		tc--
	}
}

func Update(idx, l, r, tidx int, val int64) int64 {
	if l == r {
		segTree[idx] = val
		return segTree[idx]
	}
	m := (l + r) / 2
	if l <= tidx && tidx <= m {
		segTree[idx] = Update(2*idx, l, m, tidx, val) + segTree[2*idx+1]
	} else {
		segTree[idx] = segTree[2*idx] + Update(2*idx+1, m+1, r, tidx, val)
	}
	return segTree[idx]
}

func Query(idx, l, r, tl, tr int) int64 {
	if r < tl || l > tr {
		return 0
	}
	if tl <= l && r <= tr {
		return segTree[idx]
	}
	m := (l + r) / 2
	return Query(2*idx, l, m, tl, tr) + Query(2*idx+1, m+1, r, tl, tr)
}

func InitSegTree(idx, l, r int) int64 {
	if l == r {
		segTree[idx] = data[l]
		return segTree[idx]
	}

	m := (l + r) / 2
	segTree[idx] = InitSegTree(idx*2, l, m) + InitSegTree(idx*2+1, m+1, r)
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

func atoi64(str string) int64 {
	ret, _ := strconv.ParseInt(str, 10, 64)
	return ret
}

func nextInt64() int64 {
	line, _ := reader.ReadString('\n')
	return atoi64(strings.TrimSpace(line))
}
