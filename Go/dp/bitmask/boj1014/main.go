package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/1014)
*/

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	N, M    int
	data    [][]rune
	d       [][]int
	allCase [][]rune
	caseCnt int
)

func main() {
	defer writer.Flush()
	tc := nextInt()
	for tc > 0 {
		st := stringTokenizer(" ")
		N, M = atoi(st[0]), atoi(st[1])
		data = make([][]rune, N)
		d = make([][]int, N)
		numOfCase := 1 << M
		for i := 0; i < N; i++ {
			data[i] = make([]rune, M)
			d[i] = make([]int, numOfCase+1)
			st = stringTokenizer(" ")
			data[i] = []rune(st[0])
			for j := 0; j <= numOfCase; j++ {
				d[i][j] = -1
			}
		}
		allCase = make([][]rune, numOfCase)
		line := make([]rune, M)
		caseCnt = 0
		searchAllCase(&line, 0)
		ret := search(0, 0)
		fmt.Fprintln(writer, ret)
		tc--
	}
}

func search(prevBit, idx int) int {
	if idx == N {
		return 0
	}

	if d[idx][prevBit] != -1 {
		return d[idx][prevBit]
	}

	ret := 0
	for i := 0; i < caseCnt; i++ {
		count := 0
		bit := 0
		for j := 0; j < M; j++ {
			if allCase[i][j] == '0' {
				continue
			}
			if data[idx][j] == 'x' {
				continue
			}
			if j-1 >= 0 && (prevBit&(1<<(j-1))) > 0 {
				continue
			}
			if j+1 < M && (prevBit&(1<<(j+1))) > 0 {
				continue
			}
			count++
			bit = bit | (1 << j)
		}
		nxt := search(bit, idx+1)
		if ret < nxt+count {
			ret = nxt + count
		}
	}
	d[idx][prevBit] = ret
	return d[idx][prevBit]
}

func searchAllCase(line *[]rune, cnt int) {
	if cnt == M {
		allCase[caseCnt] = make([]rune, M)
		for i := 0; i < M; i++ {
			allCase[caseCnt][i] = (*line)[i]
		}
		caseCnt++
		return
	}
	(*line)[cnt] = '0'
	searchAllCase(line, cnt+1)
	if cnt > 0 && (*line)[cnt-1] == '1' {
		return
	}
	(*line)[cnt] = '1'
	searchAllCase(line, cnt+1)
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
