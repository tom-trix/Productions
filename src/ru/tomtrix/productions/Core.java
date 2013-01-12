package ru.tomtrix.productions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ger
 */
public class Core {
    private final Map<String, Variable> _variables = new ConcurrentHashMap<>();

    public void addVariable(Variable var)
    {
        _variables.put(var.get_name(), var);
    }

    public void setVariable(String varName, String value)
    {
        if (varName == null || value == null || (varName.trim()+value.trim()).isEmpty()) throw new NullPointerException("frsd");
        _variables.get(varName.trim()).set_value(value);
    }

    public String getValue(String varName)
    {
        if (varName == null || varName.trim().isEmpty()) throw new NullPointerException("frsd");
        String s = _variables.get(varName.trim()).get_value();
        if (s == null)
        {
            inference(varName.trim());
            s = _variables.get(varName.trim()).get_value();
        }
        return s;
    }

    public void inference(String subgoal)
    {

    }

    /*public static void main(String args[])
    {
        System.out.println("66");
        new Core().bd();
    }*/
}
