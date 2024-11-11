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
[problem](https://www.acmicpc.net/problem/1214)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	D, P, Q int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	D, P, Q = atoi(st[0]), atoi(st[1]), atoi(st[2])

	// D <= Pn + Qm
	if Q > P {
		P, Q = Q, P
	}

	ans1 := solution()
	fmt.Fprintln(writer, ans1)
	return

	ans := math.MaxInt32
	for n := 0; n <= D/P+1; n++ {
		if P*n == D {
			ans = min(ans, D)
			break
		}
		tmp := D - P*n
		if tmp < 0 {
			ans = min(ans, P*n)
			continue
		}
		m := tmp / Q
		if tmp%Q == 0 {
			ans = min(ans, P*n+Q*m)
		} else {
			ans = min(ans, P*n+Q*(m+1))
		}
	}
	//fmt.Fprintln(writer, ans)
}

// 정답 코드 참고
func solution() int {
	maxN := D/P + 1
	ret := maxN * P
	for n := D / P; n >= 0; n-- {
		div, mod := (D-P*n)/Q, (D-P*n)%Q
		if mod == 0 {
			return D
		}
		tmp := P*n + (div+1)*Q
		if ret == tmp {
			break
		}
		ret = min(ret, tmp)
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
