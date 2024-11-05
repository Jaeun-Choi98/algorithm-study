package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/15678)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N, D    int
	data    []int
	d       []int
	segTree []int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, D = atoi(st[0]), atoi(st[1])
	data = make([]int, N)
	d = make([]int, N)
	segTree = make([]int, 4*N)
	st = stringTokenizer(" ")
	for i := 0; i < N; i++ {
		data[i] = atoi(st[i])
	}
	d[0] = data[0]
	update(1, 0, N-1, 0, d[0])
	ans := d[0]
	for i := 1; i < N; i++ {
		d[i] = data[i]
		d[i] = max(d[i], query(1, 0, N-1, i-D, i-1)+data[i])
		ans = max(ans, d[i])
		update(1, 0, N-1, i, d[i])
	}
	fmt.Fprintln(writer, ans)
}

func update(idx, l, r, tar, val int) int {
	if l == r {
		segTree[idx] = val
		return segTree[idx]
	}
	m := (l + r) / 2
	if tar <= m {
		segTree[idx] = max(update(2*idx, l, m, tar, val), segTree[2*idx+1])
	} else {
		segTree[idx] = max(segTree[2*idx], update(2*idx+1, m+1, r, tar, val))
	}
	return segTree[idx]
}

func query(idx, l, r, tl, tr int) int {
	if r < tl || l > tr {
		return -math.MaxInt32
	}
	if tl <= l && r <= tr {
		return segTree[idx]
	}
	m := (l + r) / 2
	return max(query(2*idx, l, m, tl, tr), query(2*idx+1, m+1, r, tl, tr))
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
