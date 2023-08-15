import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {







    public static void main(String[] args) {


        int numOfFriends = 3;
        List<List<Integer>> choiceList = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        choiceList.add(list1);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        choiceList.add(list2);

        List<Integer> list3 = new ArrayList<>();
        list2.add(1);
        list2.add(1);
        choiceList.add(list3);

        int numN = 2;
        int numM = 2;

//        int numOfFriends = 6;
//        List<List<Integer>> choiceList = new ArrayList<>();
//        List<Integer> list1 = new ArrayList<>();
//        list1.add(1);
//        list1.add(1);
//        choiceList.add(list1);
//
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(1);
//        list2.add(2);
//        choiceList.add(list2);
//
//        List<Integer> list3 = new ArrayList<>();
//        list2.add(1);
//        list2.add(3);
//        choiceList.add(list3);
//
//        List<Integer> list4 = new ArrayList<>();
//        list2.add(1);
//        list2.add(4);
//        choiceList.add(list4);
//
//        List<Integer> list5 = new ArrayList<>();
//        list2.add(1);
//        list2.add(5);
//        choiceList.add(list5);
//
//        List<Integer> list6 = new ArrayList<>();
//        list2.add(1);
//        list2.add(6);
//        choiceList.add(list6);
//
//        int numN = 2;
//        int numM = 2;
//
        int  answer = 0;

        answer = findMaxHappyFrnd(numOfFriends, choiceList, numN, numM);


//        // Write your code here
//        int max = 0;
//
//        for (int k = 0; k < numOfFriends; k++) {
//            List<Integer> choice = choiceList.get(k);
//            List<Integer> chosenNumber = new ArrayList<>();
//            int currentMax = 0;
//            for (int i = 1; i < choice.size(); i++) {  // ignore the first count
//                if (chosenNumber.contains(choice.get(i)))
//                    continue;
//                else {
//                    currentMax++;
//                    chosenNumber.add(choice.get(i));
//                }
//            }
//            max = Math.max(max, currentMax);
//        }
        System.out.println(answer);


    }

    public static int  findMaxHappyFrnd(int numOfFriends, List<List<Integer>> choiceList, int numN, int numM)
    {
        int  answer = 0;
        // Write your code here
        int max = 0;

        for (int k = 0; k < numOfFriends; k++) {
            List<Integer> choice = choiceList.get(k);
            List<Integer> chosenNumber = new ArrayList<>();
            int currentMax = 0;
            for (int i = 1; i < choice.size(); i++) {  // ignore the first count
                if (chosenNumber.contains(choice.get(i)))
                    continue;
                else {
                    currentMax++;
                    chosenNumber.add(choice.get(i));
                }
            }
            max = Math.max(max, currentMax);
        }
        answer = max;
        return answer;
    }

}
