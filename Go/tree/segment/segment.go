package segment

// 구간 합 세그먼트

// node는 탐색을 시작할 위치, left와 right는 datas의 시작 인덱스와 마지막 인덱스
func Init(tree, datas []int, node, left, right int) int {
	if left == right {
		tree[node] = datas[left]
		return tree[node]
	}
	mid := (left + right) / 2
	tree[node] = Init(tree, datas, node*2, left, mid) + Init(tree, datas, node*2+1, mid+1, right)
	return tree[node]
}

func Query(tree []int, node, left, right, targetL, targetR int) int {
	if right < targetL || left > targetR {
		return 0
	}
	if left >= targetL && right <= targetR {
		return tree[node]
	}
	mid := (left + right) / 2
	return Query(tree, 2*node, left, mid, targetL, targetR) + Query(tree, 2*node+1, mid+1, right, targetL, targetR)
}

func Update(tree []int, node, left, right, idx, val int) int {
	if left == right {
		tree[node] = val
		return tree[node]
	}

	mid := (left + right) / 2
	if left <= idx && idx <= mid {
		tree[node] = Update(tree, 2*node, left, mid, idx, val) + tree[2*node+1]
	} else {
		tree[node] = tree[2*node] + Update(tree, 2*node+1, mid+1, right, idx, val)
	}
	return tree[node]
}
