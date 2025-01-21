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
[problem](https://www.acmicpc.net/problem/4196)
*/

var (
	reader        = bufio.NewReader(os.Stdin)
	writer        = bufio.NewWriter(os.Stdout)
	ids           []int
	id            int
	stack         *list.List
	topologyStack *list.List
	N, M          int
	graph         [][]int
	complete      []bool
	sccNum        int
	sccInfo       []int
)

func main() {
	defer writer.Flush()
	tc := nextInt()
	for tc > 0 {
		st := stringTokenizer(" ")
		N, M = atoi32(st[0]), atoi32(st[1])
		graph = make([][]int, N+1)
		for i := 0; i <= N; i++ {
			graph[i] = make([]int, 0)
		}

		for i := 0; i < M; i++ {
			st = stringTokenizer(" ")
			u, v := atoi32(st[0]), atoi32(st[1])
			graph[u] = append(graph[u], v)
		}

		stack = list.New()
		topologyStack = list.New()
		ids = make([]int, N+1)
		complete = make([]bool, N+1)
		id = 1
		sccInfo = make([]int, N+1)
		sccNum = 0
		for i := 1; i <= N; i++ {
			if ids[i] == 0 {
				//targanSCC(i)
				NewSccDfs(i)
			}
		}
		visited := make([]bool, N+1)
		ans := 0
		for topologyStack.Len() > 0 {
			item := topologyStack.Front()
			topologyStack.Remove(item)
			node := item.Value.(int)
			if !visited[node] {
				search(node, &visited)
				ans++
			}
		}
		newAns := 0
		inDegreeScc := make([]int, sccNum)
		for i := 0; i <= N; i++ {
			for _, next := range graph[i] {
				if sccInfo[i] != sccInfo[next] {
					inDegreeScc[sccInfo[next]]++
				}
			}
		}
		for i := 0; i < sccNum; i++ {
			if inDegreeScc[i] == 0 {
				newAns++
			}
		}
		fmt.Fprintln(writer, newAns)
		tc--
	}
}

func NewSccDfs(node int) int {
	ids[node] = id
	id++
	stack.PushBack(node)
	ret := ids[node]
	for _, next := range graph[node] {
		if ids[next] == 0 {
			ret = min(ret, NewSccDfs(next))
		} else if !complete[next] {
			ret = min(ret, ids[next])
		}
	}
	if ret == ids[node] {
		for stack.Len() > 0 {
			item := stack.Back()
			stack.Remove(item)
			cur := item.Value.(int)
			complete[cur] = true
			sccInfo[cur] = sccNum
			if cur == node {
				sccNum++
				break
			}
		}
	}
	return ret
}

func search(cur int, visited *[]bool) {
	if (*visited)[cur] {
		return
	}
	(*visited)[cur] = true
	for _, next := range graph[cur] {
		search(next, visited)
	}
}

func targanSCC(node int) int {
	ids[node] = id
	id++
	stack.PushBack(node)
	ret := ids[node]
	for _, next := range graph[node] {
		if ids[next] == 0 {
			ret = min(ret, targanSCC(next))
		} else if !complete[next] {
			ret = min(ret, ids[next])
		}
	}

	if ret == ids[node] {
		for stack.Len() > 0 {
			item := stack.Back()
			stack.Remove(item)
			sccNode := item.Value.(int)
			complete[sccNode] = true
			topologyStack.PushFront(sccNode)
			if sccNode == node {
				break
			}
		}
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

func atoi32(str string) int {
	ret, _ := strconv.ParseInt(str, 10, 32)
	return int(ret)
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi32(strings.TrimSpace(line))
}
