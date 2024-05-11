package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

/*
웜홀
[problem](https://www.acmicpc.net/problem/1865)
*/

type edge struct {
	from, to, weight int
}

var (
	writer   = bufio.NewWriter(os.Stdout)
	reader   = bufio.NewReader(os.Stdin)
	edges    []edge
	d        []int
	vertices int
)

func main() {
	defer writer.Flush()
	tc := nextInt()
	for ; tc > 0; tc-- {
		st := stringTokenizer(" ")
		vertices = atoi(st[0])
		edges = make([]edge, 0)
		for i, len := 0, atoi(st[1]); i < len; i++ {
			st1 := stringTokenizer(" ")
			edges = append(edges, edge{atoi(st1[0]), atoi(st1[1]), atoi(st1[2])})
			edges = append(edges, edge{atoi(st1[1]), atoi(st1[0]), atoi(st1[2])})
		}
		for i, len := 0, atoi(st[2]); i < len; i++ {
			st1 := stringTokenizer(" ")
			edges = append(edges, edge{atoi(st1[0]), atoi(st1[1]), -atoi(st1[2])})
		}
		d = make([]int, vertices+1)
		for i := 0; i <= vertices; i++ {
			d[i] = math.MaxInt32
		}

		var isNegativeCycle bool
		for i := 1; i <= vertices; i++ {
			if d[i] != math.MaxInt32 {
				continue
			}
			isNegativeCycle = Bellmanford(i)
			if isNegativeCycle {
				break
			}
		}

		if isNegativeCycle {
			fmt.Fprintln(writer, "YES")
		} else {
			fmt.Fprintln(writer, "NO")
		}
	}
}

func Bellmanford(start int) bool {

	d[start] = 0

	var from, to, weight int
	var isNegativeCycle bool
	for i := 0; i < vertices; i++ {
		for j, len := 0, len(edges); j < len; j++ {
			from = edges[j].from
			to = edges[j].to
			weight = edges[j].weight
			if d[from] != math.MaxInt32 && d[to] > d[from]+weight {
				d[to] = d[from] + weight
				if i == vertices-1 {
					isNegativeCycle = true
				}
			}
		}
	}

	return isNegativeCycle
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
