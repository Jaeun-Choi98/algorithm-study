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
[problem](https://www.acmicpc.net/problem/17387)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
)

type Point struct {
	x, y int
}

func main() {
	defer writer.Flush()
	lineData := make([][]Point, 2)
	for i := 0; i < 2; i++ {
		st := stringTokenizer(" ")
		lineData[i] = make([]Point, 2)
		lineData[i][0] = Point{atoi(st[0]), atoi(st[1])}
		lineData[i][1] = Point{atoi(st[2]), atoi(st[3])}
	}
	if IsCross(lineData[0][0], lineData[0][1], lineData[1][0], lineData[1][1]) {
		fmt.Fprintln(writer, 1)
	} else {
		fmt.Fprintln(writer, 0)
	}
}

func isPointOnSegment(a1, b1, a2, b2 Point) bool {
	if (a2.x <= int(math.Max(float64(a1.x), float64(b1.x))) && int(math.Min(float64(a1.x), float64(b1.x))) <= a2.x &&
		a2.y <= int(math.Max(float64(a1.y), float64(b1.y))) && int(math.Min(float64(a1.y), float64(b1.y))) <= a2.y) ||
		(b2.x <= int(math.Max(float64(a1.x), float64(b1.x))) && int(math.Min(float64(a1.x), float64(b1.x))) <= b2.x &&
			b2.y <= int(math.Max(float64(a1.y), float64(b1.y))) && int(math.Min(float64(a1.y), float64(b1.y))) <= b2.y) {
		return true
	}
	return false
}

func IsCross(a1, b1, a2, b2 Point) bool {
	if CCW(a1, b1, a2)*CCW(a1, b1, b2) < 0 && CCW(a2, b2, a1)*CCW(a2, b2, b1) < 0 {
		return true
	} else if CCW(a1, b1, a2) == 0 && CCW(a1, b1, b2) == 0 {
		return isPointOnSegment(a1, b1, a2, b2) || isPointOnSegment(a2, b2, a1, b1)
	} else if CCW(a1, b1, a2)*CCW(a1, b1, b2) == 0 && !(CCW(a2, b2, a1)*CCW(a2, b2, b1) > 0) {
		return true
	} else if !(CCW(a1, b1, a2)*CCW(a1, b1, b2) > 0) && CCW(a2, b2, a1)*CCW(a2, b2, b1) == 0 {
		return true
	}
	return false
}

func CCW(a, b, target Point) int {
	vectorAtoB := Point{b.x - a.x, b.y - a.y}
	vectorAtoTar := Point{target.x - a.x, target.y - a.y}
	outerProduct := vectorAtoB.x*vectorAtoTar.y - vectorAtoB.y*vectorAtoTar.x
	if outerProduct < 0 {
		return -1
	} else if outerProduct == 0 {
		return 0
	} else {
		return 1
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
