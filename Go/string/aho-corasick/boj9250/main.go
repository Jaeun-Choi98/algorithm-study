package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
문자열 집합 판별
[problem](https://www.acmicpc.net/problem/9250)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
)

type TrieNode struct {
	child          map[rune]*TrieNode
	fail           *TrieNode
	isRoot, isWord bool
}

type Trie struct {
	root *TrieNode
}

func newTrie() *Trie {
	return &Trie{root: &TrieNode{child: make(map[rune]*TrieNode), fail: nil, isRoot: true, isWord: false}}
}

func (trie *Trie) insert(word string) {
	cur := trie.root
	for _, ch := range word {
		if cur.child[ch] == nil {
			cur.child[ch] = &TrieNode{child: make(map[rune]*TrieNode), fail: nil, isRoot: false, isWord: false}
		}
		cur = cur.child[ch]
	}
	cur.isWord = true
}

func (trie *Trie) fail() {
	que := make([]*TrieNode, 0)
	trie.root.fail = trie.root
	que = append(que, trie.root)
	for len(que) > 0 {
		cur := que[0]
		que = que[1:]
		if cur.child == nil {
			continue
		}
		for ch, nxt := range cur.child {
			if cur.isRoot {
				nxt.fail = trie.root
			} else {
				failure := cur.fail
				for !failure.isRoot && failure.child[ch] == nil {
					failure = failure.fail
				}
				if failure.child[ch] != nil {
					failure = failure.child[ch]
				}
				nxt.fail = failure
			}
			if nxt.fail.isWord {
				nxt.isWord = true
			}
			que = append(que, nxt)
		}
	}
}

func (trie *Trie) search(word string) bool {
	cur := trie.root
	for _, ch := range word {
		for !cur.isRoot && cur.child[ch] == nil {
			cur = cur.fail
		}
		if cur.child[ch] != nil {
			cur = cur.child[ch]
		}
		if cur.isWord {
			return true
		}
	}

	return false
}

func main() {
	defer writer.Flush()
	var n, m int
	n, _ = strconv.Atoi(nextLine())
	trie := newTrie()
	for i := 0; i < n; i++ {
		trie.insert(nextLine())
	}
	trie.fail()
	m, _ = strconv.Atoi(nextLine())
	for i := 0; i < m; i++ {
		if trie.search(nextLine()) {
			fmt.Fprintln(writer, "YES")
		} else {
			fmt.Fprintln(writer, "NO")
		}
	}
}

func nextLine() string {
	line, _ := reader.ReadString('\n')
	return strings.TrimSpace(line)
}
