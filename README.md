# Quinzical

## Intro
Quinzical is a quiz game application that allows players to learn more about New Zealand. The Quinzical quiz format is inspired by the Jeopardy! quiz game format.<br>
There are two modules: The <b>Games module</b> and the <b>Practice module</b> <br>
In the Games module, the user is presented with a grid of question which they have to answer correctly to gain points<br>
In the Practice module, the user selects a category, and a random question from the category is selected for the user to practice answering<br>
In both modules, users will be shown a clue that is the answer to a question which they must provide as an answer. <br>
This application is intended to run on a Linux VirtualBox provided in the SOFTENG206 course.

## Setup
Ensure that in the folder which you would like to start application has two files: main.java.quinzical.jar, run.sh and in addition, one categories folder. Inside the categories folder, there should be text files with New Zealand questions and a sub-folder named international containing all the international questions. These files at minimum should appear like the hierarchy shown below:
```
.
+-- categories
|   +-- Famous People
|   +-- Fauna
|   +-- and more...
|   +-- international
|       +-- France
|       +-- Geography
|       +-- and more...
+-- quinzical.jar
+-- run.sh
```
Ensure that run.sh has permissions that allow it to be executable.<br>
Open terminal in the directory with main.java.quinzical.jar and run.sh files<br>
Type in and enter:
<code>./run.sh</code><br>
You should be greeted with a starting page that looks something like this<br>
![Intro screen](https://cdn.discordapp.com/attachments/692707366897975376/767348366739898388/unknown.png)

## Adding your own categories and questions
Inside a category text file we can see this:
![Category text file](https://cdn.discordapp.com/attachments/692707366897975376/767345277530406912/unknown.png)<br>
Questions are written on one line each and formatted with vertical bar separators <code>|</code> as such:<br>
<code>clue|question prefix|answer/additional answers</code><br>
The forward slash <code>/</code> are used to separate multiple correct answers for questions<br>
For example on line 2, the clue that would be displayed to users would be" "This is the number of stars on the New Zealand flag"<br>
The prefix to the question answer would be: <code>What is</code><br>
The correct answers would be <code>4</code> or <code>four</code><br>
So the question page would look something like this: <br>
![Question example](https://cdn.discordapp.com/attachments/692707366897975376/767347790798913576/unknown.png)
<p> You can make your own category by creating a text file named with the name of the category e.g. Geography<br>
Each category text file should have at least 5 lines of questions

  
