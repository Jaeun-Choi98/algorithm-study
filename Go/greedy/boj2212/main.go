package main

/*
센서 - 골드5
[problem](https://www.acmicpc.net/problem/2212)
*/

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
)

func main() {
	defer writer.Flush()
	n := nextInt()
	k := nextInt()
	st := stringTokenizer(" ")
	if k >= n {
		fmt.Fprint(writer, 0)
		return
	}
	data := make([]int, n)

	for i := 0; i < n; i++ {
		data[i], _ = strconv.Atoi(st[i])
	}
	sort.Slice(data, func(i, j int) bool { return data[i] < data[j] })

	dif := make([]int, n-1)
	for i := 0; i < n-1; i++ {
		dif[i] = data[i+1] - data[i]
	}
	sort.Slice(dif, func(i, j int) bool { return dif[i] < dif[j] })

	var res int
	for idx, len := 0, len(dif); idx < len-(k-1); idx++ {
		res += dif[idx]
	}

	fmt.Fprint(writer, res)
}

func nextInt() int {
	input, _ := reader.ReadString('\n')
	v, _ := strconv.Atoi(strings.TrimSpace(input))
	return v
}

func stringTokenizer(token string) []string {
	input, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(input), token)
}
