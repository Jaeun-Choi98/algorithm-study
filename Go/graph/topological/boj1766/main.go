package main

/*
문제집 - 골드2
[problem](https://www.acmicpc.net/problem/1766)
*/

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type PriorityQueue []int

var (
	reader   = bufio.NewReader(os.Stdin)
	writer   = bufio.NewWriter(os.Stdout)
	vertices int
	graph    [][]int
	inDegree []int
)

func main() {
	defer writer.Flush()
	st := StringTokenizer(" ")
	vertices, _ = strconv.Atoi(st[0])
	m, _ := strconv.Atoi(st[1])

	inDegree = make([]int, vertices+1)
	graph = make([][]int, vertices+1)
	for i := 1; i <= vertices; i++ {
		graph[i] = make([]int, 0)
	}

	var from, to int
	for i := 0; i < m; i++ {
		st = StringTokenizer(" ")
		from, _ = strconv.Atoi(st[0])
		to, _ = strconv.Atoi(st[1])
		graph[from] = append(graph[from], to)
		inDegree[to]++
	}

	res := Topological()
	fmt.Fprintln(writer, res)
}

func Topological() []int {
	ret := make([]int, 0)
	pq := &PriorityQueue{}
	heap.Init(pq)
	for i := 1; i <= vertices; i++ {
		if inDegree[i] == 0 {
			heap.Push(pq, i)
		}
	}

	var cur int
	for len(*pq) > 0 {
		cur = heap.Pop(pq).(int)
		ret = append(ret, cur)
		for _, to := range graph[cur] {
			inDegree[to]--
			if inDegree[to] == 0 {
				heap.Push(pq, to)
			}
		}
	}
	return ret
}

func StringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i] < pq[j]
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
}

func (pq *PriorityQueue) Push(x interface{}) {
	*pq = append(*pq, x.(int))
}

func (pq *PriorityQueue) Pop() interface{} {
	len := len(*pq)
	ret := (*pq)[len-1]
	*pq = (*pq)[0 : len-1]
	return ret
}
