README:
Starting the Progam:
This program was built and ran on the Eclipse IDE. 

To run, first create the database, "ty_daniel_db", using the ty_daniel_backend
script.

Then, connect the gui program to the database by modifying the url, username,
password Strings in the Driver class.

If that does not work, the connection can be manually modified in the
SQLDatabaseConnection class, in the executeForm and executeReport methods 
(connection in both methods must be modified).

Finally, run the program with the Driver class as the main class.


Using the Program:
The program performs database requests based on the View Report Options
or the Change Form Options.

Selecting an Option will fill in their respective text area with the appropriate text template.

Fill in the template, replacing placeholder text with your own request info and press the Request
View Report or Request Change Form button, based on the request type. 

Keep template commas (,) and apostrophes ('') intact. Template variables not encircled 
in ('') are int or decimal variables should be replaced by integer or decimal 
(based on the selected option).


View Report Options:
There is a selectable SQL option in both request types for bugtesting.

For the Components View Report options, these are their categories and what
components they represent:

Categories:		Components:
VideoCard		Video Cards(GPUs)
Memory			Memory(RAM)
Storage			Storage(e.g., SSD, HDD)
Monitor			Monitors
CPU			CPUs
CPUCooler		CPU Coolers
PowerSupply		Power Supplies(PSU)
Motherboard		Motherboards
PCCase			PC Cases
OS			Operating Systems
Mouse			Mouse
Keyboard		Keyboard

The Price Range version of this option shows components within a given price
range and component category. The MinPrice and MaxPrice define the range and
should be filled in as a double with no ($) sign (e.g., 64.99).

For the PC Build(s) option, enter a username to view all builds from that user
For the PC Build(s) (price range) option, enter a min and max price to view all builds,
from all users, in that price range.
Selected PC Build(s) must be complete for these options (i.e., must have associated
Build GPU(s), Build Memory, Build Storage, and Build Monitor(s)).

Compatible Motherboards shows compatible motherboards given a CPU name.
Compatible CPUs shows compatible CPUs given a motherboard name.


Change Form Options:
There is a selectable SQL option in both request types for bugtesting.

Create User creates a user on the database given their Username,
Email, FirstName, and LastName

Delete User deletes a user and all of their builds, given their Username.

Create PC Build creates an initial build given a username, buildname, and
name of initial components.

Add GPU to Build adds a GPU to a PC Build given a username, buildname,
GPU#, and GPUName. GPU# is which number it is in your build. Your first GPU
should be given GPU# of 1, second GPU given GPU# of 2, and so on.

This works similar for the other Add () to Build Options.

Note: A PC Build is not complete w/o at least one of each of component type added
to the build, meaning it will not show up in the View Reports.

The Delete PC Build option deletes a build completely, given an Username and BuildName.


The Clear button simply clears the output window.


Contact:
For any questions on using or understanding the program,
Email: tyd3@uw.edu
