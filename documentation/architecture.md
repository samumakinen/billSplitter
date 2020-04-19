# Arcitechture

## Structure

The application follows a three level arcitechture: Ui (user interface), domain (logic) and dao (data storage).

<img src=resources/structure.png width="360">

### User interface

- **billsplitter.ui**
  - _LoginUi_ calls _LoginService_ and sets _HistoryUi_ to window
  - _HistoryUi_ calls _HistoryService_ and sets _NewBillUi_ or _LoginUi_ to window
  - _NewBillUi_ calls _HistoryService_ and sets _HistoryUi_ to window

### Application logic

- **billsplitter.domain**
  - _LoginService_ calls _FileUserDao_
  - _HistoryService_ calls _FileBillDao_
  - _User_ 
  - _Bill_ 
  
### Data storage

This application uses SQLite -database to store files. Db is created and the db location set to the working directory on running the app. If the db already exists, then no new db is created and the existing one is read from.

- **billsplitter.dao**
  - _FileUserDao_ (extends _UserDao_)
  - _FileBillDao_ (extends _BillDao_)
  - _UserDao_ (interface)
  - _BillDao_ (Interface)
  
## Functionality

Below are sequence diagrams of the main functionalities of this application.

### Creating a new user

**TBA**

### Login

**TBA**

### Creating a new bill

**TBA**
