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
듣보잡 - map, set
[problem](https://www.acmicpc.net/problem/1764)
*/
var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
)

type set map[string]bool

func (s *set) add(val string) {
	(*s)[val] = true
}

func (s *set) contains(val string) bool {
	return (*s)[val]
}

func main() {
	defer writer.Flush()

	st := stringTokenizer(" ")
	var n, m int
	n, _ = strconv.Atoi(st[0])
	m, _ = strconv.Atoi(st[1])

	s := make(set)
	for i := 0; i < n; i++ {
		s.add(nextLine())
	}
	res := make([]string, 0)
	for i := 0; i < m; i++ {
		if str := nextLine(); s.contains(str) {
			res = append(res, str)
		}
	}

	sort.Strings(res)
	fmt.Fprintln(writer, len(res))
	for i := 0; i < len(res); i++ {
		fmt.Fprintln(writer, res[i])
	}
}

func nextLine() string {
	line, _ := reader.ReadString('\n')
	return strings.TrimSpace(line)
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}
