![](Muzz.png)

**Demo Implementation:** 

The demo is built using MVVM architecture principles and the application also has persistent storage using a SQL database that stores each message into a model and then saves it to the database. 

**User interface:** 

I  started  first  with  the  UI  implementation  by  trying  to recreate the app interface as closely as I could with the limited time I had. 

Also, I added dark mode support and recreated Muzz logo using vector  graphics  which  handle  scalability  much  better  than rasterized assets that suffer in scaling, all the assets in the app are vector graphics including the app icon. 

This part took more than I would have liked. 

**App architecture:** 

The whole app was fully developed in MVVM architecture, and it contains the following classes: 

- **MessageModel**: Contains the message model. 
- **MessagingActivity**:  Contains  all  the  User  interface implementations including the sending message and switching  the  users  buttons  as  well  as  populating recyclerView with the said messages and updating the interface  when  changes  occur  in  the  message  or delivery status. 
- **MessagingViewModel**: Contain all the logic of the app including sending messages and retrieving messages from  the  local  database  as  well  as  showing  toast messages. 
- **Database**:  Contain  all  the  SQL  methods  that  are responsible  of  saving  messages  and  updating  the 

delivery status of a message when viewed by the other user. 

- **TimeStamp**: Contain the methods that calculate the UNIX  timestamp  of  the  message  as  well  as  to determine if the shape of the message bubble is tailed or not and to show or remove the timestamp header from those messages. 

**App limitations:** 

I started working on the app with MVVM and persistent storage using SQL in my mind from the beginning but because of that, it took more than it was supposed to and led to some shortcomings in the implementation of the shape of the bubble under certain conditions. 

All these shortcomings could be improved but it needs more time to be implemented properly. 

**Timeframe:** 

Giving more time I would have fixed the issues with the message bubble as well as generating unique userID to identify users instead of a string attached to the message model. 

I would have also added collapsible app toolbar instead of static one and add some flare using custom animations when sending messages and receiving them. 
