package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
정답 참고 블로그(https://dev-sheep.tistory.com/64)
[problem](https://www.acmicpc.net/problem/1086)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N, K   int
	data   []string
	d      [][]int64
	rd     [][]int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	data = make([]string, N)
	for i := 0; i < N; i++ {
		data[i] = nextStr()
	}
	K = nextInt()

	caseCnt := 1 << N
	d = make([][]int64, caseCnt)
	for i := 0; i < caseCnt; i++ {
		d[i] = make([]int64, K)
		for j := 0; j < K; j++ {
			d[i][j] = -1
		}
	}

	rd = make([][]int, N)
	for i := 0; i < N; i++ {
		rd[i] = make([]int, K)
		for j := 0; j < K; j++ {
			rd[i][j] = -1
		}
	}
	cnt := search(0, 0)
	if cnt == 0 {
		fmt.Fprintln(writer, "0/1")
	} else {
		var allCaseCnt int64
		allCaseCnt = 1
		for i := 1; i <= N; i++ {
			allCaseCnt *= int64(i)
		}
		gcdVal := gcd(cnt, allCaseCnt)
		fmt.Fprint(writer, cnt/gcdVal)
		fmt.Fprint(writer, "/")
		fmt.Fprint(writer, allCaseCnt/gcdVal)
	}

}

func search(bitmask, mod int) int64 {
	if bitmask == (1<<N)-1 {
		if mod == 0 {
			return int64(1)
		} else {
			return 0
		}
	}
	if d[bitmask][mod] != -1 {
		return d[bitmask][mod]
	}
	var ret int64
	for i := 0; i < N; i++ {
		idx := 1 << i
		if bitmask&idx > 0 {
			continue
		}
		ret += search(bitmask|idx, rest(i, mod))
	}
	d[bitmask][mod] = ret
	return ret
}

// mod 매개변수는 i번째 데이터 이전에 계산된 합친수의 나머지
func rest(i, mod int) int {
	if rd[i][mod] != -1 {
		return rd[i][mod]
	}
	ret := mod
	num := data[i]
	for i := 0; i < len(num); i++ {
		ret *= 10
		ret += int(num[i] - '0')
		ret %= K
	}
	rd[i][mod] = ret
	return ret
}

func gcd(a, b int64) int64 {
	if b == 0 {
		return a
	}
	ret := gcd(b, a%b)
	return ret
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}

func nextStr() string {
	line, _ := reader.ReadString('\n')
	return strings.TrimSpace(line)
}
