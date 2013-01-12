package ru.tomtrix.productions;

/**
 * fesfes
 */
public class Variable {
    private final String _name;
    private final VariableType _type;
    private String _value;

    public Variable(String name, VariableType type)
    {
        if (name == null || name.trim().isEmpty() || type == null) throw new NullPointerException("sgvrs");
        _name = name.trim();
        _type = type;
    }

    public String get_name() {
        return _name;
    }

    public String get_value()
    {
        return _value;
    }

    public void set_value(String _value)
    {
        this._value = _value;
    }
}
