package string

func (trie *Trie) Fail() {
	que, idx := make([]*TrieNode, 0), 0
	trie.root.fail = trie.root
	que = append(que, trie.root)
	idx++
	for idx > 0 {
		cur := que[idx]
		idx--
		que = que[1:]
		if cur.child == nil {
			continue
		}
		for char, next := range cur.child {
			if cur.isRoot {
				next.fail = trie.root
			} else {
				failure := cur.fail
				for !failure.isRoot && failure.child[char] == nil {
					failure = failure.fail
				}
				if failure.child[char] != nil {
					failure = failure.child[char]
				}
				next.fail = failure
			}
			if next.fail.isWord {
				next.isWord = true
			}
			que = append(que, next)
		}
	}
}

func (trie *Trie) KMP(target string) bool {
	cur := trie.root
	for _, char := range target {
		for !cur.isRoot && cur.child[char] == nil {
			cur = cur.fail
		}
		if cur.child[char] != nil {
			cur = cur.child[char]
		}
		if cur.isWord {
			return true
		}
	}

	return false
}
