package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/11377)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N, M, K int
	match   []int
	graph   [][]int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M, K = atoi(st[0]), atoi(st[1]), atoi(st[2])
	graph = make([][]int, N+1)
	match = make([]int, M+1)

	for i := 0; i <= N; i++ {
		graph[i] = make([]int, 0)
	}
	for i := 1; i <= N; i++ {
		st = stringTokenizer(" ")
		cnt := atoi(st[0])
		for j := 1; j <= cnt; j++ {
			graph[i] = append(graph[i], atoi(st[j]))
		}
	}

	ans := 0
	for i := 1; i <= N; i++ {
		visit := make([]bool, N+1)
		if dfs(i, &visit) {
			ans++
		}
	}

	k := 0
	for i := 1; i <= N; i++ {
		if k < K {
			visit := make([]bool, N+1)
			if dfs(i, &visit) {
				ans++
				k++
			}
		}
	}
	fmt.Fprintln(writer, ans)
}

func dfs(u int, visit *[]bool) bool {
	if (*visit)[u] {
		return false
	}
	(*visit)[u] = true
	for _, v := range graph[u] {
		if match[v] == 0 || dfs(match[v], visit) {
			match[v] = u
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
