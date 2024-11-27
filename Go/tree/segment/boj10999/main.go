package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/10999)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	segTree []int64
	lazy    []int64
	datas   []int64
	N, M, K int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M, K = atoi(st[0]), atoi(st[1]), atoi(st[2])
	datas = make([]int64, N)
	for i := 0; i < N; i++ {
		input := nextInt()
		datas[i] = input
	}
	segTree = make([]int64, 4*N)
	lazy = make([]int64, 4*N)
	Init(1, 0, N-1)
	for i := 0; i < M+K; i++ {
		st = stringTokenizer(" ")
		a := atoi(st[0])
		if a == 1 {
			b, c, d := atoi(st[1]), atoi(st[2]), atoi64(st[3])
			Update(1, 0, N-1, b-1, c-1, d)
		} else {
			b, c := atoi(st[1]), atoi(st[2])
			ret := Query(1, 0, N-1, b-1, c-1)
			fmt.Fprintln(writer, ret)
		}
	}
}

func UpdateLazy(idx, l, r int) {
	if lazy[idx] != 0 {
		if l != r {
			lazy[idx*2] += lazy[idx]
			lazy[idx*2+1] += lazy[idx]
		}
		segTree[idx] += lazy[idx] * int64(r-l+1)
		lazy[idx] = 0
	}
}

func Update(idx, l, r, tl, tr int, tar int64) int64 {
	UpdateLazy(idx, l, r)
	if r < tl || l > tr {
		return segTree[idx]
	}
	if tl <= l && r <= tr {
		lazy[idx] = tar
		UpdateLazy(idx, l, r)
		return segTree[idx]
	}
	m := (l + r) / 2
	segTree[idx] = Update(2*idx, l, m, tl, tr, tar) + Update(2*idx+1, m+1, r, tl, tr, tar)
	return segTree[idx]
}

func Query(idx, l, r, tl, tr int) int64 {
	UpdateLazy(idx, l, r)
	if r < tl || l > tr {
		return 0
	}
	if tl <= l && r <= tr {
		return segTree[idx]
	}
	m := (l + r) / 2
	return Query(idx*2, l, m, tl, tr) + Query(idx*2+1, m+1, r, tl, tr)
}

func Init(idx, l, r int) int64 {
	if l == r {
		segTree[idx] = datas[l]
		return segTree[idx]
	}
	m := (l + r) / 2
	segTree[idx] = Init(2*idx, l, m) + Init(2*idx+1, m+1, r)
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

func nextInt() int64 {
	line, _ := reader.ReadString('\n')
	return atoi64(strings.TrimSpace(line))
}
