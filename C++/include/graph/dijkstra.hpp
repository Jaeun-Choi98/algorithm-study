#pragma once
#include <iostream>
#include <queue>
#include <sstream>
#include <string>
#include <utility>

#define INF int(1e9)

namespace graph {
struct CompareBySecond {
  bool operator()(const std::pair<int, int>& a,
                  const std::pair<int, int>& b) const {
    return a.second > b.second;
  }
};

class Dijkstra {
 public:
  Dijkstra() : g_(4, std::vector<std::pair<int, int>>()), dist_(4, INF) {
    std::cout << "그래프 정보:" << data_ << "\n";
    g_[0].push_back(std::make_pair(1, 3));
    g_[1].push_back(std::make_pair(2, 1));
    g_[2].push_back(std::make_pair(3, 1));
    g_[0].push_back(std::make_pair(2, 5));
    g_[1].push_back(std::make_pair(3, 5));
    g_[0].push_back(std::make_pair(3, 4));
  }

  void solve() {
    pq_.push({0, 0});
    dist_[0] = 0;

    std::vector<std::vector<int>> trips(4, std::vector<int>(1));

    for (; !pq_.empty(); pq_.pop()) {
      auto curNode = pq_.top();
      for (auto nextNode : g_[curNode.first]) {
        if (dist_[curNode.first] + nextNode.second > dist_[nextNode.first])
          continue;
        dist_[nextNode.first] = dist_[curNode.first] + nextNode.second;
        trips[nextNode.first][0] = curNode.first;
        pq_.push({nextNode.first, dist_[nextNode.first]});
      }
    }

    std::ostringstream oss;
    oss << "시작정점 1에서 각 정점간의 최단거리\n";

    for (int i = 0; i < g_.size(); i++) {
      oss << "1 -> " << i + 1 << ": " << dist_[i] << "\n";
    }

    std::vector<int> curTtrip;
    oss << "\n경로 추적 \n";
    for (int i = 0; i < 4; i++) {
      auto curIdx = i;
      curTtrip.push_back(curIdx);
      while (curIdx != 0) {
        curIdx = trips[curIdx][0];
        curTtrip.push_back(curIdx);
      }
      std::reverse(curTtrip.begin(), curTtrip.end());
      oss << "1 -> ";
      for (auto v : curTtrip) {
        oss << v + 1 << " ";
      }
      oss << "\n";
      curTtrip.clear();
    }
    addHistory(oss.str());
  }

  void printHistory() { std::cout << "===결과===\n" << history_; }

 private:
  std::string history_;
  std::vector<std::vector<std::pair<int, int>>> g_;
  std::priority_queue<std::pair<int, int>, std::vector<std::pair<int, int>>,
                      CompareBySecond>
      pq_;
  std::vector<int> dist_;
  std::string data_ = R"(
vertax: 4, Edge: 6
1 2 3 -> v1 -> v2 (가중치 3)
2 3 1 
3 4 1 
1 3 5 
2 4 5 
1 4 4
  )";
  void addHistory(const std::string& record) { history_ += record + "\n"; }
};

inline void testDijkstra() {
  Dijkstra dijkstra;
  dijkstra.solve();
  dijkstra.printHistory();
}
}  // namespace graph