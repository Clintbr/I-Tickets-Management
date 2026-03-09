# 🚀 TicketSystem: Enterprise Ticket Management System for Support Team

**TicketSystem** is a high-performance full-stack solution designed to optimize customer support workflows. The system combines administrative control, data-driven analytics, and an intuitive user experience within a modern web architecture.

---

# 🛠 Core Features

## 1. Intelligent Support Dashboard

The central hub for support agents. It enables efficient ticket management through:

* **Ticket Lifecycle:** Seamless assigning and unassigning of tickets.
* **Role-Based Visibility:** Distinct views for **"My Tasks"** versus the global ticket pool.
* **Real-Time Status Updates:** Immediate feedback on ticket states (`OPEN`, `IN_PROGRESS`, `CLOSED`).

---

## 2. Administrative User Management (Drag and Drop)

A high-end feature for administrators to dynamically manage user roles:

* **Kanban-Style Management:** Move users between the roles `USER`, `SUPPORT`, and `ADMIN` using drag-and-drop.
* **Security Validation:** Every change is validated in the backend against JWT claims and role permissions.
* **Responsive UX:** Full drag-and-drop functionality on desktop and intuitive toggle controls on mobile devices.

---

## 3. Business Analytics & Reporting

Make data-driven decisions with visual insights:

* **Live Metrics:** Real-time counters for system users and ticket statistics.
* **Historical Analysis:** A responsive area chart (Recharts) visualizing ticket trends over several months.
* **PostgreSQL Optimized:** High-performance data aggregation directly at the database level using optimized `TO_CHAR` queries.

---

# 🧠 AI-Driven Prioritization (Local Engine)

One of the highlights of this project is the **AI-like prioritization logic**, implemented directly within the Spring Boot backend:

* **Zero API Latency:** Unlike external AI services, this approach avoids additional costs and latency. The logic runs natively on the server.
* **Heuristic Analysis:** The system analyzes ticket content, keywords, and the creator’s history to automatically suggest priorities (`LOW` to `URGENT`).
* **Business Rules Engine:** Priorities are dynamically adjusted based on time since creation and customer status to maintain SLAs (Service Level Agreements).

---

# 📈 Business Value

| Feature                   | Business Benefit                                                                     |
| ------------------------- | ------------------------------------------------------------------------------------ |
| **Centralized Dashboard** | Reduces ticket response time (First Response Time) through clear ticket assignments. |
| **Native Prioritization** | Ensures critical issues (`URGENT`) are handled immediately without manual triage.    |
| **Admin Analytics**       | Identifies bottlenecks within the support team and supports resource planning.       |
| **Responsive Design**     | Enables support agents to work efficiently even on mobile devices.                   |

---

# 🗺️ Roadmap: Future Features

To further expand the platform, the following features are planned:

* **Profile Management:** A personal area for users to manage avatars, passwords, and personal preferences.
* **Email Notifications:** Automatic SMTP notifications for ticket assignments, status changes, or critical escalations.
* **PDF Exports:** Generation of professional ticket summaries for customers and monthly performance reports for management.

---

# 💻 Tech Stack

* **Frontend:** React 18, Tailwind CSS, Recharts, `@hello-pangea/dnd`
* **Backend:** Java 21, Spring Boot 3, Spring Security (JWT & OAuth2)
* **Database:** PostgreSQL
* **Icons:** Lucide React

---

# ⚙️ Installation & Setup

### 1. Backend

```bash
./mvnw spring-boot:run
```

### 2. Frontend

```bash
npm install
npm run dev
```
