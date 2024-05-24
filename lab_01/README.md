Lab. 1

1. Cel: Prosty kalkulator pól i obwodów figur dwu- i trójwymiarowych;
2. Przygotuj interfejs Printable z metodą void print();
3. Przygotuj klasę abstrakcyjną Figure zawierającą, abstrakcyjne metody:
- double calculateArea();
- double calculatePerimeter();
6. Zaimplementuj klasy Triangle, Square, Circle dziedziczące po klasie Figure i implementujące
interfejs Printable:

a. Do klas można dopisać dowolne metody i pola pomocnicze;

b. Każda klasa powinna mieć konstruktor z parametrami typowymi dla danej figury;

c. Metoda print z interfejsu powinna wypisywać na ekran informacje o obiekcie (dane
charakterystyczne dla figury);

d. W przypadku podania niepoprawnych danych podczas konstrukcji obiektu - wyjątek
powinien zostać rzucony;

7. Zaimplementuj klasę Prism (oznaczającą graniastosłup prawidłowy) o dowolnej podstawie
typu Figure. Oblicz jego pole powierzchni oraz objętość. Wykorzystaj w tym celu abstrakcję;

9. Wedle uznania prosty konsolowy interfejs użytkownika (oparty o while i switch)
umożliwiający:

a. wybór figury;

b. podanie danych;

c. wyświetlenie;

d. wyjście z programu;

11. Architektura programu powinna w maksymalny sposób odgraniczyć część interfejsu
użytkownika (wprowadzania/wypisywanie danych) od logiki programu, tj. dane wprowadzaj
w oddzielnej klasie a implementacje logiki pozostaw odseparowaną od wprowadzania
danych.
