package tree

type TreeNode[T any] struct {
	Value  T
	Childs []*TreeNode[T]
}

func (t *TreeNode[T]) Add(val T) *TreeNode[T] {
	n := &TreeNode[T]{
		Value: val,
	}

	t.Childs = append(t.Childs, n)
	return n
}

func (t *TreeNode[T]) Preorder(fn func(val T)) {
	if t == nil {
		return
	}

	fn(t.Value)

	for _, n := range t.Childs {
		n.Preorder(fn)
	}
}

func (t *TreeNode[T]) Postorder(fn func(val T)) {
	if t == nil {
		return
	}

	for _, n := range t.Childs {
		n.Postorder(fn)
	}

	fn(t.Value)
}

func (t *TreeNode[T]) BFS(fn func(val T)) {
	que := make([]*TreeNode[T], 0)
	que = append(que, t)
	for len(que) > 0 {
		front := que[0]
		que = que[1:]

		fn(front.Value)

		que = append(que, front.Childs...)
	}
}
