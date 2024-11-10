package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/1126)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N      int
	data   []int
	d      [][]int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	data = make([]int, N)
	st := stringTokenizer(" ")
	for i := 0; i < N; i++ {
		data[i] = atoi(st[i])
	}
	d = make([][]int, N+1)
	for i := 0; i <= N; i++ {
		d[i] = make([]int, 500001)
		for j := 0; j <= 500000; j++ {
			d[i][j] = -1
		}
	}
	search(0, 0)
	if d[0][0] == 0 {
		fmt.Fprintln(writer, -1)
	} else {
		fmt.Fprintln(writer, d[0][0])
	}
}

func search(cur, dif int) int {
	if cur == N {
		if dif != 0 {
			return -500001
		}
		return 0
	}

	if d[cur][dif] != -1 {
		return d[cur][dif]
	}

	ret := -500001
	// 아무것도 쌓지 않을 경우
	ret = max(ret, search(cur+1, dif))
	// 높은 쪽에 쌓을 경우
	ret = max(ret, search(cur+1, dif+data[cur])+data[cur])
	// 낮은 쪽에 ''
	buf := dif - data[cur]
	if buf < 0 {
		buf *= -1
		ret = max(ret, search(cur+1, buf)+buf)
	} else {
		ret = max(ret, search(cur+1, buf))
	}

	//fmt.Println(cur, dif, ret)
	d[cur][dif] = ret
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

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
