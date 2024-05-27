package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
소수의 연속합
[problem](https://www.acmicpc.net/problem/1644)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	prime  []bool
	n      int
)

func main() {
	defer writer.Flush()
	n = nextInt()
	prime = make([]bool, n+1)
	for i := 2; i*i <= n; i++ {
		if !prime[i] {
			for j := i * i; j <= n; j += i {
				prime[j] = true
			}
		}
	}

	primeData := make([]int, 0)
	for i := 2; i <= n; i++ {
		if !prime[i] {
			primeData = append(primeData, i)
		}
	}

	var res, s, e int
	var prefixSum int64
	for e <= len(primeData) {
		if prefixSum >= int64(n) {
			prefixSum -= int64(primeData[s])
			s++
		}

		if prefixSum < int64(n) {
			if e < len(primeData) {
				prefixSum += int64(primeData[e])
			}
			e++
		}

		if prefixSum == int64(n) {
			res++
		}
	}

	fmt.Fprintln(writer, res)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
