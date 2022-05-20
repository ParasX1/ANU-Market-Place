# PineAPPLE Report

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
| u7282825 | Raymond Improgo | Post creation / displaying |
| u7315765 | Alan Biju | Advanced Search/ UI |
| [U7298779] | Raed Abdulnoor | [UI / SavedPost Developer] |

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
- Report (Application Use Cases)

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
       
 Contributions Alan:
- Designed Grammar
- Implemented tokenizer and parser
- Implemented partial/impartial search in Activity 2
- Implemented AVL tree with function for searching
- Report (Application Designs and Decisions)

u7315765 , Alan Biju, I contributed __% of the code, Specific contributions are:
  - Classes/Activity:
    - Tree/AVLtree/BinarySearchTree/EmptyTree: 100% contribution
    - Adapter: 20% contribution
    - Activity2: 30% contribution
    - Parser: 100% contribution
    - Token/Tokenizer/Exp/IntExp/StringExp: 100% contribution

  - UI:  
    - MoreCategories layout: 100% contribution
    - PostListing layout: 100% contribution


Contributions Raymond:
- Implementation of Post object creation
- Saving Posts too firebase database 
- Assistance in activity2 implementation (displaying the grid view with the Post opbects)
- Retrieval of posts from database, configuring gridview adapter to display posts
- Individual Post's pages implementation, passing through Post data
- Debugging crashes and errors

u7282825 , Raymond Improgo, I contributed __% of the code, Specific contributions are:
  - Classes/Activity:
    - CreatePostActivity: 100%
    - Posts: 100%
    - User: 5%
    - Activity2: 5%
    - ImageAdapter: 85%
    - PostListingActivity: 100%
    - Adapter: 80% contribution

  - UI:
    - activity_create_post.xml: 100%


## Conflict Resolution Protocol

The conflict resolution protocal was voting, we all would take turns in verbally explaining reasoning for what we should do, and why we should do it. Overall this was a effective method of resolving conflicts, we did not have many, but a example of effective conflict resolution was when we decided to use AVL tree for searching in comparison, the reasons for it was easy to understand (we have done it in lab), and was self balancing hence reducing code, as we would not need to write rebalancing code for the tree. The reason against was that it was slightly slower. With a vote, we decided for the positive reasons we should do a AVLtree. Another example was what way shall we construct the User factory class, as this class was responsible for creating/ storing important information to the database, as such we planned out and saw what requirements we all had for each of our parts and then after we combined/adapted it.

Following the method above we were able to resolve all conflicts effectively, and without any problems.


## Application Description

ANU Marketplace as mentioned above is a media application, directly targetting all students/staff of ANU who are in need of 2nd hand goods. It provides a safe ANU only enviroment in which any student, even those who are new to Australia (International Students) a place to buy/sell 2nd hand goods. In other buy/sell applications there is a large threat of unsecure transactions, scams and even trolling, by restricting the user base to just ANU we can minimise all these problems, as registration of a user requires a UID, Full Name and Email.

**Application Use Cases and or Examples**

1. John want to purchase a Jacket in a safe enviroment  
2. Cate want to purchase a Laptop without being scammed or manipulated

*John want to purchase a Jacket in a safe enviroment *
1. *John wants to purchase a Jacket in a safe enviroment.*
2. *He chooses to use ANU Marketplace as it allows him to trade with fellow trust worthy students at ANU.*
3. *John browses the posts and filters by clothing.*
4. *He then finds a jacket belonging to Tom which he likes.*
5. *John then starts messaging Tom and they negotiate a price.*
6. *After a price is negotiated they choose a safe location to meet which is on campus.*
7. *John and Tom safely meet and the purchase of the Jacket is successful.*

*Cate want to purchase a Laptop without being scammed or manipulated*
1. *Cate wants to purchase a laptop with out getting scammed*
2. *Cate chooses to use anu Marketplace as all the users are fellow students, hence scamming is unlikely.*
3. *Cate browses the posts and finds a laptop belonging to Robert which looks appropriate for her needs.*
4. *Cate then starts messaging Robert and they negotiate a price.*
5. *After a price is negotiated they choose a safe location to meet which is on campus. Cate is also less likely to get scammed
as the meeting is taking place on campus and the seller is a student at ANU*
6. *Cate and Robert safely meet and the purchase of the Laptop is successful.*


## Application UML

![ClassDiagramExample](./images/ClassDiagramExample.png)
*[Replace the above with a class diagram. You can look at how we have linked an image here as an example of how you can do it too.]*



## Application Design and Decisions

*We used the following data structures in my project:*

1. *AVLtree*

   * *Objective: It is used for the partial search function.*

   * *Locations: line 102 in AVLtree.java, line 93,170 in Activty2.java.*

   * *Reasons:*

     * *It allows for searching substrings of titles without going through every title*

     * *More efficient*
     
     * *Guarentees search time of O(log n) which is better than Binary Search Tree*
     
     * *Allows for alphabetical ordering which a regular binary tree does not*

2. *Map*

   * *Objective: It is used track the names for each object in the database.*

   * *Locations: line 73 in ImageAdapter.java, line 154 in Activty2.java.*

   * *Reasons:*

     * *Allows objects to be linked to their title*

     * *Allows easy recall of objects using this title*
     
     * *Avoids using two seperate structures*

3. ...

**Design Patterns**

*[What design patterns did your team utilise? Where and why?]*
Factory:
  The User class is a factory method which produces different instances of User. This is because User needs to create multiple objects with
  different properties.

Singleton:
  A singleton design pattern was used in the Parser.java class. This is because only one instance of parser is ever necessary. You are not going to
  parse two seperate searches at the same time.
  

**Grammar(s)**

<br> *Production Rules* <br>
\<Exp> ::= \<String>
<br>
\<String> ::= \<Characters>

*[How do you design the grammar? What are the advantages of your designs?]*
We designed the grammar for the search function so we had to think about how it would be used. We realised just a stream of numbers would never yield anything useful so we designed the grammar accordingly. The advantages of this grammar is it requires users to use letters when searching for posts.

**Tokenizer and Parsers**

*[Where do you use tokenisers and parsers? How are they built? What are the advantages of the designs?]*
I use the tokenisers and parsers in Activity2.java where the search is implemented. This design allows us to use a try/catch clause to let the user know whenever the search they made violated the grammar. With this informative message, the user is able to change their search so that it successfully parses and our program can interpret the search.

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

*AVL Tree Testing:*

*Number of test cases: 8*

*Code coverage: Tests whether it's immutable, single left/right rotations, double rotations, checking balance of tree, checking duplicates and checking some cases of findTitle. Branch Complete.*

*Types of tests created: JUnit tests, Black box testing for basic tree, White box testing for findTitle function, assertion tests, timeout tests. Tests outside of JUnit were done for findTitle using random inputs from an AVLtree with titles from firebase.*

*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

![AVL Tree Testing](https://gitlab.cecs.anu.edu.au/u7298779/comp2100-group_project/-/blob/main/Testing.PNG)

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

    The ability to micro-interact with items in your app (e.g. like/dislike/support/report a post/message/event) [stored in-memory]. (easy) (Raed (saving posts))

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
