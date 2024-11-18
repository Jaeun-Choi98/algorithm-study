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
최소 컷 + 정점 분할(in-out)
[problem](https://www.acmicpc.net/problem/1420)
*/

type Node struct {
	idx       int
	cap, flow int
	// 유령 간선(역간선)
	rev int
}

var (
	reader       = bufio.NewReader(os.Stdin)
	writer       = bufio.NewWriter(os.Stdout)
	N, M         int
	source, sink int
	graph        [][]Node
	leng         int
	input        [][]rune
	INF          int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi(st[0]), atoi(st[1])
	leng = N * M
	INF = 999999999
	graph = make([][]Node, 2*leng)
	input = make([][]rune, N)

	for i := 0; i < leng; i++ {
		graph[i] = make([]Node, 0)
		graph[i+leng] = make([]Node, 0)

		// 정점 분할( 내부 간선 연결 )
		// in
		graph[i] = append(graph[i], Node{i + leng, 1, 0, len(graph[i+leng])})
		// out
		graph[i+leng] = append(graph[i+leng], Node{i, 0, 0, len(graph[i]) - 1})
	}

	for i := 0; i < N; i++ {
		input[i] = nextLineToRune()
		for j := 0; j < M; j++ {
			if input[i][j] == 'K' {
				source = i*M + j
			}
			if input[i][j] == 'H' {
				sink = i*M + j
			}
			uIn, uOut := i*M+j, i*M+j+leng
			if j != M-1 && input[i][j] != '#' && input[i][j+1] != '#' {
				vIn, vOut := i*M+j+1, i*M+j+1+leng
				graph[uOut] = append(graph[uOut], Node{vIn, INF, 0, len(graph[vIn])})
				graph[vIn] = append(graph[vIn], Node{uOut, 0, 0, len(graph[uOut]) - 1})

				graph[vOut] = append(graph[vOut], Node{uIn, INF, 0, len(graph[uIn])})
				graph[uIn] = append(graph[uIn], Node{vOut, 0, 0, len(graph[vOut]) - 1})
			}
			if i != 0 && input[i][j] != '#' && input[i-1][j] != '#' {
				vIn, vOut := (i-1)*M+j, (i-1)*M+j+leng
				graph[uOut] = append(graph[uOut], Node{vIn, INF, 0, len(graph[vIn])})
				graph[vIn] = append(graph[vIn], Node{uOut, 0, 0, len(graph[uOut]) - 1})

				graph[vOut] = append(graph[vOut], Node{uIn, INF, 0, len(graph[uIn])})
				graph[uIn] = append(graph[uIn], Node{vOut, 0, 0, len(graph[vOut]) - 1})
			}
		}
	}
	ans := maxFlow(source, sink)
	// K와H가 붙어 있을 경우, -1 리턴
	for _, node := range graph[source] {
		if node.idx == sink+leng {
			fmt.Fprintln(writer, -1)
			return
		}
	}
	fmt.Fprintln(writer, ans)
}

func maxFlow(source, sink int) int {
	ret := 0
	for {
		parent := make([]int, 2*leng)
		parentj := make([]int, 2*leng)
		for i := 0; i < 2*leng; i++ {
			parent[i] = -1
			parentj[i] = -1
		}
		que := make([]int, 0)
		parent[source] = source
		que = append(que, source)
		for len(que) > 0 && parent[sink] == -1 {
			cur := que[0]
			que = que[1:]
			for i, nextNode := range graph[cur] {
				if parent[nextNode.idx] != -1 {
					continue
				}
				if nextNode.cap-nextNode.flow > 0 {
					que = append(que, nextNode.idx)
					parent[nextNode.idx] = cur
					parentj[nextNode.idx] = i
				}
			}
		}

		if parent[sink] == -1 {
			break
		}

		minFlow := math.MaxInt32
		for i := sink; i != source+leng; i = parent[i] {
			cur, j := parent[i], parentj[i]
			minFlow = min(minFlow, graph[cur][j].cap-graph[cur][j].flow)
		}
		for i := sink; i != source+leng; i = parent[i] {
			cur, j := parent[i], parentj[i]
			graph[cur][j].flow += minFlow
			graph[graph[cur][j].idx][graph[cur][j].rev].flow -= minFlow
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

func nextLineToRune() []rune {
	line, _ := reader.ReadString('\n')
	return []rune(strings.TrimSpace(line))
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
