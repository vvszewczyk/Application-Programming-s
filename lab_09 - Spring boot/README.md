<h3>lab09 - Spring Boot</h3>
1. Zaimplementuj kontroler restowy, obsługujący następujące żądania:
1 POST /api/product dodaje pojazd do salonu

2 DELETE /api/product/:id usuwa pojazd do salonu

3 GET /api/product/:id/rating zwraca średnią ocenę pojazdu/salonu

4 GET /api/product/csv zwraca wszystkie pojazdy w formie pliku CSV

5 GET /api/fulfillment zwraca wszystkie salony

6 POST /api/fulfillment dodaje nowy salon

7 DELETE /api/fulfillment/:id usuwa salon

8 GET /api/fulfillment/:id/products zwraca wszystkie pojazdy w salonie

9 GET /api/fulfillment/:id/fill zwraca zapełnienie procentowe salonu

10 POST /api/rating dodaje ocenę dla pojazdu/salonu

2. Aplikacja powinna przechowywać dane w bazie danych (wykonane ćwiczenie lab08) lub w
CarShowroomContainer (wykonane ćwiczenie lab05).
3. Napisz testy jednostkowe dla wskazanych w zadaniu 1 endpointów (1 pkt).
4. Wykorzystaj Mavena do dołączania zależności oraz uruchamiania i testowania projektu.
Uwagi
1. W kontrolerze powinna znajdować się obsługa przychodzącego obiektu request oraz
zwrócenie obiektu response. Wszystkie pozostałe operacje, tj. połączenie z bazą danych,
generacja CSV, wyliczanie wartości, powinno odbywać się w innej, dedykowanej do tego celu,
klasie.
2. Jeżeli obiekt o podanym id nie istnieje, należy zwrócić status 404.
3. Żadna metoda GET nie może modyfikować stanu obiektów.
5. Przechwytywanie wyjątków jest obowiązkowe. Należy bowiem unikać zwracaniu error 500.
6. Do testów można wykorzystać aplikacje [Postman](https://www.postman.com/) lub
[Insomnia](https://insomnia.rest/).
7. Przesłanie nieprawidłowych parametrów danego routingu powinny powodować zwrócenie
odpowiedniego statusu HTTP oraz informacji o błędzie.
8. Obsługa wyjątków powinna być sensowna - wykorzystaj odpowiednie kody HTTTP
