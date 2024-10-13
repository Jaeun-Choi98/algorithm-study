package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/6549)
*/

type Node struct {
	idx int
	h   int64
}

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	n       int64
	data    []Node
	h       int
	segTree []Node
)

func main() {
	defer writer.Flush()
	for true {
		st := stringTokenizer(" ")
		n = atoi64(st[0])
		if n == 0 {
			break
		}
		data = make([]Node, n)
		for i := 1; i <= int(n); i++ {
			data[i-1] = Node{idx: i - 1, h: atoi64(st[i])}
		}
		h = 0
		for (1 << h) < n {
			h++
		}
		segTree = make([]Node, 1<<(h+1))
		Init(1, 0, int(n-1))
		fmt.Fprintln(writer, search(0, int(n-1)))
	}
}

func search(l, r int) int64 {
	node := Query(1, 0, int(n-1), l, r)
	ret := int64((r - l + 1)) * node.h
	if l <= node.idx-1 {
		lr := search(l, node.idx-1)
		ret = max(ret, lr)
	}
	if node.idx+1 <= r {
		rr := search(node.idx+1, r)
		ret = max(ret, rr)
	}

	return ret
}

func Query(idx, l, r, tl, tr int) Node {
	if r < tl || l > tr {
		return Node{idx: -1, h: 1000000001}
	}
	if tl <= l && r <= tr {
		return segTree[idx]
	}
	m := (l + r) / 2
	ls := Query(2*idx, l, m, tl, tr)
	rs := Query(2*idx+1, m+1, r, tl, tr)
	var nidx int
	var nh int64
	if ls.h < rs.h {
		nidx, nh = ls.idx, ls.h
	} else {
		nidx, nh = rs.idx, rs.h
	}
	return Node{idx: nidx, h: nh}
}

func Init(idx, l, r int) Node {
	if l == r {
		segTree[idx] = data[l]
		return segTree[idx]
	}
	m := (l + r) / 2
	ls := Init(2*idx, l, m)
	rs := Init(2*idx+1, m+1, r)
	var nidx int
	var nh int64
	if ls.h < rs.h {
		nidx, nh = ls.idx, ls.h
	} else {
		nidx, nh = rs.idx, rs.h
	}
	segTree[idx] = Node{idx: nidx, h: nh}
	return segTree[idx]
}

func max(a, b int64) int64 {
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

func atoi64(str string) int64 {
	ret, _ := strconv.ParseInt(str, 10, 64)
	return ret
}
