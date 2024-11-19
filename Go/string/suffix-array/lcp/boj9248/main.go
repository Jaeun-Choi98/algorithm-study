package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strings"
)

var (
	reader      = bufio.NewReader(os.Stdin)
	writer      = bufio.NewWriter(os.Stdout)
	input       string
	suffixArray []int
	lcp         []int
)

func main() {
	defer writer.Flush()
	input = nextLine()
	suffixArray = getSuffixArray()
	lcp = getLCPArray(input, suffixArray)
	for i := 0; i < len(input); i++ {
		fmt.Fprintf(writer, "%d ", suffixArray[i]+1)
	}
	fmt.Fprintln(writer)
	for i := 0; i < len(input); i++ {
		if i == 0 {
			fmt.Fprintf(writer, "x ")
		} else {
			fmt.Fprintf(writer, "%d ", lcp[i-1])
		}

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

func getSuffixArray() []int {
	indexList := make([]int, len(input))
	suffixes := make([]string, len(input))
	for i, leng := 0, len(input); i < leng; i++ {
		indexList[i], suffixes[i] = i, input[i:]
	}
	sort.Slice(indexList, func(i, j int) bool { return suffixes[indexList[i]] < suffixes[indexList[j]] })
	return indexList
}

func nextLine() string {
	line, _ := reader.ReadString('\n')
	return strings.TrimSpace(line)
}
