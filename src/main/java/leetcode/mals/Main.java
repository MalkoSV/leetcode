package leetcode.mals;

import leetcode.mals.tasks.backtracking.Permutations;
import leetcode.mals.tasks.backtracking.PermutationsII;
import leetcode.mals.tasks.backtracking.Subsets;
import leetcode.mals.tasks.backtracking.SubsetsII;

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();

        int[][] grid = {{2,1,1}, {1,1,0}, {0,1,1}};

        int[] nums = {1,2,2};

        System.out.println("++++++++++++++++ Result +++++++++++++++++++");
        System.out.println(new SubsetsII().subsetsWithDup(nums) + " -> ");

        long micros = (System.nanoTime() - start) / 1_000;
        System.out.printf("Execution time: %d μs%n", micros);
    }

}
