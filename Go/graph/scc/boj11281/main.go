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
2-SAT
[problem](https://www.acmicpc.net/problem/11281)
*/

var (
	reader     = bufio.NewReader(os.Stdin)
	writer     = bufio.NewWriter(os.Stdout)
	N, M       int
	graph      [][]int
	scc, ids   []int
	ans        []int
	complete   []bool
	id, sccNum int
	stack      *list.List
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi(st[0]), atoi(st[1])
	graph = make([][]int, 2*N+1)
	ids = make([]int, 2*N+1)
	scc = make([]int, 2*N+1)
	complete = make([]bool, 2*N+1)
	for i := 0; i < 2*N+1; i++ {
		graph[i] = make([]int, 0)
	}

	for i := 0; i < M; i++ {
		st = stringTokenizer(" ")
		a, b := atoi(st[0]), atoi(st[1])
		if a < 0 {
			a = -a*2 - 1
		} else {
			a = 2 * a
		}
		if b < 0 {
			b = -b*2 - 1
		} else {
			b = 2 * b
		}
		graph[tilde(a)] = append(graph[tilde(a)], b)
		graph[tilde(b)] = append(graph[tilde(b)], a)
	}
	stack = list.New()
	id = 1
	sccNum = 0
	for i := 1; i <= 2*N; i++ {
		if ids[i] != 0 {
			continue
		}
		GetSCC(i)
	}
	ans = make([]int, N+1)
	ans1 := 1
	for i := 1; i <= N; i++ {
		if scc[2*i] == scc[2*i-1] {
			ans1 = 0
			break
		} else {
			if scc[2*i] < scc[2*i-1] {
				ans[i] = 1
			} else {
				ans[i] = 0
			}
		}
	}
	fmt.Fprintln(writer, ans1)
	if ans1 != 0 {
		for i := 1; i <= N; i++ {
			fmt.Fprintf(writer, "%d ", ans[i])
		}
	}

}

func GetSCC(idx int) int {
	ids[idx] = id
	id++
	stack.PushBack(idx)
	ret := ids[idx]
	for _, to := range graph[idx] {
		if ids[to] == 0 {
			ret = min(ret, GetSCC(to))
		} else if !complete[to] {
			ret = min(ret, ids[to])
		}
	}
	if ret == ids[idx] {
		for true {
			item := stack.Back()
			stack.Remove(item)
			v := item.Value.(int)
			complete[v] = true
			scc[v] = sccNum
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
