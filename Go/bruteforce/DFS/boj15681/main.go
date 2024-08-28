package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/15681)
*/

type Vertex struct {
	to     int
	parent int
}

var (
	reader    = bufio.NewReader(os.Stdin)
	writer    = bufio.NewWriter(os.Stdout)
	graph     [][]int
	dp        []int
	vertexCnt int
	root      int
	tc        int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	vertexCnt, root, tc = atoi(st[0]), atoi(st[1]), atoi(st[2])
	graph = make([][]int, vertexCnt+1)
	dp = make([]int, vertexCnt+1)
	for i := 0; i <= vertexCnt; i++ {
		graph[i] = make([]int, 0)
	}
	for i := 0; i < vertexCnt-1; i++ {
		st = stringTokenizer(" ")
		from, to := atoi(st[0]), atoi(st[1])
		graph[from] = append(graph[from], to)
		graph[to] = append(graph[to], from)
	}
	search(0, root)
	for tc > 0 {
		tcRoot := nextInt()
		fmt.Fprintln(writer, dp[tcRoot])
		tc--
	}
}

func search(parent, cur int) int {
	dp[cur] = 1
	for _, next := range graph[cur] {
		if next == parent {
			continue
		}
		dp[cur] += search(cur, next)
	}
	return dp[cur]
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
