package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/2150)
*/

var (
	reader   = bufio.NewReader(os.Stdin)
	writer   = bufio.NewWriter(os.Stdout)
	V, E     int
	graph    [][]int
	ids      []int
	id       int
	complete []bool
	stack    *list.List
	ans      [][]int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	V, E = atoi(st[0]), atoi(st[1])
	graph = make([][]int, V+1)
	ids = make([]int, V+1)
	complete = make([]bool, V+1)

	for i := 0; i < V+1; i++ {
		graph[i] = make([]int, 0)
	}

	for i := 0; i < E; i++ {
		st = stringTokenizer(" ")
		from, to := atoi(st[0]), atoi(st[1])
		graph[from] = append(graph[from], to)
	}

	ans = make([][]int, 0)
	id = 1
	stack = list.New()
	for i := 1; i <= V; i++ {
		if ids[i] != 0 {
			continue
		}
		search(i)
	}

	sort.Slice(ans, func(i, j int) bool { return ans[i][0] < ans[j][0] })
	fmt.Fprintln(writer, len(ans))
	for _, scc := range ans {
		for _, v := range scc {
			fmt.Fprintf(writer, "%d ", v)
		}
		fmt.Fprintf(writer, "\n")
	}
	//fmt.Println(ans, ids, complete)
}

func search(idx int) int {
	ids[idx] = id
	id++
	stack.PushBack(idx)
	ret := ids[idx]
	for _, to := range graph[idx] {
		if ids[to] == 0 {
			ret = min(ret, search(to))
		} else if !complete[to] {
			ret = min(ret, ids[to])
		}
	}

	if ret == ids[idx] {
		scc := make([]int, 0)
		for true {
			item := stack.Back()
			stack.Remove(item)
			v := item.Value.(int)
			complete[v] = true
			scc = append(scc, v)
			if v == idx {
				break
			}
		}
		sort.Slice(scc, func(i, j int) bool { return scc[i] < scc[j] })
		scc = append(scc, -1)
		ans = append(ans, scc)
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
