package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/3295)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	graph   [][]int
	matches []int
)

func main() {
	defer writer.Flush()
	tc := nextInt()
	for tc > 0 {
		st := stringTokenizer(" ")
		n, m := atoi(st[0]), atoi(st[1])
		graph = make([][]int, n)
		matches = make([]int, n)
		for i := 0; i < n; i++ {
			graph[i] = make([]int, 0)
			matches[i] = -1
		}

		for i := 0; i < m; i++ {
			st = stringTokenizer(" ")
			u, v := atoi(st[0]), atoi(st[1])
			graph[u] = append(graph[u], v)
		}

		ans := 0
		for i := 0; i < n; i++ {
			visit := make([]bool, n)
			if dfs(i, visit) {
				ans++
			}
		}
		fmt.Fprintln(writer, ans)
		tc--
	}
}

func dfs(node int, visit []bool) bool {
	if visit[node] {
		return false
	}
	visit[node] = true
	for _, v := range graph[node] {
		if matches[v] == -1 || dfs(matches[v], visit) {
			matches[v] = node
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

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
