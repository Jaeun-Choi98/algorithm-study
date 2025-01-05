package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/10891)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N, M   int
	graph  [][]int
	ids    []int
	id     int
	inCnt  []int
	outCnt []int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi(st[0]), atoi(st[1])
	graph = make([][]int, N+1)
	ids = make([]int, N+1)
	inCnt = make([]int, N+1)
	outCnt = make([]int, N+1)
	for i := 1; i <= N; i++ {
		graph[i] = make([]int, 0)
	}

	for i := 0; i < M; i++ {
		st = stringTokenizer(" ")
		u, v := atoi(st[0]), atoi(st[1])
		graph[u] = append(graph[u], v)
		graph[v] = append(graph[v], u)
	}
	id = 1
	for i := 1; i <= N; i++ {
		if ids[i] == 0 {
			dfs(i, 0)
		}
	}
	for i := 0; i <= N; i++ {
		if inCnt[i] >= 2 {
			fmt.Fprintln(writer, "Not cactus")
			return
		}
	}
	fmt.Fprintln(writer, "Cactus")
}

func dfs(node, parent int) int {
	if ids[node] > 0 {
		outCnt[node]++
		return 1
	}
	ids[node] = id
	id++
	ret := 0
	for _, next := range graph[node] {
		if next == parent || ids[next] > ids[node] {
			continue
		}
		ret += dfs(next, node)
	}
	inCnt[node] = ret
	return inCnt[node] - outCnt[node]
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
