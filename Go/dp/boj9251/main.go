package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strings"
)

/*
dp, LCS
[problem](https://www.acmicpc.net/problem/9251)
*/

var (
	reader     = bufio.NewReader(os.Stdin)
	writer     = bufio.NewWriter(os.Stdout)
	str1, str2 []rune
	d          [][]int
)

func main() {
	defer writer.Flush()
	str1 = []rune(nextString())
	str2 = []rune(nextString())
	len1 := len(str1)
	len2 := len(str2)

	d = make([][]int, len1)
	for i := 0; i < len1; i++ {
		d[i] = make([]int, len2)
	}
	var res int
	for i := 0; i < len1; i++ {
		for j := 0; j < len2; j++ {
			if str1[i] == str2[j] {
				if i-1 < 0 || j-1 < 0 {
					d[i][j] = 1
				} else {
					d[i][j] = d[i-1][j-1] + 1
				}
			} else {
				if i-1 >= 0 {
					d[i][j] = d[i-1][j]
				}
				if j-1 >= 0 {
					if d[i][j] < d[i][j-1] {
						d[i][j] = d[i][j-1]
					}
				}
			}
			res = int(math.Max(float64(res), float64(d[i][j])))
		}
	}

	fmt.Fprint(writer, res)
}

func nextString() string {
	line, _ := reader.ReadString('\n')
	return strings.TrimSpace(line)
}
