package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/2261)
*/

type Point struct {
	x, y int
}

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	n      int
	points []Point
)

func main() {
	defer writer.Flush()
	n = nextInt()
	points = make([]Point, n)
	for i := 0; i < n; i++ {
		st := stringTokenizer(" ")
		x, y := atoi(st[0]), atoi(st[1])
		points[i] = Point{x, y}
	}
	sort.Slice(points, func(i, j int) bool { return points[i].x < points[j].x })
	ans := divide(0, n-1)
	fmt.Fprintln(writer, ans)
}

func divide(l, r int) int {
	leng := r - l
	if leng < 3 {
		return calc(l, r)
	}
	m := (l + r) / 2
	lRet, rRet := divide(l, m), divide(m+1, r)
	ret := min(lRet, rRet)
	if ret == 0 {
		return ret
	}
	// 분할 기준으로 주변 점들을 탐색해주어야 함.
	mpoints := make([]Point, 0)
	p := points[m]
	for i := l; i <= r; i++ {
		if (p.x-points[i].x)*(p.x-points[i].x) <= ret {
			mpoints = append(mpoints, points[i])
		}
	}
	sort.Slice(mpoints, func(i, j int) bool { return mpoints[i].y < mpoints[j].y })

	for i := 0; i < len(mpoints); i++ {
		for j := i + 1; j < len(mpoints); j++ {
			if (mpoints[j].y-mpoints[i].y)*(mpoints[j].y-mpoints[i].y) > ret {
				break
			}
			dist := (mpoints[j].x-mpoints[i].x)*(mpoints[j].x-mpoints[i].x) + (mpoints[j].y-mpoints[i].y)*(mpoints[j].y-mpoints[i].y)
			ret = min(ret, dist)
		}
	}
	return ret
}

func calc(l, r int) int {
	ret := 800000001
	for i := l; i < r; i++ {
		for j := i + 1; j <= r; j++ {
			dist := (points[j].x-points[i].x)*(points[j].x-points[i].x) + (points[j].y-points[i].y)*(points[j].y-points[i].y)
			ret = min(ret, dist)
		}
	}
	return ret
}

func min(a, b int) int {
	if a < b {
		return a
	} else {
		return b
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
