package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

type Edge struct {
	from, to int
	w        int
}

var (
	reader    = bufio.NewReader(os.Stdin)
	writer    = bufio.NewWriter(os.Stdout)
	vertexCnt int
	parent    []int
	x, y, z   [][]int
	edges     []Edge
)

func main() {
	defer writer.Flush()
	vertexCnt = nextInt()
	parent = make([]int, vertexCnt)
	x = make([][]int, vertexCnt)
	y = make([][]int, vertexCnt)
	z = make([][]int, vertexCnt)
	for i := 0; i < vertexCnt; i++ {
		st := stringTokenizer(" ")
		x[i] = []int{i, atoi(st[0])}
		y[i] = []int{i, atoi(st[1])}
		z[i] = []int{i, atoi(st[2])}
		parent[i] = i
	}
	sort.Slice(x, func(i, j int) bool { return x[i][1] < x[j][1] })
	sort.Slice(y, func(i, j int) bool { return y[i][1] < y[j][1] })
	sort.Slice(z, func(i, j int) bool { return z[i][1] < z[j][1] })
	for i := 0; i < vertexCnt-1; i++ {
		edges = append(edges, Edge{from: x[i][0], to: x[i+1][0], w: x[i+1][1] - x[i][1]})
		edges = append(edges, Edge{from: y[i][0], to: y[i+1][0], w: y[i+1][1] - y[i][1]})
		edges = append(edges, Edge{from: z[i][0], to: z[i+1][0], w: z[i+1][1] - z[i][1]})
	}
	sort.Slice(edges, func(i, j int) bool { return edges[i].w < edges[j].w })
	ret := 0
	for _, edge := range edges {
		pFrom := Find(edge.from)
		pTo := Find(edge.to)
		if pFrom == pTo {
			continue
		}
		Union(pFrom, pTo)
		ret += edge.w
	}
	fmt.Fprintln(writer, ret)
}

func Union(i, j int) {
	if i <= j {
		parent[i] = j
	} else {
		parent[j] = i
	}
}

func Find(vertex int) int {
	if parent[vertex] == vertex {
		return vertex
	}
	parent[vertex] = Find(parent[vertex])
	return parent[vertex]
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
