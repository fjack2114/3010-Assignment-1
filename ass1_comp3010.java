/*
Frederick Jack
45511020
Everything is contained in one class and one file to reduce the chance of a compilation error during marking
*/

import java.util.*;

public class ass1_comp3010 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ass1_comp3010.eventLoop(input);
    }

    public static void eventLoop(Scanner scanner) {
        int numOfGroups;
        int numOfMembers;
        int counter = 0;
        int input = 1;

        System.out.print("Enter the number of groups from which you must find representatives: ");
        numOfGroups = scanner.nextInt();
        // Handles next line character (enter key)
        scanner.nextLine();

        // Creating a Map with integers increasing by 1 acting as indices and the values being the member IDs entered
        Map<Integer, List<Integer>> groupMembers = new HashMap<>(numOfGroups);

        System.out.println("Enter the list of members of each group (one group per line, each terminated by 0):");
        while (counter < numOfGroups) {
            // Initial capacity is 1000 in case of large inputs it does not have to be resized as often
            List<Integer> listOfInts = new ArrayList<>(1000);
            while (input > 0) {
                input = scanner.nextInt();
                // Input has been preset to 1 but ID will only be added to list if it is above 0
                if (input > 0) {
                    listOfInts.add(input);
                }
            }
            // Adding counter as index/key and the line of integers/IDs entered as a list
            groupMembers.put(counter, listOfInts);
            scanner.nextLine();
            counter++;
            // Setting the input above 0 to enter loop before reading next integer
            input = 1;
        }
        // Initialising a List that will contain the IDs to be printed out
        List<Integer> membersSelected = new ArrayList<>(algorithm(groupMembers));
        // This variable is how many IDs are going to be printed out
        numOfMembers = membersSelected.size();

        System.out.println("The number of members selected and their ids are:");
        System.out.println(numOfMembers);

        for (Integer integer : membersSelected) {
            System.out.print(integer + " ");
        }
        // New line is important to let marker know that is the end of the list
        System.out.println();
        scanner.close();
    }

    public static List<Integer> algorithm(Map<Integer, List<Integer>> input) {
        // Initialising a list that is passed back to 1000 in case of larger inputs where resizing cost will be lowered
        List<Integer> list = new ArrayList<>(1000);
        // First loop is counting to the number of indices contained in the input
        for (int i = 0; i < input.size(); i++) {
            // The placeholder Map is storing the ID as the key and how many times it has appeared as the value
            Map<Integer, Integer> placeHolder = new HashMap<>(input.get(i).size());
            // Second loop is iterating through one line IDs
            for (int j = 0; j < input.get(i).size(); j++) {
                // itemToCheck is the ID currently being evaluated
                int itemToCheck = input.get(i).get(j);
                int numberOfTimesItAppears = 0;
                // If item already exists in the list then give it a extra increment so it will break a tie
                if (list.contains(itemToCheck)) {
                    numberOfTimesItAppears++;
                }
                // This for loop is similar to the first "for" loop but is iterating for each individual ID
                for (int k = 0; k < input.size(); k++) {
                    // If the row contains the ID we are looking for and is not the same line being checked, increment
                    if (input.get(k).contains(itemToCheck) && i != k) {
                        numberOfTimesItAppears++;
                    }
                }
                placeHolder.put(itemToCheck, numberOfTimesItAppears);
            }
            int finalNumber = 0;
            // Collections.max is returning the max number of times an item has appeared
            int highestNumberOfTimesItAppearsInMap = (Collections.max(placeHolder.values()));
            // Iterating through the map and finding which value matches the max
            for (Map.Entry<Integer, Integer> placeholder : placeHolder.entrySet()) {
                if (placeholder.getValue() == highestNumberOfTimesItAppearsInMap) {
                    // Final number is the item that has appeared most in the line
                    finalNumber = placeholder.getKey();
                    break;
                }
            }
            // If list does not contain the number selected then add it to the list
            if (!list.contains(finalNumber))
                list.add(finalNumber);
        }
        return list;
    }
}
