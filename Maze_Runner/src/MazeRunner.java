/*
Your map will look like all "." Characters and one "x" character.
The "x" represents your current position and the "." Represents an unknown space.
As you move through the map the "." Will turn into either walls ("-") or free spaces ("*").
There might even be more surprises in store for you as well
*/

import java.lang.invoke.SwitchPoint;
import java.util.Scanner;

public class MazeRunner {

    static Maze myMap= new Maze();
    static Scanner sc= new Scanner(System.in);
    static String pit_ch,ch;
    static int count=0;

    public static void main(String[] args) {

        intro();

        loop:
            while(!myMap.didIWin()) {
                ch = userMove();
                ch=ch.toUpperCase();

                if (myMap.isThereAPit(ch))
                {
                    navigatePit(ch);
                }
                else{
                if (ch.equalsIgnoreCase("l") && myMap.canIMoveLeft()) {
                    myMap.moveLeft();
                    myMap.printMap();
                    count++;
                } else if (ch.equalsIgnoreCase("r") && myMap.canIMoveRight()) {
                    myMap.moveRight();
                    myMap.printMap();
                    count++;
                } else if (ch.equalsIgnoreCase("u") && myMap.canIMoveUp()) {
                    myMap.moveUp();
                    myMap.printMap();
                    count++;
                } else if (ch.equalsIgnoreCase("d") && myMap.canIMoveDown()) {
                    myMap.moveDown();
                    myMap.printMap();
                    count++;
                } else {
                    System.out.println("Sorry, you have hit a wall !!\nPlease try again");
                }
                }

                if (count==50)
                {
                    System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes\n");
                }
                else if (count==75)
                {
                    System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.\n");
                }
                else if (count==90)
                {
                    System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!\n");
                }
                else if(count==100)
                {
                    System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[\n");
                    break loop;
                }

        }
        if (myMap.didIWin())
        {
            System.out.println("Congrats! You escaped in : "+count+" steps");
        }
        else {
            System.out.println("Sorry, but you didn't escape in time- you lose!\n");
        }


    }

    public static void navigatePit(String ch)
    {
        System.out.println("Watch out! There's a pit ahead, jump it?");
        pit_ch=sc.next();
        pit_ch=pit_ch.substring(0,1);
        if (pit_ch.equalsIgnoreCase("y"))
        {
            try {
                myMap.jumpOverPit(ch);
            }
            catch(Exception e){
                System.out.println("There is a wall after pit!!");
            }
            myMap.printMap();
            count++;
        }

    }

    public static String userMove()
    {
        String choice;

        loop:
            do {
                System.out.println("Where would you like to move? (R,L,U,D)");
                choice=sc.next();
                if (choice.equalsIgnoreCase("l")||choice.equalsIgnoreCase("r")||choice.equalsIgnoreCase("u")||choice.equalsIgnoreCase("d")){
                    break loop;
                }
                else{
                    System.out.println("The choice that you have entered \""+choice.charAt(0)+"\" is an INVALID Entry \nPlease enter a valid input");
                }
            }
        while(true);
        return choice;

    }

    public static void intro(){
        System.out.println("Welcome to Maze Runner!\nHere is your current position :-");
        myMap.printMap();
    }
}
