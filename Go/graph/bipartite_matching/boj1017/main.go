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
[problem](https://www.acmicpc.net/problem/1017)
*/

var (
	reader   = bufio.NewReader(os.Stdin)
	writer   = bufio.NewWriter(os.Stdout)
	N        int
	data     []int
	graph    [][]int
	NotPrime []bool
	matches  []int
	visit    []bool
)

func main() {
	defer writer.Flush()
	N = nextInt()
	data = make([]int, N)
	st := stringTokenizer(" ")
	for i := 0; i < N; i++ {
		data[i] = atoi(st[i])
	}
	NotPrime = make([]bool, 2001)
	for k := 2; k <= 2000; k++ {
		if NotPrime[k] {
			continue
		}
		for notPrime := 2 * k; notPrime <= 2000; notPrime += k {
			NotPrime[notPrime] = true
		}
	}

	// 이분 그래프
	graph = make([][]int, N)
	for i := 0; i < N; i++ {
		graph[i] = make([]int, 0)
	}
	for i := 0; i < N; i++ {
		for j := i + 1; j < N; j++ {
			if !NotPrime[data[i]+data[j]] {
				graph[i] = append(graph[i], j)
				graph[j] = append(graph[j], i)
			}
		}
	}
	//fmt.Println(graph)
	ansCnt := 0
	ans := make([]int, 0)
	for _, node := range graph[0] {
		matches = make([]int, N)
		for i := 0; i < N; i++ {
			matches[i] = -1
		}
		matchCnt := 1
		matches[node] = 0
		for i := 0; i < N; i++ {
			visit = make([]bool, N)
			visit[0] = true
			if dfs(i) {
				matchCnt++
			}
		}
		//fmt.Println(matches, matchCnt)
		if matchCnt == N {
			ansCnt++
			ans = append(ans, data[node])
		}
	}
	if ansCnt == 0 {
		fmt.Fprintln(writer, -1)
	} else {
		sort.Slice(ans, func(i, j int) bool { return ans[i] < ans[j] })
		for i := 0; i < ansCnt; i++ {
			fmt.Fprintf(writer, "%d ", ans[i])
		}
	}
}

func dfs(uNode int) bool {
	if visit[uNode] {
		return false
	}
	visit[uNode] = true
	for _, vNode := range graph[uNode] {
		if matches[vNode] == -1 || dfs(matches[vNode]) {
			matches[vNode] = uNode
			return true
		}
	}
	return false
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
