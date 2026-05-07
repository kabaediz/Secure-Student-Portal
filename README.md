# Secure Student Portal

> **Ein Web-Security-Portfolio-Projekt (Spring Boot)**

Dieses Projekt ist eine einfache Studentenportal-Anwendung, die für Lern- und Demonstrationszwecke im Rahmen meines Studiums entwickelt wurde. Der Fokus liegt hierbei nicht auf vollständiger Business-Logik, sondern auf der **sicheren Umsetzung nach Best Practices (orientiert an den OWASP Top 10)**. Das Projekt demonstriert eine sichere Architektur, grundlegende Authentifizierung, Autorisierung und die Vermeidung gängiger Web-Schwachstellen.

## Security Features (Fokus)

* **Sichere Authentifizierung (A07: Identification and Authentication Failures):** 
  * Passwörter werden niemals im Klartext gespeichert (verwendet **Bcrypt**).
  * Session-basiertes Login.
* **Role-Based Access Control & IDOR-Schutz (A01: Broken Access Control):**
  * Strenge Trennung zwischen `ROLE_STUDENT` und `ROLE_ADMIN`.
  * Studenten können nur ihre *eigenen* Noten und Profile abrufen (Vermeidung von Insecure Direct Object References).
* **Injection-Prävention (A03: Injection):**
  * Nutzung von Spring Data JPA / Hibernate mit Prepared Statements.
* **Sichere Konfiguration & Fehlerbehandlung (A05: Security Misconfiguration):**
  * Globales Exception-Handling: Nutzer sehen nur generische Fehlermeldungen, um Information Leakage (wie Stacktraces) zu vermeiden.
* **Security Header & CSRF:**
  * Eingebaute Schutzmaßnahmen durch Spring Security (wie X-Frame-Options gegen Clickjacking).
* **Software Composition Analysis (A06: Vulnerable and Outdated Components):**
  * OWASP Dependency-Check Maven Plugin in die Build-Pipeline integriert.

## Technologien

* **Backend:** Java 17+, Spring Boot 3, Spring Security
* **Datenbank:** PostgreSQL (via Docker Compose) / H2 (für lokale Tests)
* **Build-Tool:** Maven
* **Testing:** JUnit 5, MockMvc

## Setup & Ausführung

### Voraussetzungen
* Java 17+
* Docker & Docker Compose (für die Datenbank)
* Maven

### Lokal starten

1. **Datenbank starten:**
   ```bash
   docker-compose up -d
   ```
2. **Applikation starten:**
   ```bash
   mvn spring-boot:run
   ```

## Test-Nutzer

Die Datenbank wird beim Start mit folgenden Demo-Nutzern initialisiert (nur für lokale Entwicklungszwecke):

| Rolle | Email | Passwort |
| :--- | :--- | :--- |
| **Admin** | `admin@portal.local` | `Admin!123` |
| **Student** | `student1@portal.local` | `Student!123` |

## Tests ausführen
Unit- und Integrationstests (Fokus auf Security und Access Control) ausführen:
```bash
mvn test
```
