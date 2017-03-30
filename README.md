# Task4

Необходимо реализовать класс Matrix:
<ul>
<li>значения матрицы должны храниться в двумерном массиве типа int;</li>
<li>инициализация матрицы должна осуществляться считыванием данных из файла. В первой строке файла содержится размерность матрицы, далее строки матрицы;</li>
<li>необходимо определить методы сложения, умножения матриц и деление матрицы на число.</li>
</ul>

Определить классы исключительных ситуаций: IllegalMatrixDimensionException и DivisionByZeroException.
В методе main добавить перехват исключений и вывести в консоль сообщение.

Ожидаемый вывод в результате выполнения программы:
<pre>
A + A =
2 4 6 
8 10 12 

A * B =
Matrices have illegal dimensions for this operation

A + C =
Matrices have illegal dimensions for this operation

A / 0 =
Cannot divide by zero!

B * A =
Matrices have illegal dimensions for this operation

B * B =
Matrices have illegal dimensions for this operation

B * C =
364 388 
499 532 

B / 1 =
7 8 9 
10 11 12 

C * A =
69 96 123 
79 110 141 
89 124 159 

C * B =
231 258 285 
265 296 327 
299 334 369 

C * C =
Matrices have illegal dimensions for this operation

C / 2 =
6 7 
7 8 
8 9 

</pre>

<h3>Темы для изучения:</h3>
<ul>
<li>Исключительные ситуации</li>
</ul>