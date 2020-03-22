# Software requirement specification (SRS)
## Describtion
The Bill Splitter application (**BSA**) helps users when purchasing something for a group of people. One person can pay at a store and then use Bill Splitter to calculate the amount each participant owes them. Users can also save bills to history to keep track of them and review them later.
## Users
No plans to have different levels of user privileges at this time, since there are no apparent reasons for that.
## UI-draft
BSA has been designed in a compact vertical layout so that it does not take up much space from on screen and also it is easier to transform it into a mobile app later after the course.

BSA has three different interractive views for basic funcionality, as seen from the picture below. It has not yet been decided whether BSA should have a different view for opening a saved bill from the history or should the bill open to a completely new window. Latter would allow users to have multiple bills open at the same time.

<img src="https://github.com/samumakinen/ot-harjoitustyo/blob/master/documentation/resources/uidraft1.1.png">

## Basic functionality
### Login screen (before login)
- User can login with their username by typing it into the field
  - If username exists the user is transferred to their **history view**
  - If username does not exist the user is notified to check spelling or create a new user
- User can create a new user by typing their name and a made up username into the correct fields
  - The length of the username must be atleast 3 characters
### History view (after login)
- User can browse bills by their titles and enter **single bill view** by clicking on a bill on the list
- User can enter the **new bill screen** to create a new bill
- User can log out of the app (goes back to the **login screen**)
### New bill screen
- User can create a new bill
  - A bill must have a  *unique* title of at least 3 characters
  - Describtion is optional
  - Number of payers must be a number that is at least 1
  - Amount of the bill must be a number, separated by a . (0.5, 2.0 or 15.5 for example)
  - Bottom of the screen shows the share of each payer
- User can save the bill to the history (goes back to the **history view**)
- User can cancel the creation (goes back to the **history view**)
### A single bill view
- TBA
## Future development ideas
- Ability to modify bills after creating them
- Ability to add specific persons to each bill and keep track of who has payed off their share
- Sort by Title, amount,
- Deletion of bills from the history
- Search and filtering ability, by person, by amount etc.
