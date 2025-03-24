package stack

import "root/datastructure/linkedlist"

type Stack[T any] struct {
	l *linkedlist.DoubleLinkedList[T]
}

func New[T any]() *Stack[T] {
	return &Stack[T]{
		l: &linkedlist.DoubleLinkedList[T]{},
	}
}

func (s *Stack[T]) Push(val T) {
	s.l.PushBack(val)
}

func (s *Stack[T]) Pop() T {
	return s.l.PopBack().Val
}
