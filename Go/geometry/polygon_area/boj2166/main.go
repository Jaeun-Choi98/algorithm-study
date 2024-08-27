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
[problem](https://www.acmicpc.net/problem/2166)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	data   []Point
	ret    float64
)

type Point struct {
	x, y int
}

func main() {
	defer writer.Flush()
	pointCnt := nextInt()
	data = make([]Point, pointCnt)
	for i := 0; i < pointCnt; i++ {
		st := stringTokenizer(" ")
		data[i] = Point{atoi(st[0]), atoi(st[1])}
	}
	pivot := data[0]
	for i := 1; i < pointCnt-1; i++ {
		ret += calc(pivot, data[i], data[i+1])
	}
	ret = math.Abs(ret)
	ret = math.Round(ret*10) / 10
	fmt.Fprintf(writer, "%.1f\n", ret)
}

func calc(pivot, a, b Point) float64 {
	var area float64
	vectorPtoA := Point{a.x - pivot.x, a.y - pivot.y}
	vectorPtoB := Point{b.x - pivot.x, b.y - pivot.y}
	area = (float64(vectorPtoA.x*vectorPtoB.y) - float64(vectorPtoA.y*vectorPtoB.x)) / 2
	return area
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
