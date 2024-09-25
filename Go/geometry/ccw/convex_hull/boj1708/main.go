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
외적을 이용한 ccw
1. 기준점을 기준으로 점들을 반시계 방향으로 정렬
2. 정렬된 점들을 탐색하면서 볼록(시계 방향)인지 검사
[problem](https://www.acmicpc.net/problem/1708)
*/

type Point struct {
	x, y int
}

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N      int
	data   []Point
	stack  []Point
	curIdx int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	data = make([]Point, N)

	// 시작점 정함
	lowestY := 40001
	lowestX := 40001
	startPoint := 0
	for i := 0; i < N; i++ {
		st := stringTokenizer(" ")
		data[i] = Point{atoi(st[0]), atoi(st[1])}
		if lowestY > data[i].y {
			lowestY = data[i].y
			lowestX = data[i].x
			startPoint = i
		} else if lowestY == data[i].y {
			if lowestX > data[i].x {
				lowestY = data[i].y
				lowestX = data[i].x
				startPoint = i
			}
		}
	}
	data[0], data[startPoint] = data[startPoint], data[0]
	pivot := data[0]
	data = data[1:]
	sort.Slice(data, func(i, j int) bool {
		ccwVal := ccw(pivot, data[i], data[j])
		if ccwVal != 0 {
			return ccwVal > 0
		} else {
			if data[i].y != data[j].y {
				return data[i].y < data[j].y
			} else {
				return data[i].x < data[j].x
			}
		}
	})

	stack = make([]Point, N-1)
	stack[0] = pivot
	curIdx = 0
	for i := 0; i < N-1; i++ {
		for curIdx >= 1 {
			a, b, c := stack[curIdx-1], stack[curIdx], data[i]
			if ccw(a, b, c) > 0 {
				break
			} else {
				curIdx--
			}
		}
		curIdx++
		stack[curIdx] = data[i]
	}

	fmt.Fprintln(writer, curIdx+1)
}

func ccw(a, b, target Point) int {
	vectorAtoB := Point{b.x - a.x, b.y - a.y}
	vectorAtoTarget := Point{target.x - a.x, target.y - a.y}
	outerProduct := int64(vectorAtoB.x)*int64(vectorAtoTarget.y) - int64(vectorAtoB.y)*int64(vectorAtoTarget.x)
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

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
