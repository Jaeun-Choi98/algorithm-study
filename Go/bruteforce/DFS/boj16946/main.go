package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/16946)
*/

var (
	reader     = bufio.NewReader(os.Stdin)
	writer     = bufio.NewWriter(os.Stdout)
	dx         = []int{1, -1, 0, 0}
	dy         = []int{0, 0, 1, -1}
	n, m       int
	data       [][]rune
	d          [][]int
	gropNum    [][]int
	gropNumVal []int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	n, m = atoi(st[0]), atoi(st[1])
	data = make([][]rune, n)
	d = make([][]int, n)
	gropNum = make([][]int, n)
	for i := 0; i < n; i++ {
		line, _ := reader.ReadString('\n')
		data[i] = []rune(strings.TrimSpace(line))
		d[i] = make([]int, m)
		gropNum[i] = make([]int, m)
	}
	gropNumIdx := 1
	gropNumVal = make([]int, 0)
	gropNumVal = append(gropNumVal, 0)
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if data[i][j] == '0' && d[i][j] == 0 {
				gropNumVal = append(gropNumVal, search(i, j, gropNumIdx))
				gropNumIdx++
			}
		}
	}
	//fmt.Println(gropNum, gropNumVal)
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if data[i][j] == '1' {
				val := 1
				gropCheck := make([]bool, gropNumIdx+1)
				for k := 0; k < 4; k++ {
					x, y := i+dx[k], j+dy[k]
					if x < 0 || y < 0 || x >= n || y >= m {
						continue
					}
					if gropCheck[gropNum[x][y]] {
						continue
					}
					gropCheck[gropNum[x][y]] = true
					val += gropNumVal[gropNum[x][y]]
				}
				fmt.Fprintf(writer, "%d", val%10)
			} else {
				fmt.Fprintf(writer, "%d", 0)
			}
		}
		fmt.Fprintf(writer, "\n")
	}
}

func search(x, y, gropNumIdx int) int {
	d[x][y] = 1
	gropNum[x][y] = gropNumIdx
	for i := 0; i < 4; i++ {
		nx, ny := x+dx[i], y+dy[i]
		if nx < 0 || ny < 0 || nx >= n || ny >= m || data[nx][ny] == '1' {
			continue
		}
		if d[nx][ny] != 0 {
			continue
		}
		d[x][y] += search(nx, ny, gropNumIdx)
	}
	return d[x][y]
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
