package topological

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestTopologicalSort(t *testing.T) {
	assert := assert.New(t)
	exp := []int{4, 5, 2, 0, 3, 1}
	actual := testTopologicalSort()
	assert.Equal(exp, actual)
}
