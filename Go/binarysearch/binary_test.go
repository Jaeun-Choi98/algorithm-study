package binarysearch

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

// 반환 값으로 lower값과 upper값을 반환.
func testFunc() []int {
	// 정렬되지 않은 데이터 -> 정렬 후: {1,4,4,4,5,6}
	data := []int{1, 6, 4, 5, 4, 4}
	return []int{data[LowerSearch(data, 4)], data[UpperSearch(data, 4)]}
}

func TestTestFunc(t *testing.T) {
	assert := assert.New(t)
	assert.Equal([]int{4, 5}, testFunc())
}
