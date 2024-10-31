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
[problem](https://www.acmicpc.net/problem/13334)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
)

type Item struct {
	Val int
	Idx int
}

type PriorityQueue []*Item

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].Val < pq[j].Val
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

func main() {
	defer writer.Flush()
	n := nextInt()
	data := make([][]int, n)
	for i := 0; i < n; i++ {
		data[i] = make([]int, 2)
		st := stringTokenizer(" ")
		if atoi(st[0]) < atoi(st[1]) {
			data[i][0], data[i][1] = atoi(st[0]), atoi(st[1])
		} else {
			data[i][0], data[i][1] = atoi(st[1]), atoi(st[0])
		}
	}
	sort.Slice(data, func(i, j int) bool { return data[i][1] < data[j][1] })

	pq := &PriorityQueue{}
	heap.Init(pq)
	d := nextInt()
	var cnt, ans int
	for i := 0; i < n; i++ {
		l, r := data[i][0], data[i][1]
		dl := r - d
		for pq.Len() > 0 {
			item := heap.Pop(pq).(*Item)
			if item.Val >= dl {
				heap.Push(pq, item)
				break
			}
			cnt--
		}
		if l >= dl {
			heap.Push(pq, &Item{Val: l})
			cnt++
		}
		ans = max(ans, cnt)
	}
	fmt.Fprintln(writer, ans)
}

func max(a, b int) int {
	if a > b {
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

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
