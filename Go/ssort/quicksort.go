package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println("Hellow world")
	data1 := []int{2, 1, 4, 6, 5, 5}
	data2 := []int{2, 1, 4, 6, 5, 5}
	QuickSort(&data1, func(i, j int) bool { return data1[i] < data1[j] })
	sort.Slice(data2, func(i, j int) bool { return data2[i] < data2[j] })
	fmt.Println(data1)
	fmt.Println(data2)
}

func QuickSort(arr *[]int, less func(i, j int) bool) {
	size := len(*arr)
	if size <= 1 {
		return
	}
	pivot := size / 2
	left, right := make([]int, 0), make([]int, 0)
	for idx, v := range *arr {
		if idx == pivot {
			continue
		}
		if less(pivot, idx) {
			right = append(right, v)
		} else {
			left = append(left, v)
		}
	}
	QuickSort(&left, less)
	QuickSort(&right, less)
	copy(*arr, append(append(left, (*arr)[pivot]), right...))
}
