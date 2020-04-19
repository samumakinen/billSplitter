# Arcitechture

## Structure

The application follows a three level arcitechture: Ui (user interface), domain (logic) and dao (data storage).

- **billsplitter**
  - _Main_ -class calls for _Launcher_ -class
  - _Launcher_ -class (extends Application) starts the application and sets the login stage to window
- **billsplitter.ui**
  - _LoginUi_ 
  - _HistoryUi_ 
  - _NewBillUi_ 
- **billsplitter.domain**
  - _LoginService_ 
  - _HistoryService_ 
  - _User_ 
  - _Bill_ 
- **billsplitter.dao**
  - _FileUserDao_ (extends _UserDao_)
  - _FileBillDao_ (extends _BillDao_)
  - _UserDao_ (interface)
  - _BillDao_ (Interface)

<img src=resources/structure.png width="360">

## TBA
