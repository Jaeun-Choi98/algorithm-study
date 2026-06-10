#pragma once
#include <string>

class Calculator
{
public:
  Calculator();

  double add(double a, double b);
  double subtract(double a, double b);
  double multiply(double a, double b);
  double divide(double a, double b);

  void printHistory() const;

private:
  std::string history_;
  void addHistory(const std::string &record);
};

void test_calc();