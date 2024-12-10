package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/11405)
*/

var (
	reader       = bufio.NewReader(os.Stdin)
	writer       = bufio.NewWriter(os.Stdout)
	N, M         int
	graph        [][]int
	cap, flow    [][]int
	dists        [][]int
	size         int
	source, sink int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi(st[0]), atoi(st[1])
	size = M + N + 3
	source, sink = size-2, size-1
	graph = make([][]int, size)
	cap = make([][]int, size)
	flow = make([][]int, size)
	dists = make([][]int, size)

	for i := 0; i < size; i++ {
		graph[i] = make([]int, 0)
		cap[i] = make([]int, size)
		flow[i] = make([]int, size)
		dists[i] = make([]int, size)
	}

	st = stringTokenizer(" ")
	for i := 1; i <= N; i++ {
		graph[M+i] = append(graph[M+i], sink)
		graph[sink] = append(graph[sink], M+i)
		cap[M+i][sink] = atoi(st[i-1])
	}

	st = stringTokenizer(" ")
	for i := 0; i < M; i++ {
		graph[source] = append(graph[source], 1+i)
		graph[1+i] = append(graph[1+i], source)
		cap[source][1+i] = atoi(st[i])
	}

	for i := 0; i < M; i++ {
		st = stringTokenizer(" ")
		for j := 0; j < N; j++ {
			graph[1+i] = append(graph[1+i], M+j+1)
			graph[M+j+1] = append(graph[M+j+1], 1+i)
			dists[1+i][M+j+1] = atoi(st[j])
			dists[M+j+1][1+i] = -dists[1+i][M+j+1]
			cap[1+i][M+j+1] = 100
		}
	}

	ans := mcmf()
	fmt.Fprintln(writer, ans)
}

func mcmf() int {
	ret := 0

	for {
		parent := make([]int, size)
		inQue := make([]bool, size)
		d := make([]int, size)
		for i := 0; i < size; i++ {
			d[i] = 1001
		}
		que := make([]int, 0)
		que = append(que, source)
		parent[source] = source
		d[source] = 0
		inQue[source] = true

		for len(que) > 0 {
			cur := que[0]
			que = que[1:]
			inQue[cur] = false
			for _, next := range graph[cur] {
				if cap[cur][next]-flow[cur][next] > 0 && d[next] > d[cur]+dists[cur][next] {
					d[next] = d[cur] + dists[cur][next]
					parent[next] = cur
					if !inQue[next] {
						que = append(que, next)
						inQue[next] = true
					}
				}
			}
		}

		if parent[sink] == 0 {
			break
		}

		minflow := 101
		for i := sink; i != source; i = parent[i] {
			minflow = min(minflow, cap[parent[i]][i]-flow[parent[i]][i])
		}

		for i := sink; i != source; i = parent[i] {
			ret += minflow * dists[parent[i]][i]
			flow[parent[i]][i] += minflow
			flow[i][parent[i]] -= minflow
		}
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
