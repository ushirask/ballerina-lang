/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.test.types.floattype;

import io.ballerina.runtime.api.values.BArray;
import org.ballerinalang.test.BAssertUtil;
import org.ballerinalang.test.BCompileUtil;
import org.ballerinalang.test.BRunUtil;
import org.ballerinalang.test.CompileResult;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * This test class will test the behaviour of double values with expressions.
 * Addition
 * Multiplication
 * Division
 * Subtraction
 * <p>
 * Defining a double value
 * double b;
 * b = 10.1d;
 */
public class BFloatValueTest {

    private static final double DELTA = 0.01;
    private CompileResult result;
    private CompileResult negativeResult;
    private CompileResult negativeDiscrimination;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        result = BCompileUtil.compile("test-src/types/float/float-value.bal");
        negativeResult = BCompileUtil.compile("test-src/types/float/float-value-negative.bal");
        negativeDiscrimination = BCompileUtil.compile("test-src/types/float/float-value-negative-discrimination.bal");
    }

    @Test(description = "Test double value assignment")
    public void testFloatValue() {
        Object returns = BRunUtil.invoke(result, "testFloatValue", new Object[]{});

        Assert.assertSame(returns.getClass(), Double.class);
        double floatValue = (double) returns;
        Assert.assertEquals(floatValue, 10.1f, DELTA, "Invalid float value returned.");
    }

    @Test(description = "Test negative double value assignment")
    public void testNegativeFloatValue() {
        Object returns = BRunUtil.invoke(result, "testNegativeFloatValue", new Object[]{});

        Assert.assertSame(returns.getClass(), Double.class);
        double floatValue = (double) returns;
        Assert.assertEquals(floatValue, (-10.1f), DELTA, "Invalid float value returned.");
    }

    @Test(description = "Test double value assignment from a value returned by function")
    public void testFloatValueAssignmentByReturnValue() {
        Object returns = BRunUtil.invoke(result, "testFloatValueAssignmentByReturnValue", new Object[]{});

        Assert.assertSame(returns.getClass(), Double.class);
        double floatValue = (double) returns;
        Assert.assertEquals(floatValue, 10.1d, "Invalid float value returned.");
    }

    @Test(description = "Test double value assignment")
    public void testFloatParameter() {
        Object[] args = {(3.3f)};
        Object returns = BRunUtil.invoke(result, "testFloatParameter", args);

        Assert.assertSame(returns.getClass(), Double.class);
        double floatValue = (double) returns;
        Assert.assertEquals(floatValue, 3.3f, DELTA, "Invalid float value returned.");
    }

    @Test(description = "Test double value Addition")
    public void testFloatValueAddition() {
        Object returns = BRunUtil.invoke(result, "testFloatAddition", new Object[]{});

        Assert.assertSame(returns.getClass(), Double.class);
        double floatValue = (double) returns;
        Assert.assertEquals(floatValue, 20.0d, "Invalid float value returned.");
    }

    @Test(description = "Test double value Subtraction")
    public void testFloatValueSubtraction() {
        Object returns = BRunUtil.invoke(result, "testFloatSubtraction", new Object[]{});

        Assert.assertSame(returns.getClass(), Double.class);
        double floatValue = (double) returns;
        Assert.assertEquals(floatValue, 10.0d, "Invalid float value returned.");
    }

    @Test(description = "Test double value Multiplication")
    public void testFloatValueMultiplication() {
        Object returns = BRunUtil.invoke(result, "testFloatMultiplication", new Object[]{});

        Assert.assertSame(returns.getClass(), Double.class);
        double floatValue = (double) returns;
        Assert.assertEquals(floatValue, 13.75d, "Invalid float value returned.");
    }

    @Test(description = "Test double value Division")
    public void testFloatValueDivision() {
        Object returns = BRunUtil.invoke(result, "testFloatDivision", new Object[]{});

        Assert.assertSame(returns.getClass(), Double.class);
        double floatValue = (double) returns;
        Assert.assertEquals(floatValue, 5.0d, "Invalid float value returned.");
    }

    @Test(description = "Test double value Division")
    public void testFloatValues() {
        BArray returns = (BArray) BRunUtil.invoke(result, "testFloatValues");
        Assert.assertEquals(returns.size(), 4);
        Assert.assertEquals(returns.get(0), 123.4, "Invalid float value returned.");
        Assert.assertEquals(returns.get(1), 1.234e2, "Invalid float value returned.");
        Assert.assertEquals(returns.get(2), 123.4d, "Invalid float value returned.");
        Assert.assertEquals(returns.get(3), 1.234e2d, "Invalid float value returned.");
    }

    @Test(description = "Test hexadecimal literal")
    public void testHexFloatingPointLiterals() {
        BArray returns = (BArray) BRunUtil.invoke(result, "testHexFloatingPointLiterals");
        Assert.assertEquals(returns.size(), 4);
        Assert.assertEquals(returns.get(0), 4779.0, "Invalid float value returned.");
        Assert.assertEquals(returns.get(1), 8.0, "Invalid float value returned.");
        Assert.assertEquals(returns.get(2), 5.0, "Invalid float value returned.");
        Assert.assertEquals(returns.get(3), 12.0, "Invalid float value returned.");
    }

    @Test(description = "Test int literal")
    public void testIntLiteralAssignment() {
        BArray returns = (BArray) BRunUtil.invoke(result, "testIntLiteralAssignment");
        Assert.assertEquals(returns.size(), 2);
        Assert.assertEquals(returns.get(0), 12.0, "Invalid float value returned.");
        Assert.assertEquals(returns.get(1), 15.0, "Invalid float value returned.");
    }

    @Test(description = "Test discriminated float literal")
    public void testDiscriminatedFloatLiterals() {
        BArray returns = (BArray) BRunUtil.invoke(result, "testDiscriminatedFloatLiteral");
        Assert.assertEquals(returns.size(), 3);
        Assert.assertEquals(returns.get(0), 1.0, "Invalid float value returned.");
        Assert.assertEquals(returns.get(1), 1.0, "Invalid float value returned.");
        Assert.assertEquals(returns.get(2), 2200.0, "Invalid float value returned.");
    }

    @Test()
    public void testIntegerValue() {
        Assert.assertEquals(negativeResult.getErrorCount(), 17);
        BAssertUtil.validateError(negativeResult, 0, "leading zeros in numeric literals",
                3, 9);
        BAssertUtil.validateError(negativeResult, 1, "'999e9999999999' is out of range " +
                "for 'float'", 8, 15);
        BAssertUtil.validateError(negativeResult, 2, "'999e-9999999999' is out of range " +
                "for 'float'", 9, 15);
        BAssertUtil.validateError(negativeResult, 3, "'999e9999999999' is out of range " +
                "for 'float'", 10, 23);
        BAssertUtil.validateError(negativeResult, 4, "'99.9E99999999' is out of range for " +
                "'float'", 11, 27);
        BAssertUtil.validateError(negativeResult, 5, "'99.9E-99999999' is out of range for" +
                " 'float'", 12, 27);
        BAssertUtil.validateError(negativeResult, 6, "'0x9999999p999999999999999999999999' " +
                "is out of range for 'float'", 15, 10);
        BAssertUtil.validateError(negativeResult, 7, "'0x9999999p-999999999999999999999999' " +
                "is out of range for 'float'", 17, 11);
        BAssertUtil.validateError(negativeResult, 8, "'9999999999e9999999999999999999' " +
                "is out of range for 'float'", 19, 10);
        BAssertUtil.validateError(negativeResult, 9, "'9999999999e-9999999999999999999' " +
                "is out of range for 'float'", 21, 11);
        BAssertUtil.validateError(negativeResult, 10, "'0x999.9p999999999999999' is out " +
                "of range for 'float'", 23, 1);
        BAssertUtil.validateError(negativeResult, 11, "'0x999.9p999999999999999' is out " +
                "of range for 'float'", 23, 29);
        BAssertUtil.validateError(negativeResult, 12, "'9.99E6111' is out of range " +
                "for 'float'", 25, 16);
        BAssertUtil.validateError(negativeResult, 13, "'9.99E+6111' is out of range " +
                "for 'float'", 28, 15);
        BAssertUtil.validateError(negativeResult, 14, "'9.99E6111' is out of range " +
                "for 'float'", 29, 5);
        BAssertUtil.validateError(negativeResult, 15, "'9.99E+6111' is out of range " +
                "for 'float'", 29, 21);
        BAssertUtil.validateError(negativeResult, 16, "'9.99E+6111' is out of range " +
                "for 'float'", 30, 15);
    }

    @Test(description = "Test float literal discrimination error")
    public void testFloatLiteralDiscriminationError() {
        Assert.assertEquals(negativeDiscrimination.getErrorCount(), 1);
        BAssertUtil.validateError(negativeDiscrimination, 0, "incompatible types: expected 'float', found 'decimal'",
                18, 15);
    }

    @Test(description = "Test hexa decimal literal with float type")
    public void testHexaDecimalLiteralsWithFloat() {
        BRunUtil.invoke(result, "testHexaDecimalLiteralsWithFloat");
    }

    @Test
    public void testInvalidValuesWithFloatType() {
        CompileResult result = BCompileUtil.compile("test-src/types/float/float_type_negative.bal");
        int i = 0;
        BAssertUtil.validateError(result, i++, "'0xFFFFFFFFFFFFFFFF' is out of range for 'float'",
                2, 15);
        BAssertUtil.validateError(result, i++, "'0xabc435de769FEAB0' is out of range for 'float'",
                4, 9);
        BAssertUtil.validateError(result, i++, "'0xaaaaaaaaaaaaaaa0' is out of range for 'float'",
                6, 9);
        BAssertUtil.validateError(result, i++, "'0xAAAAAAAAAAAAAAA0' is out of range for 'float'",
                8, 9);
        BAssertUtil.validateError(result, i++, "'0xFFFFFFFFFFFFFFFF' is out of range for 'float'",
                10, 23);
        BAssertUtil.validateError(result, i++, "'0xabc435de769FEAB0' is out of range for 'float'",
                12, 9);
        BAssertUtil.validateError(result, i++, "'0xaaaaaaaaaaaaaaa0' is out of range for 'float'",
                14, 9);
        BAssertUtil.validateError(result, i++, "'0xAAAAAAAAAAAAAAA0' is out of range for 'float'",
                16, 9);
        BAssertUtil.validateError(result, i++, "'0xFFFFFFFFFFFFFFFF' is out of range for 'float'",
                19, 12);
        BAssertUtil.validateError(result, i++, "'0xabc435de769FEAB0' is out of range for 'float'",
                21, 12);
        BAssertUtil.validateError(result, i++, "'0xaaaaaaaaaaaaaaa0' is out of range for 'float'",
                23, 12);
        BAssertUtil.validateError(result, i++, "'0xAAAAAAAAAAAAAAA0' is out of range for 'float'",
                25, 12);
        BAssertUtil.validateError(result, i++, "'0xFFFFFFFFFFFFFFFF' is out of range for 'float'",
                27, 20);
        BAssertUtil.validateError(result, i++, "'0xabc435de769FEAB0' is out of range for 'float'",
                29, 20);
        BAssertUtil.validateError(result, i++, "'0xaaaaaaaaaaaaaaa0' is out of range for 'float'",
                31, 20);
        BAssertUtil.validateError(result, i++, "'0xAAAAAAAAAAAAAAA0' is out of range for 'float'",
                33, 20);
        BAssertUtil.validateError(result, i++, "'0xFFFFFFFFFFFFFFFF' is out of range for 'float'",
                37, 16);
        BAssertUtil.validateError(result, i++, "'0xFFFFFFFFFFFFFFFF' is out of range for 'float'",
                41, 17);
        BAssertUtil.validateError(result, i++, "'0xFFFFFFFFFFFFFFFF' is out of range for 'float'",
                44, 20);
        BAssertUtil.validateError(result, i++, "'0Xffffffffffffffff' is out of range for 'float'",
                45, 20);
        BAssertUtil.validateError(result, i++, "'0XFFFFFFFFFFFFFFFF' is out of range for 'float'",
                46, 21);
        BAssertUtil.validateError(result, i++, "'0x' is out of range for 'float'", 48,
                16);
        BAssertUtil.validateError(result, i++, "missing hex number after hex indicator",
                48, 16);
        BAssertUtil.validateError(result, i++, "'0X' is out of range for 'float'",
                49, 10);
        BAssertUtil.validateError(result, i++, "missing hex number after hex indicator",
                49, 10);
        BAssertUtil.validateError(result, i++, "'0x' is out of range for 'float'",
                51, 20);
        BAssertUtil.validateError(result, i++, "missing hex number after hex indicator",
                51, 20);
        BAssertUtil.validateError(result, i++, "'0x' is out of range for 'float'",
                53, 24);
        BAssertUtil.validateError(result, i++, "missing hex number after hex indicator",
                53, 24);
        BAssertUtil.validateError(result, i++, "'0X' is out of range for 'float'",
                54, 10);
        BAssertUtil.validateError(result, i++, "missing hex number after hex indicator",
                54, 10);
        BAssertUtil.validateError(result, i++, "'0x' is out of range for 'float'",
                56, 21);
        BAssertUtil.validateError(result, i++, "missing hex number after hex indicator",
                56, 21);
        Assert.assertEquals(result.getErrorCount(), i);
    }

    @Test
    public void testOutOfRangeIntWithFloat() {
        BRunUtil.invoke(result, "testOutOfRangeIntWithFloat");
    }

    @Test
    public void testFloatValuesWithSyntaxErrors() {
        CompileResult result = BCompileUtil.compile("test-src/types/float/float_values_with_syntax_errors.bal");
        int i = 0;
        BAssertUtil.validateError(result, i++, "'0x' is out of range for 'float'", 18, 23);
        BAssertUtil.validateError(result, i++, "missing hex number after hex indicator", 18, 23);
        BAssertUtil.validateError(result, i++, "missing digit after exponent indicator", 18, 26);
        BAssertUtil.validateError(result, i++, "missing semicolon token", 18, 26);
        BAssertUtil.validateError(result, i++, "missing digit after exponent indicator", 18, 31);
        BAssertUtil.validateError(result, i++, "missing equal token", 18, 31);
        BAssertUtil.validateError(result, i++, "missing semicolon token", 18, 34);
        BAssertUtil.validateError(result, i++, "unknown type 'b2'", 18, 34);
        BAssertUtil.validateError(result, i++, "'0x' is out of range for 'float'", 19, 9);
        BAssertUtil.validateError(result, i++, "missing hex number after hex indicator", 19, 9);
        BAssertUtil.validateError(result, i++, "missing digit after exponent indicator", 19, 12);
        BAssertUtil.validateError(result, i++, "missing semicolon token", 19, 12);
        BAssertUtil.validateError(result, i++, "redeclared symbol 'A3'", 19, 14);
        BAssertUtil.validateError(result, i++, "missing digit after exponent indicator", 19, 16);
        BAssertUtil.validateError(result, i++, "missing equal token", 19, 16);
        BAssertUtil.validateError(result, i++, "invalid expression statement", 19, 19);
        BAssertUtil.validateError(result, i++, "missing semicolon token", 19, 19);
        BAssertUtil.validateError(result, i++, "undefined symbol 'b2p'", 19, 19);
        BAssertUtil.validateError(result, i++, "'0x' is out of range for 'float'", 20, 9);
        BAssertUtil.validateError(result, i++, "missing hex number after hex indicator", 20, 9);
        BAssertUtil.validateError(result, i++, "invalid token ':'", 20, 11);
        BAssertUtil.validateError(result, i++, "missing digit after exponent indicator", 20, 12);
        BAssertUtil.validateError(result, i++, "missing semicolon token", 20, 12);
        BAssertUtil.validateError(result, i++, "incompatible types: expected '4Ef', found '-45.0f'", 20, 17);
        BAssertUtil.validateError(result, i++, "missing equal token", 20, 17);
        BAssertUtil.validateError(result, i++, "'0x' is out of range for 'float'", 21, 9);
        BAssertUtil.validateError(result, i++, "missing hex number after hex indicator", 21, 9);
        BAssertUtil.validateError(result, i++, "invalid token ','", 21, 11);
        BAssertUtil.validateError(result, i++, "missing digit after exponent indicator", 21, 12);
        BAssertUtil.validateError(result, i++, "missing semicolon token", 21, 12);
        BAssertUtil.validateError(result, i++, "redeclared symbol 'b2P'", 21, 14);
        BAssertUtil.validateError(result, i++, "missing equal token", 21, 17);
        Assert.assertEquals(result.getErrorCount(), i);
    }

    @AfterClass
    public void tearDown() {
        result = null;
        negativeResult = null;
        negativeDiscrimination = null;
    }
}
