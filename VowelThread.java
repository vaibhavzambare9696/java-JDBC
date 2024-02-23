public class VowelThread extends Thread {
    private String str;

    public VowelThread(String str) {
        this.str = str;
    }

    public void run() {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (isVowel(ch)) {
                System.out.println(ch);
            }
        }
    }

    private boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void main(String[] args) {
        String input = "Hello, how are you?";
        VowelThread vowelThread = new VowelThread(input);
        vowelThread.start();
    }
}

