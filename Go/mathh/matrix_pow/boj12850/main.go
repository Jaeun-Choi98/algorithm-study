package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/12850)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	D      int
	matrix [][]int64
)

func main() {
	defer writer.Flush()
	D = nextInt()
	matrix = make([][]int64, 8)
	for i := 0; i < 8; i++ {
		matrix[i] = make([]int64, 8)
	}
	initMatrix()
	retMatrix := divide(D)
	fmt.Fprintln(writer, retMatrix[0][0])
}

func calc(m1, m2 [][]int64) [][]int64 {
	newMatrix := make([][]int64, 8)
	for i := 0; i < 8; i++ {
		newMatrix[i] = make([]int64, 8)
		for j := 0; j < 8; j++ {
			for m := 0; m < 8; m++ {
				newMatrix[i][j] += m1[i][m] * m2[m][j] % 1000000007
				newMatrix[i][j] %= 1000000007
			}
		}
	}
	return newMatrix
}

func divide(cnt int) [][]int64 {
	if cnt == 1 {
		return matrix
	}
	if cnt%2 == 0 {
		buf := divide(cnt / 2)
		return calc(buf, buf)
	} else {
		return calc(matrix, divide(cnt-1))
	}
}

func initMatrix() {
	link(0, 1)
	link(0, 2)
	link(1, 2)
	link(1, 3)
	link(2, 3)
	link(2, 4)
	link(3, 4)
	link(3, 5)
	link(4, 5)
	link(4, 6)
	link(6, 7)
	link(5, 7)
}

func link(from, to int) {
	matrix[from][to], matrix[to][from] = 1, 1
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
