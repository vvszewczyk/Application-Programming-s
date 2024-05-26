<h3>Lab. 3 – Algorytmy</h3>

Każde zadanie zaimplementuj w osobnej paczce. Zadbaj o przejrzystość kodu (w
metodzie main powinno być tylko uruchomienie zadania, zadanie powinno być
zaimplementowane w dedykowanej klasie, testy powinny być w osobnej klasie).

1. Zaimplementuj z wykorzystaniem wzorca projektowego strategia pięć algorytmów dla
sortowania tablic. Wynikiem ma być (dla każdego sortowania) zmierzony czas dla
wersji: pesymistycznej, optymistycznej oraz oczekiwanej.
https://www.geeksforgeeks.org/sorting-algorithms/

3. Ciąg „PAYPALISHIRING” jest zapisywany zygzakiem w określonej liczbie wierszy w
następujący sposób:
(dla lepszej czytelności możesz wyświetlić ten wzór stałą czcionką)
A następnie przeczytaj wiersz po wierszu: „PAHNAPLSIIGYIR”

Napisz kod, który pobierze ciąg znaków i dokonaj konwersji, biorąc pod uwagę liczbę
wierszy:
string convert(string s, int numRows);
Przykład 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Przykład 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"

5. Biorąc pod uwagę dwie posortowane tablice num1 i num2 o rozmiarach odpowiednio
m i n, zwróć medianę dwóch posortowanych tablic.
Ogólna złożoność czasu wykonania powinna wynosić O(log (m+n)).

Przykład 1:

Wejście: num1 = [1,3], num2 = [2] Wyjście: 2,00000
Objaśnienie: połączona tablica = [1,2,3], a mediana wynosi 2.

Przykład 2:

Wejście: num1 = [1,2], num2 = [3,4] Wyjście: 2,50000
Objaśnienie: połączona tablica = [1,2,3,4], a mediana wynosi (2 + 3) / 2 = 2,5.

7. Biorąc pod uwagę tablicę liczb całkowitych nums, zwróć wszystkie trójki [nums[i],
nums[j], nums[k]] takie, że i != j, i != k oraz j != k, poza tym nums[i] + nums[j] + nums[k] == 0.

Należy zauważyć, że zbiór rozwiązań nie może zawierać zduplikowanych trójek.

Przykład 1:
Wejście: nums = [-1,0,1,2,-1,-4]

Przykład 2:
Wejście: liczby = [0,1,1] Wyjście: [[-1,-1,2],[-1,0,1]]

Wyjaśnienie:
liczby[0] + liczby[1] + liczby[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.

Odrębne trojaczki to [-1,0,1] i [-1, -1,2].
Zauważ, że kolejność wyników i kolejność trójek nie ma znaczenia.
Wyjście: []
Objaśnienie: Jedyna możliwa trójka nie sumuje się do 0.

Przykład 3:
Wejście: liczby = [0,0,0] Wyjście: [[0,0,0]]
Wyjaśnienie: Jedyna możliwa trójka sumuje się do 0.

5. Dana jest macierz o wymiarach *n* oraz *m*. Wypisz elementy macierzy jako spirala,
przeciwnie do kierunkiem ruchu wskazówek zegara.
6. Zaimplementuj własne klasy wyjątków na wypadek nie spełnienia któregoś z
wymogów algorytmu.
