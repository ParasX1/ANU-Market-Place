# PineAPPLE
This following is a team meeting template. You may change this template as you like.

## Team Meeting [1] - Week [11]   19/05/22 (17:00 - 21:00)
**Absent:** Paras (1hr)
<br>
**Lead/scribe:** Paras

## Agreed Procedure
Stand up Procedure:
 Took turns asking about updates, and making choices like what we want to store in the Userfactory class.

## Agenda Items
| Number | Item |
| :--- | ---: |
| [1] | [UI] |
| [2] | [Chat] |
| [3] | [PostAd] |
| [4] | [Trees/Search] |

## Meeting Contributions:
- Final Idea: (ANU Marketplace) : Social platform for exchanging goods and services which is intended for use by all ANU students/staff.
- Features:
- - 1 Hard Feature:
    Provide users with the ability to message each other or an institution directly (e.g., a user can message an event/movement that is managed by another user). (hard) (Done)

- - 3 Medium Features:
    Search functionality can handle partially valid and invalid search queries. (medium)

    Use Firebase to persist all data used in your app (this item replaces the requirement to retrieve data from a local file) (medium) (Done)

    The ability to send notifications based on different types of interactions (posts, likes follows, abuse reports, etc). A notification must be sent only after a predetermined number of interactions are set (>= 2 interactions [e.g., one like and one follow or two likes or two follows]). Note that it is not mandatory to use the Android Notification classes. (medium)  (Done - saved posts)
- - 5 Easy Features:
    Use Firebase to implement user Authentication/Authorisation. (easy) (Done)

    The ability to micro-interact with items in your app (e.g. like/dislike/support/report a post/message/event) [stored in-memory]. (easy) (Semi)

    Process visualisation. Your app may implement a graphical element to visualise the progress of a process/event. (easy) (Done)

    UI must have portrait and landscape layout variants as well as support for different screen sizes. Simply using Android studio's automated support for orientation and screen sizes and or creating support without effort to make them look reasonable will net you zero marks. (easy) (Semi Done)

    User profile activity containing a media file (image, animation (e.g. gif), video). (easy) (Done)

-So Far What is done:
- - Login Implmentation - Paras
- - Posts, storage of Posts - Raymond
- - Tokeniser/Parser for Search - Alan
- - UI (Mostly) - Raed

-What needs to be done:
- - Chat (Need to work out bugs) - Paras
- - Saving Posts - Raed
- - Working out kinks Tokeniser/Parser/Trees - Alan
- - UI landscape fix - Raed

Meeting Decisions:
- User factory will now store: Name, email Password, UID, Link to Profile Picture, AND NOW (Saved posts in some form)
- Implement Avl Tree/B Tree to sort by Price in Posts
- Decided to replace previous Block user function due to lack of time with Advanced search


## Assigned Member
Mentioned Above

## Scribe Rotation
The following dictates who will be scribe in this and the next meeting.

| Name |
| :---: |
| Raed |
| Alan |
| Raymond |
| Paras |
