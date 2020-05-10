# Software requirement specification (SRS)
## Description
The Bill Splitter application (**BSA**) helps users when purchasing something for a group of people. One person can pay at a store and then use Bill Splitter to calculate the amount each participant owes them. User can also save bills to history in order to keep track of them and review them later.
## Users
Currently there are no plans to have different levels of user privileges since there is no apparent reason to do so.
## UI-draft
BSA has been designed in a compact vertical layout so that it does not take up much space on the screen. BSA has three different interractive views for basic funcionality, as seen from the picture below. When the user clicks a bill from the **history view** it opens up as a html file to a new window.

<img src=resources/uidraft.png>

## Basic functionality
- **DONE** SQLite database for storing users and bills
### Login screen (before login)
- **DONE** User can login with their username by typing it into the field
  - If username exists the user is transferred to their **history view**
  - If username does not exist the user is notified to check spelling or create a new user
- **DONE** User can create a new user by typing their name and a made up username into the correct fields
  - The length of the username must be atleast 3 characters
### History view (after login)
- **DONE** Browse bills by their titles
- **DONE** Modify bills after creating them
- **DONE** Remove bills from the history
- **DONE** Enter the **new bill screen** to create a new bill
- **DONE** Enter the **single bill view** by clicking a bill
- **DONE** Log out of the app and return to the **login screen**
### New bill screen
- **DONE** User can create a new bill
  - A bill must have a  *unique* title of at least 3 characters
  - Describtion is optional
  - Number of payers must be a number that is at least 1
  - Amount of the bill must be a number, separated by a . (0.5, 2.0 or 15.5 for example)
  - Bottom of the screen shows the share of each payer
- **DONE** User can save the bill to the history (goes back to the **history view**)
- **DONE** User can cancel the creation (goes back to the **history view**)
## Future development ideas
- User can see the total number of bills in the history
- Searching and sorting of the bills
