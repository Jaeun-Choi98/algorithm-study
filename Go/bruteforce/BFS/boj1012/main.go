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
유기농 배추 - 실버 2
[problem](https://www.acmicpc.net/problem/1012)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	datas   [][]int
	visited [][]bool
	dx      = []int{1, -1, 0, 0}
	dy      = []int{0, 0, -1, 1}
	n, m, c int
)

type pos struct {
	x int
	y int
}

func main() {
	defer writer.Flush()
	tc := nextInt()

	var st []string
	var ret int
	for ; tc > 0; tc-- {
		st = stringTokenizer(" ")
		n = atoi(st[0])
		m = atoi(st[1])
		c = atoi(st[2])
		datas = make([][]int, n)
		visited = make([][]bool, n)
		for i := 0; i < n; i++ {
			datas[i] = make([]int, m)
			visited[i] = make([]bool, m)
		}
		for i := 0; i < c; i++ {
			st = stringTokenizer(" ")
			datas[atoi(st[0])][atoi(st[1])] = 1
		}
		ret = 0
		for i := 0; i < n; i++ {
			for j := 0; j < m; j++ {
				if !visited[i][j] && datas[i][j] == 1 {
					bfs(i, j)
					ret++
				}
			}
		}
		fmt.Fprintln(writer, ret)
	}
}

func bfs(x, y int) {
	que := list.New()
	que.PushBack(pos{x, y})
	visited[x][y] = true
	for que.Len() > 0 {
		cur := que.Front().Value.(pos)
		que.Remove(que.Front())
		for i := 0; i < 4; i++ {
			nx := cur.x + dx[i]
			ny := cur.y + dy[i]
			if nx < 0 || ny < 0 || nx >= n || ny >= m || datas[nx][ny] == 0 || visited[nx][ny] {
				continue
			}
			visited[nx][ny] = true
			que.PushBack(pos{nx, ny})
		}
	}
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
