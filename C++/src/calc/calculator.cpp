#include "calc/calculator.hpp"
#include <stdexcept>
#include <iostream>
#include <sstream>

Calculator::Calculator() : history_("") {}

void Calculator::addHistory(const std::string &record)
{
  history_ += record + "\n";
}

double Calculator::add(double a, double b)
{
  double result = a + b;
  std::ostringstream oss;
  oss << a << " + " << b << " = " << result;
  addHistory(oss.str());
  return result;
}

double Calculator::subtract(double a, double b)
{
  double result = a - b;
  std::ostringstream oss;
  oss << a << " - " << b << " = " << result;
  addHistory(oss.str());
  return result;
}

double Calculator::multiply(double a, double b)
{
  double result = a * b;
  std::ostringstream oss;
  oss << a << " * " << b << " = " << result;
  addHistory(oss.str());
  return result;
}

double Calculator::divide(double a, double b)
{
  if (b == 0)
    throw std::invalid_argument("division by zero");
  double result = a / b;
  std::ostringstream oss;
  oss << a << " / " << b << " = " << result;
  addHistory(oss.str());
  return result;
}

void Calculator::printHistory() const
{
  std::cout << "=== 계산 기록 ===\n"
            << history_;
}

void test_calc()
{
  Calculator calc;

  std::cout << calc.add(10, 5) << "\n";      // 15
  std::cout << calc.subtract(10, 5) << "\n"; // 5
  std::cout << calc.multiply(10, 5) << "\n"; // 50
  std::cout << calc.divide(10, 5) << "\n";   // 2

  // 예외 처리
  try
  {
    calc.divide(10, 0);
  }
  catch (const std::invalid_argument &e)
  {
    std::cerr << "에러: " << e.what() << "\n";
  }

  calc.printHistory();
}