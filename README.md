# Blogging Application

### This project aims to deepen understanding on how backend applications are built in springboot.

## PHASE 1 -- COMPLETED
1. Define models, repos, services and controllers for journal and users.
2. Blog will hold a reference to their authors (i.e, the users).
3. Data will be stored in a local mongodb instance.
4. User -> id, Username (unique), password (stored in plain text temporarily)
5. blog -> id, title, content , userId


## PHASE 2 -- COMPLETED
1. Add Spring security for authentication.
2. Encrypt and store user passwords.
3. Add roles upon user creation. Two roles "ADMIN" and "USER".
4. Implment Role based Authorization.
5. Let users see their OWN blogs, create, edit and delete.
6. Implement a global exception handler for bad requests.

