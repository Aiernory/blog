import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Aiernory
 * @className: PACKAGE_NAME.TestRegex
 * @date 2020/4/3 23:03
 * @Description:
 */
public class TestRegex {
    public static void main(String[] args) {
        String exp = "[a-z]";
        Pattern pattern=Pattern.compile(exp);
        Matcher matcher=pattern.matcher("asdasdsdg sdgd ");
        boolean b = matcher.matches();
    
    
        matcher.find();
        
    }
}
