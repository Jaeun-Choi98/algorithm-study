package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/13141)
*/

type Edge struct {
	S, E, L int
}

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	d      [][]int
	V, E   int
	edges  []Edge
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	V, E = atoi(st[0]), atoi(st[1])
	edges = make([]Edge, E)
	d = make([][]int, V+1)
	for i := 0; i <= V; i++ {
		d[i] = make([]int, V+1)
		for j := 0; j <= V; j++ {
			if i == j {
				d[i][j] = 0
			} else {
				d[i][j] = 20001
			}
		}
	}
	for i := 0; i < E; i++ {
		st = stringTokenizer(" ")
		s, e, l := atoi(st[0]), atoi(st[1]), atoi(st[2])
		edges[i] = Edge{s, e, l}
		if d[s][e] > l {
			d[s][e] = l
			d[e][s] = l
		}
	}
	//fmt.Println(d)
	// floyd-warshall
	for i := 1; i <= V; i++ {
		for j := 1; j <= V; j++ {
			for k := 1; k <= V; k++ {
				if d[j][k] > d[j][i]+d[i][k] {
					d[j][k] = d[j][i] + d[i][k]
				}
			}
		}
	}
	//fmt.Println(d)
	ans := math.MaxFloat64
	for pivot := 1; pivot <= V; pivot++ {
		tmp := -math.MaxFloat64
		for i := 0; i < E; i++ {
			s, e, l := edges[i].S, edges[i].E, edges[i].L
			ps, pe := d[pivot][s], d[pivot][e]
			if pe < ps {
				ps, pe = pe, ps
			}
			var remain float64
			if pe-ps < l {
				remain = float64(l-(pe-ps)) / 2
			}
			if remain <= 0 {
				remain = 0
			}
			tmp = max(tmp, float64(pe)+remain)
		}
		ans = min(ans, tmp)
	}
	fmt.Fprintf(writer, "%.1f \n", ans)
}

func min(a, b float64) float64 {
	if a < b {
		return a
	} else {
		return b
	}
}

func max(a, b float64) float64 {
	if a > b {
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
