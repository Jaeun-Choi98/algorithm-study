package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/11376)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N, M    int
	adj     [][]int
	matches []int
	visited []bool
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi32(st[0]), atoi32(st[1])
	adj = make([][]int, N+1)
	matches = make([]int, M+1)

	for i := 0; i < N; i++ {
		adj[i+1] = make([]int, 0)
		st = stringTokenizer(" ")
		cnt := atoi32(st[0])
		for j := 1; j <= cnt; j++ {
			adj[i+1] = append(adj[i+1], atoi32(st[j]))
		}
	}

	ans := 0
	for i := 1; i <= N; i++ {
		for j := 0; j < 2; j++ {
			visited = make([]bool, N+1)
			if dfs(i, &visited) {
				ans++
			}
		}
	}
	fmt.Fprintln(writer, ans)
}

func dfs(u int, visited *[]bool) bool {
	if (*visited)[u] {
		return false
	}
	(*visited)[u] = true
	for _, v := range adj[u] {
		if matches[v] == 0 || dfs(matches[v], visited) {
			matches[v] = u
			return true
		}
	}
	return false
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi32(str string) int {
	ret, _ := strconv.ParseInt(str, 10, 32)
	return int(ret)
}
