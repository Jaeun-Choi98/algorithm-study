package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	graph  [][]int
	v      int
)

func main() {
	defer writer.Flush()
	v = nextInt()
	graph = make([][]int, v+1)
	for i := 1; i <= v; i++ {
		graph[i] = make([]int, 0)
	}
	for i := 0; i < v-1; i++ {
		st := stringTokenizer(" ")
		s, e := atoi(st[0]), atoi(st[1])
		graph[s] = append(graph[s], e)
		graph[e] = append(graph[e], s)
	}
	r1, r2 := dfs(1, 1)
	fmt.Fprintln(writer, min(r1, r2))
}

func dfs(cur, parent int) (int, int) {
	// ret1: 얼리 어답터(현재 정점/노드), ret2: 얼리 어답터x
	ret1, ret2 := 1, 0
	if len(graph[cur]) == 0 {
		return ret2, ret2
	}
	for _, next := range graph[cur] {
		if next == parent {
			continue
		}
		r1, r2 := dfs(next, cur)
		ret1 += min(r1, r2)
		ret2 += r1
	}
	return ret1, ret2
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
