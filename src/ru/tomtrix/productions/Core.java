package ru.tomtrix.productions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ger
 */
public abstract class Core {
    private final Map<Variable, Variable> _variables = new ConcurrentHashMap<>();
    private final Random _random = new Random(System.currentTimeMillis());
    private RuleSet _curRuleset;
    private RulesSelectionStrategies _ruleSelectionStrategy = RulesSelectionStrategies.FIRST;

    public abstract String askForVariable();

    public void addVariable(Variable var)
    {
        _variables.put(var, var);
    }

    public void setVariable(Variable var, String value)
    {
        if (var == null || value == null || value.trim().isEmpty() || !_variables.containsKey(var)) throw new NullPointerException("frsd");
        _variables.get(var).set_value(value.trim());
    }

    public String getValue(Variable var)
    {
        if (var == null || !_variables.containsKey(var)) throw new NullPointerException("frsd");
        if (!var.is_designated())
            switch (var.get_type())
            {
                case INFERRIBLE: inference(var);
                case ASKABLE: setVariable(var, askForVariable());
                case INFERRIBLY_ASKABLE:
                {
                    inference(var);
                    if (!var.is_designated())
                        setVariable(var, askForVariable());
                }
                default: throw new NoSuchElementException("gdr");
            }
        String s = var.get_value();
        return s.trim();
    }

    public void startConsulting(Variable goal, RuleSet ruleset)
    {
        _curRuleset = ruleset;
        for (Variable var : _variables.keySet())
            var.reset();
        inference(goal);
    }

    private String inference(Variable subgoal)
    {
        if (_curRuleset == null) throw new NullPointerException("fse");
        if (subgoal.is_designated())
            return subgoal.get_value();

        //BACKWARD CHAINING
        //find the fired rules
        List<Rule> firedRules = new LinkedList<>();
        for (Rule r : _curRuleset.get_rules())
            if (r.get_newVariables().contains(subgoal))
                firedRules.add(r);
        //handle fired rules one-by-one
        while (!firedRules.isEmpty())
        {
            Rule chosenRule = chooseTheRule(firedRules);
            chosenRule.execute();
            if (subgoal.is_designated())
                return subgoal.get_value();
            firedRules.remove(chosenRule);
        }
        return null;
    }

    private Rule chooseTheRule(List<Rule> rules)
    {
        if (rules == null || rules.isEmpty()) return null;
        switch (_ruleSelectionStrategy)
        {
            case FIRST: return rules.get(0);
            case LAST: return rules.get(rules.size()-1);
            case RANDOM: return rules.get(_random.nextInt(rules.size()));
            default: throw new NoSuchElementException("gr");
        }
    }
}
