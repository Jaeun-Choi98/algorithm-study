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
양팔 저울 - 골드 3
[problem](https://www.acmicpc.net/problem/2629)
*/
var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	n, m   int
	wn     []int
	d      [][]bool
)

func main() {
	defer writer.Flush()

	n = nextInt()
	wn = make([]int, n)
	st := stringTokenizer(" ")
	var max int
	for i := 0; i < n; i++ {
		wn[i] = atoi(st[i])
		max += wn[i]
	}

	d = make([][]bool, n+1)
	for i := 0; i <= n; i++ {
		d[i] = make([]bool, max+1)
	}

	search(0, 0)
	m = nextInt()
	st = stringTokenizer(" ")

	for i := 0; i < m; i++ {
		if atoi(st[i]) > max {
			fmt.Fprintf(writer, "N\n")
		} else if d[n][atoi(st[i])] {
			fmt.Fprintf(writer, "Y\n")
		} else {
			fmt.Fprintf(writer, "N\n")
		}
	}
}

func search(idx, sum int) {

	if d[idx][sum] {
		return
	}
	d[idx][sum] = true

	if idx == n {
		return
	}

	w := wn[idx]

	search(idx+1, sum)
	search(idx+1, sum+w)
	search(idx+1, int(math.Abs(float64(sum-w))))
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
