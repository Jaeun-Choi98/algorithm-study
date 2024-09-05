package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/17404)
*/

var (
	writer = bufio.NewWriter(os.Stdout)
	reader = bufio.NewReader(os.Stdin)
	N      int
	data   [][]int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	data = make([][]int, N)
	for i := 0; i < N; i++ {
		data[i] = make([]int, 3)
		st := stringTokenizer(" ")
		data[i][0], data[i][1], data[i][2] = atoi(st[0]), atoi(st[1]), atoi(st[2])
	}
	ret := 1000001
	for i := 0; i < 3; i++ {
		d := make([][]int, N)
		for j := 0; j < N; j++ {
			d[j] = make([]int, 3)
		}
		search(0, i, i, &d)
		if ret > d[0][i] {
			ret = d[0][i]
		}
	}
	fmt.Fprintln(writer, ret)
}

func search(cnt, pre, start int, d *[][]int) int {
	if cnt == N-1 {
		min := 1001
		for i := 0; i < 3; i++ {
			if start == i || pre == i {
				continue
			}
			if min > data[cnt][i] {
				min = data[cnt][i]
			}
		}
		return min
	}

	if (*d)[cnt][pre] != 0 {
		return (*d)[cnt][pre]
	}

	min := 1000001
	for i := 0; i < 3; i++ {
		if pre == i {
			continue
		}
		buf := data[cnt][i] + search(cnt+1, i, start, d)
		if min > buf {
			min = buf
		}
	}

	(*d)[cnt][pre] = min
	return (*d)[cnt][pre]
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
