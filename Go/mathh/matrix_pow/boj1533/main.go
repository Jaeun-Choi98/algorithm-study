package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
그래프 변환(가중치가 있는 그래프에서 가중치가 모두 1인 그래프로 변환)
[problem](https://www.acmicpc.net/problem/1533)
*/

var (
	reader     = bufio.NewReader(os.Stdin)
	writer     = bufio.NewWriter(os.Stdout)
	N, S, E, T int
	matrix     [][]int64
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, S, E, T = atoi(st[0]), atoi(st[1]), atoi(st[2]), atoi(st[3])

	matrix = make([][]int64, N*5)
	for i := 0; i < N*5; i++ {
		matrix[i] = make([]int64, N*5)
	}
	for i := 1; i < N*5; i++ {
		if i%5 != 0 {
			matrix[i][i-1] = 1
		}
	}

	for i := 0; i < N; i++ {
		line := nextLine()
		for j := 0; j < len(line); j++ {
			val := line[j] - '0'
			if val != 0 {
				matrix[i*5][j*5+(int(val)-1)] = 1
			}
		}
	}

	ret := divide(T)
	fmt.Fprint(writer, ret[5*(S-1)][5*(E-1)])
}

func calc(m1, m2 [][]int64) [][]int64 {
	newMatrix := make([][]int64, N*5)
	for i := 0; i < N*5; i++ {
		newMatrix[i] = make([]int64, N*5)
		for j := 0; j < N*5; j++ {
			for m := 0; m < N*5; m++ {
				newMatrix[i][j] += m1[i][m] * m2[m][j] % 1000003
				newMatrix[i][j] %= 1000003
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

func nextLine() []rune {
	line, _ := reader.ReadString('\n')
	return []rune(strings.TrimSpace(line))
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
