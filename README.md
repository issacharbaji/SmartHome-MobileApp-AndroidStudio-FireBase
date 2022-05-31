Abstract:
In the 1966, ECHO IV “Electronic Computing home automation system” was the world’s first home automation
system. It was able to control a home’s temperature, relay messages, store recipes and turn appliances on and off. Over
the years a lot of home automation applications were developed to facilitate livings and to get the best home
automation system that can cover extra features with achieving the ease of use with least time and effort, beside security
purposes. Our home automation system will surely contain the basic features of the known home automation
applications, including controlling lighting and temperature, switching TV and WIFI, managing energy and controlling
all devices in the home network. Moreover, our application will link the electricity company and the home appliances
company. So that the electricity company can access our application and get its needed information related to electricity,
like how much each house uses electricity, and assign each house a specific limit, and in our application we will notice
this limit and notify the user and not allow them to use more than the limit. And for the home appliances it has also a
link to our application so the user can report when a specific appliance is damaged and asks for maintenance via
contacting the home appliances. Moreover, the home appliance can get the stored information in its database and
communicate with the specific maintenance companies. Also, they can make some statistics regarding the products
quality from the history saved in the database.
Additionally, in our application, it will include a police station, a database that will store mainly the attendance of the
family member inside this specific house. The entry and exit of members. So, when there’s nobody in the house, at a
specific time and an unknown person enters the house, police station will be notified by the security sensors. Basically,
this database and link for the police is for extra safety and security.

Requirements
For the Application:
1. Provide an interface for the users to sign in and register and authenticate himself/herself.
2. The main user of the application who can access everything in his/her house should get a confirmation
code so he/she can register successfully.
3. This confirmation code is essential to activate the application in each home so it can be a home
automated. And this code is a random number saved in the server and it will keep changing for
security purposes, it is a one use only for the main controller of the house in order to sign up.
4. Accessing the application is Role-Based for security purposes also.
5. The main controller of the application which will be only one person having access to everything in
the house, can add members (creates an account for them) to use the application and decide his/her
role.
6. The roles are divided between partner, child and others where each has different access with specific
limits decided according to their role that is decided by the main controller when adding them as
members.
7. Each house’s electricity consumption is connected to the electricity company.
8. Each home has a specific limit of amperage consuming and the electricity company has the access to
each home so that it calculates how much each house consumes electricity based on what they turn on
home appliances like AC, water,…
9. When family members are consuming a specific amperage limit at a certain time, and any family
member tries to turn on something he/she will not be allowed and will get a notification with a
message informing that turning on this will exceed the limit. By this, electricity will not go off when it
exceeds limit because they will not be able to exceed it.
10. The user has the right to request a bill for electricity consumption from the electricity company. In the
back-end code, there exists an algorithm that calculates the bill monthly. Also, the user can request it
any time and has access to check the history of consumption.
Page 5 of 24

Application Architecture:
Our mobile application follows the Client-Server architecture.
1. Client:
The Client part is composed of the android application. The mobile app gives the user the following
capabilities:
1. Log in to the system
2. Main controller can add members
3. Family members can control home devices
4. Electricity company has access to all automated homes
5. Electricity company controls the electricity consumption
6. Reporting the damage of home appliances and asking for maintenance
7. Police station has the access of family members presence and absence in their home
8. Family members will get notified by police station if a stranger enters their house.
9. Home appliances can receive feedbacks from clients and can generate statistics from the history
archive from home devices.
Page 6 of 24
2. Server:
In order to maintain robustness in our server, we used Firebase.
Why we chose Firebase?
We’ve written our mobile application with backend data storage, real-time synchronization and user-event logging using
firebase. Some of the advantages of using firebase are real-time database where we can store data is JSON and
synchronized continuously to all connected clients. Also, firebase authentication is one of the most effective
characteristics where we can integrate various sign-in techniques to allow user log in into the application. In addition to
the hosting and storage features.
