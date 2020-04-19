# Arcitechture

## Structure

The application follows a three level arcitechture: Ui (user interface), domain (logic) and dao (data storage).

- **billsplitter**
  - _Main_ calls _Launcher_
  - _Launcher_ (extends Application) starts the application and sets the login stage to window
- **billsplitter.ui**
  - _LoginUi_ calls _LoginService_ and sets _HistoryUi_ to window
  - _HistoryUi_ calls _HistoryService_ and sets _NewBillUi_ or _LoginUi_ to window
  - _NewBillUi_ calls _HistoryService_ and sets _HistoryUi_ to window
- **billsplitter.domain**
  - _LoginService_ calls _FileUserDao_
  - _HistoryService_ calls _FileBillDao_
  - _User_ 
  - _Bill_ 
- **billsplitter.dao**
  - _FileUserDao_ (extends _UserDao_)
  - _FileBillDao_ (extends _BillDao_)
  - _UserDao_ (interface)
  - _BillDao_ (Interface)

<img src=resources/structure.png width="360">

## TBA
