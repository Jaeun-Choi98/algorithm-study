package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
최소힙
[problem](https://www.acmicpc.net/problem/1927)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
)

type priorityqueue []int

func (pq priorityqueue) Len() int {
	return len(pq)
}

func (pq priorityqueue) Less(i, j int) bool {
	return pq[i] < pq[j]
}

func (pq priorityqueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
}

func (pq *priorityqueue) Push(x interface{}) {
	val := x.(int)
	*pq = append(*pq, val)
}

func (pq *priorityqueue) Pop() interface{} {
	n := len(*pq)
	old := (*pq)[n-1]
	*pq = (*pq)[:n-1]
	return old
}

func main() {
	defer writer.Flush()
	n := nextInt()

	pq := make(priorityqueue, 0)
	heap.Init(&pq)

	var cmd int
	for i := 0; i < n; i++ {
		cmd = nextInt()
		if cmd == 0 {
			if len(pq) == 0 {
				fmt.Fprintln(writer, 0)
			} else {
				fmt.Fprintln(writer, heap.Pop(&pq).(int))
			}
		} else {
			heap.Push(&pq, cmd)
		}
	}
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
