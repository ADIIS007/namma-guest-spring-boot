```
Namma Guest

Namma Guest is a Java Spring Boot application designed to manage paying guest accommodations. This project includes various functionalities such as managing hostels, rooms, guests, and more.

## 📁 Project Structure

The project structure is as follows:

```
namma-guest-spring-boot/
├── .github/                                     # GitHub configuration files
│   └── workflows/                               # GitHub Actions workflows
├── .mvn/                                        # Maven wrapper files
│   └── wrapper/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── nammaguest/
│   │   │               ├── controller/          # Contains REST controllers
│   │   │               ├── model/               # Contains entity classes
│   │   │               ├── repository/          # Contains repository interfaces
│   │   │               ├── service/             # Contains service classes
│   │   │               └── NammaGuestApplication.java  # Main application class
│   │   └── resources/
│   │       ├── application.properties           # Application configuration
│   │       └── templates/                       # Contains Thymeleaf templates
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── nammaguest/
│                       └── NammaGuestApplicationTests.java  # Test class
├── .gitignore                                   # Git ignore file
├── mvnw                                         # Maven wrapper script for Unix
├── mvnw.cmd                                     # Maven wrapper script for Windows
├── pom.xml                                      # Maven project file
└── README.md                                    # Project README file
```

## 📋 Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- PostgreSQL

## 🚀 Getting Started

To get started with the Namma Guest Spring Boot application, follow these steps:

1. **Clone the repository:**
    ```sh
    git clone https://github.com/yourusername/namma-guest-spring-boot.git
    cd namma-guest-spring-boot
    ```

2. **Build the project:**
    ```sh
    ./mvnw clean install
    ```

3. **Run the application:**
    ```sh
    ./mvnw spring-boot:run
    ```

## Running Tests

To run the tests, use the following command:
```sh
./mvnw test

## ✨ Features

- User registration and login
- Guest management
- Event scheduling

## Project Modules

### 📂 Controllers

- `🏠 HomeController`: Manages user-related operations.
- `👤 OwnerController`: Manages owner-related operations.
- `🔐 AuthenticationController`: Handles authentication and OTP operations.

### 🛠️ Services

- `🏨 PayingGuestService`: Contains business logic for managing paying guests.
- `👥 UserService`: Contains business logic for managing users.

### 🗄️ Repositories

- `🏠 StayRepository`: Manages `Stay` entities.
- `👥 UsersRepository`: Manages `Users` entities.
- `🏨 PayingGuestRepository`: Manages `PayingGuest` entities.
- `📍 AddressRepository`: Manages `Address` entities.
- `🏢 FacilityRepository`: Manages `Facility` entities.
- `⭐ ReviewRepository`: Manages `Review` entities.

### 📦 Models

- `🏠 Stay`: Represents a stay record.
- `👤 Users`: Represents a user.
- `🏨 PayingGuest`: Represents a paying guest.
- `📍 Address`: Represents an address.
- `🏢 Facility`: Represents a facility.
- `⭐ Review`: Represents a review.

### 🔧 Utilities

- `🛠️ Utilities`: Contains utility methods for validation and other common tasks.
- `✉️ MailService`: Handles sending emails.

## CI/CD

The project uses GitHub Actions for CI/CD. The configuration is in `.github/workflows/main.yml`.


