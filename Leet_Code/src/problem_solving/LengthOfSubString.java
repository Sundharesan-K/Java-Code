package problem_solving;

import java.util.HashSet;
import java.util.Set;

public class LengthOfSubString {
    public static int lengthOfLongestSubstring(String s) {
        int start = 0;
        int end = 0;
        int maxLen = Math.min(s.length(),1);

        Set<Character> set = new HashSet<>();
        while (end < s.length()){
            char c = s.charAt(end);

            while (set.contains(c)){
                set.remove(s.charAt(start));
                start++;
            }
            set.add(c);
            int windowSize = end - start + 1;

            maxLen = Math.max(maxLen,windowSize);
            end++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcedad"));    }
}
