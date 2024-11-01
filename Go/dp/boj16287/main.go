package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/16287)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	w, n   int
	data   []int
	d      [][]int
	ans    bool
	d1     map[int][]int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	w, n = atoi(st[0]), atoi(st[1])
	data = make([]int, n)
	d = make([][]int, n)
	st = stringTokenizer(" ")
	for i := 0; i < n; i++ {
		data[i] = atoi(st[i])
		d[i] = make([]int, 5)
		for j := 0; j < 5; j++ {
			d[i][j] = -1
		}
	}
	ans = false
	//search(0, 0, 0)
	// if ans {
	// 	fmt.Fprintln(writer, "YES")
	// } else {
	// 	fmt.Fprintln(writer, "NO")
	// }

	// 정답 코드
	ans1 := false
	d1 = make(map[int][]int)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			curW := data[i] + data[j]
			tarW := w - curW

			lm, exist := d1[tarW]
			if exist {
				l, m := lm[0], lm[1]
				if i != l && i != m && j != l && j != m {
					ans1 = true
				}
			}

			_, existCurW := d1[curW]
			if !existCurW {
				d1[curW] = []int{i, j}
			}
		}
	}

	if ans1 {
		fmt.Fprintln(writer, "YES")
	} else {
		fmt.Fprintln(writer, "NO")
	}
}

// 시간 초과
func search(cur, cnt, sum int) {

	if ans {
		return
	}

	if cnt == 4 {
		if sum == w {
			ans = true
			return
		}
	}

	if cur == n || cnt == 4 {
		return
	}

	if d[cur][cnt] == sum {
		return
	}
	//fmt.Println(cur, cnt, sum)
	d[cur][cnt] = sum
	search(cur+1, cnt, sum)
	d[cur][cnt+1] = sum + data[cur]
	search(cur+1, cnt+1, sum+data[cur])
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
