import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main
{


    public static void main(String[] args)
	{

        // call the StartGame method
        startGame();

	}

    // Method to call the right search algorithm

	private static void startGame()
    {
        int[] myAr = null;
        //Collections.shuffle(Arrays.asList(myAr));
        //System.out.println(Arrays.toString(myAr));
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Welcome to 8 Puzzle solver:\n" +
				"Please select the type of algorithm (enter the number and press Enter): \n\n" +
				"1) Breadth First Search\n" +
				"2) A * Search\n" +
                "0) Quit\n" +
                ": ");

        String algType = reader.nextLine();

        if (algType.equals("1")){
            BFSearch.search(setArray(myAr) , true);
        } else if (algType.equals("2")) {
            AStar.search(setArray(myAr), true, 'o');
        }  else if (algType.equals("0")){
            System.exit(-1);
        } else {
            System.out.println("Please select again");
            startGame();

        }


}

    // Generate an array of 9 ints
    private static int[] setArray(int[] myAr){

        Integer[] intAr = {0,1,2,3,4,5,6,7,8};
        Collections.shuffle(Arrays.asList(intAr)); // Shuffle the Integers
        int[] intArray = Arrays.stream(intAr).mapToInt(Integer::intValue).toArray();

        System.out.println("Your random board is: " + Arrays.toString(intArray));
        System.out.println("Please wait is proses on some boards can take a while... \n");

        return intArray;

    }




}
