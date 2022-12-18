# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
   
   My overall approach to tackling this project was to first write my game loop in pseudocode, then figure out what functions would be needed and which classes would be in charge of those functions. After coding all the classes, I implemented my game loop one bit at a time. I tackled things as they occur in the game so first I implemented setting a budget, then writing a grocery list, then taking in user commands. I wanted the game to flow like shopping at a farm stand or grocery store would in real life and I implemented the game loop with this in mind. 
   
 - What **new thing(s)** did you learn / figure out in completing this project?
 
   I learned a lot more about using ArrayLists and Hashtables as data structures and the pros and cons of each. I also learned how complex game loops can be and how much time, energy, and knowledge can go into a simple game loop.

 - Is there anything that you wish you had **implemented differently**?

   Yes, I wish I had broken my main method into smaller bits and implemented more methods in my final driver class to use in my main method. My game loop reads as very unruly currently. I also wish I had taken time to figure out how to do some things more effeciently, instead of just using my current knowledge of coding. I ended up over complicating a few things and wasn't able to streamline many of my functions. For example, I implemented the PUT BACK and TAKE commands very differently when I could have used a similar, more streamlined approach for each.

 - If you had **unlimited time**, what additional features would you implement?

   I would make the story line of my game more interesting and maybe even have some interaction with the owner of the farmstand or other customers. I would also implement more input validation. For example, my code right now would not handle a non integer input when asked to input an amount in the take and put back commands. If the user inputs a non integer value in these instances, my code will crash. 

 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?

   Jordan sat with me for a whole hour helping me figure out how to implement my PUT BACK command which was incredibly helpful. I learned in that conversation that an ArrayList was not the most useful data structure in that situation and that I could have used a Hashtable. I used an ArrayList because I was thinking quite literally about the cart as being made up of individual objects but using a Hashtable would have allowed me to implement simpler methods.
   
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?

   I would tell myself to think more about parsimony and that focusing on the simplest route from the beginning will actually save me time in the end. I spent a lot of time untangling complicated methods. I would also tell myself to code and test smaller pieces at a time. This would have also saved me a lot of time and would have made my final code more readable. 

 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.

   I worked alone on this project. 
