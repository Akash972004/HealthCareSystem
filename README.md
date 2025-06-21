# 🏥 Health Care Management System (Java + MySQL)

A simple Java console application to manage patients, doctors, appointments, and billing using a MySQL database.

---

## ⚙️ Requirements

- Java JDK 8 or above
- MySQL Server (running on `localhost:3306`)
- MySQL Connector/J `.jar` file (e.g. `mysql-connector-j-9.3.0.jar`)

---

## 🛠️ Setup

### 1. Create the Database

Run the `hcms.sql` file in MySQL to create:

- `hcms` database
- Tables: `Patients`, `Doctors`, `Appointments`, `Billing`

You can use MySQL Workbench or VS Code with SQLTools.

---

### 2. Configure DB Connection

In `DBConnection.java`:

```java
String url = "jdbc:mysql://localhost:3306/hcms";
String user = "root";
String password = "your_mysql_password";
```

---
✅ Extra Features Added
Real-time data tables in all four tabs (Patients, Doctors, Appointments, Billing)

Auto-refresh after inserting new data

Field reset after successful data entry

Single window tabbed interface using JTabbedPane

Validation for foreign key constraints (e.g., booking appointment only for existing patients)

MySQL error handling via message dialogs

Prepared for future enhancements (Edit/Delete buttons)

Fully compatible with mysql-connector-j-9.3.0.jar

Lightweight and fast GUI — no external frameworks needed

### 3. Compile the Code

Open terminal in the project folder and run:

```bash
javac -cp ".:mysql-connector-j-9.3.0.jar" HealthCareGUI_Enhanced.java
```

---

### 4. Run the Application

```bash
java -cp ".:mysql-connector-j-9.3.0.jar" HealthCareGUI_Enhanced
```

---

## ✅ Notes

- Always register a patient before generating a bill.
- Ensure MySQL service is running before starting the app.

---