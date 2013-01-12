package ru.tomtrix.productions.test;

import org.junit.Test;
import ru.tomtrix.productions.*;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * grd
 */
public class RuleSetMover
{
    @Test
    public void moveRules() throws Exception
    {
        Core core = new Core();
        Condition cond = new Condition(new Operand("A", true, "Tom", core));
        Map<String, String> newValues = new TreeMap<>();
        newValues.put("A", "Trix");
        RuleSet rs = new RuleSet();

        rs.addRule(new Rule("R0", cond, newValues, core));
        rs.addRule(new Rule("R1", cond, newValues, core));
        rs.addRule(new Rule("R2", cond, newValues, core));
        rs.addRule(new Rule("R3", cond, newValues, core));
        rs.addRule(new Rule("R4", cond, newValues, core));
        rs.addRule(new Rule("R5", cond, newValues, core));
        rs.addRule(new Rule("R6", cond, newValues, core));
        rs.addRule(new Rule("R7", cond, newValues, core));
        rs.addRule(new Rule("R8", cond, newValues, core));
        rs.addRule(new Rule("R9", cond, newValues, core));

        assertEquals(rs.toString(), "r0, r1, r2, r3, r4, r5, r6, r7, r8, r9");
        rs.moveRule(3, 8);
        assertEquals(rs.toString(), "r0, r1, r2, r4, r5, r6, r7, r8, r3, r9");
        rs.moveRule(4, 1);
        assertEquals(rs.toString(), "r0, r5, r1, r2, r4, r6, r7, r8, r3, r9");
        rs.moveRule(9, 0);
        assertEquals(rs.toString(), "r9, r0, r5, r1, r2, r4, r6, r7, r8, r3");
        rs.moveRule(0, 6);
        assertEquals(rs.toString(), "r0, r5, r1, r2, r4, r6, r9, r7, r8, r3");
        boolean b = false;
        try
        {
            rs.moveRule(0, 10);
        }
        catch (Exception ignore) {b = true;}
        assertTrue(b);
        rs.deleteRule(3);
        assertEquals(rs.toString(), "r0, r5, r1, r4, r6, r9, r7, r8, r3");
        rs.deleteRuleByName("R7 ");
        assertEquals(rs.toString(), "r0, r5, r1, r4, r6, r9, r8, r3");
        b = false;
        try
        {
            rs.addRule(new Rule("R4", cond, newValues, core));
        }
        catch (Exception ignore) {b = true;}
        assertTrue(b);
    }
}
