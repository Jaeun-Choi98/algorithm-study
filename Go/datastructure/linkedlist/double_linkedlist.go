package linkedlist

type DNode[T any] struct {
	next *DNode[T]
	prev *DNode[T]
	Val  T
}

type DoubleLinkedList[T any] struct {
	head  *DNode[T]
	tail  *DNode[T]
	count int
}

func (d *DoubleLinkedList[T]) PushBack(val T) {
	new := &DNode[T]{nil, nil, val}
	if d.tail == nil {
		d.head = new
		d.tail = new
		d.count++
		return
	}
	d.tail.next = new
	new.prev = d.tail
	d.tail = new
	d.count++
}

func (d *DoubleLinkedList[T]) PushFront(val T) {
	new := &DNode[T]{nil, nil, val}
	if d.head == nil {
		d.head = new
		d.tail = new
		d.count++
		return
	}
	d.head.prev = new
	new.next = d.head
	d.head = new
	d.count++
}

func (d *DoubleLinkedList[T]) Back() *DNode[T] {
	return d.tail
}

func (d *DoubleLinkedList[T]) Front() *DNode[T] {
	return d.head
}

func (d *DoubleLinkedList[T]) Count() int {
	return d.count
}

func (d *DoubleLinkedList[T]) GetAt(idx int) *DNode[T] {
	if d.head == nil {
		return nil
	}
	cidx := 0
	for n := d.head; n != nil; n = n.next {
		if cidx == idx {
			return n
		}
		cidx++
	}
	return nil
}

func (d *DoubleLinkedList[T]) InsertAfter(node *DNode[T], val T) {
	if node == d.tail {
		d.PushBack(val)
		return
	}

	if !d.IsIncluded(node) {
		return
	}
	new := &DNode[T]{nil, nil, val}
	new.next = node.next
	node.next.prev = new
	node.next = new
	new.prev = node
	d.count++
}

func (d *DoubleLinkedList[T]) IsIncluded(node *DNode[T]) bool {
	for n := d.head; n != nil; n = n.next {
		if n == node {
			return true
		}
	}
	return false
}

func (d *DoubleLinkedList[T]) InsertBefore(node *DNode[T], val T) {
	if node == d.head {
		d.PushFront(val)
		return
	}

	if !d.IsIncluded(node) {
		return
	}

	new := &DNode[T]{nil, nil, val}
	node.prev.next = new
	new.prev = node.prev
	new.next = node
	node.prev = new
	d.count++
}

func (d *DoubleLinkedList[T]) PreviousNode(node *DNode[T]) *DNode[T] {
	for n := d.head; n != nil; n = n.next {
		if n.next == node {
			return n
		}
	}
	return nil
}

func (d *DoubleLinkedList[T]) PopFront() *DNode[T] {
	ret := d.head
	d.head = d.head.next
	if d.head == nil {
		d.tail = nil
	}
	d.count--
	return ret
}

func (d *DoubleLinkedList[T]) PopBack() *DNode[T] {
	ret := d.tail
	d.tail = d.tail.prev
	if d.tail == nil {
		d.head = nil
	}
	d.count--
	return ret
}

func (d *DoubleLinkedList[T]) Remove(node *DNode[T]) {
	if node == d.head {
		d.PopFront()
		return
	}
	if node == d.tail {
		d.PopBack()
		return
	}
	prev, next := node.prev, node.next
	prev.next = next
	next.prev = prev
	d.count--
}

// Reverse()
func (d *DoubleLinkedList[T]) Reverse() {
	node := d.head
	for node != nil {
		next := node.next
		node.prev, node.next = node.next, node.prev
		node = next
	}
	d.head, d.tail = d.tail, d.head
}
