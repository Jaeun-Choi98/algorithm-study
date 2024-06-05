package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
스도쿠
[problem](https://www.acmicpc.net/problem/2239)
*/

var (
	reader   = bufio.NewReader(os.Stdin)
	writer   = bufio.NewWriter(os.Stdout)
	datas    [][]int
	ans      int
	isSearch bool
)

func main() {
	defer writer.Flush()
	datas = make([][]int, 9)
	var runes []rune
	for i := 0; i < 9; i++ {
		datas[i] = make([]int, 9)
		runes = []rune(stringTokenizer(" ")[0])
		for j := 0; j < 9; j++ {
			datas[i][j] = atoi(string(runes[j]))
			if datas[i][j] == 0 {
				ans++
			}
		}
	}
	search(0, 0, 0)
}

func search(x, y, cnt int) {
	//fmt.Println(x, y)
	if isSearch {
		return
	}
	if x == 9 {
		if ans == cnt {
			for i := 0; i < 9; i++ {
				for j := 0; j < 9; j++ {
					fmt.Fprint(writer, datas[i][j])
				}
				fmt.Fprint(writer, "\n")
			}
			isSearch = true
		}
		return
	}

	if datas[x][y] != 0 {
		if y == 8 {
			search(x+1, 0, cnt)
		} else {
			search(x, y+1, cnt)
		}
	} else {
		for i := 1; i <= 9; i++ {
			if isAvailable(x, y, i) {
				datas[x][y] = i
				if y == 8 {
					search(x+1, 0, cnt+1)
				} else {
					search(x, y+1, cnt+1)
				}
				datas[x][y] = 0
			}
		}
	}
}

func isAvailable(x, y, val int) bool {
	// 가로 세로
	for i := 0; i < 9; i++ {
		if datas[x][i] == val || datas[i][y] == val {
			return false
		}
	}
	// 3 x 3
	sx, sy := (x/3)*3, (y/3)*3
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if datas[sx+i][sy+j] == val {
				return false
			}
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
