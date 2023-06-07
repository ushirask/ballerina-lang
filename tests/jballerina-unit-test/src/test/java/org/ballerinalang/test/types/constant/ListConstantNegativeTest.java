package org.ballerinalang.test.types.constant;

import org.ballerinalang.test.BCompileUtil;
import org.ballerinalang.test.CompileResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.ballerinalang.test.BAssertUtil.validateError;

/**
 * Negative tests for list constructor expr in constant context.
 */
public class ListConstantNegativeTest {

    @Test
    public void testListConstructorExprAsConstantExprNegative() {
        CompileResult compileResult = BCompileUtil.compile("test-src/types/constant/list_constant_negative.bal");

        int i = 0;
        validateError(compileResult, i++, "incompatible types: expected 'string', found '3'", 17, 29);
        validateError(compileResult, i++, "incompatible types: expected 'string', found '1'", 17, 32);
        validateError(compileResult, i++, "size mismatch in closed array. expected '1', but found '2'", 18, 25);
        validateError(compileResult, i++, "operator '+' not defined for '2.0f' and '1d'", 19, 40);
        validateError(compileResult, i++, "expression is not a constant expression", 20, 31);
        validateError(compileResult, i++, "incompatible types: expected 'float', found '1'", 27, 32);
        validateError(compileResult, i++, "incompatible types: expected 'float', found '3'", 27, 32);
        validateError(compileResult, i++, "tuple and expression size does not match", 29, 33);
        validateError(compileResult, i++, "invalid usage of list constructor: type 'record {| int a; 2 b; |}'" +
                " does not have a filler value", 30, 41);
        validateError(compileResult, i++, "invalid usage of list constructor: type '1|2' does not have a filler value",
                31, 26);
        validateError(compileResult, i++, "ambiguous type '(int[2]|[int,int])'", 32, 38);
        Assert.assertEquals(compileResult.getErrorCount(), i);
    }

}
