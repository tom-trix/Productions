package ru.tomtrix.productions;

/**
 * fesfes
 */
public class Variable implements Comparable<Variable> {
    private final String _name;
    private final VariableType _type;
    private String _value;

    public Variable(String name, VariableType type)
    {
        if (name == null || name.trim().isEmpty() || type == null) throw new NullPointerException("sgvrs");
        _name = name.trim();
        _type = type;
    }

    public void reset()
    {
        _value = null;
    }

    public String get_value()
    {
        return _value;
    }

    public VariableType get_type()
    {
        return _type;
    }

    public void set_value(String value)
    {
        _value = value;
    }

    public boolean is_designated()
    {
        return _value != null && !_value.trim().isEmpty();
    }

    public boolean equals(Object x)
    {
        return x instanceof Variable && _name.equals(((Variable) x)._name);
    }

    public String toString()
    {
        return  _name;
    }

    @Override
    public int compareTo(Variable o)
    {
        return _name.compareTo(o._name);
    }
}
