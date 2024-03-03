package bellmanford

import "math"

func Bellmanford(edges [][]int, vertices, start int) ([]int, bool) {
	d := make([]int, vertices+1)
	for i := 0; i <= vertices; i++ {
		d[i] = math.MaxInt32
	}
	d[start] = 0

	var from, to, weight int
	var isNegativeCycle bool
	for i := 0; i < vertices; i++ {
		for j, len := 0, len(edges); j < len; j++ {
			from = edges[j][0]
			to = edges[j][1]
			weight = edges[j][2]
			if d[from] != math.MaxInt32 && d[to] > d[from]+weight {
				d[to] = d[from] + weight
				if i == vertices-1 {
					isNegativeCycle = true
				}
			}
		}
	}
	return d, isNegativeCycle
}
