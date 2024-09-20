package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/2098)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N      int
	data   [][]int
	d      [][]int
	maxBit int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	data = make([][]int, N)
	for i := 0; i < N; i++ {
		data[i] = make([]int, N)
		st := stringTokenizer(" ")
		for j := 0; j < N; j++ {
			data[i][j] = atoi(st[j])
		}
	}
	maxBit = 1 << N
	d = make([][]int, maxBit)
	for i := 0; i < maxBit; i++ {
		d[i] = make([]int, N)
		for j := 0; j < N; j++ {
			d[i][j] = -1
		}
	}

	// ret := 16000001
	// for i := 0; i < N; i++ {
	// 	d = make([][]int, maxBit)
	// 	for i := 0; i < maxBit; i++ {
	// 		d[i] = make([]int, N)
	// 		for j := 0; j < N; j++ {
	// 			d[i][j] = -1
	// 		}
	// 	}
	// 	buf := search(1<<i, i, i)
	// 	fmt.Fprintln(writer, i, buf)
	// 	if ret > buf {
	// 		ret = buf
	// 	}
	// }
	fmt.Fprintln(writer, search(1, 0, 0))
}

func search(bit, cur, first int) int {
	if bit == maxBit-1 {
		if data[cur][first] == 0 {
			return 16000001
		} else {
			return data[cur][first]
		}
	}

	if d[bit][cur] != -1 {
		return d[bit][cur]
	}

	ret := 16000001
	for i := 0; i < N; i++ {
		if bit&(1<<i) > 0 || data[cur][i] == 0 {
			continue
		}
		buf := search(bit|(1<<i), i, first)
		if ret > buf+data[cur][i] {
			ret = buf + data[cur][i]
		}
	}

	d[bit][cur] = ret
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
