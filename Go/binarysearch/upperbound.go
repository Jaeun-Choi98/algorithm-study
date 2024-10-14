package binarysearch

import "sort"

// target과 같은 값을 반환. 만약 target과 같은 값이 여러 개라면 target 값보다 큰 값이 처음 등장하는 위치를 반환.
func UpperSearch(arr []int, target int) int {
	if !sort.SliceIsSorted(arr, func(i, j int) bool { return arr[i] < arr[j] }) {
		sort.Slice(arr, func(i, j int) bool { return arr[i] < arr[j] })
	}
	l, r := 0, len(arr)-1
	for l < r {
		mid := (l + r) / 2
		if target < arr[mid] {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
