package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/11438)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	graph  [][]int
	N      int
	height []int
	parent [][]int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	graph = make([][]int, N)
	parent = make([][]int, N)
	height = make([]int, N)

	for i := 0; i < N; i++ {
		graph[i] = make([]int, 0)
		parent[i] = make([]int, 18)
	}
	for i := 0; i < N-1; i++ {
		st := stringTokenizer(" ")
		from, to := atoi(st[0])-1, atoi(st[1])-1
		graph[from] = append(graph[from], to)
		graph[to] = append(graph[to], from)
	}

	dfsTree(0, 0, 0)

	// 2^j번째 조상 채우기
	for i := 1; i < 18; i++ {
		for j := 0; j < N; j++ {
			parent[j][i] = parent[parent[j][i-1]][i-1]
		}
	}

	tc := nextInt()
	for tc > 0 {
		st := stringTokenizer(" ")
		a, b := atoi(st[0])-1, atoi(st[1])-1
		fmt.Fprintln(writer, find(a, b)+1)
		tc--
	}
}

func find(a, b int) int {
	if height[a] != height[b] {
		if height[a] < height[b] {
			a, b = b, a
		}
		dif := height[a] - height[b]
		idx := 0
		for (1 << idx) <= dif {
			if (dif & (1 << idx)) > 0 {
				a = parent[a][idx]
			}
			idx++
		}
	}

	if a == b {
		return a
	}

	for i := 17; i >= 0; i-- {
		if parent[a][i] == parent[b][i] {
			continue
		}
		a = parent[a][i]
		b = parent[b][i]
	}

	return parent[a][0]
}

func dfsTree(p, c, h int) {
	size := len(graph[c])
	if size == 0 {
		return
	}
	for i := 0; i < size; i++ {
		to := graph[c][i]
		if p == to {
			continue
		}
		height[to] = h + 1
		parent[to][0] = c
		dfsTree(c, to, h+1)
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
