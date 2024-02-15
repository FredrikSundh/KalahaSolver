# KalahaSolver
Greedy Algorithm Maximizing the most obtainable points on your turn in the board game kalaha


To use, run the java file and it will ask for input on the command line. You input which nest the opponent played and then tells you which nest to play to get maximum points. As of right now the program assumes you always play the optimal nest. Also the opponent always starts in the current version and always plays as the left nest from your point of view.

The board state is visualized as a single array of size 12. Since you move counter-Clockwise the array is visualized to the board as this:
`6 - 5 - 4 -  3 -  2 -  1
7 - 8 - 9 - 10 - 11 - 12`
                                                                                                                                          
                                                                                                                                          
                                                                                                                                      
Will be updated to include options for who starts and which nest you play as well as the option to update the board state with a suboptimal play on your part should you choose to.
