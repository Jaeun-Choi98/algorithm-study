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
[problem](https://www.acmicpc.net/problem/2618)
*/

type Point struct {
	x, y int
}

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	d      [][]int
	N, W   int
	data   []Point
	ans2   []int
)

func main() {
	defer writer.Flush()
	N, W = nextInt(), nextInt()
	d = make([][]int, W+1)
	for i := 0; i <= W; i++ {
		d[i] = make([]int, W+1)
		for j := 0; j <= W; j++ {
			d[i][j] = -1
		}
	}
	data = make([]Point, W+1)
	ans2 = make([]int, W+1)
	for i := 1; i <= W; i++ {
		st := stringTokenizer(" ")
		data[i] = Point{atoi(st[0]), atoi(st[1])}
	}
	search1(0, 0, 1)
	search2(0, 0, 1)
	fmt.Fprintln(writer, d[0][0])
	for i := 1; i <= W; i++ {
		fmt.Fprintln(writer, ans2[i])
	}
}

func search2(w1, w2, next int) {
	if next == W+1 {
		return
	}

	p1, p2 := data[w1], data[w2]
	if w1 == 0 {
		p1 = Point{1, 1}
	}
	if w2 == 0 {
		p2 = Point{N, N}
	}

	cost1 := calcDist(p1, data[next]) + d[next][w2]
	cost2 := calcDist(p2, data[next]) + d[w1][next]

	if cost1 < cost2 {
		ans2[next] = 1
		search2(next, w2, next+1)
	} else {
		ans2[next] = 2
		search2(w1, next, next+1)
	}

}

func search1(w1, w2, next int) int {
	if next == W+1 {
		return 0
	}

	if d[w1][w2] != -1 {
		return d[w1][w2]
	}

	d[w1][w2] = math.MaxInt32
	p1, p2 := data[w1], data[w2]
	if w1 == 0 {
		p1 = Point{1, 1}
	}
	if w2 == 0 {
		p2 = Point{N, N}
	}

	cost1 := calcDist(p1, data[next]) + search1(next, w2, next+1)
	cost2 := calcDist(p2, data[next]) + search1(w1, next, next+1)

	if cost1 < cost2 {
		d[w1][w2] = cost1
	} else {
		d[w1][w2] = cost2
	}

	return d[w1][w2]
}

func calcDist(p1, p2 Point) int {
	return int(math.Abs(float64(p2.x)-float64(p1.x))) + int(math.Abs(float64(p2.y)-float64(p1.y)))
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
