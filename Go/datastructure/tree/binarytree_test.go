package tree

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/assert"
)

type LesserInt int

func (a LesserInt) Less(b Lesser) bool {
	bvalue, ok := b.(LesserInt)
	if !ok {
		panic(fmt.Sprintf("should be 'LesserInt' type. type:%T", b))
	}
	return a < bvalue
}

func (a LesserInt) Equal(b Lesser) bool {
	bvalue, ok := b.(LesserInt)
	if !ok {
		panic(fmt.Sprintf("should be 'LesserInt' type. type:%T", b))
	}
	return a == bvalue
}

func TestBtree(t *testing.T) {
	root := &Tree{}
	root.Add(LesserInt(5))
	root.Add(LesserInt(4))
	root.Add(LesserInt(6))
	root.Add(LesserInt(2))
	root.Add(LesserInt(3))
	var rst []Lesser
	root.InOrder(root.Root, &rst)
	t.Log(rst)
	assert.True(t, root.Contains(LesserInt(3)))
}
