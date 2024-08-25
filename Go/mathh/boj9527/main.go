package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/9527)
*/

var (
	reader           = bufio.NewReader(os.Stdin)
	writer           = bufio.NewWriter(os.Stdout)
	prefixSum        []int64
	a, b, acnt, bcnt int64
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	a, b = atoi64(st[0]), atoi64(st[1])
	prefixSum = make([]int64, 60)
	prefixSum[0] = 1
	for i := 1; i < 60; i++ {
		prefixSum[i] = prefixSum[i-1] + prefixSum[i-1] + (1 << i)
	}
	a -= 1
	acnt = a & 1
	bcnt = b & 1
	for i := 60; i > 0; i-- {
		if (a & (1 << i)) != 0 {
			acnt += prefixSum[i-1] + a - (1 << i) + 1
			a -= (1 << i)
		}
		if (b & (1 << i)) != 0 {
			bcnt += prefixSum[i-1] + b - (1 << i) + 1
			b -= (1 << i)
		}
	}

	fmt.Fprintln(writer, bcnt-acnt)
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi64(str string) int64 {
	ret, _ := strconv.ParseInt(str, 10, 64)
	return ret
}
