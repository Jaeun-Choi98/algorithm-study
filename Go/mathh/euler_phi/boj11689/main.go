package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/11689)
*/

var (
	reader      = bufio.NewReader(os.Stdin)
	writer      = bufio.NewWriter(os.Stdout)
	n           int64
	separateArr []int64
	count       map[int64]int64
)

func main() {
	defer writer.Flush()
	line, _ := reader.ReadString('\n')
	n, _ = strconv.ParseInt(strings.TrimSpace(line), 10, 64)
	separateArr = make([]int64, 0)
	count = make(map[int64]int64)
	separate(n)
	fmt.Fprintln(writer, calc())
}

func separate(k int64) {
	n = 2
	for n*n <= k {
		if k%n == 0 {
			var cnt int64
			for k%n == 0 {
				k /= n
				cnt++
			}
			separateArr = append(separateArr, n)
			count[n] = cnt
		} else {
			n += 1
		}
	}
	if k > 1 {
		separateArr = append(separateArr, k)
		count[k] = 1
	}
}

func calc() int64 {
	var ret int64
	ret = 1
	for i, size := 0, len(separateArr); i < size; i++ {
		p := separateArr[i]
		cnt := count[p]

		var tmp int64
		tmp = 1
		for j := 0; j < int(cnt)-1; j++ {
			tmp *= p
		}

		ret *= (tmp * (p - 1))
	}
	return ret
}
