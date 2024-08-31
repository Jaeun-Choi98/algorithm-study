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
[problem](https://www.acmicpc.net/problem/16566)
*/

var (
	reader       = bufio.NewReader(os.Stdin)
	writer       = bufio.NewWriter(os.Stdout)
	N, M, K      int
	data, parent []int
	dataValToIdx []int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M, K = atoi(st[0]), atoi(st[1]), atoi(st[2])
	parent = make([]int, N+1)
	dataValToIdx = make([]int, N+1)
	data = make([]int, M)
	st = stringTokenizer(" ")
	for i := 0; i < M; i++ {
		data[i] = atoi(st[i])
		parent[atoi(st[i])] = atoi(st[i])
	}
	sort.Slice(data, func(i, j int) bool { return data[i] < data[j] })
	for i := 0; i < M; i++ {
		dataValToIdx[data[i]] = i
	}
	st = stringTokenizer(" ")
	for i := 0; i < K; i++ {
		tar := atoi(st[i])
		large := search(tar)
		pLarge := find(large)
		fmt.Fprintf(writer, "%d\n", pLarge)
		pLargeIdx := dataValToIdx[pLarge]
		if pLargeIdx+1 < M {
			parent[pLarge] = data[pLargeIdx+1]
		}
	}
	//fmt.Println(data, parent, dataValToIdx)
}

func find(cur int) int {
	if parent[cur] == cur {
		return parent[cur]
	}
	parent[cur] = find(parent[cur])
	return parent[cur]
}

func search(tar int) int {
	s, e := 0, M-1
	for s <= e {
		m := (s + e) / 2
		if data[m] <= tar {
			s = m + 1
		} else {
			e = m - 1
		}
	}
	return data[s]
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
