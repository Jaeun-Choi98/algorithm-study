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
[problem](https://www.acmicpc.net/problem/2316)
*/

var (
	reader    = bufio.NewReader(os.Stdin)
	writer    = bufio.NewWriter(os.Stdout)
	N, P      int
	cap, flow [][]int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, P = atoi(st[0]), atoi(st[1])
	cap, flow = make([][]int, 2*N+1), make([][]int, 2*N+1)
	for i := 0; i <= 2*N; i++ {
		cap[i], flow[i] = make([]int, 2*N+1), make([]int, 2*N+1)
	}

	// 정점분할 in->out(내부 간선)
	for i := 1; i <= N; i++ {
		cap[i][i+N] = 1
	}

	for i := 0; i < P; i++ {
		st = stringTokenizer(" ")
		uIn, vIn := atoi(st[0]), atoi(st[1])
		uOut, vOut := uIn+N, vIn+N
		cap[uOut][vIn] = 2
		cap[vOut][uIn] = 2
	}

	ans := maxFlow(1+N, 2)
	fmt.Fprintln(writer, ans)
}

func maxFlow(source, sink int) int {
	ret := 0
	for {
		que := make([]int, 0)
		parent := make([]int, 2*N+1)
		que = append(que, source)
		parent[source] = source
		for len(que) > 0 && parent[sink] == 0 {
			cur := que[0]
			que = que[1:]
			for next := 1; next <= 2*N; next++ {
				if parent[next] != 0 {
					continue
				}
				if cap[cur][next]-flow[cur][next] > 0 {
					parent[next] = cur
					que = append(que, next)
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
