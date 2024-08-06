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
ccw, union-find
[problem](https://www.acmicpc.net/problem/2162)
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
	lineCount := nextInt()
	lineSegData := make([][]Point, lineCount)
	unionArr := make([]int, lineCount)
	cntArr := make([]int, lineCount)

	var st []string
	for i := 0; i < lineCount; i++ {
		unionArr[i] = i
		st = stringTokenizer(" ")
		lineSegData[i] = make([]Point, 2)
		lineSegData[i][0] = Point{atoi(st[0]), atoi(st[1])}
		lineSegData[i][1] = Point{atoi(st[2]), atoi(st[3])}
	}
	for i := 0; i < lineCount; i++ {
		for j := i + 1; j < lineCount; j++ {
			if IsCross(lineSegData[i][0], lineSegData[i][1], lineSegData[j][0], lineSegData[j][1]) {
				a := Find(unionArr, i)
				b := Find(unionArr, j)
				if a < b {
					Union(unionArr, b, a)
				} else {
					Union(unionArr, a, b)
				}
			}
		}
	}

	for i := 0; i < lineCount; i++ {
		// 부모 노드가 갱신되었는지 모르기 때문. 한 번더 갱신
		Find(unionArr, i)
		cntArr[unionArr[i]]++
	}

	var ret1, ret2 int
	for i := 0; i < lineCount; i++ {
		if cntArr[i] > 0 {
			ret1++
		}
		if ret2 < cntArr[i] {
			ret2 = cntArr[i]
		}
	}
	//fmt.Println(unionArr, cntArr)
	fmt.Fprintf(writer, "%d\n%d ", ret1, ret2)
}

func Find(arr []int, node int) int {
	if arr[node] == node {
		return arr[node]
	}
	arr[node] = Find(arr, arr[node])
	return arr[node]
}

func Union(arr []int, node1, node2 int) {
	arr[node1] = node2
}

func IsCross(a1, b1, a2, b2 Point) bool {
	if CCW(a1, b1, a2)*CCW(a1, b1, b2) < 0 && CCW(a2, b2, a1)*CCW(a2, b2, b1) < 0 {
		return true
	} else if CCW(a1, b1, a2) == 0 && CCW(a1, b1, b2) == 0 { // <- 4개의 점이 한 직선상에 있는 경우를 걸러야 밑에 있는 else if의 조건들이 문제가 되지않음.
		return isPointOnSegment(a1, b1, a2, b2) || isPointOnSegment(a2, b2, a1, b1)
	} else if CCW(a1, b1, a2)*CCW(a1, b1, b2) == 0 && !(CCW(a2, b2, a1)*CCW(a2, b2, b1) > 0) {
		return true
	} else if !(CCW(a1, b1, a2)*CCW(a1, b1, b2) > 0) && CCW(a2, b2, a1)*CCW(a2, b2, b1) == 0 {
		return true
	}
	return false
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

func CCW(a, b, target Point) int {
	vectorAtoB := Point{b.x - a.x, b.y - a.y}
	vectorAtoTarget := Point{target.x - a.x, target.y - a.y}
	outerProduct := vectorAtoB.x*vectorAtoTarget.y - vectorAtoB.y*vectorAtoTarget.x
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
