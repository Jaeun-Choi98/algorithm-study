package quicksort

func QuickSort(arr []int, less func(i, j int) bool) {
	size := len(arr)
	if size <= 1 {
		return
	}
	pivot := size / 2
	left, right := make([]int, 0), make([]int, 0)
	for idx, v := range arr {
		if idx == pivot {
			continue
		}
		if less(pivot, idx) {
			right = append(right, v)
		} else {
			left = append(left, v)
		}
	}
	QuickSort(left, less)
	QuickSort(right, less)
	copy(arr, append(append(left, arr[pivot]), right...))
}
