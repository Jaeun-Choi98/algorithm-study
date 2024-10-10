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
[problem](https://www.acmicpc.net/problem/3648)
*/

var (
	reader     = bufio.NewReader(os.Stdin)
	writer     = bufio.NewWriter(os.Stdout)
	n, m       int
	graph      [][]int
	ids        []int
	complete   []bool
	id, sccNum int
	scc        []int
	stack      *list.List
)

func main() {
	defer writer.Flush()
	for true {
		st := stringTokenizer(" ")
		if st[0] == "" {
			break
		}
		n, m = atoi(st[0]), atoi(st[1])
		graph = make([][]int, 2*n+1)
		for i := 0; i <= 2*n; i++ {
			graph[i] = make([]int, 0)
		}
		ids = make([]int, 2*n+1)
		scc = make([]int, 2*n+1)
		complete = make([]bool, 2*n+1)
		for i := 0; i < m; i++ {
			st = stringTokenizer(" ")
			a, b := atoi(st[0]), atoi(st[1])
			if a < 0 {
				a = 2*-a - 1
			} else {
				a = 2 * a
			}
			if b < 0 {
				b = 2*-b - 1
			} else {
				b = 2 * b
			}
			graph[tilde(a)] = append(graph[tilde(a)], b)
			graph[tilde(b)] = append(graph[tilde(b)], a)
		}
		graph[tilde(1*2)] = append(graph[tilde(1*2)], 1*2)
		id = 1
		sccNum = 0
		stack = list.New()
		for i := 1; i <= 2*n; i++ {
			if ids[i] != 0 {
				continue
			}
			targanSCC(i)
		}
		ans := true
		for i := 1; i <= n; i++ {
			if scc[2*i] == scc[2*i-1] {
				ans = false
				break
			}
		}
		if ans {
			fmt.Fprintln(writer, "yes")
		} else {
			fmt.Fprintln(writer, "no")
		}
	}
}

func targanSCC(idx int) int {
	ids[idx] = id
	id++
	stack.PushBack(idx)
	ret := ids[idx]
	for _, to := range graph[idx] {
		if ids[to] == 0 {
			ret = min(ret, targanSCC(to))
		} else if !complete[to] {
			ret = min(ret, ids[to])
		}
	}

	if ret == ids[idx] {
		for stack.Len() > 0 {
			item := stack.Back()
			stack.Remove(item)
			vertex := item.Value.(int)
			complete[vertex] = true
			scc[vertex] = sccNum
			if vertex == idx {
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
