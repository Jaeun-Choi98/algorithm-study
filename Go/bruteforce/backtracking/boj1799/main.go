package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
비숍
[problem](https://www.acmicpc.net/problem/1799)
*/

var (
	writer     = bufio.NewWriter(os.Stdout)
	reader     = bufio.NewReader(os.Stdin)
	n          int
	res1, res2 int
	data       [][]bool
	visited    [][]bool
	dp         [][]int
	dx         = []int{1, 1, -1, -1}
	dy         = []int{-1, 1, -1, 1}
)

func main() {
	defer writer.Flush()
	n = nextInt()
	data = make([][]bool, n)
	visited = make([][]bool, n)
	dp = make([][]int, n)
	for i := 0; i < n; i++ {
		st := stringTokenizer(" ")
		data[i] = make([]bool, n)
		visited[i] = make([]bool, n)
		dp[i] = make([]int, n)
		for j := 0; j < n; j++ {
			if atoi(st[j]) == 1 {
				data[i][j] = true
			}
		}
	}

	search1(0, 0)
	search2(1, 0)
	//fmt.Println(res1, res2)
	fmt.Fprintln(writer, res1+res2)
}

func search2(index, cnt int) {
	if index >= n*n {
		if res2 < cnt {
			res2 = cnt
		}
		return
	}
	x, y := index/n, index%n

	var next int
	if (index+2)/n > x && n%2 == 1 {
		next = index + 2
	} else if (index+2)/n > x && (y)%2 == 0 {
		next = index + 3
	} else if (index+2)/n > x && (y)%2 == 1 {
		next = index + 1
	} else {
		next = index + 2
	}

	if ok := isAvailable(x, y); ok {
		visited[x][y] = true
		search2(next, cnt+1)
		visited[x][y] = false
	}

	search2(next, cnt)
}

func search1(index, cnt int) {
	if index >= n*n {
		if res1 < cnt {
			//fmt.Println(visited)
			res1 = cnt
		}
		return
	}
	x, y := index/n, index%n

	var next int
	if (index+2)/n > x && n%2 == 1 {
		next = index + 2
	} else if (index+2)/n > x && (y)%2 == 0 {
		next = index + 3
	} else if (index+2)/n > x && (y)%2 == 1 {
		next = index + 1
	} else {
		next = index + 2
	}
	//fmt.Println(next)
	if ok := isAvailable(x, y); ok {
		visited[x][y] = true
		search1(next, cnt+1)
		visited[x][y] = false
	}

	search1(next, cnt)
}

func isAvailable(x, y int) bool {
	// 놓을 수 있는 자리인지 확인
	if !data[x][y] {
		return false
	}
	// 대각선에 비숍이 있는지 확인
	for i := 0; i < 4; i++ {
		nx, ny := x+dx[i], y+dy[i]
		for nx >= 0 && nx < n && ny >= 0 && ny < n {
			if visited[nx][ny] {
				return false
			}
			nx += dx[i]
			ny += dy[i]
		}
	}
	return true
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
