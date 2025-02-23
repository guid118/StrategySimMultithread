# Strategy Sim Multithread
### What is this?
This is a java application that can simulate a formula one race, and tell you what strategy is fastest.
It does this by some mathematical calculations based on the values given in the config.json

### How do I use this?
Clone the repository and package the project using maven.
You can edit the config.json file to try different values for laptimes and tyre degradation values.
You can also add another race, though note that this has to be one of the races specified in the utils/Round.java enum.
Then run the program, your output will be saved to output_#.txt where # depends on the number of output files already in the folder.

### Will you update this in the future?
Maybe, currently I'm not very motivated to work on this, but I do have a version with a GUI for result analytics in a local repository that I might publish at a later date.
