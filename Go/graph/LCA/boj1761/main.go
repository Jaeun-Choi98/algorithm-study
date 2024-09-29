package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/1761)
*/

type Node struct {
	val   int
	index int
}

var (
	reader    = bufio.NewReader(os.Stdin)
	writer    = bufio.NewWriter(os.Stdout)
	N         int
	graph     [][]Node
	parent    [][]int
	values    [][]int
	height    []int
	maxHeight int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	graph = make([][]Node, N)
	height = make([]int, N)
	parent = make([][]int, N)
	values = make([][]int, N)
	maxHeight = 20
	for i := 0; i < N; i++ {
		graph[i] = make([]Node, 0)
		parent[i] = make([]int, maxHeight)
		values[i] = make([]int, maxHeight)
	}
	for i := 0; i < N-1; i++ {
		st := stringTokenizer(" ")
		from, to, val := atoi(st[0])-1, atoi(st[1])-1, atoi(st[2])
		graph[from] = append(graph[from], Node{val: val, index: to})
		graph[to] = append(graph[to], Node{val: val, index: from})
	}
	dfs(0, 0, 0)
	for i := 1; i < maxHeight; i++ {
		for j := 0; j < N; j++ {
			parent[j][i] = parent[parent[j][i-1]][i-1]
			values[j][i] = values[j][i-1] + values[parent[j][i-1]][i-1]
		}
	}

	tc := nextInt()
	for tc > 0 {
		st := stringTokenizer(" ")
		a, b := atoi(st[0])-1, atoi(st[1])-1
		fmt.Fprintln(writer, calc(a, b))
		tc--
	}
}

func calc(a, b int) int {
	ret := 0

	if height[a] != height[b] {
		if height[a] < height[b] {
			a, b = b, a
		}

		dif := height[a] - height[b]
		idx := 0
		for (1 << idx) <= dif {
			if (1<<idx)&dif > 0 {
				ret += values[a][idx]
				a = parent[a][idx]
			}
			idx++
		}
	}

	if a != b {
		for i := maxHeight - 1; i >= 0; i-- {
			if parent[a][i] == parent[b][i] {
				continue
			}
			ret += (values[a][i] + values[b][i])
			a, b = parent[a][i], parent[b][i]
		}

		ret += values[a][0] + values[b][0]
	}

	return ret
}

func dfs(p, c, h int) {
	size := len(graph[c])
	if size == 0 {
		return
	}
	for i := 0; i < size; i++ {
		next := graph[c][i]
		if p == next.index {
			continue
		}
		height[next.index] = h + 1
		parent[next.index][0] = c
		values[next.index][0] = next.val
		dfs(c, next.index, h+1)
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
