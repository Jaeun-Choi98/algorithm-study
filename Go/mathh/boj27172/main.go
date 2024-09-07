package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/27172)
*/

var (
	reader            = bufio.NewReader(os.Stdin)
	writer            = bufio.NewWriter(os.Stdout)
	N                 int
	inputData         []int
	inputDataValToIdx []int
	exist             []bool
	ret               []int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	inputData = make([]int, N)
	inputDataValToIdx = make([]int, 1000001)
	ret = make([]int, N)
	exist = make([]bool, 1000001)
	st := stringTokenizer(" ")
	for i := 0; i < N; i++ {
		inputData[i] = atoi(st[i])
		inputDataValToIdx[inputData[i]] = i
		exist[inputData[i]] = true
	}
	for i := 0; i < N; i++ {
		cur := inputData[i]
		for j := cur * 2; j <= 1000000; j += cur {
			if exist[j] {
				ret[i]++
				ret[inputDataValToIdx[j]]--
			}
		}
	}
	for i := 0; i < N; i++ {
		fmt.Fprintf(writer, "%d ", ret[i])
	}
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
