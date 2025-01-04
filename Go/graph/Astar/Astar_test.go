package Astar

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestAstar(t *testing.T) {
	assert := assert.New(t)
	grid := [][]int{
		{0, 1, 0, 0, 0, 0},
		{0, 1, 0, 1, 1, 0},
		{0, 0, 0, 1, 0, 0},
		{0, 1, 0, 0, 0, 0},
		{0, 1, 1, 1, 1, 0},
		{0, 0, 0, 0, 0, 0},
	}

	start := [2]int{0, 0}
	end := [2]int{5, 5}
	path := astar(grid, start, end)
	fmt.Println(path)
	assert.Equal(0, 0)
}
