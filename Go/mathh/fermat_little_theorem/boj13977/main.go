package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var (
	reader           = bufio.NewReader(os.Stdin)
	writer           = bufio.NewWriter(os.Stdout)
	N, M, K          int
	factorial        []int
	inverseFactorial []int
	m                int
)

func main() {
	defer writer.Flush()
	M := nextInt()
	factorial = make([]int, 4000001)
	inverseFactorial = make([]int, 4000001)
	m = 1000000007
	factorial[0] = 1
	inverseFactorial[0] = 1
	for i := 1; i <= 4000000; i++ {
		factorial[i] = (factorial[i-1] * i) % m
		inverseFactorial[i] = Congruence(factorial[i], m-2)
	}
	for i := 0; i < M; i++ {
		st := stringTokenizer(" ")
		N, K := atoi(st[0]), atoi(st[1])
		fmt.Fprintln(writer, factorial[N]*inverseFactorial[N-K]%m*inverseFactorial[K]%m)
	}
}

/*
p가 소수라면, a ^ p (mod m) 은 a (mod m) 는 합동이다.
그리고 a 가 p의 배수가 아니라면, 양변에 a를 약분할 수 있다.
ex) a ^ (p-1) (mod m) 과 1 (mod m) 은 합동.
ex) a ^ (p-2) (mod m) 과 a ^ -1 (mod m) 도 합동.
*/
func Congruence(a, p int) int {
	if p == 1 {
		return a
	}
	half := Congruence(a, p/2)
	if p%2 == 0 {
		return (half * half) % m
	} else {
		return (half * half) % m * a % m
	}
}

func stringTokenizer(token string) []string {
	line, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(line), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}

func nextInt() int {
	line, _ := reader.ReadString('\n')
	return atoi(strings.TrimSpace(line))
}
