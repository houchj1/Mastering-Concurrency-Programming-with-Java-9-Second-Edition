package leetcode;

/**
 * remove duplicates from sorted array
 */
public class LeetCode026 {

    static int removeDuplicates(int[] nums) {
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast - 1] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,2,3,3,4,4,4,5,7,9}));
    }


}
