package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/2188)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	u, v    int
	graph   [][]int
	visit   []bool
	matches []int
	ans     int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	u, v = atoi(st[0]), atoi(st[1])
	graph = make([][]int, u+1)
	matches = make([]int, v+1)
	for i := 0; i < u; i++ {
		st = stringTokenizer(" ")
		cnt := atoi(st[0])
		graph[i+1] = make([]int, 0)
		for j := 1; j <= cnt; j++ {
			graph[i+1] = append(graph[i+1], atoi(st[j]))
		}
	}
	ans = 0
	bipartiteSearch()
	fmt.Fprintln(writer, ans)
}

func bipartiteSearch() {
	for i := 1; i <= u; i++ {
		visit = make([]bool, u+1)
		if dfs(i) {
			ans++
		}
	}
}

func dfs(uNode int) bool {
	if visit[uNode] {
		return false
	}
	visit[uNode] = true
	for _, vNode := range graph[uNode] {
		if matches[vNode] == 0 || dfs(matches[vNode]) {
			matches[vNode] = uNode
			return true
		}
	}
	return false
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
