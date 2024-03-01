package topological

import (
	"container/list"
)

type Graph [][]int

func (g *Graph) Init(n int) {
	*g = make([][]int, n)
	for i := 0; i < n; i++ {
		(*g)[i] = make([]int, 0)
	}
}

func (g *Graph) Add(from, to int, inDegree []int) {
	(*g)[from] = append((*g)[from], to)
	inDegree[to] += 1
}

func TopologicalSort(g Graph, inDegree []int) []int {
	ret := make([]int, 0)
	que := list.New()

	for vertex, val := range inDegree {
		if val == 0 {
			que.PushBack(vertex)
		}
	}

	for que.Len() > 0 {
		cur := que.Front().Value.(int)
		que.Remove(que.Front())
		ret = append(ret, cur)
		for _, to := range g[cur] {
			inDegree[to] -= 1
			if inDegree[to] == 0 {
				que.PushBack(to)
			}
		}
	}

	return ret
}

func testTopologicalSort() []int {
	graph := Graph{}
	graph.Init(6)
	inDegree := make([]int, 6)

	graph.Add(5, 2, inDegree)
	graph.Add(5, 0, inDegree)
	graph.Add(4, 0, inDegree)
	graph.Add(4, 1, inDegree)
	graph.Add(2, 3, inDegree)
	graph.Add(3, 1, inDegree)

	ret := TopologicalSort(graph, inDegree)
	return ret
}
