# 🛡️ Security Documentation: TicketSystem

Security is a top priority for **TicketSystem**.
This document outlines the implemented security measures designed to protect user data, API endpoints, and the business-critical prioritization logic.

---

# 🔐 Authentication & Authorization

The system follows a **stateless security approach** based on modern industry standards:

* **JWT (JSON Web Tokens):**
  Communication between the frontend and backend is handled using cryptographically signed tokens. This prevents session hijacking and enables scalable authentication.

* **RBAC (Role-Based Access Control):**
  Access to resources is strictly separated by roles:

  * `USER`: Create and view their own tickets
  * `SUPPORT`: Process and assign tickets
  * `ADMIN`: Full control over user roles, system statistics, and global priorities

* **Method-Level Security:**
  Every backend service call is protected using `@PreAuthorize` annotations to prevent unauthorized access at the controller and service layer.

---

# 🛡️ Backend Protection Mechanisms (Spring Boot)

* **Password Hashing:**
  Passwords are never stored in plain text. The system uses **BCrypt** with a strong salt factor to protect against brute-force attacks.

* **CORS Policy:**
  The API only accepts requests from verified domains (Cross-Origin Resource Sharing) to minimize the risk of Cross-Site Request Forgery (CSRF).

* **SQL Injection Prevention:**
  By using **Spring Data JPA** and prepared statements, the system is natively protected against SQL injection attacks.

* **Input Validation:**
  All incoming data is validated using `@Valid` and Jakarta Bean Validation to prevent malformed-data attacks.

---

# 🧠 Security of the Internal AI Logic

A major security advantage of the **locally implemented prioritization engine**:

* **Data Sovereignty:**
  Since no external AI APIs (such as OpenAI or Anthropic) are used, sensitive ticket content never leaves the internal network.

* **Manipulation Protection:**
  The prioritization logic is deeply encapsulated within the backend and cannot be manipulated by end users. Only administrators can influence heuristic weightings.

---

# 🏗️ Infrastructure & Deployment Recommendations

For production deployments, the following security measures are recommended:

1. **SSL/TLS Encryption:**
   All connections must be secured using HTTPS.

2. **Environment Variables:**
   Sensitive data such as `JWT_SECRET` or database credentials must never be stored in the source code and should instead be loaded via environment variables.

3. **Rate Limiting:**
   Implement an API rate limiter to protect the system against Denial-of-Service (DoS) attacks.

---

> **Note:** Security is an ongoing process. Regular dependency updates using tools such as `npm audit` or Snyk are strongly recommended.
