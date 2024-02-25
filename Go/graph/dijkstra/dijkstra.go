package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"math"
	"os"
	"root/priorityqueue"
	"strconv"
	"strings"
)

type Node struct {
	dest   int
	weight int
}

var (
	data = `4 6
	1 2 3
	2 3 3
	3 4 1
	1 3 5
	2 4 5
	1 4 4
	`
	graph  [][]Node
	d      []int
	writer = bufio.NewWriter(os.Stdout)
	reader = bufio.NewReader(strings.NewReader(data))
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	v, _ := strconv.Atoi(st[0])
	e, _ := strconv.Atoi(st[1])
	graph = make([][]Node, v)
	d = make([]int, v)
	for i := 0; i < v; i++ {
		graph[i] = make([]Node, 0)
		d[i] = math.MaxInt32
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
	dijkstra()
	fmt.Fprintln(writer, d)
}

func dijkstra() {
	pq := make(priorityqueue.PriorityQueue, 0)
	heap.Init(&pq)

	// 시작 정점: 0 번째 인덱스
	d[0] = 0
	heap.Push(&pq, &priorityqueue.Item{0, 0, -1})
	for len(pq) != 0 {
		item := pq.Pop().(*priorityqueue.Item)
		cur := item.Val
		dis := item.Priority
		if dis > d[cur] {
			continue
		}
		for _, node := range graph[cur] {
			next := node.dest
			nextDis := dis + node.weight
			if d[next] > nextDis {
				d[next] = nextDis
				heap.Push(&pq, &priorityqueue.Item{next, nextDis, -1})
			}
		}
	}

}

func stringTokenizer(token string) []string {
	input, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(input), token)
}
