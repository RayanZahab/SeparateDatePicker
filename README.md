# SeparateDatePicker
Android Date Picker with separate fields : day , month , year


Dates are part of almost every project we develop, and that includes mobile application.



But the way we extract, read and validate dates also varies with our needs from one project to another, and the built in Data Picker and Calendar doesn't serve all our purposes.



One of my projects required reading a date separately, therefore I am sharing the outcome!



In this tutorial, I will walk you through my open source Android date picker with separated fields:

Day, Month, Year.


The complete code version can be found on GitHub : Download Here




First we need to build the layout

And then the JAVA code of the activity 

Lets explain what is happening:  

First we need to populate the data in the spinners taking the following into consideration : 

Each month has different number of days

Some years are Leap years and February has 29 days instead of 28

We need to be able to limit the chosen dates to specific age range, depending on current year



Populating the years:

In my tutorial I aim to strict the date selection to represent an age between 10 and 30.



Therefore I should limit the year selection to be between :

current year-30 < current year < current year-10



For this purpose we created the populateYears function with the following parameters:

minAge: the minimum age we accept based on current year 

maxAge: the minimum age we accept based on current year

The function is then called within the Activity's onCreate function, to automatically load data once activity is created.



In the populateYears function:

We fill a String array of those years.

We create an ArrayAdapter and pass our years array to it.

And finally we set adapter to our years spinner.

​



Populating the months:

Months are the easiest part of this tutorial, they are a fixed dozen which we previously know the order and the name.

Therefore we added an Array String to our strings resources for months:



​



And to populate this array in the Month spinner, we just set value of entries attribute of the Spinner to the array name:

​



Populating the days:

The trickiest part, because it should be based on the month and year selected.

Therefore we will need to update the content of the days spinner every time the value of the year and month changes.

For this purpose we needed to create an event listener to those spinners, therefore we create the listener:

​
Now lets connect the spinners to the listener:

​
And lets see what the "setDays()" function is doing:

The "getActualMaximum" function will get the number of days in a specific month of a specific year taking into consideration leap years..

Once we know that we can set the content of the days spinner! 

​

And that's it! Enjoy coding !
