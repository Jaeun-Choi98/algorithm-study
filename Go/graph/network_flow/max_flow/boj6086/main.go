package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

// [최대 유량 참고 블로그](https://everenew.tistory.com/177)
/*
[problem](https://www.acmicpc.net/problem/6086)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N      int
	flow   [][]int
	cap    [][]int
	ans    int
)

func main() {
	defer writer.Flush()
	N = nextInt()
	flow, cap = make([][]int, 52), make([][]int, 52)
	for i := 0; i < 52; i++ {
		flow[i] = make([]int, 52)
		cap[i] = make([]int, 52)
	}

	for i := 0; i < N; i++ {
		st := stringTokenizer(" ")
		v1, v2, c := []rune(st[0]), []rune(st[1]), atoi(st[2])
		if v1[0] > 90 {
			v1[0] = v1[0] - 'a' + 26
		} else {
			v1[0] = v1[0] - 'A'
		}
		if v2[0] > 90 {
			v2[0] = v2[0] - 'a' + 26
		} else {
			v2[0] = v2[0] - 'A'
		}

		cap[v1[0]][v2[0]] += c
		cap[v2[0]][v1[0]] += c
	}
	maximumFlow(0, 25)
	fmt.Fprintln(writer, ans)
}

func maximumFlow(s, e int) {
	for {
		p := make([]int, 52)
		for i := 0; i < 52; i++ {
			p[i] = -1
		}
		que := make([]int, 0)
		p[s] = s
		que = append(que, s)

		for len(que) > 0 {
			if p[e] != -1 {
				break
			}
			cur := que[0]
			que = que[1:]
			for next := 0; next < 52; next++ {
				if p[next] != -1 {
					continue
				}
				// 잔여 용량이 있다면, 다음 정점을 탐색
				if cap[cur][next]-flow[cur][next] > 0 {
					que = append(que, next)
					p[next] = cur
				}
			}
		}

		if p[e] == -1 {
			break
		}

		// source에서 sink까지 경로 중 최소 잔여 용량(cap - flow)
		// 최소 잔여 용량이 증가 경로에 흐를 유량
		minFlow := math.MaxInt32
		for i := e; i != s; i = p[i] {
			minFlow = min(minFlow, cap[p[i]][i]-flow[p[i]][i])
		}
		for i := e; i != s; i = p[i] {
			flow[p[i]][i] += minFlow
			flow[i][p[i]] -= minFlow
		}

		ans += minFlow
	}
}

func min(a, b int) int {
	if a < b {
		return a
	} else {
		return b
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
