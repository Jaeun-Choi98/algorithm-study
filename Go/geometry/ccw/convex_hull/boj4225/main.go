package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/4225)
*/

type Point struct {
	x, y int
}

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	data   []Point
)

func main() {
	defer writer.Flush()
	tc := 1
	for true {
		n := nextInt()
		if n == 0 {
			break
		}
		tmpX, tmpY := 10001, 10001
		pivotIndex := -1
		data = make([]Point, n)
		for i := 0; i < n; i++ {
			st := stringTokenizer(" ")
			data[i] = Point{atoi(st[0]), atoi(st[1])}
			if data[i].y < tmpY {
				tmpX, tmpY, pivotIndex = data[i].x, data[i].y, i
			} else if data[i].y == tmpY && data[i].x < tmpX {
				tmpX, tmpY, pivotIndex = data[i].x, data[i].y, i
			}
		}
		data[0], data[pivotIndex] = data[pivotIndex], data[0]
		pivot := data[0]
		data = data[1:]
		sort.Slice(data, func(i, j int) bool { return comparator(pivot, data[i], data[j]) })
		stack := make([]Point, n)
		stack[0] = pivot
		idx := 0
		for i, leng := 0, len(data); i < leng; i++ {
			nextPoint := data[i]
			for idx >= 1 {
				a, b := stack[idx-1], stack[idx]
				ret := ccw(a, b, nextPoint)
				if ret > 0 {
					break
				} else {
					idx--
				}
			}
			idx++
			stack[idx] = nextPoint
		}
		ans := 20000.0
		for i := 0; i <= idx; i++ {
			ai, bi := i, -1
			if ai == idx {
				bi = 0
			} else {
				bi = ai + 1
			}
			a, b := stack[ai], stack[bi]
			tmp := 0.0
			for j := 0; j <= idx; j++ {
				if j == ai || j == bi {
					continue
				}
				tmp = max(tmp, calc(a, b, stack[j]))
			}
			ans = min(ans, tmp)
		}
		ans = math.Ceil(ans*100) / 100
		fmt.Fprintf(writer, "Case %d: %.2f\n", tc, ans)
		tc++
	}
}

func calc(a, b, p Point) float64 {
	grad := float64(a.y-b.y) / float64(a.x-b.x)
	if math.IsInf(grad, 0) {
		return math.Abs(float64(p.x - a.x))
	} else if grad == 0 {
		return math.Abs(float64(p.y - a.y))
	}
	ret := math.Abs(float64(p.x)*grad+(-1)*float64(p.y)+float64(a.y)-grad*float64(a.x)) / math.Sqrt(math.Pow(grad, 2)+1)
	return ret
}

func min(a, b float64) float64 {
	if a < b {
		return a
	} else {
		return b
	}
}

func max(a, b float64) float64 {
	if a < b {
		return b
	} else {
		return a
	}
}

func comparator(a, b, c Point) bool {
	ret := ccw(a, b, c)
	if ret == 0 {
		if b.y != c.y {
			return b.y < c.y
		} else {
			return b.x < c.x
		}
	} else {
		return ret > 0
	}
}

func ccw(a, b, t Point) int {
	ab := Point{b.x - a.x, b.y - a.y}
	at := Point{t.x - a.x, t.y - a.y}
	crossProduct := ab.x*at.y - ab.y*at.x
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

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
