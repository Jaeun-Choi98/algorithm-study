package 프로그래머스문제.신규아이디추천;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("...!@BaT#*..y.abcdefghijklm"));
        System.out.println(new Solution().solution("z-+.^."));
        System.out.println(new Solution().solution("=.="));
        System.out.println(new Solution().solution("123_.def"));
        System.out.println(new Solution().solution("abcdefghijklmn.p"));
    }
}

class Solution {
    public String solution(String new_id) {
        String answer = "";
        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-z0-9-_.]","");
        new_id = new_id.replaceAll("\\.+",".");
        if(new_id.charAt(0)=='.') new_id = new_id.replaceAll("^\\.","");
        if(!new_id.isEmpty() && new_id.charAt(new_id.length()-1)=='.') new_id = new_id.replaceAll("\\.$","");
        if(new_id.isEmpty()) new_id = "a";
        if(new_id.length()>15) new_id = new_id.substring(0,15);
        if(!new_id.isEmpty() &&new_id.charAt(new_id.length()-1)=='.') new_id = new_id.replaceAll("\\.$","");
        if(new_id.length()<3) {
            char a = new_id.charAt(new_id.length()-1);
            char[] b = new char[3];
            for(int i=0;i<new_id.length();i++) b[i] = new_id.charAt(i);
            for(int i=new_id.length();i<3;i++){
                b[i] = a;
            }
            new_id = new String(b);
        }
        answer = new_id;
        return answer;
    }
}
