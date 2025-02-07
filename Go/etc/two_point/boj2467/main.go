package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

/*
용액
[problem](https://www.acmicpc.net/problem/2467)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	n      int
	datas  []int
)

func main() {
	defer writer.Flush()
	n = nextInt()
	datas = make([]int, n)
	st := stringTokenizer(" ")
	for i := 0; i < n; i++ {
		datas[i] = atoi(st[i])
	}

	s, e := 0, n-1
	d := 2000000001
	ret := make([]int, 2)
	var v int
	for s < e {
		v = datas[s] + datas[e]
		if int(math.Abs(float64(v))) <= d {
			ret[0] = datas[s]
			ret[1] = datas[e]
			d = int(math.Abs(float64(v)))
		}
		if v == 0 {
			break
		}
		if v < 0 {
			s++
		}
		if v > 0 {
			e--
		}
	}
	fmt.Fprintf(writer, "%d %d\n", ret[0], ret[1])
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
