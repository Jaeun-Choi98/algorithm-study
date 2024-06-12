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

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	datas  []int
	size   int
)

func main() {
	defer writer.Flush()
	size = nextInt()
	datas = make([]int, size)
	st := stringTokenizer(" ")

	for i := 0; i < size; i++ {
		datas[i] = atoi(st[i])
	}
	sort.Slice(datas, func(i, j int) bool { return datas[i] < datas[j] })
	//fmt.Fprintln(writer, datas)
	var min, sum int64
	ret := make([]int, 3)
	min = math.MaxInt64
	for i := 0; i < size-2; i++ {
		l := i + 1
		r := size - 1
		for l < r {
			sum = int64(datas[i]) + int64(datas[l]) + int64(datas[r])
			if min > int64(math.Abs(float64(sum))) {
				min = int64(math.Abs(float64(sum)))
				ret[0] = datas[i]
				ret[1] = datas[l]
				ret[2] = datas[r]
			}

			if sum > 0 {
				r -= 1
			} else {
				l += 1
			}
		}
	}
	fmt.Fprint(writer, ret)
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
