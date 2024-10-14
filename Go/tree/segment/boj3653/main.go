package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/3653)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	n, m    int
	segTree []int
	data    []int
	pos     []int
)

func main() {
	defer writer.Flush()
	tc := nextInt()
	for tc > 0 {
		st := stringTokenizer(" ")
		n, m = atoi(st[0]), atoi(st[1])
		segTree = make([]int, 4*(n+m))
		data = make([]int, n+m)
		pos = make([]int, n+m)
		for i := m; i < n+m; i++ {
			data[i] = 1
			pos[i-m] = i
		}
		Init(1, 0, n+m-1)

		st = stringTokenizer(" ")
		for i := 0; i < m; i++ {
			index := atoi(st[i]) - 1
			fmt.Fprintf(writer, "%d ", Query(1, 0, n+m-1, 0, pos[index]-1))
			Update(1, 0, n+m-1, pos[index], 0)
			Update(1, 0, n+m-1, m-i-1, 1)
			pos[index] = m - i - 1
		}
		fmt.Fprintf(writer, "\n")
		tc--
	}
}

func Query(idx, l, r, tl, tr int) int {
	if r < tl || l > tr {
		return 0
	}
	if tl <= l && r <= tr {
		return segTree[idx]
	}
	m := (l + r) / 2
	ret := Query(2*idx, l, m, tl, tr) + Query(2*idx+1, m+1, r, tl, tr)
	return ret
}

func Update(idx, l, r, tar, val int) int {
	if l == r {
		segTree[idx] = val
		return segTree[idx]
	}
	m := (l + r) / 2
	if tar <= m {
		segTree[idx] = Update(2*idx, l, m, tar, val) + segTree[2*idx+1]
	} else {
		segTree[idx] = segTree[2*idx] + Update(2*idx+1, m+1, r, tar, val)
	}
	return segTree[idx]
}

func Init(idx, l, r int) int {
	if l == r {
		segTree[idx] = data[l]
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

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
