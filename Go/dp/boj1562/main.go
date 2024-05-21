package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
계단 수
[problem](https://www.acmicpc.net/problem/1562)
참고블로그 - https://velog.io/@js43o/%EB%B0%B1%EC%A4%80-1562%EB%B2%88-%EA%B3%84%EB%8B%A8-%EC%88%98
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	dp     [][][]int
	n      int
)

func main() {
	defer writer.Flush()
	n = nextInt()

	// [n번째짜리][현재 숫자][방문한 숫자]
	dp = make([][][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([][]int, 10)
		for j := 0; j < 10; j++ {
			dp[i][j] = make([]int, 1024)
		}
	}

	for i := 1; i < 10; i++ {
		dp[0][i][1<<i] = 1
	}

	for i := 1; i < n; i++ {
		for j := 0; j < 10; j++ {
			for k := 0; k < 1024; k++ {
				if j-1 >= 0 {
					dp[i][j][k|1<<j] += dp[i-1][j-1][k]
				}
				if j+1 <= 9 {
					dp[i][j][k|1<<j] += dp[i-1][j+1][k]
				}
				dp[i][j][k|1<<j] %= 1000000000
			}
		}
	}

	var res int
	for i := 0; i < 10; i++ {
		res += dp[n-1][i][1023]
		res %= 1000000000
	}

	fmt.Fprintln(writer, res)
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
