package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
부분수열의 합2
[problem](https://www.acmicpc.net/problem/1208)
*/

var (
	writer   = bufio.NewWriter(os.Stdout)
	reader   = bufio.NewReader(os.Stdin)
	data     []int
	left     []int64
	right    []int64
	leftMap  map[int64]int
	rightMap map[int64]int
	n, s     int
	res      int64
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	n, s = atoi(st[0]), atoi(st[1])
	st = stringTokenizer(" ")
	data = make([]int, n)
	for i := 0; i < n; i++ {
		data[i] = atoi(st[i])
	}
	left = make([]int64, 0)
	right = make([]int64, 0)

	// 시간초과 -> map 이용
	leftMap = make(map[int64]int)
	rightMap = make(map[int64]int)

	leftMakeSubset(0, n/2, 0)
	rightMakeSubset(n/2, n, 0)

	for k, lv := range leftMap {
		target := int64(s) - k
		rv, ok := rightMap[target]
		if ok {
			res += int64(lv * rv)
		}
	}

	// 52% 시간 초과
	//sort.Slice(right, func(i, j int) bool { return right[i] < right[j] })
	// var cntL, cntR, idx int
	// var target int64
	// for i, size := 0, len(left); i < size; i++ {
	// 	cntL = 1
	// 	if i+1 < size && left[i] == left[i+1] {
	// 		cntL++
	// 		i++
	// 	}
	// 	target = int64(s) - left[i]
	// 	idx, isSearch = LowerSearch(right, target)
	// 	cntR = 0
	// 	for idx < len(right) && right[idx] == target {
	// 		cntR++
	// 		idx++
	// 	}
	// 	res += int64(cntL * cntR)
	// }

	if s == 0 {
		res -= 1
	}
	fmt.Fprint(writer, res)
}

// target과 같은 값을 반환. 만약 target과 같은 값이 여러 개라면 가장 좌측에 있는 값을 반환
func LowerSearch(arr []int64, target int64) int {
	l, r := 0, len(arr)-1
	for l <= r {
		mid := (l + r) / 2
		if arr[mid] >= target {
			r = mid - 1
		} else {
			l = mid + 1
		}
	}
	return l
}

func leftMakeSubset(s, e int, sum int64) {
	if s == e {
		left = append(left, sum)
		leftMap[sum]++
		return
	}
	leftMakeSubset(s+1, e, sum)
	leftMakeSubset(s+1, e, sum+int64(data[s]))
}

func rightMakeSubset(s, e int, sum int64) {
	if s == e {
		right = append(right, sum)
		rightMap[sum]++
		return
	}
	rightMakeSubset(s+1, e, sum)
	rightMakeSubset(s+1, e, sum+int64(data[s]))
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
