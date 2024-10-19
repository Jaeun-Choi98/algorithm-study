package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	n       int
	segTree []int
	line1   []int
	l1Tol2  map[int]int
)

func main() {
	defer writer.Flush()
	n = nextInt()
	line1 = make([]int, n)
	segTree = make([]int, 4*n)

	var st []string
	st = stringTokenizer(" ")
	for i := 0; i < n; i++ {
		line1[i] = atoi(st[i])
	}
	l1Tol2 = make(map[int]int)
	st = stringTokenizer(" ")
	for i := 0; i < n; i++ {
		l1Tol2[atoi(st[i])] = i
	}

	ans := 0
	for i := 0; i < n; i++ {
		tar := l1Tol2[line1[i]]
		ans += Query(1, 0, n-1, tar+1, n-1)
		Update(1, 0, n-1, tar)
	}
	fmt.Fprintln(writer, ans)
}

func Query(idx, l, r, tl, tr int) int {
	if r < tl || l > tr {
		return 0
	}

	if tl <= l && r <= tr {
		return segTree[idx]
	}

	m := (l + r) / 2
	return Query(2*idx, l, m, tl, tr) + Query(2*idx+1, m+1, r, tl, tr)
}

func Update(idx, l, r, tar int) int {
	if l == r {
		segTree[idx] = 1
		return segTree[idx]
	}
	m := (l + r) / 2
	if tar <= m {
		segTree[idx] = Update(2*idx, l, m, tar) + segTree[2*idx+1]
	} else {
		segTree[idx] = segTree[2*idx] + Update(2*idx+1, m+1, r, tar)
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
