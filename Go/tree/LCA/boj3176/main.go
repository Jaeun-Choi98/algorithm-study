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
[problem](https://www.acmicpc.net/problem/3176)
*/

type Vertex struct {
	index, weight int
}

var (
	reader     = bufio.NewReader(os.Stdin)
	writer     = bufio.NewWriter(os.Stdout)
	graph      [][]Vertex
	parent     [][]int
	maxW, minW [][]int
	height     []int
	N, K       int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	graph = make([][]Vertex, N)
	height = make([]int, N)
	parent = make([][]int, N)
	maxW = make([][]int, N)
	minW = make([][]int, N)
	for i := 0; i < N; i++ {
		graph[i] = make([]Vertex, 0)
		parent[i] = make([]int, 18)
		maxW[i] = make([]int, 18)
		minW[i] = make([]int, 18)
	}

	for i := 0; i < N-1; i++ {
		st := stringTokenizer(" ")
		a, b, c := atoi(st[0])-1, atoi(st[1])-1, atoi(st[2])
		graph[a] = append(graph[a], Vertex{index: b, weight: c})
		graph[b] = append(graph[b], Vertex{index: a, weight: c})
	}

	dfsTree(0, 0, 0)

	for i := 1; i < 18; i++ {
		for j := 0; j < N; j++ {
			parent[j][i] = parent[parent[j][i-1]][i-1]
			maxW[j][i] = max(maxW[j][i-1], maxW[parent[j][i-1]][i-1])
			minW[j][i] = min(minW[j][i-1], minW[parent[j][i-1]][i-1])
		}
	}

	K = nextInt()
	for K > 0 {
		st := stringTokenizer(" ")
		a, b := atoi(st[0])-1, atoi(st[1])-1
		maxRet, minRet := find(a, b)
		fmt.Fprintln(writer, minRet, maxRet)
		K--
	}

}

func find(a, b int) (int, int) {
	minRet, maxRet := math.MaxInt32, math.MinInt64
	if height[a] != height[b] {
		if height[a] < height[b] {
			a, b = b, a
		}
		dif := height[a] - height[b]
		idx := 0
		for (1 << idx) <= dif {
			if (dif & (1 << idx)) > 0 {
				maxRet = max(maxRet, maxW[a][idx])
				minRet = min(minRet, minW[a][idx])
				a = parent[a][idx]
			}
			idx++
		}
	}

	if a == b {
		return maxRet, minRet
	}

	for i := 17; i >= 0; i-- {
		if parent[a][i] == parent[b][i] {
			continue
		}
		maxRet = max(maxRet, maxW[a][i])
		minRet = min(minRet, minW[a][i])
		maxRet = max(maxRet, maxW[b][i])
		minRet = min(minRet, minW[b][i])
		a, b = parent[a][i], parent[b][i]
	}

	maxRet = max(maxRet, maxW[a][0])
	minRet = min(minRet, minW[a][0])
	maxRet = max(maxRet, maxW[b][0])
	minRet = min(minRet, minW[b][0])

	return maxRet, minRet
}

func dfsTree(p, c, h int) {
	size := len(graph[c])
	if size == 0 {
		return
	}
	for i := 0; i < size; i++ {
		vertex := graph[c][i]
		if vertex.index == p {
			continue
		}
		parent[vertex.index][0] = c
		maxW[vertex.index][0] = vertex.weight
		minW[vertex.index][0] = vertex.weight
		height[vertex.index] = h + 1
		dfsTree(c, vertex.index, h+1)
	}
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
