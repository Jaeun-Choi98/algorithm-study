package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/11266)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	ids    []int
	id     int
	graph  [][]int
	V, E   int
	cnt    int
	ans    []bool
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	V, E = atoi(st[0]), atoi(st[1])
	graph = make([][]int, V+1)
	ids = make([]int, V+1)
	ans = make([]bool, V+1)
	for i := 0; i <= V; i++ {
		graph[i] = make([]int, 0)
	}
	for i := 0; i < E; i++ {
		st = stringTokenizer(" ")
		s, e := atoi(st[0]), atoi(st[1])
		graph[s] = append(graph[s], e)
		graph[e] = append(graph[e], s)
	}
	id = 1
	cnt = 0
	for i := 1; i <= V; i++ {
		if ids[i] != 0 {
			continue
		}
		search(i, true)
	}
	fmt.Fprintln(writer, cnt)
	for i := 1; i <= V; i++ {
		if ans[i] {
			fmt.Fprintf(writer, "%d ", i)
		}
	}
}

func search(idx int, root bool) int {
	ids[idx] = id
	id++
	ret := ids[idx]
	degree := 0
	for _, next := range graph[idx] {
		// 이미 방문한 정점이면서(Backword Edge가 존재하면서), 상위 정점이라면 상위 정점을 반환.
		if ids[next] != 0 {
			ret = min(ret, ids[next])
			continue
		}
		subTreeRet := search(next, false)
		if !root && subTreeRet >= ids[idx] && !ans[idx] {
			ans[idx] = true
			cnt++
		}
		degree++
		ret = min(ret, subTreeRet)
	}

	if root && degree > 1 {
		ans[idx] = true
		cnt++
	}
	return ret
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
