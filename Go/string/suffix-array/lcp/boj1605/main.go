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
[problem](https://www.acmicpc.net/problem/1605)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	leng   int
	input  string
)

func main() {
	defer writer.Flush()
	leng = nextInt()
	input = nextLine()
	suffixArray := NewSuffixArray(input, leng)
	lcp := getLCPArray(input, suffixArray)
	ans := 0
	for i := 0; i < leng-1; i++ {
		ans = max(ans, lcp[i])
	}
	fmt.Fprintln(writer, ans)
}

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

// Kasai algorithm
func getLCPArray(str string, sf []int) []int {
	leng := len(str)
	lcp := make([]int, leng-1)
	ranks := make([]int, leng)
	for i := 0; i < leng; i++ {
		ranks[sf[i]] = i
	}
	comPfx := 0

	for i := 0; i < leng; i++ {
		rank := ranks[i]
		if rank == leng-1 {
			continue
		}
		j := sf[rank+1]
		for i+comPfx < leng && j+comPfx < leng && str[i+comPfx] == str[j+comPfx] {
			comPfx++
		}

		lcp[rank] = comPfx
		if comPfx > 0 {
			comPfx--
		}
	}

	return lcp
}

// Prefix Doubling Algorithm
func NewSuffixArray(s string, n int) []int {
	suffixArray := make([]int, n)
	rank, tmpRank := make([]int, n), make([]int, n)

	for i := 0; i < n; i++ {
		suffixArray[i] = i
		rank[i] = int(s[i])
	}

	for k := 1; k < n; k *= 2 {
		sort.Slice(suffixArray, func(i, j int) bool {
			idx1, idx2 := suffixArray[i], suffixArray[j]
			r1, r2 := rank[idx1], rank[idx2]
			rr1, rr2 := -1, -1
			if idx1+k < n {
				rr1 = rank[idx1+k]
			}
			if idx2+k < n {
				rr2 = rank[idx2+k]
			}
			return r1 < r2 || (r1 == r2 && rr1 < rr2)
		})
		tmpRank[suffixArray[0]] = 0
		for i := 1; i < n; i++ {
			tmpRank[suffixArray[i]] = tmpRank[suffixArray[i-1]]
			r1, r2 := rank[suffixArray[i-1]], rank[suffixArray[i]]
			rr1, rr2 := -1, -1
			if suffixArray[i-1]+k < n {
				rr1 = rank[suffixArray[i-1]+k]
			}
			if suffixArray[i]+k < n {
				rr2 = rank[suffixArray[i]+k]
			}
			if r1 != r2 || rr1 != rr2 {
				tmpRank[suffixArray[i]]++
			}
		}
		for i := 0; i < n; i++ {
			rank[i] = tmpRank[i]
		}
	}

	return suffixArray
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
