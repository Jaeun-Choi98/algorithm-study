package string

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestKmp(t *testing.T) {
	assert := assert.New(t)
	text := "ABC ABCDAB ABCDABCDABDE"
	pattern := "ABCDABD"
	ans := []int{16}
	assert.Equal(ans, Kmp(text, pattern))
}
