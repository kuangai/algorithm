package recursion;

/**
 * 递归代码模板
 */
public class Recursion {

    private static final int MAX_LEVEL = 0;

    public void recur(int level, int param){

        if (level > MAX_LEVEL){
            return;
        }
        //process current logic
        process(level, param);

        //drill down
        recur(level+1, param);
        // restore current status

        return;
    }

    private void process(int level, int param) {
    }
}
