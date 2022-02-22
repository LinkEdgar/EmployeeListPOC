## Build tools & versions used

## Steps to run the app
Use Android Studio Artic Fox

## What areas of the app did you focus on? Areas I focues on were architecture and the functionality outlined in the guide. Most of the work consisted on loading in employee information and rendering it to our ui. I wanted to do this efficiently and with seperation of concern built in to the solution.

## What was the reason for your focus? What problems were you trying to solve? Loading the employee list efficiently meant that I would have to load the list once. Given that I chose to observe the list in the onStart() lifecycle event I had to focus some efforts to do this properly. Making sure to only load data initially and on the users refresh click allows for an efficient soluition. Luckily Glide's image loading software has a lot of caching built in so that aspect of the project took considerably less time.

## How long did you spend on this project? The project took 3-4 hours approximately.

## Did you make any trade-offs for this project? What would you have done differently with more time? Given more time I would like to add better UI and a detail screen for employees.

## What do you think is the weakest part of your project?
Given more time writing both unit and ui tests would improve the app's reliability and overall test coverage.

## Did you copy any code or dependencies? Please make sure to attribute them here!
For the networking I used retrofit and okhttp. For image loading and caching I used glide's library. 

## Is there any other information you’d like us to know?