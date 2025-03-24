package linkedlist

type Node[T any] struct {
	next *Node[T]
	val  T
}

type SingleLinkedList[T any] struct {
	head  *Node[T]
	tail  *Node[T]
	count int
}

func (s *SingleLinkedList[T]) PushBack(val T) {
	new := &Node[T]{nil, val}
	if s.tail == nil {
		s.head = new
		s.tail = new
		s.count++
		return
	}
	s.tail.next = new
	s.tail = new
	s.count++
}

func (s *SingleLinkedList[T]) PushFront(val T) {
	new := &Node[T]{nil, val}
	if s.head == nil {
		s.head = new
		s.tail = new
		s.count++
		return
	}
	new.next = s.head
	s.head = new
	s.count++
}

func (s *SingleLinkedList[T]) Back() *Node[T] {
	return s.tail
}

func (s *SingleLinkedList[T]) Front() *Node[T] {
	return s.head
}

func (s *SingleLinkedList[T]) Count() int {
	return s.count
}

func (s *SingleLinkedList[T]) GetAt(idx int) *Node[T] {
	if s.head == nil {
		return nil
	}
	c := s.head
	cidx := 0
	for n := c; n != nil; n = n.next {
		if cidx == idx {
			return n
		}
		cidx++
	}
	return nil
}

func (s *SingleLinkedList[T]) InsertAfter(node *Node[T], val T) {
	if node == s.tail {
		s.PushBack(val)
		return
	}

	if !s.IsIncluded(node) {
		return
	}
	new := &Node[T]{nil, val}
	new.next, node.next = node.next, new
	s.count++
}

func (s *SingleLinkedList[T]) IsIncluded(node *Node[T]) bool {
	c := s.head
	for n := c; n != nil; n = n.next {
		if n == node {
			return true
		}
	}
	return false
}

func (s *SingleLinkedList[T]) InsertBefore(node *Node[T], val T) {
	if node == s.head {
		s.PushFront(val)
		return
	}
	prevNode := s.PreviousNode(node)
	if prevNode == nil {
		return
	}
	new := &Node[T]{nil, val}
	new.next, prevNode.next = prevNode.next, new
	s.count++
}

func (s *SingleLinkedList[T]) PreviousNode(node *Node[T]) *Node[T] {
	for n := s.head; n != nil; n = n.next {
		if n.next == node {
			return n
		}
	}
	return nil
}

func (s *SingleLinkedList[T]) PopFront() *Node[T] {
	ret := s.head
	s.head = s.head.next
	if s.head == nil {
		s.tail = nil
	}
	s.count--
	return ret
}

func (s *SingleLinkedList[T]) PopBack() *Node[T] {
	ret := s.tail
	s.tail = s.PreviousNode(s.tail)
	if s.tail == nil {
		s.head = nil
	}
	s.count--
	return ret
}

func (s *SingleLinkedList[T]) Remove(node *Node[T]) {
	if node == s.head {
		s.PopFront()
		return
	}
	if node == s.tail {
		s.PopBack()
		return
	}
	prev := s.PreviousNode(node)
	prev.next = node.next
	s.count--
}

// Reverse()
func (s *SingleLinkedList[T]) Reverse() {
	if s.head == nil {
		return
	}
	node := s.head
	next := node.next
	s.head.next = nil

	for next != nil {
		nextnext := next.next
		next.next = node

		node = next
		next = nextnext

	}
	s.head, s.tail = s.tail, s.head
}
