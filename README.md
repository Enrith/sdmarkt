# Bewerberaufgabe SuperDuperMarkt

## Inhaltsverzeichnis

- [Aufgaben](#aufgaben)
- [Erste Schritte](#erste-schritte)
- [Projektstruktur](#projektstruktur)
- [Entwurfsmuster](#entwurfsmuster)

## Aufgaben
### Pflicht
- ✔️ Erstelle eine Konsolenanwendung.
- ✔️ Gib zunächst die Produkte deines Sortiments und deren Startwerte aus.
- ✔️ Gib iterativ für alle fortfolgenden Tage eine Übersicht aller Produkte aus. Die tägliche Übersicht
soll Namen, Preis, Qualität und die Information, ob ein Produkt entsorgt werden muss,
beinhalten.
### Kür
- ✔️ Arbeite testorientiert (nicht „Test-Driven“) und schreibe Tests für die Verarbeitungsregeln.
- ✔️ Verwende passende Muster des „objektorientierten“ Designs.
- ✔️ Begründe deine Wahl verwendeter Design Patterns. (Siehe [Entwurfsmuster](#entwurfsmuster))
- ❌ Lies Produktdaten aus einer .csv-Datei ein.
- 🟡 Gestalte die Anwendung modular, sodass neue Produkttypen und alternative Datenquellen für
den Import der Produktdaten integriert werden können, ohne den zentralen Steuerungs-Code
zu modifizieren.
- ❌ Erstelle ein Modul für einen neuen Produkttypen (gestalte das Produkt und die
Verarbeitungsregeln selbst).
- ❌ Erstelle ein Modul für eine weitere Datenquelle (SQL).
- 🟡 Gestalte die Anwendung so, dass sie im laufenden Betrieb modular um zuvor genannte Module
erweitert werden kann.

## Erste Schritte

### Voraussetzungen

- Java Development Kit (JDK) 11 oder höher
- Eine IDE (z. B. IntelliJ IDEA, Eclipse)
- Maven

### Installation

1. Klone das Repository:
   ```bash
   git clone https://github.com/Enrith/superdupermarkt.git
   ```
2. Wechsle in das Projektverzeichnis:
   ```bash
   cd superdupermarkt
   ```
3. Öffne das Projekt in deiner bevorzugten IDE.

4. Baue das Projekt (wenn du Maven verwendest):
   ```bash
   mvn clean install
   ```

Natürlich! Hier ist der Abschnitt zur Verwendung der Anwendung, angepasst mit der Verwendung des Maven-Plugins `mvn exec:java`:

### Verwendung

Um die Anwendung auszuführen, kannst du das Maven-Exec-Plugin verwenden, um die `Main`-Klasse zu starten. Dies initialisiert das Produktmanagementsystem und demonstriert die verschiedenen Funktionen.

Stelle sicher, dass du im Projektverzeichnis bist und führe den folgenden Befehl aus:

```bash
mvn exec:java
```

## Projektstruktur

```
src
└── java
    └── com.superdupermarkt
        ├── factory
        │   └── ProductFactory.java
        ├── main
        │   └── Main.java
        ├── model
        │   ├── Cheese.java
        │   ├── Product.java
        │   ├── ProductType.java
        │   └── Wine.java
        ├── observer
        │   ├── ProductLogger.java
        │   ├── ProductManager.java
        │   └── ProductObserver.java
        └── strategy
            ├── CheeseStrategy.java
            ├── ProductStrategy.java
            └── WineStrategy.java
tests
└── java
    └── com.superdupermarkt
        ├── factory
        │   └── ProductFactoryTest.java
        ├── observer
        │   └── ProductManagerTest.java
        └── strategy
            ├── CheeseStrategyTest.java
            └── WineStrategyTest.java
```

### Verzeichnisbeschreibungen

- **factory**: Enthält Klassen zur Erstellung von Produkten mit dem Factory-Muster.
- **main**: Einstiegspunkt der Anwendung.
- **model**: Enthält die Produktmodelle wie `Product`, `Cheese`, und `Wine`.
- **observer**: Implementiert das Observer-Muster zur Benachrichtigung über Produktbestandsänderungen.
- **strategy**: Enthält verschiedene Strategien für die Verarbeitungsregeln der Produkte.

## Entwurfsmuster

**Factory-Muster**:
- Wird verwendet, um Produktinstanzen zu erstellen, ohne die Instanziierungslogik dem Client offenzulegen.
- Es ist denkbar, hier das Programm zu erweitern um verschiedene Datenquellen zu ermöglichen.

**Observer-Muster**: 
- Ermöglicht es einem Objekt (Subjekt), andere Objekte (Beobachter) über Änderungen seines Zustands zu benachrichtigen.
- Benachrichtigt in diesem Fall alle Observer über den aktuellen Warenbestand und mögliche Ware, welche entfernt werden muss.
- Es ist denkbar, dass es noch mehr use cases für den Warenbestand gibt zum Beispiel ein Nutzer, welcher benachrichtigt werden möchte, wenn Waren aus den Bestand entfernt werden müssen, oder ein Display welches den eingehen Warenbestand filtert und darstellt.

**Strategy-Muster**:
- Ermöglicht die Auswahl des Verhaltens eines Algorithmus zur Laufzeit.
- In diesem Fall kapselt das Strategy Pattern die Verarbeitungsregeln der verschiedenen Produktarten.
- So ist es möglich zur Laufzeit die Verarbeitungsregel eines Produktes zu wechseln.

## Verbesserungsmöglichkeiten
- Implementation der weiteren Kür Aufgaben.
- Erweiterung und evtl. weitere Aufteilung des Produktmanagers um die Tagesdatum basierende Logik aus den ProduktVerhalten Strategy Pattern zu lösen.
- Obiges ebenfalls für die Überprüfung, ob Ware in den Bestand aufgenommen werden kann, um zum Beispiel eigene Exceptions und Validatoren.
- Abänderung des Umgangs mit verworfenen Produktbestand, zurzeit wird dieser sofort entfernt und die Observer dementsprechend benachrichtigt.
Ich denke jedoch, dass es auch gut sein könnte dieses Kriterium aufzuweichen und im Vorgang etwaige Rabatte etc durchzuführen.
- Portieren des Projektes in einen Spring Kontext um zum Beispiel in einem kleinen Web Frontend die Wahl eines bestimmten Datums und Ausgabe dessen Warenbestands zu ermöglichen.
