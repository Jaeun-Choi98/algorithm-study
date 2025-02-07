package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/3117)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N, M, K int
	parents [][]int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, K, M = atoi(st[0]), atoi(st[1]), atoi(st[2])
	datas := make([]int, N)
	st = stringTokenizer(" ")
	for i := 0; i < N; i++ {
		datas[i] = atoi(st[i]) - 1
	}
	st = stringTokenizer(" ")
	parents = make([][]int, K)
	for i := 0; i < K; i++ {
		parents[i] = make([]int, 31)
		parents[i][0] = atoi(st[i]) - 1
	}
	for k := 1; k < 31; k++ {
		for i := 0; i < K; i++ {
			parents[i][k] = parents[parents[i][k-1]][k-1]
		}
	}
	for i := 0; i < 31; i++ {
		if ((1 << i) & (M - 1)) > 0 {
			for j := 0; j < N; j++ {
				datas[j] = parents[datas[j]][i]
			}
		}
	}
	for i := 0; i < N; i++ {
		fmt.Fprintf(writer, "%d ", datas[i]+1)
	}
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
