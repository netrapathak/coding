package Leetcode;

/**
 * Created by rbhatnagar2 on 3/15/17.
 * <p>
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * Return the total number of ways you can paint the fence.
 */
public class Q276_Paint_Fence {

    // Constant Space Solution
    public int numWaysConstantSpace(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        // Case 1: First 2 posts have same color.
        int sameCase = k;
        // Case 2: First 2 posts have different colors.
        int diffCase = k * (k - 1);
        for (int i = 3; i <= n; i++) {
            int temp = diffCase;
            /**
             * To every sameCase and diffCase we can add a new post with different color as the last one. We have k-1 color
             * options for the last one.
             */
            diffCase = (sameCase + diffCase) * (k - 1);
            /**
             * To every diffCase we can add a new post with the same color as the last one to not generate violation - no
             * more than 2 adjacent fence posts have the same color.
             */
            sameCase = temp;
        }
        return sameCase + diffCase;
    }

    // DP
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (n == 1) return k;
        // same[i] means the ith post has the same color with the (i-1)th post.
        int[] same = new int[n];
        // diff[i] means the ith post has a different color with the (i-1)th post.
        int[] diff = new int[n];
        same[0] = same[1] = k;
        diff[0] = k;
        diff[1] = k * (k - 1);
        for (int i = 2; i < n; ++i) {
            // the i-th in same should be equal the previous one in diff since only two consectutive
            // same are allowed
            same[i] = diff[i - 1];
            // the i-th in diff should be either different from its previous one or from the one
            // before the previous one
            diff[i] = (k - 1) * (same[i - 1] + diff[i - 1]);
        }
        return same[n - 1] + diff[n - 1];
    }


}
