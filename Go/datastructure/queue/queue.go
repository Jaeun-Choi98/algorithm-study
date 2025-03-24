package queue

import "root/datastructure/linkedlist"

type Queue[T any] struct {
	l *linkedlist.DoubleLinkedList[T]
}

func New[T any]() *Queue[T] {
	return &Queue[T]{
		l: &linkedlist.DoubleLinkedList[T]{},
	}
}

func (s *Queue[T]) Push(val T) {
	s.l.PushBack(val)
}

func (s *Queue[T]) Pop() T {
	return s.l.PopFront().Val
}
