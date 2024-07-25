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
음악프로그램
[problem](https://www.acmicpc.net/problem/2623)
*/

var (
	reader      = bufio.NewReader(os.Stdin)
	writer      = bufio.NewWriter(os.Stdout)
	graph       [][]int
	checkEdge   map[int]int
	inDegree    []int
	vertices, n int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	vertices, n = atoi(st[0]), atoi(st[1])
	inDegree = make([]int, vertices+1)
	graph = make([][]int, vertices+1)
	checkEdge = make(map[int]int)
	for i := 1; i < vertices+1; i++ {
		graph[i] = make([]int, 0)
	}
	var s, e, length int
	for i := 0; i < n; i++ {
		st = stringTokenizer(" ")
		length = atoi(st[0])
		s = atoi(st[1])
		for j := 2; j < length+1; j++ {
			e = atoi(st[j])
			if checkEdge[s] == e {
				s = e
				continue
			}
			checkEdge[s] = e
			graph[s] = append(graph[s], e)
			inDegree[e]++
			s = e
		}
	}
	//fmt.Println(graph, inDegree)
	ret := make([]int, 0)
	que := list.New()

	for vertax, val := range inDegree {
		if vertax == 0 {
			continue
		}
		if val == 0 {
			que.PushBack(vertax)
		}
	}

	for que.Len() > 0 {
		cur := que.Front().Value.(int)
		que.Remove(que.Front())
		ret = append(ret, cur)
		for _, e := range graph[cur] {
			inDegree[e]--
			if inDegree[e] == 0 {
				que.PushBack(e)
			}
		}
	}

	if len(ret) != vertices {
		fmt.Fprintln(writer, 0)
	} else {
		for i, leng := 0, len(ret); i < leng; i++ {
			fmt.Fprintln(writer, ret[i])
		}
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
