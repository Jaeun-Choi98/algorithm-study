package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
색종이 만들기 - 분할정복
[problem](https://www.acmicpc.net/problem/2630)
*/

var (
	reader        = bufio.NewReader(os.Stdin)
	writer        = bufio.NewWriter(os.Stdout)
	n, res1, res2 int
	datas         [][]int
)

func main() {
	defer writer.Flush()
	n = nextInt()
	datas = make([][]int, n)
	var st []string
	for i := 0; i < n; i++ {
		datas[i] = make([]int, n)
		st = stringTokenizer(" ")
		for j := 0; j < n; j++ {
			datas[i][j], _ = strconv.Atoi(st[j])
		}
	}
	res1, res2 = 0, 0
	search(n, 0, 0)
	fmt.Fprintf(writer, "%d\n%d", res1, res2)
}

func search(size, x, y int) {
	if color, ok := check(size, x, y); ok {
		if color == 1 {
			res2++
		} else {
			res1++
		}
	} else {
		search(size/2, x, y)
		search(size/2, x, y+size/2)
		search(size/2, x+size/2, y)
		search(size/2, x+size/2, y+size/2)
	}
}

func check(size, x, y int) (int, bool) {
	cur := datas[x][y]
	for i := x; i < x+size; i++ {
		for j := y; j < y+size; j++ {
			if cur != datas[i][j] {
				return -1, false
			}
		}
	}
	return cur, true
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	ret, _ := strconv.Atoi(strings.TrimSpace(line))
	return ret
}
