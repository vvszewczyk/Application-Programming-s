<h3>Lab. 2 – Kolekcje</h3>

Cel zadania: Stworzyć prosty symulator salonu samochodowego

Zaimplementuj:

1. Typ wyliczeniowy ItemCondition z polami: NEW, USED, DAMAGED
2. Klasę Vehicle z polami: marka (String), model (String), stan (ItemCondition), cena (double), rok produkcji
(integer), przebieg (double), pojemność silnika (double)

a. Konstruktor pozwalający na łatwą inicjalizację obiektu (marka, model, stan, cena, rok, przebieg,
silnik)

b. Metodę print wypisujący na standardowe wyjście pełne informacje o towarze

c. Niech klasa Vehicle implementuje interfejs Comparable< Vehicle > pozwalający na porównanie
obiektów ze względu na nazwę.

3. Klasę CarShowroom, która zawiera takie informacje jak: nazwa salonu, lista samochodów, maksymalna
pojemność salonu (maksymalna ilość wszystkich pojazdów). Oraz następujące metody:

a. addProduct(Vehicle) – Dodająca produkt. Jeśli dany produkt będzie już obecny w magazynie
(produkt o tej samej nazwie istnieje) to należy zsumować ich ilość. Produkt może zostać dodany,
tylko jeśli niezostanie przekroczona pojemność magazynu. Jeśli pojemność zostanie przekroczona
wypisz komunikat na standardowe wyjście błędów (System.err)

b. getProduct(Vehicle) – Zmniejszający ilość danego produktu o jeden lub usuwający go całkowicie,
jeśli po zmianie wartość będzie równa 0.

c. removeProduct(Vehicle) – usuwający dany produkt całkowicie z magazynu.

d. search(String) - Przyjmującej nazwę produktu i zwracający go. Zastosuj Comparator

e. searchPartial(String) – Przyjmujący fragment nazwy produktu i zwracający wszystkie produkty,
które pasują.

f. countByCondition(ItemCondition) – zwracający ilość produktów o danym stanie

g. summary() – wypisującą na standardowe wyjście informację o wszystkich produktach

h. sortByName() – zwracającą posortowana listę produktów – po nazwie alfabetycznie

i. sortByAmount() – zwracającą posortowaną listę produktów po ilości – malejąco – zastosuj własny
Comparator

j. max() – zwracającą produkt którego jest najwięcej - zastosuj metodę Collections.max

4. Klasę CarShowroomContainer przechowującą w Map<String, CarShowroom > salony. (Kluczem jest nazwa
salonu), zaimplementuj metody:

a. addCenter(String, double) – dodającą nowy salon o podanej nazwie i zadanej pojemności do spisu
salonów

b. removeCenter(String) – usuwający salon o podanej nazwie

c. findEmpty() – zwracający listę pustych magazynów

d. summary() – wypisujący na standardowe wyjście informacje zawierające: nazwę salonu i
procentowe zapełnienie

5. Dodać co najmniej jedną przydatną metodę i zmienną.

Pokazać działanie wszystkich metod w aplikacji w metodzie main poprzez uruchomienie każdej
metody wedle potrzeb. NIE twórz menu – pokaż przykładowe wywołania w metodzie main.
