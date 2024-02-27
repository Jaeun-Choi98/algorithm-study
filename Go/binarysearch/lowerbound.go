package binarysearch

import "sort"

// target과 같은 값을 반환. 만약 target과 같은 값이 여러 개라면 가장 좌측에 있는 값을 반환
func LowerSearch(arr []int, target int) int {
	if !sort.SliceIsSorted(arr, func(i, j int) bool { return arr[i] < arr[j] }) {
		sort.Slice(arr, func(i, j int) bool { return arr[i] < arr[j] })
	}
	l, r := 0, len(arr)-1
	for l <= r {
		mid := (l + r) / 2
		if arr[mid] <= target {
			r = mid - 1
		} else {
			l = mid + 1
		}
	}
	return l
}
