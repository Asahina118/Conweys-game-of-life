import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RuleManager {
    private static RuleManager instance;
    private static List<Rule> rules;
    public static int reproductionNumber = 3;
    public static int surviveLowerBound = 2;
    public static int surviveUpperBound = 3;
    private RuleManager(){
        rules = new ArrayList<>();
    }
    public static RuleManager getInstance() {
        if (instance == null) {
            instance = new RuleManager();
        }
        return instance;
    }
    public static void builder(List<Rule> rulesInput) {
        getInstance().rules = rulesInput;
        Debug.out("rules.size() is " + getInstance().rules.size());
    }
    public void addRule(Rule rule) {
        rules.add(rule);
    }
    public void addRule(Rule ... rules) {
        for (Rule rule : rules) {
            getInstance().rules.add(rule);
            Debug.out("Adding " + rule.getClass().getSimpleName() + " to the RuleManager...");
        }
    }
    public void applyRules(Cell cell) {
        getInstance().rules.forEach(rule ->{
            rule.apply(cell);
        });
    }

}
