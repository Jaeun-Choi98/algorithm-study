package mymap

import (
	"sort"

	"golang.org/x/exp/constraints"
)

type Element[TKey constraints.Ordered, TValue any] struct {
	Key   TKey
	Value TValue
}

type SortedMap[TKey constraints.Ordered, TValue any] struct {
	Arr []Element[TKey, TValue]
}

func (s *SortedMap[TKey, TValue]) Add(key TKey, val TValue) {
	idx := sort.Search(len(s.Arr), func(i int) bool { return key <= s.Arr[i].Key })
	if idx < len(s.Arr) && s.Arr[idx].Key == key {
		s.Arr[idx].Value = val
		return
	}
	s.Arr = append(s.Arr[:idx], append([]Element[TKey, TValue]{{key, val}}, s.Arr[idx:]...)...)
}

func (s *SortedMap[TKey, TValue]) Get(key TKey) (TValue, bool) {
	idx := sort.Search(len(s.Arr), func(i int) bool { return key <= s.Arr[i].Key })

	if idx < len(s.Arr) && s.Arr[idx].Key == key {
		return s.Arr[idx].Value, true
	}

	var defualtV TValue
	return defualtV, false
}

func (s *SortedMap[TKey, TValue]) Remove(key TKey) bool {
	idx := sort.Search(len(s.Arr), func(i int) bool { return key <= s.Arr[i].Key })

	if idx < len(s.Arr) && s.Arr[idx].Key == key {
		s.Arr = append(s.Arr[:idx], s.Arr[idx+1:]...)
		return true
	}

	return false
}
