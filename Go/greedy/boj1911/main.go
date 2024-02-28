package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
)

/*
흙길보수하기
[problem](https://www.acmicpc.net/problem/1911)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, _ := strconv.Atoi(st[0])
	L, _ := strconv.Atoi(st[1])
	datas := make([][]int, N)
	for i := 0; i < N; i++ {
		st = stringTokenizer(" ")
		datas[i] = make([]int, 2)
		datas[i][0], _ = strconv.Atoi(st[0])
		datas[i][1], _ = strconv.Atoi(st[1])
	}
	sort.Slice(datas, func(i, j int) bool { return datas[i][0] < datas[j][0] })
	res, pos := 0, 0
	for _, data := range datas {
		pos = int(math.Max(float64(pos), float64(data[0])))
		for pos < data[1] {
			pos += L
			res++
		}
	}
	fmt.Fprintln(writer, res)
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}
