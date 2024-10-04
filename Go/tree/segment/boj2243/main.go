package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/2243)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N       int
	segTree []int
	maxSize int
)

func main() {
	defer writer.Flush()
	maxSize = 1000001
	h := 0
	for (1 << h) < maxSize {
		h++
	}
	segTree = make([]int, 1<<(h+1))

	N = nextInt()
	for N > 0 {
		st := stringTokenizer(" ")
		a := atoi(st[0])
		if a == 1 {
			b := atoi(st[1])
			fmt.Fprintln(writer, query(1, 1, maxSize, b))
		} else {
			b, c := atoi(st[1]), atoi(st[2])
			update(1, 1, maxSize, b, c)
		}
		N--
	}
}

func query(idx, l, r, tar int) int {
	if l == r {
		update(1, 1, maxSize, l, -1)
		return l
	}
	m := (l + r) / 2

	if tar > segTree[idx*2] {
		return query(2*idx+1, m+1, r, tar-segTree[2*idx])
	} else {
		return query(2*idx, l, m, tar)
	}
}

func update(idx, l, r, tar, val int) int {
	if l == r {
		segTree[idx] += val
		return segTree[idx]
	}
	m := (l + r) / 2
	if tar <= m {
		segTree[idx] = update(2*idx, l, m, tar, val) + segTree[2*idx+1]
	} else {
		segTree[idx] = segTree[2*idx] + update(2*idx+1, m+1, r, tar, val)
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
