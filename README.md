 📚 Library Management System (Java OOP)

A simple, console-based **Library Management System** built with Java. This project demonstrates core **Object-Oriented Programming (OOP)** principles such as Encapsulation, Abstraction, and Inheritance, along with **File I/O** for persistent data storage.

 🚀 Features

   **Book Management**: Add, Update, and Delete books from the inventory.
   **User Management**: Register users to the system.
   **Issue & Return**: Track which books are issued to which users.
   **Data Persistence**: Automatically saves and loads book data from a `.txt` file so you don't lose data when the program closes.
   **Error Handling**: Prevents deleting issued books and handles invalid IDs.

 🛠️ Technologies Used

  **Language**: Java (JDK 11 or higher)
   **IDE**: Visual Studio Code / IntelliJ IDEA
   **Storage**: Flat-file system (`books.txt`)

📋 How to Run

1.  **Clone the repository**:
     git clone https://github.com
   
2.  **Navigate to the folder**:
       cd Library-Management-System
   
3.  **Compile the code**:
        javac Main1.java
    
4.  **Run the application**:
       java Main1
   

 🏗️ Project Structure

- `Main1.java`: The entry point containing the `main` method and logic.
- `Book.java`: Class representing the book entity.
- `User.java`: Class representing library members.
- `LibrarySystem.java`: The controller class handling CRUD and File operations.
- `books.txt`: The database file (generated automatically).


