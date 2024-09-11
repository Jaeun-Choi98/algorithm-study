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
[참고블로그](https://blog.itcode.dev/posts/2021/06/06/a1006)
[problem](https://www.acmicpc.net/problem/1006)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N, W    int
	a, b, c []int
	data    [][]int
	ret     int
)

func main() {
	defer writer.Flush()
	tc := nextInt()
	for tc > 0 {
		st := stringTokenizer(" ")
		N, W = atoi(st[0]), atoi(st[1])
		a, b, c = make([]int, N), make([]int, N), make([]int, N+1)
		data = make([][]int, 2)
		for i := 0; i < 2; i++ {
			data[i] = make([]int, N)
			st = stringTokenizer(" ")
			for j := 0; j < N; j++ {
				data[i][j] = atoi(st[j])
			}
		}
		ret = 20001

		a[0], b[0] = 1, 1
		search(0)
		ret = int(math.Min(float64(ret), float64(c[N])))

		if N > 1 {
			if data[0][0]+data[0][N-1] <= W && data[1][0]+data[1][N-1] <= W {
				a[1] = 1
				b[1] = 1
				c[1] = 0
				search(1)
				ret = int(math.Min(float64(ret), float64(c[N-1]+2)))
			}

			if data[0][0]+data[0][N-1] <= W {
				a[1] = 2
				if data[1][0]+data[1][1] <= W {
					b[1] = 1
				} else {
					b[1] = 2
				}
				c[1] = 1
				search(1)
				ret = int(math.Min(float64(ret), float64(b[N-1]+1)))
			}

			if data[1][0]+data[1][N-1] <= W {
				if data[0][0]+data[0][1] <= W {
					a[1] = 1
				} else {
					a[1] = 2
				}
				b[1] = 2
				c[1] = 1
				search(1)
				ret = int(math.Min(float64(ret), float64(a[N-1]+1)))
			}
		}
		fmt.Fprintln(writer, ret)
		tc--
	}
}

func search(idx int) {
	for i := idx; i < N; i++ {
		c[i+1] = int(math.Min(float64(a[i])+1, float64(b[i])+1))
		if data[0][i]+data[1][i] <= W {
			c[i+1] = int(math.Min(float64(c[i+1]), float64(c[i])+1))
		}
		if i-1 >= 0 && data[0][i-1]+data[0][i] <= W && data[1][i-1]+data[1][i] <= W {
			c[i+1] = int(math.Min(float64(c[i+1]), float64(c[i-1])+2))
		}

		if i+1 < N {
			a[i+1] = c[i+1] + 1
			if data[0][i]+data[0][i+1] <= W {
				a[i+1] = int(math.Min(float64(b[i])+1, float64(a[i+1])))
			}

			b[i+1] = c[i+1] + 1
			if data[1][i]+data[1][i+1] <= W {
				b[i+1] = int(math.Min(float64(a[i])+1, float64(b[i+1])))
			}
		}
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
