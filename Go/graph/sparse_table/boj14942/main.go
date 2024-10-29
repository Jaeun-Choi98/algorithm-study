package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/14942)
*/

type Vertex struct {
	next, weight int
}

var (
	reader    = bufio.NewReader(os.Stdin)
	writer    = bufio.NewWriter(os.Stdout)
	inputData []int
	graph     [][]Vertex
	dist      [][]int64
	parent    [][]int
	V         int
)

func main() {
	defer writer.Flush()
	V := nextInt()
	inputData = make([]int, V+1)
	graph = make([][]Vertex, V+1)
	dist = make([][]int64, V+1)
	parent = make([][]int, V+1)
	for i := 1; i <= V; i++ {
		inputData[i] = nextInt()
		graph[i] = make([]Vertex, 0)
		dist[i] = make([]int64, 21)
		parent[i] = make([]int, 21)
	}
	for i := 0; i < V-1; i++ {
		st := stringTokenizer(" ")
		a, b, c := atoi(st[0]), atoi(st[1]), atoi(st[2])
		graph[a] = append(graph[a], Vertex{b, c})
		graph[b] = append(graph[b], Vertex{a, c})
	}

	dfs(1, 1)
	parent[1][0] = 1
	for n := 1; n <= 20; n++ {
		for v := 1; v <= V; v++ {
			parent[v][n] = parent[parent[v][n-1]][n-1]
			dist[v][n] = dist[parent[v][n-1]][n-1] + dist[v][n-1]
		}
	}

	for i := 1; i <= V; i++ {
		cur, cost := i, inputData[i]
		for n := 20; n >= 0; n-- {
			if int64(cost) < dist[cur][n] {
				continue
			}
			cost -= int(dist[cur][n])
			cur = parent[cur][n]
			if cur == 1 {
				break
			}
		}
		fmt.Fprintln(writer, cur)
	}
}

func dfs(idx, p int) {
	for _, v := range graph[idx] {
		if v.next == p {
			continue
		}
		parent[v.next][0] = idx
		dist[v.next][0] = int64(v.weight)
		dfs(v.next, idx)
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
