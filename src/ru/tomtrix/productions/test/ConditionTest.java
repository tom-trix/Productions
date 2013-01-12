package ru.tomtrix.productions.test;

import org.junit.Test;
import ru.tomtrix.productions.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * grd
 */
public class ConditionTest
{
    @Test
    public void testConditions()
    {
        Core core = new Core() {
            @Override
            public String askForVariable() {return null;}
        };
        Variable A = new Variable("A", VariableType.INFERRIBLE);
        Variable B = new Variable("B", VariableType.INFERRIBLE);
        Variable C = new Variable("C", VariableType.INFERRIBLE);
        core.addVariable(A);
        core.addVariable(B);
        core.addVariable(C);
        Operand a = new Operand(A, true, "Tom", core);
        Operand b = new Operand(B, true, "Trix", core);
        Operand c = new Operand(C, true, "Rem", core);

        // A
        Condition cond = new Condition(a);
        core.setVariable(A, "Tom");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        assertFalse(cond.execute());

        // A && B
        cond = new Condition(a);
        cond.addOperand(Operation.AND, b);
        core.setVariable(A, "Tom");
        core.setVariable(B, "Trix");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Trix");
        assertFalse(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Srix");
        assertFalse(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Srix");
        assertFalse(cond.execute());

        // A || B
        cond = new Condition(a);
        cond.addOperand(Operation.OR, b);
        core.setVariable(A, "Tom");
        core.setVariable(B, "Trix");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Trix");
        assertTrue(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Srix");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Srix");
        assertFalse(cond.execute());

        // A && B && C
        cond = new Condition(a);
        cond.addOperand(Operation.AND, b);
        cond.addOperand(Operation.AND, c);
        core.setVariable(A, "Tom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Rem");
        assertFalse(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Rem");
        assertFalse(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Rem");
        assertFalse(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());

        // A || B || C
        cond = new Condition(a);
        cond.addOperand(Operation.OR, b);
        cond.addOperand(Operation.OR, c);
        core.setVariable(A, "Tom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Gem");
        assertTrue(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Gem");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Gem");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());

        // A && B || C
        cond = new Condition(a);
        cond.addOperand(Operation.AND, b);
        cond.addOperand(Operation.OR, c);
        core.setVariable(A, "Tom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Gem");
        assertTrue(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());

        // A || B && C
        cond = new Condition(a);
        cond.addOperand(Operation.OR, b);
        cond.addOperand(Operation.AND, c);
        core.setVariable(A, "Tom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());
        core.setVariable(A, "Tom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Rem");
        assertTrue(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Rem");
        assertFalse(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Trix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());
        core.setVariable(A, "Rom");
        core.setVariable(B, "Srix");
        core.setVariable(C, "Gem");
        assertFalse(cond.execute());
    }
}
