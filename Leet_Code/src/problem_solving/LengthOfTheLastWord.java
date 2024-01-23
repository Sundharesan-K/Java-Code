package problem_solving;

public class LengthOfTheLastWord {
    public static int lengthOfLastWord(String s) {
        s = s.stripTrailing();
        int count = 0;
        for (int i = s.length()-1; i>=0; i--) {
            if (s.charAt(i) == ' '){
                return count;
            }
            count++;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord(" I am a sundhar  "));
    }
}
