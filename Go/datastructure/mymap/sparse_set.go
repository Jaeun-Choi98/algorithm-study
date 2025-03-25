package mymap

import "golang.org/x/exp/constraints"

type SparseSet[TKey constraints.Ordered, TValue any] struct {
	dense  []Element[TKey, TValue]
	sparse map[TKey]int
}

func NewSparseSet[TKey constraints.Ordered, TValue any]() *SparseSet[TKey, TValue] {
	return &SparseSet[TKey, TValue]{
		dense:  make([]Element[TKey, TValue], 0),
		sparse: make(map[TKey]int),
	}
}

func (s *SparseSet[TKey, TValue]) Add(key TKey, val TValue) {
	if idx, ok := s.sparse[key]; ok {
		s.dense[idx].Value = val
		return
	}
	s.dense = append(s.dense, Element[TKey, TValue]{key, val})
	s.sparse[key] = len(s.dense) - 1
}

func (s *SparseSet[TKey, TValue]) Get(key TKey) (TValue, bool) {
	if idx, ok := s.sparse[key]; ok {
		return s.dense[idx].Value, true
	}
	var defualtV TValue
	return defualtV, false
}

func (s *SparseSet[TKey, TValue]) Remove(key TKey) bool {
	if idx, ok := s.sparse[key]; ok {
		lastIdx := len(s.dense) - 1
		if idx < lastIdx {
			s.dense[idx] = s.dense[lastIdx]
			s.sparse[s.dense[lastIdx].Key] = idx
		}
		s.dense = s.dense[:lastIdx]
		delete(s.sparse, key)
		return true
	}
	return false
}
