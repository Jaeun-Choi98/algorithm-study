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
[problem](https://www.acmicpc.net/problem/9328)
*/

var (
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	dx     = []int{1, -1, 0, 0}
	dy     = []int{0, 0, 1, -1}
)

type Coordinate struct {
	x, y int
}

func main() {
	defer writer.Flush()
	tc := nextInt()
	for tc > 0 {
		st := stringTokenizer(" ")
		h, w := atoi(st[0]), atoi(st[1])
		data := make([][]rune, h)
		visited := make([][]bool, h)
		keyData := make([]bool, 26)
		restDoor := make([]list.List, 26)
		que := list.New()
		res := 0
		for i := 0; i < h; i++ {
			data[i] = make([]rune, w)
			visited[i] = make([]bool, w)
			line, _ := reader.ReadString('\n')
			runes := []rune(strings.TrimSpace(line))
			for j := 0; j < w; j++ {
				data[i][j] = runes[j]
				if data[i][j] == '.' {
					if i == 0 || j == 0 || i == h-1 || j == w-1 {
						que.PushBack(Coordinate{i, j})
						visited[i][j] = true
					}
				}
				if data[i][j] == '$' {
					if i == 0 || j == 0 || i == h-1 || j == w-1 {
						res++
						que.PushBack(Coordinate{i, j})
						visited[i][j] = true
					}

				}
				if data[i][j] >= 'a' && data[i][j] <= 'z' {
					if i == 0 || j == 0 || i == h-1 || j == w-1 {
						que.PushBack(Coordinate{i, j})
						visited[i][j] = true
						keyData[data[i][j]-'a'] = true
						for restDoor[data[i][j]-'a'].Len() > 0 {
							crd := restDoor[data[i][j]-'a'].Front().Value.(Coordinate)
							restDoor[data[i][j]-'a'].Remove(restDoor[data[i][j]-'a'].Front())
							que.PushBack(crd)
						}
					}

				}
				if data[i][j] >= 'A' && data[i][j] <= 'Z' {
					if i == 0 || j == 0 || i == h-1 || j == w-1 {
						if keyData[data[i][j]-'A'] {
							que.PushBack(Coordinate{i, j})
						} else {
							restDoor[data[i][j]-'A'].PushBack(Coordinate{i, j})
						}
					}
				}
			}
		}
		key, _ := reader.ReadString('\n')
		keyRunes := []rune(strings.TrimSpace(key))
		for i := 0; i < len(keyRunes); i++ {
			if keyRunes[0] == '0' {
				break
			}
			keyData[keyRunes[i]-'a'] = true
			for restDoor[keyRunes[i]-'a'].Len() > 0 {
				crd := restDoor[keyRunes[i]-'a'].Front().Value.(Coordinate)
				restDoor[keyRunes[i]-'a'].Remove(restDoor[keyRunes[i]-'a'].Front())
				que.PushBack(crd)
			}
		}
		// a~z 97~122, A~Z 65~90
		//fmt.Fprintln(writer, rune('a'), rune('A'), rune('z'), rune('Z'))
		for que.Len() > 0 {
			coordinate := que.Front().Value.(Coordinate)
			que.Remove(que.Front())
			for i := 0; i < 4; i++ {
				newX, newY := coordinate.x+dx[i], coordinate.y+dy[i]
				if newX < 0 || newX >= h || newY < 0 || newY >= w || data[newX][newY] == '*' || visited[newX][newY] {
					continue
				}
				visited[newX][newY] = true
				if data[newX][newY] == '$' {
					res++
				}
				if data[newX][newY] >= 'A' && data[newX][newY] <= 'Z' {
					if !keyData[data[newX][newY]-'A'] {
						restDoor[data[newX][newY]-'A'].PushBack(Coordinate{newX, newY})
						continue
					}
				}
				if data[newX][newY] >= 'a' && data[newX][newY] <= 'z' {
					if !keyData[data[newX][newY]-'a'] {
						keyData[data[newX][newY]-'a'] = true
						for restDoor[data[newX][newY]-'a'].Len() > 0 {
							crd := restDoor[data[newX][newY]-'a'].Front().Value.(Coordinate)
							restDoor[data[newX][newY]-'a'].Remove(restDoor[data[newX][newY]-'a'].Front())
							que.PushBack(crd)
						}
					}
				}
				que.PushBack(Coordinate{newX, newY})
			}
		}
		fmt.Fprintln(writer, res)
		tc--
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
