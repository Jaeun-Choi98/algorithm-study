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
부분합
[problem](https://www.acmicpc.net/problem/1806)
*/

var (
	writer = bufio.NewWriter(os.Stdout)
	reader = bufio.NewReader(os.Stdin)
	N, S   int
	sumArr []int64
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, S = atoi(st[0]), atoi(st[1])
	st = stringTokenizer(" ")
	sumArr = make([]int64, N+1)

	for i := 1; i <= N; i++ {
		sumArr[i] += int64(atoi(st[i-1])) + sumArr[i-1]
	}

	ret := math.MaxInt32
	s, e := 0, 0
	//fmt.Println(sumArr)
	for e <= N+1 {
		//fmt.Println(s, e)
		if e == N+1 {
			if sumArr[e-1]-sumArr[s] < int64(S) {
				break
			} else {
				for sumArr[e-1]-sumArr[s] >= int64(S) {
					if ret > e-s {
						ret = e - 1 - s
					}
					s++
				}
				break
			}
		}
		if sumArr[e]-sumArr[s] >= int64(S) {
			if ret > e-s {
				ret = e - s
			}
			s++
		}
		if sumArr[e]-sumArr[s] < int64(S) {
			e++
		}
	}
	if ret == math.MaxInt32 {
		ret = 0
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

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
