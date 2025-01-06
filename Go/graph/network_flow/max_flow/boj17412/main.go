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
[problem](https://www.acmicpc.net/problem/17412)
rev를 revI, revJ로 나누는 것이 논리적으로 더 맞는 표현이지만,
증가 경로를 탐색하는 과정에서 revI를 구할 수 있기 때문에 revJ(rev)필드 하나만 선언.
*/
type Node struct {
	to        int
	flow, cap int
	rev       int
}

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N, P   int
	graph  [][]Node
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, P = atoi32(st[0]), atoi32(st[1])
	graph = make([][]Node, N+1)
	for i := 0; i <= N; i++ {
		graph[i] = make([]Node, 0)
	}
	for i := 0; i < P; i++ {
		st = stringTokenizer(" ")
		u, v := atoi32(st[0]), atoi32(st[1])
		graph[u] = append(graph[u], Node{to: v, flow: 0, cap: 1, rev: len(graph[v])})
		graph[v] = append(graph[v], Node{to: u, flow: 0, cap: 0, rev: len(graph[u]) - 1})
	}
	ans := MaxFlow(1, 2)
	fmt.Fprintln(writer, ans)
}

func MaxFlow(source, sink int) int {
	ret := 0
	for {
		parentVertex := make([]int, N+1)
		parentIndex := make([]int, N+1)
		que := make([]int, 0)
		que = append(que, source)
		parentVertex[source] = source
		for len(que) > 0 && parentVertex[sink] == 0 {
			cur := que[0]
			que = que[1:]
			for idx, nextNode := range graph[cur] {
				if parentVertex[nextNode.to] != 0 {
					continue
				}
				if nextNode.cap-nextNode.flow > 0 {
					que = append(que, nextNode.to)
					parentVertex[nextNode.to] = cur
					parentIndex[nextNode.to] = idx
				}
			}
		}

		if parentVertex[sink] == 0 {
			break
		}

		minFlow := math.MaxInt32
		for i := sink; i != source; i = parentVertex[i] {
			pv, pi := parentVertex[i], parentIndex[i]
			minFlow = min(minFlow, graph[pv][pi].cap-graph[pv][pi].flow)
		}

		for i := sink; i != source; i = parentVertex[i] {
			pv, pi := parentVertex[i], parentIndex[i]
			graph[pv][pi].flow += minFlow
			graph[i][graph[pv][pi].rev].flow -= minFlow
		}

		ret++
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

func atoi32(str string) int {
	ret, _ := strconv.ParseInt(str, 10, 32)
	return int(ret)
}
