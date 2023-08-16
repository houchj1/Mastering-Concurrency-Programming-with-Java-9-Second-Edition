package leetcode;


/**
 * Container with most water.
 * the walls do NOT push the water away (eg. do not have any width, but can stop water as container)
 * <p>
 * So can also transfer to maxArea problem. the walls become straight lines.
 * maxArea problem finds the 2 walls that has max area of the rectangle that they form.
 *
 * @see LeetCode042
 */
public class LeetCode011 {

    public static int maxArea(int[] height) {

        if (height == null || height.length < 2) return 0;

        int area = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            area = Math.max(area, (right - left) * Math.min(height[left], height[right]));

            // move the shorter wall has the chance to get larger area
            if (height[left] > height[right]) right--;
            else if (height[left] < height[right]) left++;
            else {  // if they are the same, then move both has the chance to get larger area
                left++;
                right--;
            }

        }
        return area;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

}
