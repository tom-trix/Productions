package ru.tomtrix.productions;


import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;
import static java.lang.Math.min;
import static java.lang.Math.max;

/**
 * gedgr
 */
public class RuleSet {
    private final List<Rule> _rules = Collections.synchronizedList(new ArrayList<Rule>());

    public void addRule(Rule rule) throws Exception
    {
        if (rule == null) throw new NullPointerException("gsdr");
        if (_rules.contains(rule)) throw new KeyAlreadyExistsException("gr");
        _rules.add(rule);
    }

    public void deleteRuleByName(String s) throws Exception
    {
        if (s == null || s.trim().isEmpty()) throw new NullPointerException("fse");
        for (Rule r : _rules)
            if (r.get_name().equals(s.trim()))
            {
                _rules.remove(r);
                return;
            }
    }

    public void deleteRule(int index) throws Exception
    {
        _rules.remove(index);
    }

    public void moveRule(int index, int newIndex)
    {
        if (min(index, newIndex) < 0 || max(index, newIndex) >= _rules.size()) throw new IllegalArgumentException("Wrong index");
        _rules.add(newIndex, _rules.remove(index));
    }

    public String toString()
    {
        StringBuilder sbuf = new StringBuilder();
        for (Rule r : _rules)
            sbuf.append(", ").append(r.get_name());
        return sbuf.substring(2);
    }

}
