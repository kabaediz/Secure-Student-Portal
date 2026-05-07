# Secure Student Portal 🛡️🎓

> **Ein praxisnahes Web-Security-Portfolio-Projekt in Spring Boot**

Dieses Projekt ist eine einfache Studentenportal-Anwendung, die für Lern- und Demonstrationszwecke entwickelt wurde. Der Fokus liegt nicht auf dem Business-Value, sondern auf der **sicheren Umsetzung nach Best Practices (OWASP)**. Es demonstriert sichere Architektur, Authentifizierung, Autorisierung und die Vermeidung gängiger Web-Schwachstellen.

## 🔒 Security Features (OWASP Top 10 Fokus)

* **Sichere Authentifizierung (A07: Identification and Authentication Failures):** 
  * Passwörter werden niemals im Klartext gespeichert (verwendet **Bcrypt** oder **Argon2**).
  * JWT- oder Session-basiertes Login mit `HttpOnly` und `Secure` Flags für Cookies.
* **Role-Based Access Control & IDOR-Schutz (A01: Broken Access Control):**
  * Strenge Trennung zwischen `ROLE_STUDENT` und `ROLE_ADMIN`.
  * Studenten können nur ihre *eigenen* Noten und Profile abrufen (verhindert Insecure Direct Object References).
* **Injection-Prävention (A03: Injection):**
  * Nutzung von Spring Data JPA / Hibernate mit Prepared Statements.
* **Sichere Konfiguration & Fehlerbehandlung (A05: Security Misconfiguration):**
  * Keine sensitiven Daten im Quellcode; Nutzung von Environment-Variablen für Secrets.
  * Globales Exception-Handling: Nutzer sehen nur generische Fehlermeldungen (kein Information Leakage / Stacktraces).
* **Security Header & CSRF (A05 / A10):**
  * CSRF-Protection ist aktiv.
  * HTTP Strict Transport Security (HSTS), X-Content-Type-Options und X-Frame-Options (Clickjacking-Schutz) sind konfiguriert.
* **Software Composition Analysis (A06: Vulnerable and Outdated Components):**
  * OWASP Dependency-Check Maven Plugin in die Build-Pipeline integriert.

## 🛠️ Technologien

* **Backend:** Java 17+, Spring Boot 3, Spring Security
* **Datenbank:** PostgreSQL (via Docker Compose) / H2 (für Tests)
* **Build-Tool:** Maven
* **Sicherheit:** Bcrypt, JWT/Session, OWASP Dependency Check
* **Testing:** JUnit 5, MockMvc

## 🚀 Setup & Ausführung

### Voraussetzungen
* Java 17+
* Docker & Docker Compose (für die Datenbank)
* Maven

### Lokal starten

1. **Datenbank starten:**
   ```bash
   docker-compose up -d
   ```
2. **Umgebungsvariablen konfigurieren:**  
   Die Anwendung benötigt folgende Variablen (z.B. in der IDE oder in `.env`):
   `DB_URL`, `DB_USER`, `DB_PASSWORD`, `JWT_SECRET` (falls JWT verwendet wird).
3. **Applikation starten:**
   ```bash
   mvn spring-boot:run
   ```

## 👥 Test-Nutzer

Die Datenbank wird beim Start mit folgenden Demo-Nutzern initialisiert:

| Rolle | Email | Passwort |
| :--- | :--- | :--- |
| **Admin** | `admin@portal.local` | `Admin!123` |
| **Student** | `student1@portal.local` | `Student!123` |

## 🧪 Tests ausführen
Unit- und Integrationstests (Fokus auf Security und Access Control) ausführen:
```bash
mvn test
```
