# 🚀 TicketSystem: Enterprise Ticket Management System

**TicketSystem** ist eine hochperformante Full-Stack-Lösung zur Optimierung von Kundensupport-Workflows. Das System vereint administrative Kontrolle, datengestützte Analysen und ein intuitives Benutzererlebnis in einer modernen Web-Architektur.

## 🛠 Kernfunktionalitäten

### 1. Intelligentes Support-Dashboard

Das Zentrum für Support-Mitarbeiter. Es ermöglicht eine effiziente Ticket-Verwaltung durch:

* **Ticket-Lifecycle:** Nahtloses Übernehmen (Assign) und Freigeben (Unassign) von Tickets.
* **Rollenbasierte Sichtbarkeit:** Differenzierte Ansichten für "Eigene Aufgaben" vs. den globalen Ticket-Pool.
* **Echtzeit-Status-Updates:** Sofortige Rückmeldung über Ticket-Zustände (`OPEN`, `IN_PROGRESS`, `CLOSED`).

### 2. Administrative Benutzerverwaltung (Drag-and-Drop)

Ein High-End-Feature für Admins zur dynamischen Rollenverteilung:

* **Kanban-Style Management:** Verschieben von Nutzern zwischen den Rollen `USER`, `SUPPORT` und `ADMIN` per Drag-and-Drop.
* **Sicherheits-Validierung:** Jede Änderung wird im Backend gegen JWT-Claims und Rollenberechtigungen geprüft.
* **Responsive UX:** Vollwertige Drag-and-Drop-Funktion am Desktop, intuitive Umschalter in der mobilen Ansicht.

### 3. Business Analytics & Reporting

Datengesteuerte Entscheidungen durch visuelle Aufbereitung:

* **Live-Metriken:** Echtzeit-Counter für Systemnutzer und Ticket-Quoten.
* **Historische Analyse:** Ein responsives Area-Chart (Recharts), das Ticket-Trends über Monate hinweg visualisiert.
* **PostgreSQL Optimized:** Hochperformante Aggregation der Daten direkt auf Datenbankebene mittels optimierter `TO_CHAR`-Abfragen.

---

## 🧠 AI-Driven Prioritization (Local Engine)

Ein besonderes Highlight dieses Projekts ist die **KI-ähnliche Priorisierungs-Logik**, die direkt im Spring Boot Backend implementiert wurde:

* **Zero-API-Latency:** Im Gegensatz zu externen KI-Diensten fallen keine Kosten oder Latenzen an. Die Logik läuft nativ auf dem Server.
* **Heuristische Analyse:** Das System analysiert Ticket-Inhalte, Schlagwörter und die Historie des Erstellers, um automatisch Prioritäten (`LOW` bis `URGENT`) vorzuschlagen.
* **Business Rules Engine:** Prioritäten werden dynamisch angepasst, basierend auf der Zeit seit der Erstellung und dem Kundenstatus, um SLAs (Service Level Agreements) einzuhalten.

---

## 📈 Mehrwert für den Business-Workflow

| Feature | Business-Nutzen |
| --- | --- |
| **Zentrales Dashboard** | Reduziert die Ticket-Reaktionszeit (First Response Time) durch klare Zuweisungen. |
| **Native Priorisierung** | Garantiert, dass kritische Probleme (`URGENT`) sofort bearbeitet werden, ohne manuelle Triage. |
| **Admin-Statistiken** | Identifiziert Engpässe im Support-Team und hilft bei der Ressourcenplanung. |
| **Responsive Design** | Ermöglicht Support-Mitarbeitern volle Handlungsfähigkeit auch auf mobilen Endgeräten. |

---

## 🗺️ Roadmap: Zukünftige Features

Um die Plattform zu vervollständigen auszubauen, sind folgende Erweiterungen geplant:

* **Profilkontoverwaltung:** Ein persönlicher Bereich für Nutzer zum Verwalten von Avataren, Passwörtern und persönlichen Präferenzen.
* **E-Mail-Notifikationen:** Automatische Benachrichtigung per SMTP bei Ticket-Zuweisung, Statusänderungen oder kritischen Eskalationen.
* **PDF-Exporte:** Generierung von professionellen Ticket-Zusammenfassungen für Kunden und monatlichen Performance-Berichten für das Management.

---

## 💻 Tech Stack

* **Frontend:** React 18, Tailwind CSS, Recharts, `@hello-pangea/dnd`.
* **Backend:** Java 21, Spring Boot 3, Spring Security (JWT & OAuth2).
* **Datenbank:** PostgreSQL.
* **Icons:** Lucide-React.

---

### ⚙️ Installation & Start

1. **Backend:** `./mvnw spring-boot:run`
2. **Frontend:** `npm install` -> `npm run dev`
