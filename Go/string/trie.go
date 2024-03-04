package string

type TrieNode struct {
	child  map[rune]*TrieNode
	fail   *TrieNode
	isWord bool
	isRoot bool
}

type Trie struct {
	root *TrieNode
}

func NewTrie() *Trie {
	return &Trie{&TrieNode{make(map[rune]*TrieNode), nil, false, true}}
}

func (trie *Trie) Insert(word string) {
	cur := trie.root
	for _, ch := range word {
		if cur.child[ch] == nil {
			cur.child[ch] = &TrieNode{make(map[rune]*TrieNode), nil, false, false}
		}
		cur = cur.child[ch]
	}
	cur.isWord = true
}

func (trie *Trie) Search(word string) bool {
	cur := trie.root
	for _, ch := range word {
		if cur.child[ch] == nil {
			return false
		}
		cur = cur.child[ch]
	}
	return cur.isWord
}

func Trietest() bool {
	trie := NewTrie()
	trie.Insert("apple")
	trie.Insert("app")
	trie.Insert("banana")
	return trie.Search("apple")
}
