package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

/*
도시분할계획
[problem](https://www.acmicpc.net/problem/1647)
*/

type Node struct {
	dest   int
	weight int
}

type Item struct {
	Val      int
	Priority int
	Idx      int
}

type PriorityQueue []*Item

var (
	reader           = bufio.NewReader(os.Stdin)
	writer           = bufio.NewWriter(os.Stdout)
	vertics, edgeCnt int
	graph            [][]Node
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	vertics, edgeCnt = atoi(st[0]), atoi(st[1])
	graph = make([][]Node, vertics)
	for i := 0; i < vertics; i++ {
		graph[i] = make([]Node, 0)
	}

	for i := 0; i < edgeCnt; i++ {
		st := stringTokenizer(" ")
		from, to, weight := atoi(st[0]), atoi(st[1]), atoi(st[2])
		from -= 1
		to -= 1
		graph[from] = append(graph[from], Node{to, weight})
		graph[to] = append(graph[to], Node{from, weight})
	}
	ret := Prim()
	sort.Slice(ret, func(i, j int) bool { return ret[i] < ret[j] })
	sum := 0
	for i := 0; i < vertics-2; i++ {
		sum += ret[i]
	}
	fmt.Fprintln(writer, sum)
}

func Prim() []int {
	ret := make([]int, 0)
	pq := make(PriorityQueue, 0)
	visited := make([]bool, vertics)
	heap.Init(&pq)
	heap.Push(&pq, &Item{0, 0, -1})
	for len(pq) > 0 {
		item := heap.Pop(&pq).(*Item)
		cur := item.Val
		w := item.Priority
		if visited[cur] {
			continue
		}
		visited[cur] = true
		//fmt.Println(cur, w, visited)
		if cur != 0 {
			ret = append(ret, w)
		}
		for _, node := range graph[cur] {
			heap.Push(&pq, &Item{node.dest, node.weight, -1})
		}
	}
	return ret
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].Priority < pq[j].Priority
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
