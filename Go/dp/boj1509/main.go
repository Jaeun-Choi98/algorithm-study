package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strings"
)

/*
팰린드롬 분할
[problem](https://www.acmicpc.net/problem/1509)
참고블로그(https://yabmoons.tistory.com/592)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	data    string
	isPalin [][]int
	cnt     []int
	leng    int
)

func main() {
	defer writer.Flush()
	data = nextLine()

	leng = len(data)
	isPalin = make([][]int, leng)
	for i := 0; i < leng; i++ {
		isPalin[i] = make([]int, leng)
	}

	for i := 0; i < leng; i++ {
		isPalin[i][i] = 1
	}
	for i := 0; i < leng-1; i++ {
		if data[i] == data[i+1] {
			isPalin[i][i+1] = 1
		}
	}
	for size := 2; size < leng; size++ {
		for l := 0; l+size < leng; l++ {
			r := l + size
			if data[l] == data[r] && isPalin[l+1][r-1] == 1 {
				isPalin[l][r] = 1
			} else {
				isPalin[l][r] = 2
			}
		}
	}

	//search(0, leng-1)

	cnt = make([]int, leng+1)
	for i := 1; i <= leng; i++ {
		cnt[i] = math.MaxInt
		for j := 1; j <= i; j++ {
			if isPalin[j-1][i-1] == 1 {
				cnt[i] = int(math.Min(float64(cnt[i]), float64(cnt[j-1])+1))
			}
		}
	}

	fmt.Fprint(writer, cnt[leng])
}

// 1이면 true, 2이면 false
func search(l, r int) {
	if l > r {
		return
	}
	fmt.Println(l, r)

	if isPalin[l][r] != 0 {
		return
	}

	if l == r {
		isPalin[l][r] = 1
		return
	}

	search(l+1, r)
	search(l, r-1)

	if data[l] == data[r] {
		if l+1 == r || isPalin[l+1][r-1] == 1 {
			isPalin[l][r] = 1
		}
	} else {
		isPalin[l][r] = 2
	}

}

func nextLine() string {
	line, _ := reader.ReadString('\n')
	return strings.TrimSpace(line)
}
