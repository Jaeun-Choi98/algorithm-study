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
[problem](https://www.acmicpc.net/problem/3679)
*/

type Point struct {
	x, y  int
	index int
}

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	n      int
	data   []Point
)

func main() {
	defer writer.Flush()
	tc := nextInt()
	for tc > 0 {
		st := stringTokenizer(" ")
		n = atoi(st[0])
		data = make([]Point, n)

		lowestY := 10001
		lowestX := 10001
		startPoint := 0
		for i := 0; i < n; i++ {
			data[i] = Point{atoi(st[i*2+1]), atoi(st[i*2+2]), i}
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
		sort.Slice(data, func(i, j int) bool { return comparator(pivot, data[i], data[j]) })

		answer1 := make([]int, 0)
		answer2 := make([]int, 0)

		answer1 = append(answer1, pivot.index)
		leng := len(data)
		for i := 0; i < leng; i++ {
			if ccw(pivot, data[leng-1], data[i]) == 0 {
				answer2 = append(answer2, data[i].index)
			} else {
				answer1 = append(answer1, data[i].index)
			}
		}

		for i := 0; i < len(answer1); i++ {
			fmt.Fprintf(writer, "%d ", answer1[i])
		}
		for i := len(answer2) - 1; i >= 0; i-- {
			fmt.Fprintf(writer, "%d ", answer2[i])
		}
		fmt.Fprintf(writer, "\n")
		tc--
	}
}

func comparator(a, b, c Point) bool {
	ret := ccw(a, b, c)
	if ret != 0 {
		return ret > 0
	} else {
		return distance(a, b) < distance(a, c)
	}
}

func distance(a, b Point) int {
	ret := math.Pow(float64(a.x-b.x), 2) + math.Pow(float64(a.y-b.y), 2)
	return int(ret)
}

func ccw(a, b, target Point) int {
	vectorAtoB := Point{b.x - a.x, b.y - a.y, 0}
	vectorAtoTarget := Point{target.x - a.x, target.y - a.y, 0}
	crossProduct := int64(vectorAtoB.x)*int64(vectorAtoTarget.y) - int64(vectorAtoB.y)*int64(vectorAtoTarget.x)
	if crossProduct < 0 {
		return -1
	} else if crossProduct == 0 {
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
