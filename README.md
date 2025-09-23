# A2 Genetic Algorithms

Your readme should include the following information. Each student needs to submit all of this information themselves, even when pair programming. 

Group Members:
- Sierra Zhang (we didn't pair program)

Any peers and/or TAs/Tutors you collaborated with:
- Sierra Zhang

Would you like to give "kudos" to anyone who was particularly supportive or helpful?
- Just talking to Sierra, another classmate, was nice to break down the homework instructions, especially when it gets so wordy. She helped me conceptually understand the mirror comparison aspect of the getFitness() method and how to write the for loop for it. It was also nice to work with a new classmate and get to know each other as well. I didn't know Sierra before this class.

Cite any references used:
- https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#method.summary
- lots of GeeksforGeeks articles
    - https://www.geeksforgeeks.org/java/java/
    - https://www.geeksforgeeks.org/java/find-first-and-last-element-of-arraylist-in-java/


If you used AI, please describe how you used it and what the experience taught you:
- I used AI to break down how to implement the getFitness method based on the instructions for the mirror comparison. I learned that in a for loop you can split the chromosome string in half to help you identify the left most character, and then you can identify the right most character from subtracting the string's size by one and the current index. Then, you iterate until you reach the element number that is halfway. This experience really helped me understand the instructions and for loops conceptually, as well as why the code works and how I can improve upon it with my own ideas.


## Questions

Please briefly describe what you observed about the "winners" produced by your genetic algorithm. Did changing the parameter values have any effect on what you observed?
- I noticed that the "winners" were perfect palindromes even when I reduced the number of evolution rounds, winners per round, and population size. I did notice the palindromes would come in different forms, either consisting of two different letters (ex. CECECECECEECECECECEC, CACACACAC) or three different letters (ex. DCDEDCD). Of course when I decreased the parameter c_max, the final best chromosome was shorter than previous simulations. But I also noticed palindromes were generated pretty fast (ex. in 4-5 rounds) regardless, and those earlier ones had three different letters in them.

## Reflection

Please provide a reflection on your experience with this assignment-- what was interesting? what was hard? what do you feel like you learned?
- This assignment was definitely challenging; I think comprehending what the instructions were telling me to do was an important first step to coding correctly. The second step that was also difficult was turning my ideas, for the different parts of the assignment, into actual, working code. From these challenges, I learned more on what I could accomplish with arraylists, for loops, and method overloading to help me complete this assignment. Overall, it was so interesting and rewarding to see this program be applicable to real life contexts like natural selection/evolution. It was fun to run my code and see how the population of "chromosomes" evolve over time, from very different random strings to perfect palindromes.