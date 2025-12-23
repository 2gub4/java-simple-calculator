# Java Swing Calculator

## Project Description
This project is a fully functional desktop calculator application built using **Java Swing**. It provides a graphical user interface for performing basic mathematical operations. The application features logging system that records all calculations and memory operations to a local text file, ensuring a history of user activity is maintained across sessions.
The project follows an Object-Oriented approach, separating logic (calculations), utility (formatting and validation), and UI (Swing components).

---

## Technologies and Libraries
* **Java (Swing & AWT)** – Core framework for the Graphical User Interface (GUI).
* **NIO (java.nio.file)** – Used for advanced file handling and logging operations.
* **Functional Interfaces** – Utilizes `DoubleBinaryOperator` and `DoubleUnaryOperator` for mapping mathematical functions.
* **BigDecimal** – Used for precise decimal formatting and rounding.

---

## Main Functionalities
* **Basic & Advanced Math** – Supports addition, subtraction, multiplication, division, squaring ($x^2$), and square roots ($\sqrt{x}$).
* **Memory Management** – Dedicated functions for memory storage (`M`), addition (`M+`), and subtraction (`M-`).
* **Persistent Logging** – Automatically writes every operation to `calcLogs.txt`.
* **Keyboard Support** – Full integration with physical keyboard input via a custom `KeyAdapter`.
* **Error Handling** – Robust validation for division by zero and negative square roots, displaying "Error" on the interface.
* **Smart Formatting** – Automatically strips trailing zeros and rounds long decimals to 8 decimal places for clarity.

---

## Code Architecture
* **`SimpleCalculator.java`** – The main controller and UI definition.
* **`CalcFunc.java`** – The mathematical engine containing all static logic for operations.
* **`CalcLogs.java`** – Handles file I/O operations including writing, reading, and wiping logs.
* **`Pomocnicze.java`** – Utility class for numeric validation and string formatting.
* **`ValueHolder.java`** – A data structure for managing the current state of operands, results, and memory.
* **`CalcKeybordListener.java`** – Maps physical key presses to calculator actions.

---

## Usage
1. Run the `main` method in `Main.java`.
2. Use the GUI buttons or your keyboard to enter numbers and operators.
3. Use `C` to clear the entire state or `CE` to clear the current entry.
4. Check `calcLogs.txt` in the project directory for the calculation history.

---

## Author
* **Jakub Sosna** – Core logic, UI design, and hardware-style implementation.


<br>
<br>
<br>
<hr>
<br>
<br>
<br>


# Kalkulator Java Swing z Systemem Logowania i Pamięcią

## Opis projektu
W pełni funkcjonalna aplikacja kalkulatora desktopowego zbudowana przy użyciu biblioteki **Java Swing**. Zapewnia graficzny interfejs użytkownika do wykonywania podstawowych operacji matematycznych. Aplikacja posiada system trwałego logowania, który zapisuje wszystkie obliczenia i operacje na pamięci w lokalnym pliku tekstowym, co pozwala na zachowanie historii aktywności użytkownika między sesjami.
Projekt został zrealizowany w podejściu obiektowym, z wyraźnym oddzieleniem logiki (obliczenia), narzędzi (formatowanie i walidacja) oraz interfejsu (komponenty Swing).

---

## Technologie i biblioteki
* **Java (Swing & AWT)** – Główny framework do budowy graficznego interfejsu użytkownika (GUI).
* **NIO (java.nio.file)** – Wykorzystane do zaawansowanej obsługi plików i operacji logowania.
* **Interfejsy Funkcyjne** – Wykorzystanie `DoubleBinaryOperator` oraz `DoubleUnaryOperator` do mapowania funkcji matematycznych.
* **BigDecimal** – Użyte do precyzyjnego formatowania liczb dziesiętnych i zaokrąglania.

---

## Główne funkcjonalności
* **Podstawowa i zaawansowana matematyka** – Obsługa dodawania, odejmowania, mnożenia, dzielenia, potęgowania ($x^2$) oraz pierwiastkowania ($\sqrt{x}$).
* **Zarządzanie pamięcią** – Dedykowane funkcje przywoływania pamięci (`M`), dodawania (`M+`) oraz odejmowania (`M-`).
* **Trwałe logowanie** – Automatyczny zapis każdej operacji do pliku `calcLogs.txt`.
* **Obsługa klawiatury** – Pełna integracja z fizyczną klawiaturą poprzez autorski `KeyAdapter`.
* **Obsługa błędów** – Walidacja dzielenia przez zero oraz pierwiastkowania liczb ujemnych z wyświetlaniem komunikatu "Error".
* **Inteligentne formatowanie** – Automatyczne usuwanie zbędnych zer końcowych i zaokrąglanie długich ułamków do 8 miejsc po przecinku.

---

## Architektura kodu
* **`SimpleCalculator.java`** – Główny kontroler i definicja interfejsu użytkownika.
* **`CalcFunc.java`** – Silnik matematyczny zawierający statyczną logikę operacji.
* **`CalcLogs.java`** – Obsługa operacji wejścia/wyjścia (zapis, odczyt i czyszczenie logów).
* **`Pomocnicze.java`** – Klasa narzędziowa do walidacji numerycznej i formatowania tekstu.
* **`ValueHolder.java`** – Struktura danych zarządzająca aktualnym stanem operandów, wyników i pamięci.
* **`CalcKeybordListener.java`** – Mapowanie fizycznych klawiszy na akcje kalkulatora.

---

## Instrukcja obsługi
1. Uruchom metodę `main` w klasie `Main.java`.
2. Użyj przycisków GUI lub klawiatury do wprowadzania liczb i operatorów.
3. Użyj `C`, aby zresetować cały stan, lub `CE`, aby usunąć aktualnie wpisywaną liczbę.
4. Sprawdź plik `calcLogs.txt` w katalogu projektu, aby przejrzeć historię obliczeń.

---
