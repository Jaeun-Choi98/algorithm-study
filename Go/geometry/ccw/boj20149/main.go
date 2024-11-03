package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type Point struct {
	x, y float64
}

var (
	reader   = bufio.NewReader(os.Stdin)
	writer   = bufio.NewWriter(os.Stdout)
	lineData [][]*Point
)

func main() {
	defer writer.Flush()
	lineData = make([][]*Point, 2)
	for i := 0; i < 2; i++ {
		st := stringTokenizer(" ")
		lineData[i] = make([]*Point, 2)
		lineData[i][0], lineData[i][1] = &Point{aotf(st[0]), aotf(st[1])},
			&Point{aotf(st[2]), aotf(st[3])}
	}
	if iscross, point := IsCross(lineData[0][0], lineData[0][1], lineData[1][0], lineData[1][1]); iscross {
		if point == nil {
			fmt.Fprintln(writer, 1)
		} else {
			fmt.Fprintln(writer, 1)

			fmt.Fprintf(writer, "%.9f %.9f", point.x, point.y)
		}
	} else {
		fmt.Fprintln(writer, 0)
	}
}

func IsCross(a1, a2, b1, b2 *Point) (bool, *Point) {
	if CCW(a1, a2, b1)*CCW(a1, a2, b2) < 0 && CCW(b1, b2, a1)*CCW(b1, b2, a2) < 0 {
		return true, intersection(a1, a2, b1, b2)
	} else if CCW(a1, a2, b1) == 0 && CCW(a1, a2, b2) == 0 {
		if min(a1.x, a2.x) == max(b1.x, b2.x) && min(a1.y, a2.y) == max(b1.y, b2.y) {
			return true, &Point{min(a1.x, a2.x), min(a1.y, a2.y)}
		} else if max(a1.x, a2.x) == min(b1.x, b2.x) && max(a1.y, a2.y) == min(b1.y, b2.y) {
			return true, &Point{max(a1.x, a2.x), max(a1.y, a2.y)}
		} else if isPointOnSegment(a1, a2, b1, b2) || isPointOnSegment(b1, b2, a1, a2) {
			return true, nil
		}
	} else if CCW(a1, a2, b1)*CCW(a1, a2, b2) == 0 && !(CCW(b1, b2, a1)*CCW(b1, b2, a2) > 0) {
		if CCW(a1, a2, b1) == 0 {
			return true, b1
		} else if CCW(a1, a2, b2) == 0 {
			return true, b2
		}
	} else if !(CCW(a1, a2, b1)*CCW(a1, a2, b2) > 0) && CCW(b1, b2, a1)*CCW(b1, b2, a2) == 0 {
		if CCW(b1, b2, a1) == 0 {
			return true, a1
		} else if CCW(b1, b2, a2) == 0 {
			return true, a2
		}
	}
	return false, nil
}

func intersection(a1, a2, b1, b2 *Point) *Point {
	px := ((a1.x*a2.y-a1.y*a2.x)*(b1.x-b2.x) - (a1.x-a2.x)*(b1.x*b2.y-b1.y*b2.x)) / ((a1.x-a2.x)*(b1.y-b2.y) - (a1.y-a2.y)*(b1.x-b2.x))
	py := ((a1.x*a2.y-a1.y*a2.x)*(b1.y-b2.y) - (a1.y-a2.y)*(b1.x*b2.y-b1.y*b2.x)) / ((a1.x-a2.x)*(b1.y-b2.y) - (a1.y-a2.y)*(b1.x-b2.x))
	return &Point{x: px, y: py}
}

func isPointOnSegment(a1, a2, b1, b2 *Point) bool {
	if (min(a1.x, a2.x) <= b1.x && b1.x <= max(a1.x, a2.x) && min(a1.y, a2.y) <= b1.y && b1.y <= max(a1.y, a2.y)) ||
		(min(a1.x, a2.x) <= b2.x && b2.x <= max(a1.x, a2.x) && min(a1.y, a2.y) <= b2.y && b2.y <= max(a1.y, a2.y)) {
		return true
	} else {
		return false
	}
}

func min(a, b float64) float64 {
	if a < b {
		return a
	} else {
		return b
	}
}

func max(a, b float64) float64 {
	if a > b {
		return a
	} else {
		return b
	}
}

func CCW(a1, a2, b1 *Point) int {
	va := Point{a2.x - a1.x, a2.y - a1.y}
	vb := Point{b1.x - a1.x, b1.y - a1.y}
	crossProduct := va.x*vb.y - va.y*vb.x
	if crossProduct == 0 {
		return 0
	} else if crossProduct > 0 {
		return 1
	} else {
		return -1
	}
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func aotf(str string) float64 {
	ret, _ := strconv.ParseFloat(str, 64)
	return ret
}
