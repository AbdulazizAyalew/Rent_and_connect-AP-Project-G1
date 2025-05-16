# Rent & Connect: Your One-Step Rental Marketplace

Rent & Connect is a Java-based application that streamlines the rental process by connecting **renters** and **lenders** in a centralized platform. Users can list homes, browse available properties, and communicate through an integrated messaging system.

---

## 🔧 Technologies Used

- Java (OOP Principles)
- Swing (for GUI)
- JDBC (Database integration)
- Threads (Background tasks)
- RMI/Networking

---

## 📁 Project Structure
<pre>
├── Renter.java
├── Lender.java
├── HouseListing.java
├── Message.java
├── Database.java
├── GUI/
├── Main.java
</pre>

---

## ✅ Features

- Renter and Lender registration/login
- Lenders can add, edit, and remove house listings
- Renters can browse listings
- In-app messaging between renter and lender
- GUI interface for interaction
- Uses JDBC for database connectivity
- Threads for responsive UI
- RMI planned for future extension

---

## 🔐 Object-Oriented Principles Applied

- **Encapsulation** – private attributes with getters/setters  
- **Inheritance** – common `User` class for `Renter` and `Lender`  
- **Polymorphism** – method overriding where needed  
- **Abstraction** – using abstract classes or interfaces for structure

---

## 🛠️ Setup Instructions

1. Clone the repository or download the ZIP file
2. Open the project in IntelliJ, Eclipse, or NetBeans
3. Set up your database connection (configure `Database.java`)
4. Run `Main.java` to launch the GUI
5. Make sure JDBC driver is included in your project library

---

## 🧑‍💻 Contributors

- Abdulaziz Ayalew
- Abdulaziz Mohammedsani
- Abdurahman Miftah
- Abdurazak Mohammed
- Abel Getachew

---

## 📌 Notes

- This is a course project for **Advanced Programming**.
- Focus is on applying Java OOP and integrating external components like JDBC, Threads, and RMI.

