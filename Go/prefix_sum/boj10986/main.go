package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
 나머지 합 - 골드 3
 [problem](https://www.acmicpc.net/problem/10986)
*/

var (
	reader           = bufio.NewReader(os.Stdin)
	writer           = bufio.NewWriter(os.Stdout)
	N, M             int
	prefixSum, count []int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N = atoi(st[0])
	M = atoi(st[1])

	prefixSum = make([]int, N+1)
	count = make([]int, M)
	st = stringTokenizer(" ")
	for i := 1; i <= N; i++ {
		prefixSum[i] = prefixSum[i-1] + atoi(st[i-1])
		count[prefixSum[i]%M]++
	}

	res := count[0]
	for i := 0; i < M; i++ {
		res += count[i] * (count[i] - 1) / 2
	}

	fmt.Fprintln(writer, res)
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
