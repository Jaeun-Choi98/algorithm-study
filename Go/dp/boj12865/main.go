package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
배낭 문제를 dp + 완전 탐색 or 점화식으로 풀 수 있음.
하지만, 완전 탐색일 경우 해당 조건에서의 최소/최대 비용만 구할 수 있는 반면,
점화식일 경우 해당 조건을 포함한 이전 단계를 추적할 수 있음.
[problem](https://www.acmicpc.net/problem/12865)
*/

var (
	reader       = bufio.NewReader(os.Stdin)
	writer       = bufio.NewWriter(os.Stdout)
	N, K         int
	wData, vData []int
	d1, d2       [][]int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, K = atoi(st[0]), atoi(st[1])
	d1 = make([][]int, N)
	d2 = make([][]int, N)
	wData = make([]int, N)
	vData = make([]int, N)
	for i := 0; i < N; i++ {
		st = stringTokenizer(" ")
		wData[i], vData[i] = atoi(st[0]), atoi(st[1])
		d1[i] = make([]int, K+1)
		d2[i] = make([]int, K+1)
		for j := 0; j <= K; j++ {
			d1[i][j] = -1
		}
	}
	ret := knapsack1(0, 0)
	knapsack2()
	fmt.Fprintln(writer, d1)
	fmt.Fprintln(writer, d2)
	fmt.Fprintln(writer, ret)
}

func knapsack2() {
	for i := 0; i < N; i++ {
		w, v := wData[i], vData[i]
		for j := 0; j <= K; j++ {
			if j-w >= 0 {
				if i > 0 {
					d2[i][j] = max(d2[i-1][j], v+d2[i-1][j-w])
				} else {
					d2[0][j] = v
				}
			} else {
				if i > 0 {
					d2[i][j] = d2[i-1][j]
				} else {
					d2[0][j] = 0
				}
			}
		}
	}
}

func knapsack1(idx, w int) int {
	if idx == N || w > K {
		return 0
	}

	if d1[idx][w] != -1 {
		return d1[idx][w]
	}

	ret := 0
	if wData[idx]+w > K {
		ret = knapsack1(idx+1, w)
	} else {
		ret = max(vData[idx]+knapsack1(idx+1, w+wData[idx]), knapsack1(idx+1, w))
	}
	d1[idx][w] = ret
	return ret
}

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
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
