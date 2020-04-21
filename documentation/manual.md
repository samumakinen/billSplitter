# User guide

## Usage

Download a runnable **jar** file from the [releases](https://github.com/samumakinen/ot-harjoitustyo/releases) page. Choose the latest release from the top of the page.

Place the **jar** file in a location of your choosing and run the application by double clicking on the **jar** file. Please keep in mind, that a **billsplitter.db** file is created into the location that you store your **jar** file in. This file will be needed to save the changes you make in the application.

### Running from command line

When running the **jar** for the first time, a **billsplitter.db** file will appear in the _working directory_. In order to access the saved data, you will always need to run the app from the same working directory, e.g:

**Jar** is located in **/User/Downloads/BillSplitter-1.x.jar**.
You `cd` with command line to **Downloads** folder and run the **jar** `java -jar BillSplitter-1.x.jar`.
A **billsplitter.db** file is created in **Downloads** folder. Next time you run the app you must run it from the same path in order to keep the changes, otherwise a new **billsplitter.db** file is created into the path you are running the app from this time.
