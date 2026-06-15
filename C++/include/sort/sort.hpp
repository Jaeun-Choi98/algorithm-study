#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <cstdlib>

namespace sort
{
  class Bubble
  {
  public:
    Bubble(int cap, int max_value) : history_("")
    {
      int r = 0;
      std::ostringstream oss;
      oss << "정렬 전 배열: ";
      for (int i = 0; i < cap; i++)
      {
        r = std::rand() % max_value;
        array_.push_back(r);
        oss << r << ",";
      }
      oss << "\n";
      addHistory(oss.str());
    };

    void run()
    {
      int size = array_.size();
      for (int i = 0; i < size; i++)
      {
        for (int j = i + 1; j < size; j++)
        {
          if (array_.at(i) > array_.at(j))
          {
            std::swap(array_[i], array_[j]);
          }
        }
      }
      std::ostringstream oss;
      oss << "정렬 후 배열: ";
      int r = 0;
      for (int i = 0; i < size; i++)
      {
        r = array_[i];
        oss << r << ",";
      }
      oss << "\n";
      addHistory(oss.str());
    };

    void printHistory()
    {
      std::cout << "=== 결과 ===\n"
                << history_;
    }

  private:
    std::string history_;
    std::vector<int> array_;
    void addHistory(const std::string &record) { history_ += record + "\n"; };
  };

  inline void testSort()
  {
    Bubble bubble(10, 100);
    bubble.run();
    bubble.printHistory();
  }
}