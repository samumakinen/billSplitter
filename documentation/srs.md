# Software requirement specification (SRS)
## Description
## Users
## UI-draft
The Bill Splitter application (BSA) has been designed in a compact vertical layout so that it does not take much space from the screen and also it is easier to transform into a mobile app later after the course.

BSA has three different interractive views for basic funcionality, as seen from the picture below. It has not yet been decided whether BSA should have a different view for opening a saved bill from the history or should the bill open to a completely new window. Latter would allow users to have multiple bills open at the same time when working on a computer.
<img src="https://github.com/samumakinen/ot-harjoitustyo/blob/master/documentation/resources/ui_draft_1.0.png">
## Basic functionality
### Login screen / before login
- User can login with their username by typing it into the field
  - If username exists the user is transferred to their history view
  - If username does not exist the user is notified to check spelling or create a new user
- User can create a new user by typing their name and a made up username into the correct fields
  - The length of the username must be atleast 3 characters
