---
SpaceAdventure Kotlin GUI Addition
---
The kotlin GUI version of space adventure is a port of my python space adventure from https://github.com/cwdatlas/SpaceAdventure-GUI-.
You are able to use a compose Desktop GUI to build a rocket and fly it, and if you have enough thrust and DeltaV, then you can get to space!
This is my first ever Kotlin compose Desktop program, I followed this https://www.youtube.com/watch?v=7IOfsV6dTeg tutorial by YoursSohail on YouTube to learn how to use compose,
then I used what I learned to build the rest of the application.
Vocab for the game!
- DeltaV -> the change in velocity for your rocket, the more the better.
- TWR -> Thrust to weight ratio is the thrust / mass * 9.81. If it is over 1, then your rocket goes up. If it is over 1.5 it has a chance to get to space.
- ISP -> Specific impulse (ISP) is the efficiency of a rocket engine measured in seconds. think of it as miles per gallon.

---
how to install
---
you must have git and java17 and up installed to run this program

- in a new folder use 'git repo clone 'https://github.com/cwdatlas/SpaceAdventureKotlin-GUI' in your command line
- Navigate into the newly created directory so you can see the 'gradlew' file.
- Now type './gradlew run' or 'gradlew run', then it might take a while for the app to build.
- The app should be booted within a couple of minutes! have fun!

---
how to use
---
Here is a list of Buttons and their uses for the game:
- capsule -> Lists capsules and their stats. Any choice will override a previous choice
- tank    -> Lists tanks and their stats. Any choice will override a previous choice
- engine  -> Lists engines and their stats. Any choice will override a previous choice
- launch  -> Launches your rocket! See how far you get!
- Exit program using the x in the top right corner
