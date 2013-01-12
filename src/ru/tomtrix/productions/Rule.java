package ru.tomtrix.productions;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * htdr
 */
public class Rule implements ICommand
{
    private final String _name;
    private final Condition _condition;
    private final Map<Variable, String> _newValues;
    private final Core _coreRef;

    public Rule(String name, Condition condition, Map<Variable, String> newValues, Core core)
    {
        if (name == null || condition == null || newValues == null || newValues.size() == 0 || name.trim().isEmpty()) throw new IllegalArgumentException("fsr");
        _name = name.trim();
        _condition = condition;
        _newValues = newValues;
        _coreRef = core;
    }

    public Rule(String name, Condition condition, Variable variable, String value, Core core)
    {
        if (name == null || condition == null || variable == null || value == null || (name.trim()+value.trim()).isEmpty()) throw new IllegalArgumentException("fsr");
        _name = name.trim();
        _condition = condition;
        Map<Variable, String> newValues = new TreeMap<>();
        newValues.put(variable,  value);
        _newValues = newValues;
        _coreRef = core;
    }

    public String get_name()
    {
        return _name;
    }

    public Set<Variable> get_newVariables()
    {
        return _newValues.keySet();
    }

    public boolean equals(Object w)
    {
        return (w instanceof Rule) && _name.equals(((Rule) w)._name);
    }

    @Override
    public boolean execute()
    {
        boolean result = _condition.execute();
        if (result)
            for (Entry<Variable, String> e : _newValues.entrySet())
                _coreRef.setVariable(e.getKey(), e.getValue());
        return result;
    }
}
