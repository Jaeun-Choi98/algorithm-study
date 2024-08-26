package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/10775)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	parent []int
)

func main() {
	defer writer.Flush()
	G := nextInt()
	P := nextInt()
	parent = make([]int, G+1)
	for i := 0; i <= G; i++ {
		parent[i] = i
	}

	var ret int

	// 만약 루트가 0이라면, 더 이상 도킹할 수 없음.
	for i := 0; i < P; i++ {
		cur := nextInt()
		pCur := Find(cur)
		if pCur == 0 {
			break
		}
		ret++
		parent[pCur] = pCur - 1
	}

	fmt.Fprintln(writer, ret)
}

func Find(cur int) int {
	if parent[cur] == cur {
		return cur
	}
	parent[cur] = Find(parent[cur])
	return parent[cur]
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	ret, _ := strconv.Atoi(strings.TrimSpace(line))
	return ret
}
