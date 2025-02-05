package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/3033)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	L      int
	data   string
)

func main() {
	defer writer.Flush()
	L, data = nextInt(), nextLine()
	suffixArray := getSuffixArray()
	lcp := getLCPArray(suffixArray)
	ans := 0
	for i := 0; i < L; i++ {
		ans = max(ans, lcp[i])
	}
	fmt.Fprint(writer, ans)
}

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

func getLCPArray(sf []int) []int {
	lcp := make([]int, L)
	rank := make([]int, L)
	for i := 0; i < L; i++ {
		rank[sf[i]] = i
	}
	comPfx := 0
	for i := 0; i < L; i++ {
		r := rank[i]
		if r == L-1 {
			continue
		}
		j := sf[r+1]
		for i+comPfx < L && j+comPfx < L && data[i+comPfx] == data[j+comPfx] {
			comPfx++
		}
		lcp[r] = comPfx
		if comPfx > 0 {
			comPfx--
		}
	}
	return lcp
}

func getSuffixArray() []int {
	sf := make([]int, L)
	rank, tmpRank := make([]int, L), make([]int, L)

	for i := 0; i < L; i++ {
		sf[i] = i
		rank[i] = int(data[i])
	}

	for k := 1; k < L; k *= 2 {
		sort.Slice(sf, func(i, j int) bool {
			idx1, idx2 := sf[i], sf[j]
			r1, r2 := rank[idx1], rank[idx2]
			rr1, rr2 := -1, -1
			if idx1+k < L {
				rr1 = rank[idx1+k]
			}
			if idx2+k < L {
				rr2 = rank[idx2+k]
			}
			return r1 < r2 || (r1 == r2 && rr1 < rr2)
		})

		tmpRank[sf[0]] = 0
		for i := 1; i < L; i++ {
			tmpRank[sf[i]] = tmpRank[sf[i-1]]
			r1, r2 := rank[sf[i-1]], rank[sf[i]]
			rr1, rr2 := -1, -1
			if sf[i-1]+k < L {
				rr1 = rank[sf[i-1]+k]
			}
			if sf[i]+k < L {
				rr2 = rank[sf[i]+k]
			}
			if r1 != r2 || rr1 != rr2 {
				tmpRank[sf[i]]++
			}
		}
		for i := 0; i < L; i++ {
			rank[i] = tmpRank[i]
		}
	}

	return sf
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}

func nextLine() string {
	line, _ := reader.ReadString('\n')
	return strings.TrimSpace(line)
}
