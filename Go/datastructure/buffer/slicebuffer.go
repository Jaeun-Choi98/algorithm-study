package buffer

type SliceBuffer[T any] struct {
	buf []T
}

func NewSliceBuffer[T any]() *SliceBuffer[T] {
	return &SliceBuffer[T]{}
}

func (s *SliceBuffer[T]) Write(data []T) {
	s.buf = append(s.buf, data...)
}

func (s *SliceBuffer[T]) Readable() int {
	return len(s.buf)
}

func (s *SliceBuffer[T]) Read(cnt int) []T {
	readCnt := min(cnt, s.Readable())
	rst := make([]T, readCnt)
	copy(rst, s.buf[:readCnt])
	s.buf = s.buf[readCnt:]
	return rst
}

func min(a, b int) int {
	if a < b {
		return a
	} else {
		return b
	}
}
