package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/2357)
*/

type Node struct {
	min, max int
}

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N, M    int
	data    []int
	segTree []Node
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi(st[0]), atoi(st[1])
	data = make([]int, N)
	for i := 0; i < N; i++ {
		data[i] = nextInt()
	}
	h := 0
	for (1 << h) < N {
		h++
	}
	segTree = make([]Node, 1<<(h+1))
	Init(1, 0, N-1)

	for M > 0 {
		st = stringTokenizer(" ")
		a, b := atoi(st[0])-1, atoi(st[1])-1
		ret := Query(1, 0, N-1, a, b)
		fmt.Fprintln(writer, ret.min, ret.max)
		M--
	}
}

func Query(idx, l, r, tL, tR int) Node {
	if r < tL || l > tR {
		return Node{max: 0, min: 1000000001}
	}
	if tL <= l && r <= tR {
		return segTree[idx]
	}
	m := (l + r) / 2
	ln := Query(2*idx, l, m, tL, tR)
	rn := Query(2*idx+1, m+1, r, tL, tR)
	maxVal := max(ln.max, rn.max)
	minVal := min(ln.min, rn.min)
	return Node{max: maxVal, min: minVal}
}

func Init(idx, l, r int) Node {
	if l == r {
		segTree[idx] = Node{max: data[l], min: data[l]}
		return segTree[idx]
	}
	m := (l + r) / 2
	ln := Init(2*idx, l, m)
	rn := Init(2*idx+1, m+1, r)
	maxVal := max(ln.max, rn.max)
	minVal := min(ln.min, rn.min)
	segTree[idx] = Node{max: maxVal, min: minVal}
	return segTree[idx]
}

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

func min(a, b int) int {
	if a < b {
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
