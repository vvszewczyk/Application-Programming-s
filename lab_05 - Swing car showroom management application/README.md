<h3>lab05 - Swing</h3>

implementacja aplikacji okienkowej przy pomocy biblioteki Swing języka Java

Zadania
1. Zaimplementuj lub wykorzystaj model z lab02, który dysponuje następującymi
funkcjonalnościami:

a. Klasę Vehicle z polami: marka (String), model (String), stan (ItemCondition), cena
(double), rok produkcji (integer), przebieg (double), pojemność silnika (double)

i. Konstruktor pozwalający na łatwą inicjalizację obiektu (marka, model, stan,
cena, rok, przebieg, silnik)

ii. Metodę print wypisujący na standardowe wyjście pełne informacje o
towarze

iii. Niech klasa Vehicle implementuje interfejs Comparable< Vehicle >
pozwalający na porównanie obiektów ze względu na nazwę.

b. Klasę CarShowroom, która zawiera takie informacje jak: nazwa salonu, lista
samochodów, maksymalna pojemność salonu (maksymalna ilość wszystkich
pojazdów). Oraz następujące metody:

i. addProduct(Vehicle) – Dodająca produkt. Jeśli dany produkt będzie już
obecny w magazynie (produkt o tej samej nazwie istnieje) to należy
zsumować ich ilość. Produkt może zostać dodany, tylko jeśli niezostanie
przekroczona pojemność magazynu. Jeśli pojemność zostanie przekroczona
wypisz komunikat na standardowe wyjście błędów (System.err)

ii. getProduct(Vehicle) – Zmniejszający ilość danego produktu o jeden lub
usuwający go całkowicie, jeśli po zmianie wartość będzie równa 0.

iii. removeProduct(Vehicle) – usuwający dany produkt całkowicie z magazynu.

iv. search(String) - Przyjmującej nazwę produktu i zwracający go. Zastosuj
Comparator

v. searchPartial(String) – Przyjmujący fragment nazwy produktu i zwracający
wszystkie produkty, które pasują.

vi. countByCondition(ItemCondition) – zwracający ilość produktów o danym
stanie

vii. summary() – wypisującą na standardowe wyjście informację o wszystkich
produktach

viii. sortByName() – zwracającą posortowana listę produktów – po nazwie
alfabetycznie

ix. sortByAmount() – zwracającą posortowaną listę produktów po ilości –
malejąco – zastosuj własny Comparator

x. max() – zwracającą produkt którego jest najwięcej - zastosuj metodę
Collections.max

c. Klasę CarShowroomContainer przechowującą w Map<String, CarShowroom > salony.
(Kluczem jest nazwa salonu), zaimplementuj metody:

i. addCenter(String, double) – dodającą nowy salon o podanej nazwie i zadanej
pojemności do spisu salonów

ii. removeCenter(String) – usuwający salon o podanej nazwie

iii. findEmpty() – zwracający listę pustych magazynów

iv. summary() – wypisujący na standardowe wyjście informacje zawierające:
nazwę salonu i procentowe zapełnienie

3. Zaprojektuj interfejs graficzny do obsługi salonów
4. Zaimplementuj obsługę interfejsu graficznego wedle następujących reguł:
- Interfejs składa się z dwóch list: salonów (_list of centers) oraz pojazdów (_list of vehicles
in selected center).

 2. Stwórz generyczny komponent na bazie
o [`JTable`]
(https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html)
Oraz
o [`AbstractTableModel`]
(https://docs.oracle.com/javase/7/docs/api/javax/swing/table/AbstractTableModel.ht
ml), który można wykorzystać zarówno dla salonów jak i dla pojazdów. Więcej
informacji nt. AbstractTableModel na [StackOverflow]
(https://stackoverflow.com/questions/9845800/abstracttablemodel-tutorial).
- Po wybraniu salonu (zaznaczeniu go na liście salonów) wyświetla się dostępna w nim lista
samochodów.
- Zaznaczony salon lub pojazd ma zostać usunięty po naciśnięciu odpowiedniego guzika
(_remove carShowroom _ lub _remove vehicle_).
- Po naciśnięciu przycisków dodaj salon (_add carShowroom center_) oraz dodaj produkt
(_add new vehicle_), wprowadzając odpowiednie parametry za pomocą komponentu
[JOptionPane](https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html).
Alternatywnie, przycisk może dodać wiersz do tabeli a wprowadzenie wartości odbywa się
poprzez edycję w tabeli.
6. Po naciśnięciu przycisku _sort centers by current load_ ma zostać wykonane sortowanie
obiektów względem maksymalnego obciążenia. **Uwaga**: przekazuj referencje w
odpowiedni sposób, wówczas taka operacja to wywołanie jednej metody.
7. [**Nieobowiązkowe**]
Pole _filter textbox_ służy do wprowadzania nazwy produktu. Po naciśnięciu klawisza
enter, w tabeli mają zostać wyświetlone produkty zgodne z wprowadzonym tekstem.
[**Nieobowiązkowe**]
Pole _state combobox_ ma zostawić w tabeli produkty, zgodne z wybraną wartością. W
tym celu skorzystaj z komponentu
[JComboBox](https://docs.oracle.com/javase/7/docs/api/javax/swing/JComboBox.html).
5. Staraj się nie mieszać modelu systemu z warstwą użytkownika. Ogranicz interakcję
pomiędzy nimi do minimum
