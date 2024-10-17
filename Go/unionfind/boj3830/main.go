package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/3830)
*/

type Node struct {
	index  int
	weight int64
}

var (
	reader   = bufio.NewReader(os.Stdin)
	writer   = bufio.NewWriter(os.Stdout)
	unionArr []Node
)

func main() {
	defer writer.Flush()
	for true {
		st := stringTokenizer(" ")
		N, M := atoi(st[0]), atoi(st[1])
		if N == 0 {
			break
		}
		unionArr = make([]Node, N+1)
		for i := 0; i <= N; i++ {
			unionArr[i] = Node{index: i, weight: 0}
		}
		for i := 0; i < M; i++ {
			st = stringTokenizer(" ")
			cmd := st[0]
			if cmd == "!" {
				a, b, c := atoi(st[1]), atoi(st[2]), atoi(st[3])
				paNode, pbNode := find(a), find(b)
				pa, pb := paNode.index, pbNode.index
				if pa < pb {
					unionArr[pb].index = pa
					unionArr[pb].weight = (paNode.weight + int64(c)) - pbNode.weight
				} else {
					unionArr[pa].index = pb
					unionArr[pa].weight = (pbNode.weight - int64(c)) - paNode.weight
				}
			} else {
				a, b := atoi(st[1]), atoi(st[2])
				paNode, pbNode := find(a), find(b)
				if paNode.index == pbNode.index {
					fmt.Fprintln(writer, pbNode.weight-paNode.weight)
				} else {
					fmt.Fprintln(writer, "UNKNOWN")
				}
			}
		}
	}
}

func find(idx int) Node {
	if idx == unionArr[idx].index {
		return unionArr[idx]
	}
	ret := find(unionArr[idx].index)
	unionArr[idx].index = ret.index
	unionArr[idx].weight += ret.weight
	return unionArr[idx]
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
