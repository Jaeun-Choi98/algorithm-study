package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
배낭 문제 + 분리집합(Disjoint set/Union-Find)
[problem](https://www.acmicpc.net/problem/20303)
*/

type ParentInfo struct {
	count    int
	totalVal int
}

var (
	writer          = bufio.NewWriter(os.Stdout)
	reader          = bufio.NewReader(os.Stdin)
	N, M, K         int
	data            []int
	parent          []int
	parentIdxToInfo map[int]ParentInfo
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	N, M, K = atoi(st[0]), atoi(st[1]), atoi(st[2])
	data = make([]int, N)
	parent = make([]int, N)
	parentIdxToInfo = make(map[int]ParentInfo)

	st = stringTokenizer(" ")
	for i := 0; i < N; i++ {
		data[i] = atoi(st[i])
		parent[i] = i
		parentIdxToInfo[i] = ParentInfo{1, atoi(st[i])}
	}

	for i := 0; i < M; i++ {
		st := stringTokenizer(" ")
		a, b := atoi(st[0])-1, atoi(st[1])-1
		pA, pB := find(a), find(b)
		if pA != pB {
			union(pA, pB)
		}
	}

	unionData := make([]ParentInfo, 0)
	for _, info := range parentIdxToInfo {
		unionData = append(unionData, info)
	}

	//fmt.Println(unionData)

	d := make([][]int, len(unionData)+1)
	for i := 0; i <= len(unionData); i++ {
		d[i] = make([]int, K)
	}

	for i, leng := 1, len(unionData)+1; i < leng; i++ {
		info := unionData[i-1]
		for j := 0; j < K; j++ {
			if j-info.count >= 0 && d[i-1][j-info.count]+info.totalVal > d[i-1][j] {
				d[i][j] = d[i-1][j-info.count] + info.totalVal
			} else {
				d[i][j] = d[i-1][j]
			}
		}
	}
	fmt.Fprintln(writer, d[len(unionData)][K-1])
}

func union(a, b int) {
	var lr, li int
	if a < b {
		li, lr = a, b
	} else {
		li, lr = b, a
	}
	parent[lr] = li
	liInfo := parentIdxToInfo[li]
	lrInfo := parentIdxToInfo[lr]
	parentIdxToInfo[li] = ParentInfo{liInfo.count + lrInfo.count, liInfo.totalVal + lrInfo.totalVal}
	delete(parentIdxToInfo, lr)
}

func find(n int) int {
	if parent[n] == n {
		return parent[n]
	}
	parent[n] = find(parent[n])
	return parent[n]
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
