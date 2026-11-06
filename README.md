# Project Name
## Bank of Potato: Personal Ledger

<!-- HOW TO USE THIS TEMPLATE
     Save it as README.md at the root of your project, next to pom.xml. Replace every comment like this one
     with your own text; comments do not show on GitHub, so what you leave untouched shows as an empty heading.
     Two sections are optional and say so: Class Diagram, and Data and configuration. When your project does not
     need one, delete the whole section, heading included. Every other section stays. -->

<!-- One sentence under the title: what the app is, for whom.
     Example: A console app that keeps a store's inventory in a csv file and lets a clerk search it. -->
A console app that keeps record of transactions in a csv file and lets a account holder to view their reports

## Description of the Project

<!-- Three to five sentences. What the application does, who uses it, what problem it solves.
     Write it for someone who has never seen the workbook: a recruiter, a classmate, you in six months. -->
The personal ledger is a java console application for an account holders or system users to keep track of their 
transactions. Each transaction keep track of the date,time,description,vendor,and amount in the csv file, when a user or
holder enters the information. Account holders can search through their file for any deposits and payment made no matter
the date,time, or vendor. This will helps account holders with managing their spending and income with ease.


## Features

<!-- One bullet per thing the app can do, in the order of your menu. Mark bonuses.
     For an API (Capstone 3), one line per endpoint instead: method, path, what it does.
     Be honest: list what works. If something is half done, say so under Future Work instead.
     Example:
     - Shows every product in the store
     - Searches products by name (bonus) -->

- add new deposits and payments and save to the csv file
- shows newest entries first
- prints requested transactions
- Searches transactions by any entered information

## User Stories

<!-- The stories you worked from, one per bullet, in the format you learned in Workbook 3b:
     As a [type of user], I want [some goal] so that [some reason].
     Same text as the cards on your GitHub project board. -->

- As a user, I want to see the newest entries first, so that I can see recent added transactions in my account
- As a user, I want to add a deposit with the date,time,description,vendor,and amount, so that I know when it was saved 
and where the transaction was made
- As a user, I want to search for a specific entrée or entries with one or two enters, so that I could find it in my ledger

## Setup

### Prerequisites

- Java 17 (Amazon Corretto), the version used in class.
- IntelliJ IDEA Community Edition: <https://www.jetbrains.com/idea/download/>

### Running the application

1. Clone this repository, or download it as a zip and unzip it.
2. In IntelliJ: File > Open, pick the project folder (the one that holds `pom.xml`), press Open.
3. Wait for IntelliJ to finish indexing (progress bar at the bottom).
4. Open `src/main/java/com/pluralsight/YourMainClassName.java`. <!-- replace YourMainClassName with the class that has main -->
5. Right-click inside the file and choose Run 'YourMainClassName.main()'.

## Technologies Used

- Java 17
- IntelliJ IDEA
<!-- Add anything else you used: a library, Maven plugins, an external tool. For a console app this list is short. That is fine. -->

## Demo

<!-- Pictures of the app running: the console for a console app, Insomnia or Swagger for an API.
     Screenshots are enough; a GIF is a bonus.
     1. Create a folder named `images` at the project root, next to `pom.xml`.
     2. Take screenshots of the app in the IntelliJ console (the menu, one feature, one error message):
          Windows: Win + Shift + S, drag a rectangle, then paste into Paint and save as PNG, or open the
                   notification and save. Save into the `images` folder.
          Mac:     Shift + Cmd + 4, drag a rectangle. The file lands on the Desktop; move it into `images`.
     3. Name the files by what they show: `images/menu.png`, `images/search.png`.
     4. Reference each one below with a relative path. No spaces in file names.
     5. Commit the images with the rest of your work (Git > Commit shows them as unversioned files: tick them).
     Optional GIF: LICEcap (free, Windows and Mac, <https://www.cockos.com/licecap/>) records a region of the
     screen straight to a .gif. Keep it under 10 seconds and under 10 MB, save it into `images`. -->

![Main menu](images/menu.png)

![One feature in action](images/search.png)
![One feature in action](images/error.png)

## A piece of code I am proud of

<!-- Paste one short block of your own code (5 to 15 lines) between the three backticks, then write two
     or three sentences: what it does and why you chose to write it that way. This is what you show on
     demo day and what you talk about in an interview. -->

```java
 for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
```
This code prints all the deposits in the file by using only the positive amount. I write the code like this to display the 
new entries first. I like this code because it open my mind to different ways I can use it for other projects.
## Future Work

<!-- What you would add or fix next, one bullet each. Unfinished bonuses belong here. -->

- use while loops for my user inputs
- add color
- calculate the total sum of transaction for deposits,payments,and ledger
- write better user stories and sub-tasks

## Resources

<!-- What helped you: the workbook, the week's hub pages, a tutorial, a documentation page. Real links. -->

- Java YearUp Fall 2026 hub: <https://raymaroun.github.io/yearup-java-fall2026/>
- workbook 3: <https://github.com/RayMaroun/yearup-fall-section-8-2026/tree/main/pluralsight/java-development/workbook-3>
- coPilot AI agent: <https://copilot.cloud.microsoft/chat/agent/T_20b53e3f-d488-d128-27fc-440ed32e7a02.eade2051-4773-4cb0-aa3e-b17cdf11ebd4.gpt.17fde386-a7a1-4dd0-a089-a0b0a70bb567?fromcode=cmmu8qodiz1&lhredirected=1&es=AutoSignIn>
- website: <https://www.geeksforgeeks.org/java/yearmonth-minusmonths-method-in-java-with-examples/>
- website: <https://www.tutorialspoint.com/javatime/javatime_monthday_withdayofmonth.htm>

## Author

<!-- Your name and, if you like, your GitHub profile link. For a team workshop, one line per person with what each one built. -->

- **Adrian Lewis:** <https://github.com/Adrian-Lewis217>

## Thanks

<!-- Optional. The people who helped: the instructor, a classmate who debugged with you, a mentor. -->

- Thank you to Raymond for the file reading demo that this app is built on, and the resources and support through the month
- Thank you to Zion and Maalik for clarification and support you give during the week 