package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type TrieNode struct {
	child  [26]*TrieNode
	isEnd  bool
	isRoot bool
}

type Trie struct {
	root *TrieNode
}

var (
	reader  = bufio.NewReader(os.Stdin)
	writer  = bufio.NewWriter(os.Stdout)
	C, N, Q int
)

func main() {
	defer writer.Flush()
	st := stringTokenizer(" ")
	C, N = atoi(st[0]), atoi(st[1])
	trie1 := NewTrie()
	trie2 := NewTrie()
	for i := 0; i < C; i++ {
		trie1.Insert(nextLine())
	}
	for i := 0; i < N; i++ {
		trie2.Insert(nextLine())
	}
	Q = nextInt()
	for i := 0; i < Q; i++ {
		q := nextLine()
		if ok, idx := trie1.Query(q); ok {
			if ok, _ := trie2.Query(q[idx:]); ok {
				fmt.Fprintln(writer, "Yes")
			} else {
				fmt.Fprintln(writer, "No")
			}
		} else {
			fmt.Fprintln(writer, "No")
		}
	}
}

func (trie *Trie) Query(word string) (bool, int) {
	cur := trie.root
	for idx, ch := range word {
		chIdx := ch - 'a'
		if cur.child[chIdx] == nil {
			return false, -1
		}
		cur = cur.child[chIdx]
		if cur.isEnd {
			return true, idx + 1
		}
	}
	return false, -1
}

func (trie *Trie) Insert(word string) {
	cur := trie.root
	for _, ch := range word {
		chIdx := ch - 'a'
		if cur.child[chIdx] == nil {
			cur.child[chIdx] = &TrieNode{}
		}
		cur = cur.child[chIdx]
	}
	cur.isEnd = true
}

func NewTrie() *Trie {
	return &Trie{root: &TrieNode{isEnd: false, isRoot: true}}
}

func stringTokenizer(token string) []string {
	input, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(input), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}

func nextLine() string {
	line, _ := reader.ReadString('\n')
	return strings.TrimSpace(line)
}
