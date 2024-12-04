package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/1671)
*/

type Shark struct {
	a, b, c int
}

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N      int
	sharks []Shark
	graph  [][]int
	match  []int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	sharks = make([]Shark, N)
	for i := 0; i < N; i++ {
		st := stringTokenizer(" ")
		a, b, c := atoi(st[0]), atoi(st[1]), atoi(st[2])
		sharks[i] = Shark{a, b, c}
	}

	graph = make([][]int, N)
	match = make([]int, N)
	for i := 0; i < N; i++ {
		graph[i] = make([]int, 0)
		match[i] = -1
	}
	for i := 0; i < N; i++ {
		for j := i + 1; j < N; j++ {
			s1, s2 := sharks[i], sharks[j]
			if s1.a == s2.a && s1.b == s2.b && s1.c == s2.c {
				graph[i] = append(graph[i], j)
				continue
			}
			if s1.a >= s2.a && s1.b >= s2.b && s1.c >= s2.c {
				graph[i] = append(graph[i], j)
			}
			if s2.a >= s1.a && s2.b >= s1.b && s2.c >= s1.c {
				graph[j] = append(graph[j], i)
			}
		}
	}
	cnt := 0
	for i := 0; i < N; i++ {
		for j := 0; j < 2; j++ {
			visit := make([]bool, N)
			if dfs(i, &visit) {
				cnt++
			}
		}
	}

	fmt.Fprintln(writer, N-cnt)
}

func dfs(u int, visit *[]bool) bool {
	if (*visit)[u] {
		return false
	}
	(*visit)[u] = true
	for _, v := range graph[u] {
		if match[v] == -1 || dfs(match[v], visit) {
			match[v] = u
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
