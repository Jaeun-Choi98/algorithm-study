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
트리의 지름
[problem](https://www.acmicpc.net/problem/1167)
*/

var (
	reader   = bufio.NewReader(os.Stdin)
	writer   = bufio.NewWriter(os.Stdout)
	graph    [][]Info
	visited  []bool
	vertices int
	res, buf int
)

type Info struct {
	vertax int
	weight int
}

func main() {
	defer writer.Flush()
	vertices = nextInt()
	graph = make([][]Info, vertices)

	var st []string
	var from int
	for i := 0; i < vertices; i++ {
		st = StringTokenizer(" ")
		from = atoi(st[0]) - 1
		graph[from] = make([]Info, 0)
		for j := 1; j < len(st)-1; j += 2 {
			graph[from] = append(graph[from], Info{atoi(st[j]) - 1, atoi(st[j+1])})
		}
	}

	visited = make([]bool, vertices)
	res = math.MinInt32
	visited[0] = true
	dfs(0, 0)

	visited = make([]bool, vertices)
	res = math.MinInt32
	visited[buf] = true
	dfs(buf, 0)
	fmt.Fprint(writer, res)
}

func dfs(vCur, wCur int) {
	for _, info := range graph[vCur] {
		if visited[info.vertax] {
			continue
		}
		visited[info.vertax] = true
		if res < wCur+info.weight {
			res = wCur + info.weight
			buf = info.vertax
		}
		dfs(info.vertax, wCur+info.weight)
	}
}

func StringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
