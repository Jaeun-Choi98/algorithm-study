package unionfind

// 부모 노드를 찾는 함수. 부모 노드를 찾으면서 자식 노드들을 부모 노드로 갱신.
func Find(arr []int, node int) int {
	if arr[node] == node {
		return arr[node]
	}
	arr[node] = Find(arr, arr[node])
	return arr[node]
}

// node1과 node2는 root 노드여야 함
func Union(arr []int, node1, node2 int) {
	arr[node1] = node2
}
