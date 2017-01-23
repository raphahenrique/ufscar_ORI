To edit your C program:

From the main menu select File -> New -> Project
In the New Project window:
Under Project types, select Win32 - Win32 Console Application
Name your project, and specify a location for your project directory
Click 'OK', then 'next'
In the Application Wizard:
Select Console application
Select Empty project
Deselect Precompiled header
Once the project has been created, in the window on the left hand side you should see three folders:
Header Files
Resource Files
Source Files
Right-click on Source Files and Select Add-> New Item
Select Code, and give the file a name
The default here will be a file with a *.cpp extension (for a C++ file). After creating the file, save it as a *.c file.
To compile and run:

Press the green play button.
By default, you will be running in debug mode and it will run your code and bring up the command window.
To prevent the command window from closing as soon as the program finishes execution, add the following line to the end of your main function: 
getchar();
This library function waits for any input key, and will therefore keep your console window open until a key is pressed.