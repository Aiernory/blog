package ren.aiernory.blog.leeCode;

import net.bytebuddy.agent.ByteBuddyAgent;
import org.assertj.core.api.AbstractCharSequenceAssert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.leeCode.Problem1
 * @date 2020/2/29 16:49
 */


public class Problem1 {
    Problem1() {
    }
    
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
    
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int curLength = 0;
        Map<Character, Integer> map = new HashMap<>();//key放字符，value放下标
        for (int i = 0; i < s.length(); i++) {
            Integer put = map.put(s.charAt(i), i);
            if (put == null) {
                //放入成功，不重复
                curLength++;
            } else {
                //重复
                map.clear();
                if (max < curLength) {
                    max = curLength;
                }
                curLength = 0;
                i = put;
                if(max+i>s.length()){
                    break;
                }
            }
        }
        if (max < curLength) {
            max = curLength;
        }
        return max;
    }
}
