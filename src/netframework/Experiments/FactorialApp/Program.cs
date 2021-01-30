using System;

namespace FactorialApp
{
    class Program
    {
        static void Main(string[] args)
        {
            var n = 10;

            long fact = 1;
            for (var i = 1; i <= n; i++)
            {
                fact *= i;
            }

            Console.WriteLine("Factorial({0}) = {1}", n, fact);
        }
    }
}
