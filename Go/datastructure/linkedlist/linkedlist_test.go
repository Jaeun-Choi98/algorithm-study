package linkedlist

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestSingleLinkedList(t *testing.T) {
	var sl SingleLinkedList[int]
	assert.Nil(t, sl.head)
	sl.PushBack(1)
	assert.Equal(t, 1, sl.head.val)
	assert.Equal(t, 1, sl.tail.val)
	sl.PushFront(3)
	assert.Equal(t, 3, sl.Front().val)
	sl.PushBack(4)
	assert.Equal(t, 4, sl.Back().val)
	assert.Equal(t, 1, sl.GetAt(1).val)

	// 3,1,4
	tmp := sl.Back() // tmp:(4)
	// 3,1,4,5
	sl.InsertAfter(tmp, 5)
	fmt.Println(sl.tail.val)
	assert.Equal(t, 5, sl.Back().val)
	// 3,1,2,4,5
	sl.InsertBefore(tmp, 2)
	assert.Equal(t, 2, sl.GetAt(2).val)
	assert.Equal(t, 4, sl.GetAt(3).val)
	// 0,3,1,2,4,5
	sl.InsertBefore(sl.Front(), 0)
	assert.Equal(t, 0, sl.head.val)
	// 0,3,1,2,4,6,5
	sl.InsertAfter(tmp, 6)
	assert.Equal(t, 6, sl.GetAt(5).val)

	sl.Remove(tmp)
	assert.Equal(t, 6, sl.GetAt(4).val)
}

func TestDoubleLinkedList(t *testing.T) {
	var sl DoubleLinkedList[int]
	assert.Nil(t, sl.head)
	sl.PushBack(1)
	assert.Equal(t, 1, sl.head.Val)
	assert.Equal(t, 1, sl.tail.Val)
	sl.PushFront(3)
	assert.Equal(t, 3, sl.Front().Val)
	sl.PushBack(4)
	assert.Equal(t, 4, sl.Back().Val)
	assert.Equal(t, 1, sl.GetAt(1).Val)

	// 3,1,4
	tmp := sl.Back() // tmp:(4)
	// 3,1,4,5
	sl.InsertAfter(tmp, 5)
	assert.Equal(t, 5, sl.Back().Val)
	assert.Equal(t, 4, sl.Back().prev.Val)

	// 3,1,2,4,5
	sl.InsertBefore(tmp, 2)
	assert.Equal(t, 2, sl.GetAt(2).Val)
	assert.Equal(t, 4, sl.GetAt(3).Val)
	// 0,3,1,2,4,5
	sl.InsertBefore(sl.Front(), 0)
	assert.Equal(t, 0, sl.head.Val)
	// 0,3,1,2,4,6,5
	sl.InsertAfter(tmp, 6)
	assert.Equal(t, 6, sl.GetAt(5).Val)

	sl.Remove(tmp)
	assert.Equal(t, 6, sl.GetAt(4).Val)
}
