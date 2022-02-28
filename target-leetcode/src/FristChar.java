import java.util.HashMap;
import java.util.HashSet;

/**
 * @author admin
 */
public class FristChar {
    /**
     * 输入：s = "abaccdeff"
     * 输出：'b'
     *
     * @return char
     */
    public char firstUniqChar(String s) {
        HashMap<Character, Boolean> dic = new HashMap<>();
        char[] sc = s.toCharArray();
        for (char c : sc) {
            dic.put(c, !dic.containsKey(c));
        }
        for (char c : sc) {
            if (dic.get(c)) {
                return c;
            }
        }
        return ' ';
    }
}