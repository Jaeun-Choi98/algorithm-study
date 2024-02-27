package quicksort

import (
	"sort"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestQuickSort(t *testing.T) {
	assert := assert.New(t)
	data1 := []int{2, 1, 4, 6, 5, 5}
	data2 := []int{2, 1, 4, 6, 5, 5}
	sort.Slice(data1, func(i, j int) bool { return data1[i] < data1[j] })
	QuickSort(data2, func(i, j int) bool { return data2[i] < data2[j] })
	assert.Equal(data1, data2, "test")
}
