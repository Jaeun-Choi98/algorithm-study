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
[problem](https://www.acmicpc.net/problem/13907)
*/

type Item struct {
	vertex int
	cost   int
	cnt    int
}

type PriorityQueue []Item

func (pq PriorityQueue) Len() int { return len(pq) }

func (pq PriorityQueue) Less(i, j int) bool { return pq[i].cost < pq[j].cost }

func (pq PriorityQueue) Swap(i, j int) { pq[i], pq[j] = pq[j], pq[i] }

func (pq *PriorityQueue) Push(x interface{}) { *pq = append(*pq, x.(Item)) }

func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	x := old[n-1]
	*pq = old[0 : n-1]
	return x
}

type Node struct {
	next   int
	weight int
}

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N, M, K int
	S, E    int
	graph   [][]Node
	d       [][]int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M, K = atoi(st[0]), atoi(st[1]), atoi(st[2])
	st = stringTokenizer(" ")
	S, E = atoi(st[0])-1, atoi(st[1])-1

	graph = make([][]Node, N)
	d = make([][]int, N)
	for i := 0; i < N; i++ {
		graph[i] = make([]Node, 0)
		d[i] = make([]int, N)
		for j := 0; j < N; j++ {
			d[i][j] = 30000001
		}
	}

	for i := 0; i < M; i++ {
		st = stringTokenizer(" ")
		a, b, c := atoi(st[0])-1, atoi(st[1])-1, atoi(st[2])
		graph[a] = append(graph[a], Node{b, c})
		graph[b] = append(graph[b], Node{a, c})
	}

	dijk()

	for i := 0; i <= K; i++ {
		tax := 0
		if i != 0 {
			tax = nextInt()
		}
		ans := math.MaxInt32
		for i := 0; i < N; i++ {
			d[E][i] += i * tax
			ans = min(ans, d[E][i])
		}
		fmt.Fprintln(writer, ans)
	}
}

func min(a, b int) int {
	if a < b {
		return a
	} else {
		return b
	}
}

func dijk() {
	pq := &PriorityQueue{}
	heap.Init(pq)
	d[S][0] = 0
	heap.Push(pq, Item{S, 0, 0})
	for pq.Len() > 0 {
		item := heap.Pop(pq).(Item)
		cur, cost, cnt := item.vertex, item.cost, item.cnt
		if cnt >= N || d[cur][cnt] < cost {
			continue
		}
		for _, node := range graph[cur] {
			nextWeight := node.weight
			nextNode := node.next
			nextCost := nextWeight + cost
			if cnt+1 < N && d[nextNode][cnt+1] > nextCost {
				d[nextNode][cnt+1] = nextCost
				heap.Push(pq, Item{nextNode, nextCost, cnt + 1})
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
