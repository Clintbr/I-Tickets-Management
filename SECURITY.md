# 🛡️ Security Documentation: TicketSystem

Sicherheit steht bei **TicketSystem** an erster Stelle. Dieses Dokument beschreibt die implementierten Sicherheitsmaßnahmen zum Schutz von Benutzerdaten, API-Endpunkten und der geschäftskritischen Priorisierungs-Logik.

## 🔐 Authentifizierung & Autorisierung

Das System nutzt einen **Stateless Security Ansatz** basierend auf modernen Industriestandards:

* **JWT (JSON Web Tokens):** Die Kommunikation zwischen Frontend und Backend erfolgt über kryptografisch signierte Tokens. Dies verhindert Session-Hijacking und ermöglicht eine skalierbare Authentifizierung.
* **RBAC (Role-Based Access Control):** Der Zugriff auf Ressourcen ist streng nach Rollen getrennt:
* `USER`: Erstellen und Einsehen eigener Tickets.
* `SUPPORT`: Bearbeitung und Zuweisung von Tickets.
* `ADMIN`: Volle Kontrolle über Benutzerrollen, Systemstatistiken und globale Prioritäten.


* **Method-Level Security:** Im Backend wird jeder Service-Aufruf durch `@PreAuthorize`-Annotationen geschützt, um unbefugte Zugriffe auf Controller-Ebene zu verhindern.

## 🛡️ Backend-Schutzmechanismen (Spring Boot)

* **Password Hashing:** Passwörter werden niemals im Klartext gespeichert. Wir nutzen **BCrypt** mit einem starken Salt-Faktor, um Brute-Force-Angriffe zu erschweren.
* **CORS Policy:** Die API akzeptiert nur Anfragen von verifizierten Domains (Cross-Origin Resource Sharing), um Cross-Site-Request-Forgery (CSRF) zu minimieren.
* **SQL Injection Prevention:** Durch den Einsatz von **Spring Data JPA** und vorbereiteten Statements (Prepared Statements) ist das System nativ gegen SQL-Injection-Angriffe geschützt.
* **Input Validation:** Alle eingehenden Daten werden mittels `@Valid` und Jakarta Bean Validation geprüft, um Malformed-Data-Angriffe zu verhindern.

## 🧠 Sicherheit der internen KI-Logik

Ein entscheidender Sicherheitsvorteil der **lokal implementierten Priorisierungs-Engine**:

* **Datensouveränität:** Da keine externen AI-APIs (wie OpenAI oder Anthropic) genutzt werden, verlassen keine sensiblen Ticket-Inhalte das interne Netzwerk.
* **Manipulation-Protection:** Die Logik zur Priorisierung ist tief im Backend gekapselt und für Endnutzer nicht manipulierbar. Nur Admins können die Gewichtung der Heuristiken beeinflussen.

---

## 🏗️ Infrastruktur & Deployment Empfehlungen

Für den produktiven Einsatz sollten folgende Maßnahmen ergriffen werden:

1. **SSL/TLS Verschlüsselung:** Alle Verbindungen müssen zwingend über HTTPS laufen.
2. **Environment Variables:** Sensible Daten wie der `JWT_SECRET` oder Datenbank-Logins dürfen niemals im Code stehen, sondern müssen über Umgebungsvariablen geladen werden.
3. **Rate Limiting:** Implementierung eines Rate-Limiters, um die API gegen DoS-Angriffe (Denial of Service) zu schützen.

---

> **Hinweis:** Sicherheit ist ein fortlaufender Prozess. Regelmäßige Updates der Abhängigkeiten (Dependencies) mittels Tools wie `npm audit` oder Snyk werden dringend empfohlen.
