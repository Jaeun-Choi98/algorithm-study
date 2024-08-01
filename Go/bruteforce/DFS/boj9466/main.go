package main

import (
	"bufio"
	"fmt"
	"os"
	"slices"
	"strconv"
	"strings"
)

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	data    []int
	visited []bool
	ret     int
)

/*
[problem](https://www.acmicpc.net/problem/9466)
*/

func main() {
	defer writer.Flush()
	tc := nextInt()
	for tc > 0 {
		cnt := nextInt()
		st := stringTokenizer(" ")
		data = make([]int, cnt)
		visited = make([]bool, cnt)

		for i := 0; i < cnt; i++ {
			data[i] = atoi(st[i])
		}

		ret = cnt
		for i := 0; i < cnt; i++ {
			if visited[i] {
				continue
			}
			list := make([]int, 0)
			search(i, &list)
		}

		fmt.Fprintln(writer, ret)
		tc--
	}
}

func search(idx int, list *[]int) {
	visited[idx] = true
	*list = append(*list, idx)
	nextIdx := data[idx] - 1
	if visited[nextIdx] == true {
		if slices.Contains(*list, nextIdx) {
			ret -= len((*list)[slices.Index(*list, nextIdx):])
		}
		return
	} else {
		search(nextIdx, list)
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
