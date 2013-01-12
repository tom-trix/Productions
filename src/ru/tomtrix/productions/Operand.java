package ru.tomtrix.productions;

/**
 * hftd
 */
public class Operand implements ICommand
{
    private final Variable _variable;
    private final Inequality _equals;
    private final String _value;
    private final Core _coreRef;

    public Operand(Variable variable, Inequality equals, String value, Core core)
    {
        if (variable == null || value == null || value.trim().isEmpty() || core == null) throw new IllegalArgumentException("Parameters can't be empty");
        _variable = variable;
        _equals = equals;
        _value = value.trim();
        _coreRef = core;
    }

    @Override
    public boolean execute()
    {
        boolean f = _value.equals(_coreRef.getValue(_variable));
        return _equals==Inequality.EQUALS ? f : !f;
    }
}
