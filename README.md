
# Backend Project Setup Guide

## 📦 Prerequisites

- Java & Spring Boot installed
- PostgreSQL installed and running

---

## 📖 Setup Instructions

### 1️⃣ Create a New PostgreSQL Database

First, create a new database in your PostgreSQL server for this project.

Example:
```sql
CREATE DATABASE your_database_name;
```

---

### 2️⃣ Configure Environment Variables

#### 📄 Create `.env` File

At the root level of the project, create a `.env` file by copying the existing `.env.example` file.

```bash
cp .env.example .env
```

#### ✏️ Add Your Database Credentials

Open the `.env` file and set your PostgreSQL database connection details:

```env
SPRING_DATASOURCE_HOST=your_db_host
SPRING_DATASOURCE_PORT=your_db_port
SPRING_DATASOURCE_NAME=your_database_name
SPRING_DATASOURCE_USERNAME=your_database_user
SPRING_DATASOURCE_PASSWORD=your_database_password
```

---

#### 🔐 Add JWT Secret Key

Generate a secure secret key for JWT and add it to your `.env` file:

```env
JWT_SECRET_KEY=your_secure_jwt_secret
```

---

#### 👤 Set Admin User Credentials

Define the default admin account credentials in the `.env` file:

```env
SPRING_ADMIN_ACCOUNT_USERNAME=your_admin_username
SPRING_ADMIN_ACCOUNT_PASSWORD=your_admin_password
```

---

## ⚙️ How It Works

- When the project starts, it will automatically check if an admin user exists in the database.
- If no admin user is found, it will create one using the credentials provided in your `.env` file.
- **Note:** There’s no public registration endpoint in this system — the admin is created and managed internally.

---

## 🛠️ Additional IntelliJ Setup

### ✅ Check EnvFile Plugin Settings

- Make sure **Modify options** has **EnvFile** checked.
- Ensure **Enable EnvFile** is checked and the correct path to your `.env` file is provided.

---

### ⚙️ Fix Annotation Processor Issues (Setters/Getters)

If you encounter errors related to missing setters and getters:

1. Go to **IntelliJ Settings**
2. Under the **Build, Execution, Deployment** tab, open **Compiler > Annotation Processors**
3. On the left side, open the annotation profile for  
   `Adstart Media Backend -> backend`
4. Change **Obtain processors from** to `Project classpath` instead of `Processor path`
5. Click **Apply** and **OK`

Setters and getters should now be processed correctly.
