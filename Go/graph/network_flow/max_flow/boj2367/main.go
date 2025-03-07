package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

type AdjNode struct {
	to   int
	flow int
	cap  int
	revJ int
}

var (
	writer  = bufio.NewWriter(os.Stdout)
	reader  = bufio.NewReader(os.Stdin)
	N, K, D int
	graph   [][]*AdjNode
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, K, D = atoi32(st[0]), atoi32(st[1]), atoi32(st[2])
	// 0(source), 1(sink), 1+n(1<=n<=N, person), 1+N+d(1<=d<=D, cook)
	size := 2 + N + D
	graph = make([][]*AdjNode, size)
	for i := 0; i < size; i++ {
		graph[i] = make([]*AdjNode, 0)
	}

	st = stringTokenizer(" ")
	for i := 0; i < D; i++ {
		graph[2+N+i] = append(graph[2+N+i], &AdjNode{to: 1, flow: 0, cap: atoi32(st[i]), revJ: len(graph[1])})
		graph[1] = append(graph[1], &AdjNode{to: 2 + N + i, flow: 0, cap: 0, revJ: len(graph[2+N+i]) - 1})
	}

	for i := 0; i < N; i++ {
		st = stringTokenizer(" ")
		graph[0] = append(graph[0], &AdjNode{to: 2 + i, flow: 0, cap: K, revJ: len(graph[2+i])})
		graph[2+i] = append(graph[2+i], &AdjNode{to: 0, flow: 0, cap: 0, revJ: len(graph[0]) - 1})
		Z := atoi32(st[0])
		for z := 1; z <= Z; z++ {
			cookNum := 1 + N + atoi32(st[z])
			graph[2+i] = append(graph[2+i], &AdjNode{to: cookNum, flow: 0, cap: 1, revJ: len(graph[cookNum])})
			graph[cookNum] = append(graph[cookNum], &AdjNode{to: 2 + i, flow: 0, cap: 0, revJ: len(graph[2+i]) - 1})
		}
	}

	ans := MaxFlow(0, 1)
	fmt.Fprintln(writer, ans)
}

func MaxFlow(source, sink int) int {
	ret := 0
	for {
		parentVertex := make([]int, 2+N+D)
		parentIdx := make([]int, 2+N+D)
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
					parentIdx[nextNode.to] = idx
				}
			}
		}

		if parentVertex[sink] == 0 {
			break
		}

		minFlow := math.MaxInt32
		for i := sink; i != source; i = parentVertex[i] {
			pv, pi := parentVertex[i], parentIdx[i]
			minFlow = min(minFlow, graph[pv][pi].cap-graph[pv][pi].flow)
		}
		ret += minFlow

		for i := sink; i != source; i = parentVertex[i] {
			pv, pi := parentVertex[i], parentIdx[i]
			graph[pv][pi].flow += minFlow
			graph[i][graph[pv][pi].revJ].flow -= minFlow
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

func atoi32(str string) int {
	ret, _ := strconv.ParseInt(str, 10, 32)
	return int(ret)
}
