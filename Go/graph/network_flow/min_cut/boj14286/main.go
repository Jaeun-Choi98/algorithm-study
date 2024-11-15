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
[problem](https://www.acmicpc.net/problem/14286)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	V, E   int
	cap    [][]int
	flow   [][]int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	V, E = atoi(st[0]), atoi(st[1])
	cap = make([][]int, V+1)
	flow = make([][]int, V+1)
	for i := 0; i <= V; i++ {
		cap[i], flow[i] = make([]int, V+1), make([]int, V+1)
	}
	for i := 0; i < E; i++ {
		st = stringTokenizer(" ")
		a, b, c := atoi(st[0]), atoi(st[1]), atoi(st[2])
		cap[a][b], cap[b][a] = c, c
	}
	st = stringTokenizer(" ")
	source, sink := atoi(st[0]), atoi(st[1])
	ans := MaxFlow(source, sink)
	fmt.Fprintln(writer, ans)
}

func MaxFlow(source, sink int) int {
	ret := 0
	for {
		parent := make([]int, V+1)
		que := make([]int, 0)
		parent[source] = source
		que = append(que, source)
		for len(que) > 0 && parent[sink] == 0 {
			cur := que[0]
			que = que[1:]
			for next := 0; next <= V; next++ {
				if parent[next] != 0 {
					continue
				}
				if cap[cur][next]-flow[cur][next] > 0 {
					que = append(que, next)
					parent[next] = cur
				}
			}
		}
		if parent[sink] == 0 {
			break
		}

		minFlow := math.MaxInt32
		for i := sink; i != source; i = parent[i] {
			minFlow = min(minFlow, cap[parent[i]][i]-flow[parent[i]][i])
		}
		for i := sink; i != source; i = parent[i] {
			flow[parent[i]][i] += minFlow
			flow[i][parent[i]] -= minFlow
		}
		ret += minFlow
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
