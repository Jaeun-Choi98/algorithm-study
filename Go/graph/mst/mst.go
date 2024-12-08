package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
	"root/priorityqueue"
	"root/unionfind"
	"sort"
	"strconv"
	"strings"
)

type Node struct {
	dest   int
	weight int
}

var (
	data = `3 3
	1 2 1
	2 3 2
	1 3 3
	`
	graph   [][]Node
	visited []bool
	writer  = bufio.NewWriter(os.Stdout)
	reader  = bufio.NewReader(strings.NewReader(data))
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	v, _ := strconv.Atoi(st[0])
	e, _ := strconv.Atoi(st[1])
	graph = make([][]Node, v)
	visited = make([]bool, v)
	for i := 0; i < v; i++ {
		graph[i] = make([]Node, 0)
	}
	for i := 0; i < e; i++ {
		st := stringTokenizer(" ")
		start, _ := strconv.Atoi(st[0])
		end, _ := strconv.Atoi(st[1])
		val, _ := strconv.Atoi(st[2])
		start -= 1
		end -= 1
		// 무방향 그래프
		node := Node{end, val}
		graph[start] = append(graph[start], node)
		node = Node{start, val}
		graph[end] = append(graph[end], node)
	}
	fmt.Fprintln(writer, kruskal())
	fmt.Fprint(writer, prim())
}

// 유니온파인드 알고리즘을 사용
func kruskal() int {
	ret := 0
	size := len(graph)
	union := make([]int, size)
	for i := 0; i < size; i++ {
		union[i] = i
	}

	// 1. 간선 정보 저장
	edges := make([][]int, 0)
	for i := 0; i < size; i++ {
		for _, node := range graph[i] {
			edge := make([]int, 3)
			edge[0] = i
			edge[1] = node.dest
			edge[2] = node.weight
			edges = append(edges, edge)
		}
	}

	// 2. 간선 정보 정렬
	sort.Slice(edges, func(i, j int) bool { return edges[i][2] < edges[j][2] })

	// 3. 간선을 순회하면서 연결되어 있지 않은 노드만 연결
	for _, edge := range edges {
		a := unionfind.Find(union, edge[0])
		b := unionfind.Find(union, edge[1])
		if a == b {
			continue
		}
		ret += edge[2]
		unionfind.Union(union, a, b)
	}

	return ret
}

// 우선순위 큐를 사용
func prim() int {
	ret := 0
	pq := make(priorityqueue.PriorityQueue, 0)
	heap.Init(&pq)
	/*
		1. 시작 정점을 우선순위 큐에 넣고, 해당 정점에 연결된 모든 (정점, 간선) 정보를 우선순위 큐에 넣음
		2. 우선순위 큐에서 하나 추출(최소or최대)하고, 해당 정점이 방문한 정점이라면 건너뛰고, 그렇지 않다면 1번 작업을 실행
		3. 모든 정점을 방문할 때까지 2번 반복
	*/
	heap.Push(&pq, priorityqueue.Item{0, 0})
	for len(pq) != 0 {
		item := heap.Pop(&pq).(priorityqueue.Item)
		cur := item.Val
		val := item.Priority
		if visited[cur] {
			continue
		}
		visited[cur] = true
		ret += val
		for _, node := range graph[cur] {
			heap.Push(&pq, priorityqueue.Item{node.dest, node.weight})
		}
	}
	return ret
}

func stringTokenizer(token string) []string {
	input, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(input), token)
}
