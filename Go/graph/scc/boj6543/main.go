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
[problem](https://www.acmicpc.net/problem/6543)
*/

var (
	reader     = bufio.NewReader(os.Stdin)
	writer     = bufio.NewWriter(os.Stdout)
	graph      [][]int
	stack      *list.List
	ids        []int
	complete   []int
	scc        [][]int
	id, sccNum int
	ans        []int
)

func main() {
	defer writer.Flush()
	for {
		st := stringTokenizer(" ")
		if atoi(st[0]) == 0 {
			break
		}
		n, m := atoi(st[0]), atoi(st[1])
		st = stringTokenizer(" ")
		graph = make([][]int, n+1)
		for i := 0; i <= n; i++ {
			graph[i] = make([]int, 0)
		}
		for i := 0; i < m; i++ {
			u, v := atoi(st[i*2]), atoi(st[i*2+1])
			graph[u] = append(graph[u], v)
		}
		stack = list.New()
		ids = make([]int, n+1)
		complete = make([]int, n+1)
		scc = make([][]int, 0)
		id = 1
		sccNum = 1

		for i := 1; i <= n; i++ {
			if ids[i] != 0 {
				continue
			}
			targanSCC(i)
		}

		ans = make([]int, 0)
		for i := 0; i < len(scc); i++ {
			if check(&scc[i], i+1) {
				for _, node := range scc[i] {
					ans = append(ans, node)
				}
			}
		}

		sort.Slice(ans, func(i, j int) bool { return ans[i] < ans[j] })
		for i := 0; i < len(ans); i++ {
			fmt.Fprintf(writer, "%d ", ans[i])
		}
		fmt.Fprintln(writer)
	}
}

func check(arr *[]int, num int) bool {
	for i := 0; i < len(*arr); i++ {
		for _, v := range graph[(*arr)[i]] {
			if complete[v] != num {
				return false
			}
		}
	}
	return true
}

func targanSCC(u int) int {
	ids[u] = id
	id++
	stack.PushBack(u)
	ret := ids[u]
	for _, v := range graph[u] {
		if ids[v] == 0 {
			ret = min(ret, targanSCC(v))
		} else if complete[v] == 0 {
			ret = min(ret, ids[v])
		}
	}

	if ret == ids[u] {
		buf := make([]int, 0)
		for stack.Len() > 0 {
			item := stack.Back()
			stack.Remove(item)
			node := item.Value.(int)
			complete[node] = sccNum
			buf = append(buf, node)
			if node == u {
				break
			}
		}
		scc = append(scc, buf)
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

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
