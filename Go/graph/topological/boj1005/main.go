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
ACM Craft
[problem](https://www.acmicpc.net/problem/1005)
*/

var (
	writer   = bufio.NewWriter(os.Stdout)
	reader   = bufio.NewReader(os.Stdin)
	inDegree []int
	weights  []int
)

type Graph [][]int

func (g *Graph) Init(n int) {
	*g = make([][]int, n)
	for i := 0; i < n; i++ {
		(*g)[i] = make([]int, 0)
	}
}

func (g *Graph) Add(from, to int, inDegree []int) {
	(*g)[from] = append((*g)[from], to)
	inDegree[to] += 1
}

func main() {
	defer writer.Flush()
	tc := nextInt()
	var n, k int
	var st []string
	for ; tc > 0; tc-- {
		st = stringTokenizer(" ")
		n, k = atoi(st[0]), atoi(st[1])
		weights = make([]int, n)
		inDegree = make([]int, n)

		st = stringTokenizer(" ")
		for i := 0; i < n; i++ {
			weights[i] = atoi(st[i])
		}

		g := Graph{}
		g.Init(n)
		for i := 0; i < k; i++ {
			st = stringTokenizer(" ")
			g.Add(atoi(st[0])-1, atoi(st[1])-1, inDegree)
		}

		dest := nextInt()
		d := TopologicalSort(g, inDegree)
		fmt.Fprintln(writer, d[dest-1])
	}
}

func TopologicalSort(g Graph, inDegree []int) []int {
	d := make([]int, len(inDegree))
	que := list.New()

	for vertex, val := range inDegree {
		if val == 0 {
			que.PushBack(vertex)
			d[vertex] = weights[vertex]
		}
	}

	for que.Len() > 0 {
		cur := que.Front().Value.(int)
		que.Remove(que.Front())
		//ret = append(ret, cur)
		for _, to := range g[cur] {
			inDegree[to] -= 1
			if d[to] < d[cur]+weights[to] {
				d[to] = d[cur] + weights[to]
			}
			if inDegree[to] == 0 {
				que.PushBack(to)
			}
		}
	}
	return d
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
