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
[problem](https://www.acmicpc.net/problem/5670)
*/

type TrieNode struct {
	child  map[rune]*TrieNode
	isWord bool
	isRoot bool
}

type Trie struct {
	root *TrieNode
}

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	N      int
	count  int
)

func main() {
	defer writer.Flush()
	for true {
		N = nextInt()
		if N == 0 {
			break
		}
		trie := NewTrie()
		for i := 0; i < N; i++ {
			str := nextLine()
			trie.insert(str)
		}
		count = 0
		trie.search(trie.root, 0)
		fmt.Fprintf(writer, "%.2f\n", math.Round(float64(count)/float64(N)*100)/100)
	}

}

func (trie *Trie) search(cur *TrieNode, cnt int) {
	if cur.isWord {
		count += cnt
	}
	size := len(cur.child)
	for _, v := range cur.child {
		if cur.isRoot {
			trie.search(v, 1)
		} else if cur.isWord {
			trie.search(v, cnt+1)
		} else if size == 1 {
			trie.search(v, cnt)
		} else {
			trie.search(v, cnt+1)
		}
	}
}

func (trie *Trie) insert(word string) {
	cur := trie.root
	for _, ch := range word {
		if cur.child[ch] == nil {
			cur.child[ch] = &TrieNode{make(map[rune]*TrieNode), false, false}
		}
		cur = cur.child[ch]
	}
	cur.isWord = true
}

func NewTrie() *Trie {
	return &Trie{&TrieNode{make(map[rune]*TrieNode), false, true}}
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
