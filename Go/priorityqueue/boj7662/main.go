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
이중 우선순위 큐
[problem](https://www.acmicpc.net/problem/7662)
*/
var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
)

type priorityqueueMin []int
type priorityqueueMax []int

func (pq priorityqueueMin) Len() int {
	return len(pq)
}

func (pq priorityqueueMin) Less(i, j int) bool {
	return pq[i] < pq[j]
}

func (pq priorityqueueMin) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
}

func (pq *priorityqueueMin) Push(x interface{}) {
	val := x.(int)
	*pq = append(*pq, val)
}

func (pq *priorityqueueMin) Pop() interface{} {
	n := len(*pq)
	old := (*pq)[n-1]
	*pq = (*pq)[:n-1]
	return old
}

func (pq priorityqueueMax) Len() int {
	return len(pq)
}

func (pq priorityqueueMax) Less(i, j int) bool {
	return pq[i] > pq[j]
}

func (pq priorityqueueMax) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
}

func (pq *priorityqueueMax) Push(x interface{}) {
	val := x.(int)
	*pq = append(*pq, val)
}

func (pq *priorityqueueMax) Pop() interface{} {
	n := len(*pq)
	old := (*pq)[n-1]
	*pq = (*pq)[:n-1]
	return old
}

func main() {
	defer writer.Flush()

	tc := nextInt()
	for tc != 0 {
		minPq := make(priorityqueueMin, 0)
		maxPq := make(priorityqueueMax, 0)
		heap.Init(&minPq)
		heap.Init(&maxPq)
		check := make(map[int]int)
		k := nextInt()
		for k != 0 {
			st := stringTokenizer(" ")
			command, data := st[0], atoi(st[1])
			if command == "I" {
				heap.Push(&minPq, data)
				heap.Push(&maxPq, data)
				check[data]++
			} else {
				if data == 1 {
					var buf int
					if len(maxPq) > 0 {
						buf = heap.Pop(&maxPq).(int)
					}
					for check[buf] == 0 && len(maxPq) > 0 {
						buf = heap.Pop(&maxPq).(int)
					}
					if check[buf] > 0 {
						check[buf]--
					}
					if check[buf] == 0 {
						delete(check, buf)
					}
				} else {
					var buf int
					if len(minPq) > 0 {
						buf = heap.Pop(&minPq).(int)
					}
					for check[buf] == 0 && len(minPq) > 0 {
						buf = heap.Pop(&minPq).(int)
					}

					if check[buf] > 0 {
						check[buf]--
					}
					if check[buf] == 0 {
						delete(check, buf)
					}
				}
			}
			k--
		}
		if len(check) == 0 {
			fmt.Fprintf(writer, "EMPTY\n")
		} else {
			max := heap.Pop(&maxPq).(int)
			for check[max] == 0 {
				max = heap.Pop(&maxPq).(int)
			}
			min := heap.Pop(&minPq).(int)
			for check[min] == 0 {
				min = heap.Pop(&minPq).(int)
			}
			fmt.Fprintf(writer, "%d %d", max, min)
			fmt.Fprintf(writer, "\n")
		}
		tc--
	}
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
