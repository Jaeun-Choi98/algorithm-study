package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/1395)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N, M    int
	segTree []int
	lazyBuf []int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi(st[0]), atoi(st[1])
	h := 1
	for (1 << h) < N {
		h++
	}
	segTree = make([]int, 1<<(h+1))
	lazyBuf = make([]int, 1<<(h+1))
	for i := 0; i < M; i++ {
		st = stringTokenizer(" ")
		cmd, s, e := atoi(st[0]), atoi(st[1]), atoi(st[2])
		switch cmd {
		case 0:
			Update(1, 1, N, s, e, 1)
		case 1:
			ans := Query(1, 1, N, s, e)
			fmt.Fprintln(writer, ans)
		default:
		}
	}
}

func UpdateLazy(idx, l, r int) {
	if lazyBuf[idx] != 0 {
		xor := r - l + 1
		if l != r {
			lazyBuf[2*idx] += lazyBuf[idx]
			lazyBuf[2*idx+1] += lazyBuf[idx]
		}
		if lazyBuf[idx]%2 == 1 {
			segTree[idx] = xor - segTree[idx]
		}
		lazyBuf[idx] = 0
	}
}

func Update(idx, l, r, tl, tr, tar int) int {
	UpdateLazy(idx, l, r)
	if r < tl || l > tr {
		return segTree[idx]
	}
	if tl <= l && r <= tr {
		lazyBuf[idx]++
		UpdateLazy(idx, l, r)
		return segTree[idx]
	}
	m := (l + r) / 2
	segTree[idx] = Update(2*idx, l, m, tl, tr, tar) + Update(2*idx+1, m+1, r, tl, tr, tar)
	return segTree[idx]
}

func Query(idx, l, r, tl, tr int) int {
	UpdateLazy(idx, l, r)
	if r < tl || l > tr {
		return 0
	}
	if tl <= l && r <= tr {
		return segTree[idx]
	}
	m := (l + r) / 2
	return Query(2*idx, l, m, tl, tr) + Query(2*idx+1, m+1, r, tl, tr)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}
