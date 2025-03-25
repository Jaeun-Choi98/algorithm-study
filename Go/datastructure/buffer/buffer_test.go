package buffer

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestRing(t *testing.T) {
	buf := NewRingBuffer[byte](10)
	buf.Write([]byte{1, 2, 3, 4})
	assert.Equal(t, 4, buf.Readable())
}

func TestReadRing(t *testing.T) {
	buf := NewRingBuffer[byte](10)
	buf.Write([]byte{1, 2, 3, 4})
	assert.Equal(t, 4, buf.Readable())

	data := buf.Read(4)
	for i := 0; i < 4; i++ {
		assert.Equal(t, i+1, int(data[i]))
	}
	assert.Equal(t, 0, buf.Readable())
}

func TestOverWriteRing(t *testing.T) {
	buf := NewRingBuffer[byte](5)
	buf.Write([]byte{1, 2, 3, 4})
	assert.Equal(t, 4, buf.Readable())
	assert.Equal(t, 1, buf.Writable())

	writed := buf.Write([]byte{5, 6})
	assert.Equal(t, 1, writed)
	assert.Equal(t, 5, buf.Readable())
	assert.Equal(t, 0, buf.Writable())

	// ----5
	data := buf.Read(4)
	for i := 0; i < 4; i++ {
		assert.Equal(t, i+1, int(data[i]))
	}

	// 78--5
	writed = buf.Write([]byte{7, 8})
	assert.Equal(t, 4, buf.readPt)
	assert.Equal(t, 2, writed)
	assert.Equal(t, 2, buf.writePt)
}
