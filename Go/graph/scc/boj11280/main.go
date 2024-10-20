package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/11280)
*/

var (
	reader     = bufio.NewReader(os.Stdin)
	writer     = bufio.NewWriter(os.Stdout)
	N, M       int
	graph      [][]int
	scc, ids   []int
	id, sccNum int
	stack      *list.List
	complete   []bool
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi(st[0]), atoi(st[1])
	graph = make([][]int, 2*N+1)
	scc = make([]int, 2*N+1)
	ids = make([]int, 2*N+1)
	complete = make([]bool, 2*N+1)

	for i := 0; i <= 2*N; i++ {
		graph[i] = make([]int, 0)
	}

	for i := 0; i < M; i++ {
		st = stringTokenizer(" ")
		a, b := atoi(st[0]), atoi(st[1])
		if a < 0 {
			a = -2*a - 1
		} else {
			a = 2 * a
		}
		if b < 0 {
			b = -2*b - 1
		} else {
			b = 2 * b
		}
		graph[tilde(a)] = append(graph[tilde(a)], b)
		graph[tilde(b)] = append(graph[tilde(b)], a)
	}
	stack = list.New()
	id, sccNum = 1, 1
	for i := 0; i <= 2*N; i++ {
		if ids[i] != 0 {
			continue
		}
		sccFunc(i)
	}

	ans := true
	for i := 1; i <= 2*N; i++ {
		if scc[i] == scc[tilde(i)] {
			ans = false
		}
	}

	if ans {
		fmt.Fprintln(writer, 1)
	} else {
		fmt.Fprintln(writer, 0)
	}
}

func sccFunc(idx int) int {
	ids[idx] = id
	id++
	stack.PushBack(idx)
	ret := ids[idx]
	for _, next := range graph[idx] {
		if ids[next] == 0 {
			ret = min(ret, sccFunc(next))
		} else if !complete[next] {
			ret = min(ret, ids[next])
		}
	}
	if ret == ids[idx] {
		for {
			item := stack.Back()
			stack.Remove(item)
			v := item.Value.(int)
			scc[v] = sccNum
			complete[v] = true
			if v == idx {
				break
			}
		}
		sccNum++
	}
	return ret
}

func min(a, b int) int {
	if a < b {
		return a
	} else {
		return b
	}
}

func tilde(k int) int {
	if k%2 == 0 {
		return k - 1
	} else {
		return k + 1
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
