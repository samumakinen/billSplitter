# Software requirement specification (SRS)
## Description
The Bill Splitter application (**BSA**) helps users when purchasing something for a group of people. One person can pay at a store and then use Bill Splitter to calculate the amount each participant owes them. User can also save bills to history in order to keep track of them and review them later.
## Users
Currently there are no plans to have different levels of user privileges since there is no apparent reason to do so.
## UI-draft
BSA has been designed in a compact vertical layout so that it does not take up much space on the screen. BSA has three different interractive views for basic funcionality, as seen from the picture below. When the user clicks a bill from the **history view** it opens up as a html file to a new window.

<img src="https://github.com/samumakinen/ot-harjoitustyo/blob/master/documentation/resources/uidraft1.1.png">

## Basic functionality
### Login screen (before login)
- User can login with their username by typing it into the field **DONE**
  - If username exists the user is transferred to their **history view**
  - If username does not exist the user is notified to check spelling or create a new user
- User can create a new user by typing their name and a made up username into the correct fields **DONE**
  - The length of the username must be atleast 3 characters
### History view (after login)
- User can browse bills by their titles **DONE**
- Click bill to enter single **bill view**
- User can enter the **new bill screen** to create a new bill **DONE**
- User can log out of the app (goes back to the **login screen**) **DONE**
- User can remove bills
- User can see the total number of bills in the history
### New bill screen
- User can create a new bill **DONE**
  - A bill must have a  *unique* title of at least 3 characters
  - Describtion is optional
  - Number of payers must be a number that is at least 1
  - Amount of the bill must be a number, separated by a . (0.5, 2.0 or 15.5 for example)
  - Bottom of the screen shows the share of each payer
- User can save the bill to the history (goes back to the **history view**) **DONE**
- User can cancel the creation (goes back to the **history view**) **DONE**
### A single bill view
- Bill opens as a html file, no editing possible
## Future development ideas
- Ability to modify bills after creating them
- Ability to add specific persons to each bill and keep track of who has payed off their share
- Sort by Title, amount, etc.
- Search by Title, amount, describtion, etc.
