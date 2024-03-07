package segment

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestMain(t *testing.T) {
	t.Run("testInit", func(t *testing.T) {
		assert := assert.New(t)
		assert.Equal(testInit(), 15)
	})

	t.Run("testQuery", func(t *testing.T) {
		assert := assert.New(t)
		assert.Equal(testQuery(), 12)
	})

	t.Run("testUpdate", func(t *testing.T) {
		assert := assert.New(t)
		assert.Equal(testUpdate(), 17)
	})
}

var (
	datas = []int{1, 2, 3, 4, 5}
	size  = 4 * len(datas)
	tree  = make([]int, size)
)

func testInit() int {
	ret := Init(tree, datas, 1, 0, len(datas)-1)
	fmt.Println(tree)
	return ret
}

func testQuery() int {
	Init(tree, datas, 1, 0, len(datas)-1)
	return Query(tree, 1, 0, len(datas)-1, 2, 4)
}

func testUpdate() int {
	Init(tree, datas, 1, 0, len(datas)-1)
	return Update(tree, 1, 0, len(datas)-1, 2, 5)
}
