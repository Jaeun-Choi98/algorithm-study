package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
[problem](https://www.acmicpc.net/problem/17143)
*/

type Shark struct {
	id         int
	x, y       int
	speed, dir int
	size       int
}

var (
	reader       = bufio.NewReader(os.Stdin)
	writer       = bufio.NewWriter(os.Stdout)
	n, m         int
	sharkCnt     int
	dx           = []int{0, -1, 1, 0, 0}
	dy           = []int{0, 0, 0, 1, -1}
	curMap       [][]int
	nextMap      [][]int
	que, nextQue *list.List
	diedShark    []bool
	idToShark    map[int]Shark
	ret          int
)

func main() {
	defer writer.Flush()
	que = list.New()
	nextQue = list.New()
	st := stringTokenizer(" ")
	n, m, sharkCnt = atoi(st[0]), atoi(st[1]), atoi(st[2])
	curMap = make([][]int, n)
	nextMap = make([][]int, n)
	for i := 0; i < n; i++ {
		curMap[i] = make([]int, m)
		nextMap[i] = make([]int, m)
		for j := 0; j < m; j++ {
			curMap[i][j] = -1
			nextMap[i][j] = -1
		}
	}
	diedShark = make([]bool, sharkCnt)
	idToShark = map[int]Shark{}

	for i := 0; i < sharkCnt; i++ {
		st = stringTokenizer(" ")
		shark := Shark{id: i, x: atoi(st[0]) - 1, y: atoi(st[1]) - 1,
			speed: atoi(st[2]), dir: atoi(st[3]), size: atoi(st[4])}
		nextQue.PushBack(shark)
		nextMap[shark.x][shark.y] = i
		idToShark[shark.id] = shark
	}

	ret = 0
	for i := 0; i < m; i++ {
		curMap, nextMap = nextMap, curMap
		que, nextQue = nextQue, que
		// for i := 0; i < n; i++ {
		// 	for j := 0; j < m; j++ {
		// 		fmt.Printf("%d ", curMap[i][j])
		// 	}
		// 	fmt.Println()
		// }
		// fmt.Println(que.Len(), nextQue.Len())

		// 열에서 땅과 제일 가까운 상어를 잡는다.
		for j := 0; j < n; j++ {
			if curMap[j][i] != -1 {
				diedShark[curMap[j][i]] = true
				ret += idToShark[curMap[j][i]].size
				//fmt.Println(idToShark[curMap[j][i]].size)
				curMap[j][i] = -1
				break
			}
		}

		for que.Len() > 0 {
			el := que.Front()
			que.Remove(el)
			shark := el.Value.(Shark)

			// 죽은 상어라면 패스
			if diedShark[shark.id] {
				continue
			}

			// 이동
			curMap[shark.x][shark.y] = -1
			var ndir, nx, ny int
			nx = shark.x + dx[shark.dir]*shark.speed
			ny = shark.y + dy[shark.dir]*shark.speed
			if dx[shark.dir] != 0 {
				nx, ndir = calcMovePos(nx, n-1, shark.dir)
			}
			if dy[shark.dir] != 0 {
				ny, ndir = calcMovePos(ny, m-1, shark.dir)
			}
			shark.x, shark.y, shark.dir = nx, ny, ndir
			idToShark[shark.id] = shark

			// 이동을 마친 칸에 이미 상어가 있다면, 조건 처리 후 큐에 삽입 혹은 삽입x
			if nextMap[nx][ny] != -1 {
				s := idToShark[nextMap[nx][ny]]
				if shark.size < s.size {
					diedShark[shark.id] = true
				} else {
					diedShark[s.id] = true
					nextMap[nx][ny] = shark.id
					nextQue.PushBack(shark)
				}
			} else {
				nextQue.PushBack(shark)
				nextMap[nx][ny] = shark.id
			}
		}
	}
	fmt.Fprintln(writer, ret)
}

func calcMovePos(pos, mod, dir int) (int, int) {
	var nPos, nDir int
	var opposite bool
	nPos = pos
	if pos < 0 {
		nPos = pos * -1
	}
	if (nPos/mod)%2 == 1 {
		nPos = mod - nPos%mod
		opposite = true
	} else {
		nPos = 0 + nPos%mod
	}

	if pos > 0 {
		if pos/mod == 0 {
			return nPos, dir
		}
	}

	if dir <= 2 {
		if opposite {
			nDir = 1
		} else {
			nDir = 2
		}
	} else {
		if opposite {
			nDir = 4
		} else {
			nDir = 3
		}
	}
	return nPos, nDir
}

func stringTokenizer(token string) []string {
	input, _ := reader.ReadString('\n')
	return strings.Split(strings.TrimSpace(input), token)
}

func atoi(str string) int {
	ret, _ := strconv.Atoi(str)
	return ret
}
