package string

func GetPartialLPS(pattern string) []int {
	m := len(pattern)
	lps := make([]int, m)
	i, j := 1, 0

	for i < m {
		if pattern[i] == pattern[j] {
			j++
			lps[i] = j
			i++
		} else {
			if j == 0 {
				//lps[i] = 0
				i++
			} else {
				j = lps[j-1]
			}
		}

	}

	/*
		for ;i < m; i++ {
			for j > 0 && pattern[i] != pattern[j] {
				j = lps[j-1]
			}
			if pattern[i] == pattern[j] {
				j++
				lps[i] = j
			}
		}
	*/
	return lps
}

func Kmp(text, pattern string) []int {
	ret := make([]int, 0)
	lps := GetPartialLPS(pattern)
	n, m := len(text), len(pattern)
	i, j := 0, 0
	for i+(m-j) < n {
		if text[i] == pattern[j] {
			i++
			j++
			if j == m {
				ret = append(ret, i-j+1)
				j = lps[j-1]
			}
		} else {
			if j == 0 {
				i++
			} else {
				j = lps[j-1]
			}
		}
	}
	return ret
}
