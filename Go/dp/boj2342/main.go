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
[problem](https://www.acmicpc.net/problem/2342)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	d      [][][]int
	w      [][]int
	data   []int
	leng   int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	leng = len(st)
	d = make([][][]int, leng+1)
	data = make([]int, leng+1)
	w = make([][]int, 5)

	for i := 0; i < leng-1; i++ {
		data[i+1] = atoi(st[i])
	}

	for i := 0; i < leng; i++ {
		d[i] = make([][]int, 5)
		for j := 0; j < 5; j++ {
			d[i][j] = make([]int, 5)
			for k := 0; k < 5; k++ {
				d[i][j][k] = -1
			}
		}
	}

	for i := 0; i < 5; i++ {
		w[i] = make([]int, 5)
		for j := 0; j < 5; j++ {
			if j == 0 {
				w[i][j] = 0
			} else if i == j {
				w[i][j] = 1
			} else if i == 0 {
				w[i][j] = 2
			} else if math.Abs(float64(i)-float64(j)) == 2 {
				w[i][j] = 4
			} else {
				w[i][j] = 3
			}
		}
	}
	fmt.Fprintln(writer, search(0, 0, 0))
}

func search(cur, l, r int) int {
	if cur == leng {
		return 0
	}
	if l != 0 && r != 0 && l == r {
		return 400003
	}

	if d[cur][l][r] != -1 {
		return d[cur][l][r]
	}

	leftToNext := search(cur+1, data[cur+1], r) + w[l][data[cur+1]]
	rightToNext := search(cur+1, l, data[cur+1]) + w[r][data[cur+1]]
	if leftToNext < rightToNext {
		d[cur][l][r] = leftToNext
	} else {
		d[cur][l][r] = rightToNext
	}
	return d[cur][l][r]
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
