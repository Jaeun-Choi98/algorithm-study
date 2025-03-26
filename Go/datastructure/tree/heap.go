package tree

import "golang.org/x/exp/constraints"

type Heap[T constraints.Ordered] struct {
	arr []T
}

func (h *Heap[T]) Push(v T) {
	h.arr = append(h.arr, v)
	h.upheapify(len(h.arr) - 1)
}

func (h *Heap[T]) upheapify(idx int) {
	parentIdx := (idx - 1) / 2

	if parentIdx <= 0 {
		return
	}

	if h.arr[parentIdx] < (h.arr[idx]) {
		h.arr[idx], h.arr[parentIdx] = h.arr[parentIdx], h.arr[idx]
		h.upheapify(parentIdx)
	}
}

func (h *Heap[T]) Pop(v T) T {
	rt := h.arr[0]
	h.arr[0] = h.arr[len(h.arr)-1]
	h.arr = h.arr[:len(h.arr)-1]
	h.downheapify(0)
	return rt
}

func (h *Heap[T]) downheapify(idx int) {
	l := len(h.arr)

	left, right := 2*idx+1, 2*idx+2

	tmp := -1
	if left < l && h.arr[left] < h.arr[idx] {
		tmp = left
	}

	if right < l && h.arr[right] > h.arr[idx] {
		if tmp < 0 || h.arr[right] > h.arr[tmp] {
			tmp = right
		}
	}

	if tmp > 0 {
		h.arr[tmp], h.arr[idx] = h.arr[idx], h.arr[tmp]
		h.downheapify(tmp)
	}
}
