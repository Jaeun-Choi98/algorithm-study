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
	우수 마을 - 골드2
	[problem](https://www.acmicpc.net/problem/1949)
*/

var (
	reader   = bufio.NewReader(os.Stdin)
	writer   = bufio.NewWriter(os.Stdout)
	n        int
	w        []int
	d, grahp [][]int
)

func main() {
	defer writer.Flush()
	n = NextInt()
	w = make([]int, n)
	d = make([][]int, n)
	grahp = make([][]int, n)

	st := stringTokenizer(" ")
	for i := 0; i < n; i++ {
		d[i] = make([]int, 2)
		grahp[i] = make([]int, 0)
		w[i] = Atoi(st[i])
	}

	var s, e int
	for i := 0; i < n-1; i++ {
		st = stringTokenizer(" ")
		s = Atoi(st[0]) - 1
		e = Atoi(st[1]) - 1
		grahp[s] = append(grahp[s], e)
		grahp[e] = append(grahp[e], s)
	}

	res1, res2 := search(0, -1)

	//fmt.Fprint(writer, d)

	if res1 < res2 {
		fmt.Fprint(writer, res2)
	} else {
		fmt.Fprint(writer, res1)
	}
}

func search(cur, pre int) (int, int) {

	if d[cur][0] != 0 || d[cur][1] != 0 {
		return d[cur][0], d[cur][1]
	}

	d[cur][0] = 0
	d[cur][1] = w[cur]

	for _, next := range grahp[cur] {
		if next == pre {
			continue
		}
		buf1, buf2 := search(next, cur)
		d[cur][1] += buf1
		d[cur][0] += int(math.Max(float64(buf1), float64(buf2)))
	}

	return d[cur][0], d[cur][1]
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func Atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func NextInt() int {
	line, _ := reader.ReadString('\n')
	return Atoi(strings.TrimSpace(line))
}
