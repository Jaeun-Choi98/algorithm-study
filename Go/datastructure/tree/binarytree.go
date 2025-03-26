package tree

type Lesser interface {
	Less(other Lesser) bool
	Equal(other Lesser) bool
}

type Tree struct {
	Root *BTreeNode
}

type BTreeNode struct {
	Value Lesser
	Left  *BTreeNode
	Right *BTreeNode

	//avl
	Depth int
}

func (t *Tree) Add(value Lesser) *BTreeNode {
	if t.Root == nil {
		t.Root = &BTreeNode{
			Value: value,
		}
		return t.Root
	}
	return t.Root.add(value)
}

func (bt *BTreeNode) add(value Lesser) *BTreeNode {
	if bt.Value.Less(value) {
		if bt.Right == nil {
			bt.Right = &BTreeNode{
				Value: value,
			}
			return bt.Right
		}
		return bt.Right.add(value)
	} else {
		if bt.Left == nil {
			bt.Left = &BTreeNode{
				Value: value,
			}
			return bt.Left
		}
		return bt.Left.add(value)
	}
}

func (t *Tree) Contains(value Lesser) bool {
	if t.Root == nil {
		return false
	}
	return t.Root.contains(value)
}

func (bt *BTreeNode) contains(value Lesser) bool {
	if bt.Value.Equal(value) {
		return true
	}
	if bt.Value.Less(value) {
		if bt.Right == nil {
			return false
		}
		return bt.Right.contains(value)
	} else {
		if bt.Left == nil {
			return false
		}
		return bt.Left.contains(value)
	}
}

// 수정 필요
func (t *Tree) Remove(value Lesser) bool {
	if t.Root == nil {
		return false
	}
	var removed bool
	t.Root, removed = t.Root.remove(value)
	return removed
}

func (bt *BTreeNode) remove(value Lesser) (*BTreeNode, bool) {
	if bt.Value.Equal(value) {
		if bt.Left == nil && bt.Right == nil {
			return nil, true
		}
		if bt.Left == nil {
			return bt.Right, true
		}
		if bt.Right == nil {
			return bt.Left, true
		}
		leftNode := bt.findAndRemoveLeftMostNode()
		leftNode.Right = bt.Right
		return leftNode, true
	}

	var removed bool
	if bt.Value.Less(value) {
		if bt.Right == nil {
			return bt, false
		}
		bt.Right, removed = bt.Right.remove(value)
		return bt, removed
	} else {
		if bt.Left == nil {
			return bt, false
		}
		bt.Left, removed = bt.Left.remove(value)
		return bt, removed
	}
}

// rightMost의 left(왼쪽) 자식 노드들이 있을 수도 있음.
func (bt *BTreeNode) findAndRemoveLeftMostNode() *BTreeNode {
	if bt.Left == nil {
		return nil
	}

	parent, rightMost := bt, bt.Left
	for rightMost.Right != nil {
		parent = rightMost
		rightMost = rightMost.Right
	}

	if parent.Left == rightMost {
		parent.Left = nil
	} else {
		parent.Right = nil
	}

	// rightMost의 left 트리가 남아있는 것을 처리
	if rightMost.Left != nil {
		processRemain(parent, rightMost.Left)
	}
	return rightMost
}

func processRemain(parent *BTreeNode, node *BTreeNode) {
	if node == nil {
		return
	}
	parent.add(node.Value)
	processRemain(parent, node.Left)
	processRemain(parent, node.Right)
}

func (t *Tree) InOrder(node *BTreeNode, rst *[]Lesser) {
	if node == nil {
		return
	}
	t.InOrder(node.Left, rst)
	*rst = append(*rst, node.Value)
	t.InOrder(node.Right, rst)
}
