# PineAPPLE Report

The following is a report template to help your team successfully provide all the details necessary for your report in a structured and organised manner. Please give a straightforward and concise report that best demonstrates your project. Note that a good report will give a better impression of your project to the reviewers.

*Here are some tips to write a good report:*

* *Try to summarise and list the `bullet points` of your project as many as possible rather than give long, tedious paragraphs that mix up everything together.*

* *Try to create `diagrams` instead of text descriptions, which are more straightforward and explanatory.*

* *Try to make your report `well structured`, which is easier for the reviewers to capture the necessary information.*

*We give instructions enclosed in square brackets [...] and examples for each sections to demonstrate what are expected for your project report.*

*Please remove the instructions or examples in `italic` in your final report.*

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Basic Description / Features of our app](#basic-description-and-features-of-our-app)
3. [Summary of Individual Contributions](#summary-of-individual-contributions)
4. [Conflict Resolution Protocol](#conflict-resolution-protocol)
5. [Application Description](#application-description)
6. [Application UML](#application-uml)
7. [Application Design and Decisions](#application-design-and-decisions)
8. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
9. [Testing Summary](#testing-summary)
10. [Implemented Features](#implemented-features)
11. [Team Meetings](#team-meetings)

## Team Members and Roles

| UID | Name | Role |
| :--- | :----: | ---: |
| u7291471 | Paras Patange | Login/Chat Developer |
| [uid] | Raymond Improgo | [role] |
| [uid] | Alan Biju | [role] |
| [uid] | Raed Abdulnoor | [role] |

## Basic Description and Features Of Our App
Our App:
  ANU Marketplace: A place to buy sell used goods in a safe ANU community.

Why we chose this app to develop:
  Students these days are quite burdened financially by many aspects of life, ranging from moving out and working to Uni fees, all causing lots of stress. As the goal of this group project was to develop a app that does "Social Good", we chose to tackle this problem faced by many students at the ANU. As such we came up with a idea of a app which would allow students of the ANU to form a close community who would buy and sell used goods. This app would be called "ANU Marketplace". We thought limiting the app to ANU students for the time being would eliminate forms of trolling/scamming as to sign up to the app it will be requiring your studnent UID number. As such if any "bad behaviour" was to occur it can be brought to ANUs eyes and dealt with, resulting in a very safe buying and selling enviroment. To accomplish this goal of a "Market place" we would have to complete the key functionality of which a student has the ability to post ads with pictures, have a way to contact them, have a secure application using validification to make sure no one can steal protected information, and the ability to have a cool customisable profile. As such we chose our "feature goals" as follows:

- - 1 Hard Feature:
    Provide users with the ability to message each other or an institution directly (e.g., a user can message an event/movement that is managed by another user). (hard) (Done: Paras)

    Using Firebase or another remote database to store user information and having the app updated as the remote database is updated without restarting the application. E.g. User A makes a transfer, user B on a separate instance of the application sees user A’s transfer appear on their app instance without restarting their application. (very hard) (Chat, Posts Updates Automatic,refresh on pull)

- - 3 Medium Features:
    Search functionality can handle partially valid and invalid search queries. (medium) (Done: Alan)

    Use Firebase to persist all data used in your app (this item replaces the requirement to retrieve data from a local file) (medium) (Done: Paras,Alan,Raed,Raymond)

    The ability to send notifications based on different types of interactions (posts, likes follows, abuse reports, etc). A notification must be sent only after a predetermined number of interactions are set (>= 2 interactions [e.g., one like and one follow or two likes or two follows]). Note that it is not mandatory to use the Android Notification classes. (medium)  (Done - save posts : Raed/Raymond)
- - 5 Easy Features:
    Use Firebase to implement user Authentication/Authorisation. (easy) (Done:Paras)

    The ability to micro-interact with items in your app (e.g. like/dislike/support/report a post/message/event) [stored in-memory]. (easy) (Raymond)

    Process visualisation. Your app may implement a graphical element to visualise the progress of a process/event. (easy) (Done:Paras, Raed)

    UI must have portrait and landscape layout variants as well as support for different screen sizes. Simply using Android studio's automated support for orientation and screen sizes and or creating support without effort to make them look reasonable will net you zero marks. (easy) (Semi Done: Paras,Raed)

    User profile activity containing a media file (image, animation (e.g. gif), video). (easy) (Done:Raed,Paras)

## Summary of Individual Contributions

Contributions Paras:
- Code Implementation/ Design for the Chat/Login functions
- Code/UI design for Login/Chat as well as Landscape orientation (Chat and Login/Registration/Forgot Password UI design)
- Assistance with activity2 (Refresh/Realtime updates)
- Profile Activity (uploading/chosing images and storing them)
- User object creation (Storing important information, such as Name,Email, Uid, Password, Profile Picture and )
- Report (Intro/Description)

u7291471 , Paras Patange, I contributed __% of the code, Specific contributions are:
  - Classes/Activity:
    - MainActivity: 100% Contribution
    - registerUser: 100% Contribution
    - forgotPassword: 100% Contribution
    - MainChat: 100% Contribution
    - MessageActivity: 100% Contribution
    - MessageProfile: 100% Contribution
    - UserAdapter: 100% Contribution
    - MessageAdapter: 100% Contribution
    - Activty2: 5% Contribution Lines(Refresh)
    - UserInformation: 15% Contribution Lines(41 - 87,)
    - User: 95% Contribution
  - UI:
    - ALL LANDSCAPE UI VARIENTS (EXCEPTION:activity_saved_posts)
    - activity_main: 100% Contribution
    - activity_register_user: 100% Contribution
    - activity_forgot_password: 100% Contribution
    - activity_main_chat: 100% Contribution
    - activity_message: 100% Contribution
    - activity_message_profile: 100% Contribution
    - message_holder: 100% Contribution
    - activity_2: 5% (Some error fixes)
    - activity_user_information: 15% Contribution (Lines 10 - 26)
  - Design:
    - Suggested AVL Trees, to store/search posts, instead of B tree as AVL is self balancing.
  - Report:
    - Report (Intro/Description)
  - Slide Presentation:
     - Helped in creation (25% contribution)
  - Miscellaneous:
     - Organised some group meetings.


 Contributions Raed:
- Code Implementation/ Design for the saved user posts
- Code design for Saved_Post/Activity2 classes ()
- Assistance with activity2 (displaying the grid view with the grid items)
- UI implementation for Activity2(Horizontal), saved_post, activity_user_information and grid item layouts.
- Report (Intro/Description)

u7298779 , Raed Abdulnoor, I contributed __% of the code, Specific contributions are:
  - Classes/Activity:
    - UserInformation: 85% contribution
    - ImageAdapter2: 100% contribution
    - Activity2: 60% contribution Overlap
    - savedPosts: 100% contribution

  - UI:  
    - Activity 2 Horizontal layout 100% contribution
    - activity_saved_posts layout 100% contribution
    - activity_user_information layout 100% contribution
    - grid_item xml layout 100% contribution
       






*[Summarise the contributions made by each member to the project, e.g. code implementation, code design, UI design, report writing, etc.]*

*[Code Implementation. Which features did you implement? Which classes or methods was each member involved in? Provide an approximate proportion in pecentage of the contribution of each member to the whole code implementation, e.g. 30%.]*

*Here is an example:*

*UID1, Name1, I contribute 30% of the code. Here are my contributions:*
* A.class
* B.class: function1(), function2(), ...
* ....

*[Code Design. What design patterns, data structures, did the involved member propose?]*

*[UI Design. Specify what design did the involved member propose? What tools were used for the design?]*

*[Report Writing. Which part of the report did the involved member write?]*

*[Slide preparation. Were you responsible for the slides?]*

*[Miscellaneous contributions. You are welcome to provide anything that you consider as a contribution to the project or team.]*




## Conflict Resolution Protocol

The conflict resolution protocal was voting, we all would take turns in verbally explaining reasoning for what we should do, and why we should do it. Overall this was a effective method of resolving conflicts, we did not have many, but a example of effective conflict resolution was when we decided to use AVL tree for searching in comparison, the reasons for it was easy to understand (we have done it in lab), and was self balancing hence reducing code, as we would not need to write rebalancing code for the tree. The reason against was that it was slightly slower. With a vote, we decided for the positive reasons we should do a AVLtree. Another example was what way shall we construct the User factory class, as this class was responsible for creating/ storing important information to the database, as such we planned out and saw what requirements we all had for each of our parts and then after we combined/adapted it.

Following the method above we were able to resolve all conflicts effectively, and without any problems.

*[Write a well defined protocol your team can use to handle conflicts. That is, if your group has problems, what is the procedure for reaching consensus or solving a problem? (If you choose to make this an external document, link to it here)]*





## Application Description

ANU Marketplace as mentioned above is a media application, directly targetting all students/staff of ANU who are in need of 2nd hand goods. It provides a safe ANU only enviroment in which any student, even those who are new to Australia (International Students) a place to buy/sell 2nd hand goods. In other buy/sell applications there is a large threat of unsecure transactions, scams and even trolling, by restricting the user base to just ANU we can minimise all these problems, as registration of a user requires a UID, Full Name and Email.

Example Use Cases:
1.  
2. 
3. 
4. 


*[What is your application, what does it do? Include photos or diagrams if necessary]*

*Here is a pet specific application example*

*PetBook is a social media application specifically targetting pet owners... it provides... certified practitioners, such as veterians are indicated by a label next to their profile...*

**Application Use Cases and or Examples**

*[Provide use cases and examples of people using your application. Who are the target users of your application? How do the users use your application?]*

*Here is a pet training application example*

*Molly wants to inquiry about her cat, McPurr's recent troublesome behaviour*
1. *Molly notices that McPurr has been hostile since...*
2. *She makes a post about... with the tag...*
3. *Lachlan, a vet, writes a reply to Molly's post...*
4. ...
5. *Molly gives Lachlan's reply a 'tick' response*

*Here is a map navigation application example*

*Targets Users: Drivers*

* *Users can use it to navigate in order to reach the destinations.*
* *Users can learn the traffic conditions*
* ...

*Target Users: Those who want to find some good restaurants*

* *Users can find nearby restaurants and the application can give recommendations*
* ...

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*


## Application UML

![ClassDiagramExample](./images/ClassDiagramExample.png)
*[Replace the above with a class diagram. You can look at how we have linked an image here as an example of how you can do it too.]*



## Application Design and Decisions

*Please give clear and concise descriptions for each subsections of this part. It would be better to list all the concrete items for each subsection and give no more than `5` concise, crucial reasons of your design. Here is an example for the subsection `Data Structures`:*

*I used the following data structures in my project:*

1. *LinkedList*

   * *Objective: It is used for storing xxxx for xxx feature.*

   * *Locations: line xxx in XXX.java, ..., etc.*

   * *Reasons:*

     * *It is more efficient than Arraylist for insertion with a time complexity O(1)*

     * *We don't need to access the item by index for this feature*

2. ...

3. ...

**Data Structures**

*[What data structures did your team utilise? Where and why?]*

**Design Patterns**

*[What design patterns did your team utilise? Where and why?]*

**Grammar(s)**

<br> *Production Rules* <br>
\<Non-Terminal> ::= \<some output>
<br>
\<Non-Terminal> ::= \<some output>

*[How do you design the grammar? What are the advantages of your designs?]*

*If there are several grammars, list them all under this section and what they relate to.*

**Tokenizer and Parsers**

*[Where do you use tokenisers and parsers? How are they built? What are the advantages of the designs?]*

**Surpise Item**

*[If you implement the surprise item, explain how your solution addresses the surprise task. What decisions do your team make in addressing the problem?]*

**Other**

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*

*Here is an example:*

1. *Bug 1:*
- *During signup the UID can be manipulated, in a non acceptable way, if you fill out the registration fast enough it will allow non-acceptable variations such as u2093092, or -320433, a basic password check function has been implmented*
- Consequence: Results in problematic user profiles, and possible errors in creation of chat rooms ids. 

2. *Bug 2:*
- *When messaging another user, the other users profile pic wont show up at the top of the chat, possibly due to a variable misnamed, which could not be pinpointed*
- Consequence: Results in possible error to talking to the wrong friend, however the name of the friend is also displayed, so the impact is low. As such the error is mostly cosmetic.

3. *Bug 3:*
- *When searching in activity 2, for posts, the signout button floats to the top, was a easy fix we missed. Only was missing a constraint*
- Consequence: Accidental signout is possible while typing, possible frustration. Overall minimal effect.

4. *Bug 4:*
- *Search search works only from the left????*  - ALAN TALK ABOUT THIS ERROR
- Consequence: Results in possible error to talking to the wrong friend, however the name of the friend is also displayed, so the impact is low. As such the error is mostly cosmetic.

5. *Bug 5:*
- *No search function to chat to your friend.There is no search function, as such its very dificult to find user to message who owns the post*
- Consequence: Possible frustration, however in the post the uID of the postee is mentioned, as such emailing is still possible.

6. *Bug 6:*
- *To chat to your friend, there is no search function, as such its very dificult to find user to message who owns the post*
- Consequence: Possible frustration, however in the post the uID of the postee is mentioned, as such emailing is still possible.

*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

## Testing Summary

Non-JUint Testing:
  - Visual Testing was conducted for Landsacpe orientation UI. (Pixel 2, 2XL, 3aXL, 3XL)
  - Visual Testing was conducted for Chat/Login Implementation. (Pixel 2, 2XL, 3aXL, 3XL) (Low coverage,4 Test Devices)



*[What features have you tested? What is your testing coverage?]*

*Here is an example:*

*Number of test cases: ...*

*Code coverage: ...*

*Types of tests created: ...*

*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

## Implemented Features

- - 1 Hard Feature:
    Provide users with the ability to message each other or an institution directly (e.g., a user can message an event/movement that is managed by another user). (hard) (Done: Paras)
    
    Using Firebase or another remote database to store user information and having the app updated as the remote database is updated without restarting the application. E.g. User A makes a transfer, user B on a separate instance of the application sees user A’s transfer appear on their app instance without restarting their application. (very hard) (Chat, Posts Updates Automatic,refresh on pull)

- - 3 Medium Features:
    Search functionality can handle partially valid and invalid search queries. (medium) (Done: Alan)

    Use Firebase to persist all data used in your app (this item replaces the requirement to retrieve data from a local file) (medium) (Done: Paras,Alan,Raed,Raymond)

    The ability to send notifications based on different types of interactions (posts, likes follows, abuse reports, etc). A notification must be sent only after a predetermined number of interactions are set (>= 2 interactions [e.g., one like and one follow or two likes or two follows]). Note that it is not mandatory to use the Android Notification classes. (medium)  (Done - save posts : Raed/Raymond)
- - 5 Easy Features:
    Use Firebase to implement user Authentication/Authorisation. (easy) (Done:Paras)

    The ability to micro-interact with items in your app (e.g. like/dislike/support/report a post/message/event) [stored in-memory]. (easy) (Raymond)

    Process visualisation. Your app may implement a graphical element to visualise the progress of a process/event. (easy) (Done:Paras, Raed)

    UI must have portrait and landscape layout variants as well as support for different screen sizes. Simply using Android studio's automated support for orientation and screen sizes and or creating support without effort to make them look reasonable will net you zero marks. (easy) (Semi Done: Paras,Raed)

    User profile activity containing a media file (image, animation (e.g. gif), video). (easy) (Done:Raed,Paras)

## Team Meetings

*Here is an example:*

- *[Meeting_1](./MeetingTemplate.md)*
- *[meeting-2](./MeetingTemplate.md)*
- *[Meeting_3](./MeetingTemplate.md)*
- *[meeting4](./MeetingTemplate.md)*
- *[meeting5](./MeetingTemplate.md)*


*Either write your meeting minutes here or link to documents that contain them. There must be at least 4 team meetings. Note that you must commit your minute meetings shortly after your meeting has taken place (e.g., within 24h), otherwise your meeting minute will not be accepted.*
