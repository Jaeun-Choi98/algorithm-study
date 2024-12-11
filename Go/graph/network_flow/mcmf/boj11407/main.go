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
[problem](https://www.acmicpc.net/problem/11407)
*/

var (
	reader    = bufio.NewReader(os.Stdin)
	writer    = bufio.NewWriter(os.Stdout)
	N, M      int
	graph     [][]int
	cap, flow [][]int
	dists     [][]int
	size      int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi(st[0]), atoi(st[1])
	size = N + M + 3
	source, sink := size-2, size-1

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
	for i := 1; i <= M; i++ {
		graph[i] = append(graph[i], source)
		graph[source] = append(graph[source], i)
		cap[source][i] = atoi(st[i-1])
	}

	for i := 1; i <= M; i++ {
		st = stringTokenizer(" ")
		for j := 1; j <= N; j++ {
			graph[i] = append(graph[i], M+j)
			graph[M+j] = append(graph[M+j], i)
			cap[i][M+j] = atoi(st[j-1])
		}
	}

	for i := 1; i <= M; i++ {
		st = stringTokenizer(" ")
		for j := 1; j <= N; j++ {
			dists[i][M+j] = atoi(st[j-1])
			dists[M+j][i] = -dists[i][M+j]
		}
	}
	ans1, ans2 := MCMF(source, sink)
	fmt.Fprintf(writer, "%d\n%d\n", ans1, ans2)
}

func MCMF(source, sink int) (int, int) {
	retCnt, retDist := 0, 0
	for {
		que := make([]int, 0)
		inQue := make([]bool, size)
		d := make([]int, size)
		parent := make([]int, size)
		for i := 0; i < sink+1; i++ {
			d[i] = math.MaxInt32
		}

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

		minFlow := math.MaxInt32
		for i := sink; i != source; i = parent[i] {
			minFlow = min(minFlow, cap[parent[i]][i]-flow[parent[i]][i])
		}

		for i := sink; i != source; i = parent[i] {
			retDist += minFlow * dists[parent[i]][i]
			flow[parent[i]][i] += minFlow
			flow[i][parent[i]] -= minFlow
		}

		retCnt += minFlow
	}
	return retCnt, retDist
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
