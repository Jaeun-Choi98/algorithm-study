package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
N까지의 각 숫자 세기
[problem](https://www.acmicpc.net/problem/1019)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N      int
	ret    []int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	ret = make([]int, 10)
	buf := N
	unit := 1
	rSum := 0
	for buf > 0 {
		m, r := buf/10, buf%10
		ret[0] -= unit
		for i := 0; i < r; i++ {
			ret[i] += (m + 1) * unit
		}
		ret[r] += m*unit + rSum + 1
		for i := r + 1; i < 10; i++ {
			ret[i] += m * unit
		}
		rSum += r * unit

		buf /= 10
		unit *= 10
	}
	for i := 0; i < 10; i++ {
		fmt.Fprintf(writer, "%d ", ret[i])
	}
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
