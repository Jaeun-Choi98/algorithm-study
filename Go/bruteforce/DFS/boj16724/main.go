package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	reader   = bufio.NewReader(os.Stdin)
	writer   = bufio.NewWriter(os.Stdout)
	n, m     int
	data     [][]rune
	group    [][]int
	visited  [][]bool
	groupCnt int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	n, m = atoi(st[0]), atoi(st[1])
	data = make([][]rune, n)
	group = make([][]int, n)
	visited = make([][]bool, n)
	for i := 0; i < n; i++ {
		st = stringTokenizer(" ")
		data[i] = []rune(st[0])
		group[i] = make([]int, m)
		visited[i] = make([]bool, m)
	}
	groupCnt = 1
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if group[i][j] != 0 {
				continue
			}
			if search(i, j) == groupCnt {
				groupCnt++
			}
		}
	}
	// for i := 0; i < n; i++ {
	// 	for j := 0; j < m; j++ {
	// 		fmt.Printf("%d ", group[i][j])
	// 	}
	// 	fmt.Println()
	// }
	fmt.Fprintln(writer, groupCnt-1)
}

func search(x, y int) int {
	group[x][y] = groupCnt
	var nx, ny int
	switch data[x][y] {
	case 'U':
		nx, ny = x-1, y
	case 'D':
		nx, ny = x+1, y
	case 'R':
		nx, ny = x, y+1
	case 'L':
		nx, ny = x, y-1
	}
	if nx < 0 || ny < 0 || nx >= n || ny >= m {
		return group[x][y]
	}
	if group[nx][ny] != 0 {
		group[x][y] = group[nx][ny]
		return group[x][y]
	}
	group[x][y] = search(nx, ny)
	return group[x][y]
}

func stringTokenizer(token string) []string {
	input, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(input), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
