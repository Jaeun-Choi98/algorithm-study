package main

import (
	"bufio"
	"container/list"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

/*
성곽 - 골드2
[problem](https://www.acmicpc.net/problem/2234)
*/

var (
	reader      = bufio.NewReader(os.Stdin)
	writer      = bufio.NewWriter(os.Stdout)
	n, m        int
	dx          = []int{0, -1, 0, 1}
	dy          = []int{-1, 0, 1, 0}
	datas       [][]int
	visitedInit [][]bool
)

func main() {
	defer writer.Flush()
	st := StringTokenizer(" ")
	m, _ = strconv.Atoi(st[0])
	n, _ = strconv.Atoi(st[1])
	datas = make([][]int, n)
	visitedInit = make([][]bool, n)
	for i := 0; i < n; i++ {
		datas[i] = make([]int, m)
		visitedInit[i] = make([]bool, m)
		st = StringTokenizer(" ")
		for j := 0; j < m; j++ {
			datas[i][j], _ = strconv.Atoi(st[j])
		}
	}

	res1, res2 := Solution12()
	res3 := Solution3()
	if res3 < res2 {
		res3 = res2
	}
	fmt.Fprintf(writer, "%d\n%d\n%d", res1, res2, res3)
}

func Solution3() int {
	ret := 0
	visited := make([][]bool, n)
	for i := range visited {
		visited[i] = make([]bool, m)
	}

	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			for k := 0; k < 4; k++ {
				dir := 1 << k
				if datas[i][j]&dir > 0 {
					datas[i][j] -= dir
					for i := range visitedInit {
						copy(visited[i], visitedInit[i])
					}
					visited[i][j] = true
					ret = int(math.Max(float64(BFS(i, j, visited)), float64(ret)))
					datas[i][j] += dir
				}
			}
		}
	}
	return ret
}

func Solution12() (int, int) {
	res1, res2 := 0, 0
	visited := make([][]bool, n)
	for i := range visited {
		visited[i] = make([]bool, m)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if !visited[i][j] {
				visited[i][j] = true
				res2 = int(math.Max(float64(res2), float64(BFS(i, j, visited))))
				res1++
			}
		}
	}
	return res1, res2
}

func BFS(i, j int, visited [][]bool) int {
	ret := 1
	que := list.New()
	que.PushBack([]int{i, j})
	for que.Len() != 0 {
		element := que.Front()
		que.Remove(element)
		ints, _ := element.Value.([]int)
		for i := 0; i < 4; i++ {
			nx := ints[0] + dx[i]
			ny := ints[1] + dy[i]
			if nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || datas[ints[0]][ints[1]]&(1<<i) > 0 {
				continue
			}
			visited[nx][ny] = true
			que.PushBack([]int{nx, ny})
			ret++
		}
	}
	return ret
}

func StringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}
