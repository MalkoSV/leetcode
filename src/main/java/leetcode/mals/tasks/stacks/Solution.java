package leetcode.mals.tasks.stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    private static final Map<Character, Character> BRACKETS = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
    );

    public boolean isValid(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (BRACKETS.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != BRACKETS.get(ch)) {
                    return false;
                }
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }


    public String decodeString(String s) {

        StringBuilder result = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        StringBuilder prefix = new StringBuilder();
        StringBuilder inBrackets = new StringBuilder();
        int repeat = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (stack.isEmpty()) {

                if (Character.isDigit(ch)) {
                    repeat = repeat * 10 + (ch - '0');
                    continue;
                }

                if (ch == '[') {
                    stack.push(ch);
                } else {
                    prefix.append(ch);
                }

            } else {

                if (ch != '[' && ch != ']') {
                    inBrackets.append(ch);
                    continue;
                }

                if (ch == '[') {
                    inBrackets.append(ch);
                    stack.push(ch);
                    continue;
                }

                stack.pop();

                if (stack.isEmpty()) {
                    String decoded = decodeString(inBrackets.toString());

                    result.append(prefix).append(decoded.repeat(repeat));

                    prefix.setLength(0);
                    inBrackets.setLength(0);
                    repeat = 0;

                } else {
                    inBrackets.append(ch);
                }
            }
        }
        return result.append(prefix).toString();
    }


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        Map<Integer, Integer> numsMap2 = new HashMap<>();
        for (int j = 0; j < nums2.length; j++) {
            numsMap2.put(nums2[j], j);
        }

        for (int i = 0; i < nums1.length; i++) {
            result[i] = -1;

            int indexInNums2 = numsMap2.get(nums1[i]);

            for (int j = indexInNums2 + 1; j < nums2.length; j++) {
                if (nums2[j] > nums2[indexInNums2]) {
                    result[i] = nums2[j];
                    break;
                }
            }
        }
        return result;
    }

    public int[] nextGreaterElementByStack(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> nextGreater = new HashMap<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                nextGreater.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreater.getOrDefault(nums1[i], -1);
        }

        return result;
    }

    static class TemperatureDay {
        private final int temperature;
        private final int indexInArray;

        public TemperatureDay(int temperature, int indexInArray) {
            this.temperature = temperature;
            this.indexInArray = indexInArray;
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];

        Deque<TemperatureDay> stack = new ArrayDeque<>();
        Map<Integer, Integer> daysAfterMap = new HashMap<>();

        for (int i = 0; i < temperatures.length; i++) {

            TemperatureDay day = new TemperatureDay(temperatures[i], i);

            while (!stack.isEmpty() && day.temperature > stack.peek().temperature) {
                TemperatureDay peek = stack.pop();
                result[peek.indexInArray] = i - peek.indexInArray;
            }

            stack.push(day);
        }

        return result;
    }


    public int[] nextGreaterElementByStackTwo(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }

        for (int num : nums) {
            while (num > nums[stack.peek()]) {
                result[stack.pop()] = num;
            }
        }

        return result;
    }

}
