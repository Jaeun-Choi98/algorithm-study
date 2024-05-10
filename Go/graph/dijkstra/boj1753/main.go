package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

/*
최단경로
[problem](https://www.acmicpc.net/problem/1753)
*/

type Item struct {
	Val      int
	Priority int
	Idx      int
}

type PriorityQueue []*Item

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].Priority > pq[j].Priority
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
	pq[i].Idx = i
	pq[j].Idx = j
}

func (pq *PriorityQueue) Push(x interface{}) {
	item := x.(*Item)
	item.Idx = len(*pq)
	*pq = append(*pq, item)
}

func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	item := old[n-1]
	item.Idx = -1
	*pq = old[0 : n-1]
	return item
}

type info struct {
	to     int
	weight int
}

var (
	reader         = bufio.NewReader(os.Stdin)
	writer         = bufio.NewWriter(os.Stdout)
	graph          [][]info
	d              []int
	vertics, edges int
	start          int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	vertics = atoi(st[0])
	edges = atoi(st[1])
	start = nextInt() - 1
	graph = make([][]info, vertics)
	d = make([]int, vertics)
	for i := 0; i < vertics; i++ {
		graph[i] = make([]info, 0)
		d[i] = math.MaxInt32
	}

	var from, to, w int
	for i := 0; i < edges; i++ {
		st = stringTokenizer(" ")
		from = atoi(st[0]) - 1
		to = atoi(st[1]) - 1
		w = atoi(st[2])
		graph[from] = append(graph[from], info{to, w})
	}

	dijk(start)

	for i := 0; i < vertics; i++ {
		if d[i] == math.MaxInt32 {
			fmt.Fprintln(writer, "INF")
		} else {
			fmt.Fprintln(writer, d[i])
		}
	}
}

func dijk(start int) {
	pq := make(PriorityQueue, 0)
	heap.Init(&pq)
	d[start] = 0
	heap.Push(&pq, &Item{start, 0, -1})
	for len(pq) != 0 {
		item := pq.Pop().(*Item)
		from := item.Val
		w := item.Priority
		if w > d[from] {
			continue
		}
		for _, node := range graph[from] {
			next := node.to
			nextWeight := w + node.weight
			if d[next] > nextWeight {
				d[next] = nextWeight
				heap.Push(&pq, &Item{next, nextWeight, -1})
			}
		}
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

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
