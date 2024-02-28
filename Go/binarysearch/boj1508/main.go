package main

/*
레이스
[problem](https://www.acmicpc.net/problem/1508)
*/

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdin)
	datas  []int
	K, M   int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, _ := strconv.Atoi(st[0])
	M, _ = strconv.Atoi(st[1])
	K, _ = strconv.Atoi(st[2])
	datas = make([]int, K)
	st = stringTokenizer(" ")

	for i := 0; i < K; i++ {
		datas[i], _ = strconv.Atoi(st[i])
	}

	l, r := 0, N
	var cnt, m int
	for l <= r {
		m = (l + r) / 2
		cnt = Search(m)
		if cnt >= M {
			l = m + 1
		} else {
			r = m - 1
		}
	}
	fmt.Print(Print(r))
}

func Print(t int) string {
	var (
		sb            strings.Builder
		dis, pre, cnt int
	)
	sb.WriteString("1")
	cnt = 1
	for i := 1; i < K; i++ {
		dis = datas[i] - datas[pre]
		if dis < t || cnt >= M {
			sb.WriteString("0")
			continue
		}
		if cnt < M {
			sb.WriteString("1")
		}
		pre = i
		cnt++
	}
	return sb.String()
}

func Search(t int) int {
	cnt, pre := 1, 0
	var dis int
	for i := 1; i < K; i++ {
		dis = datas[i] - datas[pre]
		if dis >= t {
			pre = i
			cnt++
		}
	}
	return cnt
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}
