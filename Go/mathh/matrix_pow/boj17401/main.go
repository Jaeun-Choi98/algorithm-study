package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/17401)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	T, N, D int
	matrix  [][][]int
	mod     int
	matrixT [][]int
)

func main() {
	defer writer.Flush()
	mod = 1000000007
	st := stringTokenizer(" ")
	T, N, D = atoi(st[0]), atoi(st[1]), atoi(st[2])
	matrix = make([][][]int, T)
	for i := 0; i < T; i++ {
		matrix[i] = make([][]int, N)
		for j := 0; j < N; j++ {
			matrix[i][j] = make([]int, N)
		}
		M := nextInt()
		for j := 0; j < M; j++ {
			st = stringTokenizer(" ")
			a, b, c := atoi(st[0])-1, atoi(st[1])-1, atoi(st[2])
			matrix[i][a][b] = c
			//matrix[i][b][a] = c
		}
	}

	if D == 0 {
		buf := make([][]int, N)
		for i := 0; i < N; i++ {
			buf[i] = make([]int, N)
		}
	}

	matrixT = matrix[0]
	for i := 1; i < T; i++ {
		matrixT = MatMulti(&matrixT, &matrix[i])
	}

	var ans [][]int
	if D == 0 {
		ans = make([][]int, N)
		for i := 0; i < N; i++ {
			ans[i] = make([]int, N)
		}
	} else if D/T == 0 {
		ans = matrix[0]
		for i := 1; i < D%T; i++ {
			ans = MatMulti(&ans, &matrix[i])
		}
	} else {
		ans = divide(D / T)
		for i := 0; i < D%T; i++ {
			ans = MatMulti(&ans, &matrix[i])
		}
	}

	for i := 0; i < N; i++ {
		for j := 0; j < N; j++ {
			fmt.Fprintf(writer, "%d ", ans[i][j])
		}
		fmt.Fprintln(writer)
	}
}

func divide(cnt int) [][]int {

	if cnt == 1 {
		return matrixT
	}
	m, r := cnt/2, cnt%2
	if r == 0 {
		buf := divide(m)
		return MatMulti(&buf, &buf)
	} else {
		buf := divide(cnt - 1)
		return MatMulti(&buf, &matrixT)
	}
}

func MatMulti(mat1, mat2 *[][]int) [][]int {
	ret := make([][]int, N)
	for i := 0; i < N; i++ {
		ret[i] = make([]int, N)
	}
	for i := 0; i < N; i++ {
		for j := 0; j < N; j++ {
			for k := 0; k < N; k++ {
				ret[i][j] = (ret[i][j] + ((*mat1)[i][k]*(*mat2)[k][j])%mod) % mod
			}
		}
	}
	return ret
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
