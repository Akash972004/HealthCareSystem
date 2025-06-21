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

### 3. Compile the Code

Open terminal in the project folder and run:

```bash
javac -cp ".;mysql-connector-j-9.3.0.jar" *.java
```

---

### 4. Run the Application

```bash
java -cp ".;mysql-connector-j-9.3.0.jar" Main
```

---

## ✅ Notes

- Always register a patient before generating a bill.
- Ensure MySQL service is running before starting the app.

---