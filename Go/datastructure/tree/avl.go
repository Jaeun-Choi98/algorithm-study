package tree

func (bt *BTreeNode) GetBalance() int {
	if bt == nil {
		return 0
	}
	return bt.Left.getDepth() - bt.Right.getDepth()
}

func (bt *BTreeNode) getDepth() int {
	if bt == nil {
		return 0
	}
	return bt.Depth
}

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

func (bt *BTreeNode) updateDepth() {
	bt.Depth = max(bt.Left.getDepth(), bt.Right.getDepth()) + 1
}

// roteteAfterDelete도 구현 필요

func rotateAfterInsert(bt *BTreeNode, value Lesser) *BTreeNode {
	bt.updateDepth()
	balance := bt.GetBalance()
	if balance >= -1 && balance <= 1 {
		return bt
	}

	// LL
	if balance > 1 && value.Less(bt.Left.Value) {
		return rightRotate(bt)
	}
	// RR
	if balance < -1 && !value.Less(bt.Right.Value) {
		return leftRotate(bt)
	}
	// LR
	if balance > 1 && !value.Less(bt.Left.Value) {
		bt.Left = leftRotate(bt.Left)
		return rightRotate(bt)
	}
	// RL
	if balance < -1 && value.Less(bt.Right.Value) {
		bt.Right = rightRotate(bt.Right)
		return leftRotate(bt)
	}
	return bt
}

func rightRotate(bt *BTreeNode) *BTreeNode {
	l := bt.Left
	t := l.Right

	l.Right = bt
	bt.Left = t

	bt.updateDepth()
	l.updateDepth()
	return l
}

func leftRotate(bt *BTreeNode) *BTreeNode {
	r := bt.Right
	t := r.Left

	r.Left = bt
	bt.Right = t

	bt.updateDepth()
	r.updateDepth()

	return r
}
