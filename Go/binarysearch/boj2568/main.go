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
[problem](https://www.acmicpc.net/problem/2568)
*/

type Element struct {
	From, To int
}

var (
	reader     = bufio.NewReader(os.Stdin)
	writer     = bufio.NewWriter(os.Stdout)
	data, list []Element
	idxList    []int
	n          int
)

func main() {
	defer writer.Flush()
	n = nextInt()
	data = make([]Element, n)
	list = make([]Element, 0)
	idxList = make([]int, n)
	for i := 0; i < n; i++ {
		st := stringTokenizer(" ")
		data[i] = Element{atoi(st[0]), atoi(st[1])}
	}
	sort.Slice(data, func(i, j int) bool { return data[i].From < data[j].From })
	for i := 0; i < n; i++ {
		idx := find(data[i])
		if len(list) == idx {
			list = append(list, data[i])
		} else {
			list[idx] = data[i]
		}
		idxList[i] = idx
	}

	lis := len(list)
	ret := make([]Element, 0)
	for i := n - 1; i >= 0; i-- {
		if idxList[i] == lis-1 {
			lis--
		} else {
			ret = append(ret, data[i])
		}
	}
	sort.Slice(ret, func(i, j int) bool { return ret[i].From < ret[j].From })
	fmt.Fprintln(writer, len(ret))
	for i := 0; i < len(ret); i++ {
		fmt.Fprintln(writer, ret[i].From)
	}
}

func find(t Element) int {
	s, e := 0, len(list)-1
	for s <= e {
		mid := (s + e) / 2
		if t.To <= list[mid].To {
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
