package ru.tomtrix.productions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * dsrgr
 */
public class Condition implements ICommand
{
    private final List<Operand> _operands = Collections.synchronizedList(new ArrayList<Operand>());
    private final List<Operation> _operations = Collections.synchronizedList(new ArrayList<Operation>());

    public Condition(Operand firstOperand)
    {
        _operands.add(firstOperand);
    }

    public void addOperand(Operation operation, Operand operand) throws NullPointerException
    {
        if (operation == null || operand == null) throw new NullPointerException("fs");
        _operations.add(operation);
        _operands.add(operand);
    }

    private boolean calculate(boolean a, Operation operation, boolean b) throws EnumConstantNotPresentException
    {
        switch (operation)
        {
            case AND: return a && b;
            case OR: return a || b;
            default:throw new IllegalStateException("gr");
        }
    }

    @Override
    public boolean execute()
    {
        boolean result = _operands.get(0).execute();
        for (int i=1; i<_operands.size(); i++)
        {
            Operation operation = _operations.get(i-1);
            boolean nextOperand = _operands.get(i).execute();
            result = calculate(result, operation, nextOperand);
        }
        return result;
    }
}
