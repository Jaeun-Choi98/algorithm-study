package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strings"
)

/*
LCS2
[problem](https://www.acmicpc.net/problem/9252)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
)

func main() {
	defer writer.Flush()
	a, _ := reader.ReadString('\n')
	b, _ := reader.ReadString('\n')
	a = strings.TrimSpace(a)
	b = strings.TrimSpace(b)

	dataA := []rune(a)
	dataB := []rune(b)

	la := len(dataA)
	lb := len(dataB)

	d := make([][]int, la+1)

	for i := 0; i <= la; i++ {
		d[i] = make([]int, lb+1)
	}

	//fmt.Println(len(res2))
	for i := 1; i <= la; i++ {
		for j := 1; j <= lb; j++ {
			if dataA[i-1] == dataB[j-1] {
				d[i][j] = d[i-1][j-1] + 1
			} else {
				d[i][j] = int(math.Max(float64(d[i-1][j]), float64(d[i][j-1])))
			}
		}
	}

	fmt.Fprintln(writer, d[la][lb])

	var res2 []rune
	idx := d[la][lb]
	res2 = make([]rune, idx)
	idx--
	x, y := la, lb

	for x != 0 && y != 0 {
		if dataA[x-1] == dataB[y-1] {
			res2[idx] = dataA[x-1]
			idx--
			x--
			y--
		} else {
			if d[x][y] == d[x-1][y] {
				x--
			} else if d[x][y] == d[x][y-1] {
				y--
			}
		}
	}

	fmt.Fprintln(writer, string(res2))
}
