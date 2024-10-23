package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/11400)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	graph  [][]int
	ans    [][]int
	ids    []int
	id     int
	V, E   int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	V, E = atoi(st[0]), atoi(st[1])
	graph = make([][]int, V+1)
	ids = make([]int, V+1)
	for i := 0; i <= V; i++ {
		graph[i] = make([]int, 0)
	}

	for i := 0; i < E; i++ {
		st = stringTokenizer(" ")
		s, e := atoi(st[0]), atoi(st[1])
		graph[s] = append(graph[s], e)
		graph[e] = append(graph[e], s)
	}
	ans = make([][]int, 0)
	id = 1
	for i := 1; i <= V; i++ {
		if ids[i] != 0 {
			continue
		}
		search(i, 0)
	}
	sort.Slice(ans, func(i, j int) bool {
		if ans[i][0] != ans[j][0] {
			return ans[i][0] < ans[j][0]
		} else {
			return ans[i][1] < ans[j][1]
		}
	})
	fmt.Fprintln(writer, len(ans))
	for i := 0; i < len(ans); i++ {
		fmt.Fprintln(writer, ans[i][0], ans[i][1])
	}
}

func search(idx, parent int) int {
	ids[idx] = id
	id++
	ret := ids[idx]
	for _, next := range graph[idx] {
		if next == parent {
			continue
		}
		if ids[next] != 0 {
			ret = min(ret, ids[next])
			continue
		}
		subTreeRet := search(next, idx)
		if ids[idx] < subTreeRet {
			a, b := idx, next
			if a > b {
				a, b = b, a
			}
			ans = append(ans, []int{a, b})
		}
		ret = min(ret, subTreeRet)
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
