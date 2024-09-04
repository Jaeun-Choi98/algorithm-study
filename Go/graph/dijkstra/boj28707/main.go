package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
)

/*
배열 자체를 노드로 추상화하여 풀 수 있음...
[problem](https://www.acmicpc.net/problem/28707)
*/

type Node struct {
	id    int
	cost  int
	index int
}

type PriorityQueue []*Node

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].cost > pq[j].cost
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
	pq[i].index = i
	pq[j].index = j
}

func (pq *PriorityQueue) Push(x interface{}) {
	node := x.(*Node)
	node.index = len(*pq)
	*pq = append(*pq, node)
}

func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	item := old[n-1]
	item.index = -1
	*pq = old[0 : n-1]
	return item
}

var (
	reader   = bufio.NewReader(os.Stdin)
	writer   = bufio.NewWriter(os.Stdout)
	d        map[int]int
	N, M     int
	startArr []int
	swapData [][]int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	startArr = make([]int, N)
	st := stringTokenizer(" ")
	// numToArr 나 arrToNum를 사용할 때 데이터가 10인 경우는 처리하지 못함.
	// 입력 데이터의 값의 대소 관계를 변경하지 않는 선에서는 데이터를 변경해도 괜찮음.
	for i := 0; i < N; i++ {
		startArr[i] = atoi(st[i]) - 1
	}
	M = nextInt()
	swapData = make([][]int, M)
	for i := 0; i < M; i++ {
		st = stringTokenizer(" ")
		swapData[i] = make([]int, 3)
		swapData[i][0], swapData[i][1], swapData[i][2] = atoi(st[0]), atoi(st[1]), atoi(st[2])
	}
	d = make(map[int]int)
	dijk()
	sort.Slice(startArr, func(i, j int) bool { return startArr[i] < startArr[j] })
	if val, exist := d[arrToNum(startArr)]; exist {
		fmt.Fprintln(writer, val)
	} else {
		fmt.Fprintln(writer, -1)
	}
	//fmt.Println(d)
}

func dijk() {
	pq := &PriorityQueue{}
	heap.Init(pq)
	s := arrToNum(startArr)
	d[s] = 0
	heap.Push(pq, &Node{id: s, cost: 0})
	for len(*pq) != 0 {
		node := pq.Pop().(*Node)
		if node.cost > d[node.id] {
			continue
		}
		for i := 0; i < M; i++ {
			l, r, c := swapData[i][0], swapData[i][1], swapData[i][2]
			nextCost := c + node.cost
			nextArrId := nextArr(node.id, l, r)
			if val, exist := d[nextArrId]; exist {
				if val > nextCost {
					d[nextArrId] = nextCost
					heap.Push(pq, &Node{id: nextArrId, cost: nextCost})
				}
			} else {
				d[nextArrId] = nextCost
				heap.Push(pq, &Node{id: nextArrId, cost: nextCost})
			}
		}
	}
}

func nextArr(num, l, r int) int {
	arr := numToArr(num)
	arr[l-1], arr[r-1] = arr[r-1], arr[l-1]
	return arrToNum(arr)
}

func numToArr(num int) []int {
	mul := N - 1
	ret := make([]int, N)
	for i := 0; i < N; i++ {
		ret[i] = num / int(math.Pow10(mul))
		num %= int(math.Pow10(mul))
		mul--
	}
	return ret
}

func arrToNum(arr []int) int {
	ret, mul := 0, N-1
	for i := 0; i < N; i++ {
		ret += arr[i] * int(math.Pow10(mul))
		mul--
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

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
