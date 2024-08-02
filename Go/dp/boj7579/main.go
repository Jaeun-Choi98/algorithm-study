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
앱
[problem](https://www.acmicpc.net/problem/7579)
*/

var (
	reader       = bufio.NewReader(os.Stdin)
	writer       = bufio.NewWriter(os.Stdout)
	N, M         int
	mData, cData []int
	d            [][]int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M = atoi(st[0]), atoi(st[1])
	d = make([][]int, N+1)
	mData = make([]int, N+1)
	cData = make([]int, N+1)
	st = stringTokenizer(" ")
	for i := 1; i < N+1; i++ {
		mData[i] = atoi(st[i-1])
	}
	st = stringTokenizer(" ")
	var sum int
	for i := 1; i < N+1; i++ {
		cData[i] = atoi(st[i-1])
		sum += cData[i]
	}

	for i := 0; i < N+1; i++ {
		d[i] = make([]int, sum+1)
	}

	for i := 1; i < N+1; i++ {
		m, c := mData[i], cData[i]
		for j := 0; j < sum+1; j++ {
			if j-c >= 0 {
				d[i][j] = int(math.Max(float64(d[i-1][j]), float64(m+d[i-1][j-c])))
			} else {
				d[i][j] = d[i-1][j]
			}
		}
	}
	//fmt.Println(d)
	var ret int
	for i := 0; i < sum+1; i++ {
		if d[N][i] >= M {
			ret = i
			break
		}
	}

	fmt.Fprintln(writer, ret)
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
