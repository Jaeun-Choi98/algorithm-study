package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	reader              = bufio.NewReader(os.Stdin)
	writer              = bufio.NewWriter(os.Stdout)
	data, idxList, list []int
	n                   int
)

/*
가장 긴 증가하는 부분 수열 5
[problem](https://www.acmicpc.net/problem/14003)
*/

func main() {
	defer writer.Flush()
	n = nextInt()
	st := stringTokenizer(" ")
	data = make([]int, n)
	idxList = make([]int, n)
	list = make([]int, 0)
	for i := 0; i < n; i++ {
		data[i] = atoi(st[i])
	}

	for i := 0; i < n; i++ {
		idx := find(data[i])
		if len(list) == idx {
			list = append(list, data[i])
		} else {
			list[idx] = data[i]
		}
		idxList[i] = idx
	}
	lengList := len(list)
	fmt.Fprintln(writer, lengList)
	lengList--
	for i := n - 1; i >= 0; i-- {
		if idxList[i] == lengList {
			list[lengList] = data[i]
			lengList--
		}
	}
	for _, val := range list {
		fmt.Fprintf(writer, "%d ", val)
	}
}

func find(tar int) int {
	s, e := 0, len(list)-1
	for s <= e {
		mid := (s + e) / 2
		if list[mid] >= tar {
			e = mid - 1
		} else {
			s = mid + 1
		}
	}
	return s
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
