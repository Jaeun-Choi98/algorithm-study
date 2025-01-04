package Astar

import (
	"container/heap"
	"math"
)

type Node struct {
	position [2]int
	parent   *Node
	g, h, f  float64
	index    int
}

type PriorityQueue []*Node

func (pq PriorityQueue) Len() int { return len(pq) }

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].f < pq[j].f
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
	pq[i].index = i
	pq[j].index = j
}

func (pq *PriorityQueue) Push(x interface{}) {
	n := x.(*Node)
	n.index = len(*pq)
	*pq = append(*pq, n)
}

func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	item := old[n-1]
	old[n-1] = nil // avoid memory leak
	item.index = -1
	*pq = old[0 : n-1]
	return item
}

func heuristic(a, b [2]int) float64 {
	return math.Abs(float64(a[0]-b[0])) + math.Abs(float64(a[1]-b[1]))
}

func astar(grid [][]int, start, end [2]int) [][2]int {
	startNode := &Node{position: start}
	endNode := &Node{position: end}

	openList := &PriorityQueue{}
	heap.Init(openList)
	heap.Push(openList, startNode)

	closedList := make(map[[2]int]bool)

	for openList.Len() > 0 {
		currentNode := heap.Pop(openList).(*Node)
		closedList[currentNode.position] = true

		if currentNode.position == endNode.position {
			path := [][2]int{}
			for currentNode != nil {
				path = append([][2]int{currentNode.position}, path...)
				currentNode = currentNode.parent
			}
			return path
		}

		neighbors := [][2]int{
			{0, -1}, {-1, 0}, {0, 1}, {1, 0},
		}

		for _, offset := range neighbors {
			nodePosition := [2]int{currentNode.position[0] + offset[0], currentNode.position[1] + offset[1]}

			if nodePosition[0] < 0 || nodePosition[0] >= len(grid) || nodePosition[1] < 0 || nodePosition[1] >= len(grid[0]) {
				continue
			}

			if grid[nodePosition[0]][nodePosition[1]] != 0 {
				continue
			}

			if closedList[nodePosition] {
				continue
			}

			neighborNode := &Node{position: nodePosition, parent: currentNode}
			neighborNode.g = currentNode.g + 1
			neighborNode.h = heuristic(neighborNode.position, endNode.position)
			neighborNode.f = neighborNode.g + neighborNode.h

			inOpenList := false
			for _, openNode := range *openList {
				if neighborNode.position == openNode.position && neighborNode.g > openNode.g {
					inOpenList = true
					break
				}
			}
			if !inOpenList {
				heap.Push(openList, neighborNode)
			}
		}
	}
	return nil
}
