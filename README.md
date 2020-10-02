# Quinzical

## Intro
Quinzical is a quiz game application that allows players to learn more about New Zealand. The Quinzical quiz format is inspired by the Jeopardy! quiz game format.<br>
There are two modules: The <b>Games module</b> and the <b>Practice module</b> <br>
In both modules, users will be shown a clue that is the answer to question they must complete as an answer. <br>
This application is intended to run on a Linux VirtualBox provided in the SOFTENG206 course.

## Setup
Ensure that in the directory which you would like to start application has these two files and one categories directory provided similar to the following hierarchy:
```
.
+-- categories
|   +-- Famous People
|   +-- Fauna
|   +-- and more...
+-- quinzical.jar
+-- run.sh
```
Ensure that run.sh has permissions that allow it to be executable.<br>
Open terminal in the directory with quinzical.jar and run.sh files<br>
Type in and enter:
<code>./run.sh</code><br>
You should be greeted with a welcome page that looks something like this<br>
![Intro screen](https://cdn.discordapp.com/attachments/692707366897975376/761569551921446932/unknown.png)

## Features
<p>
To make the game accessible, there is a text-to-speech (tts) system that reads clue to the user in addition to a text version of the clue displayed.
  This was done to ensure those that have hearing or sight difficulties are still able to play the game and learn about New Zealand.
  On the starting page of the app, there is a voice speed slider that allows the user to increase or decrease the speed of the tts voice to cater to
  users who prefer listen to a faster voice or those who prefer a slower voice.
</p>
When a voice speed is selected on the slider, clicking Test speed will play a sample sound from the tts systemm so the user can hear the speed of the voice they have selected<br>

![Voice speed slider](https://cdn.discordapp.com/attachments/692707366897975376/761580756027179018/unknown.png)

## Games Module
The Games Module can be entered by clicking the Play button on the starting screen<br>
You will be greeted with a question board with five random categories, each with five New Zealand related questions.<br>
You can only select the lowest money value for each category, but higher values are unlocked as you progress through a category.<br>
For example, answering a 100 point questions in a category will unlock the 200 point question in the same category.
![Games screen](https://cdn.discordapp.com/attachments/692707366897975376/761572272619126834/unknown.png)

Lets click on a button, 100 for Places<br>
![Answer page](https://cdn.discordapp.com/attachments/692707366897975376/761574809976176690/unknown.png)
<br>We're greeted with an answering page and the clue is spoken to us. On this page there are several options<br>
- <b>Submit</b>
  - Once you type in an answer in the text box, click this to submit your answer
- <b>Don't know</b>
  - If you've pondered over the question for a while, but can't come up with answer, click this to skip the question
- <b>Replay clue</b>
  - If you want to hear the tts voiced clue again, click this for it be read to you again
  <br>
 When an answer is submitted, you will get awarded points for getting it correct, but none for getting it incorrect or skipping<br>
 After submitting or skipping, we will return to the question board where the 200 point question for Places will now be unlocked <br>
![Question board](https://cdn.discordapp.com/attachments/692707366897975376/761578078560321556/unknown.png)

## Practice Module
![Practice screen](https://cdn.discordapp.com/attachments/692707366897975376/761572993028980766/unknown.png)
