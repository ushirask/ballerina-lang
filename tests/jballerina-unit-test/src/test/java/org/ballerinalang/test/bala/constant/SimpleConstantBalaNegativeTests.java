/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.test.bala.constant;

import org.ballerinalang.test.BAssertUtil;
import org.ballerinalang.test.BCompileUtil;
import org.ballerinalang.test.CompileResult;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Negative test cases for reading constants.
 */
public class SimpleConstantBalaNegativeTests {

    private CompileResult compileResult;

    @BeforeClass
    public void setup() {
        BCompileUtil.compileAndCacheBala("test-src/bala/test_projects/test_project_negative");
        compileResult = BCompileUtil.compile("test-src/bala/test_bala/constant/constant-negative.bal");
    }

    @Test
    public void testNegative() {
        int i = 0;
        int offset = 1;
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'BooleanTypeWithType'," +
                        " found 'boolean'", offset += 7, 29);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'BooleanTypeWithoutType'," +
                        " found 'boolean'", offset += 7, 32);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'IntTypeWithType'," +
                        " found 'int'", offset += 9, 25);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'IntTypeWithoutType'," +
                        " found 'int'", offset += 7, 28);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'ByteTypeWithType'," +
                        " found 'int'", offset += 9, 26);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'FloatTypeWithType'," +
                        " found 'float'", offset += 9, 27);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'FloatTypeWithoutType'," +
                        " found 'float'", offset += 7, 30);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'DecimalTypeWithType'," +
                        " found 'float'", offset += 9, 29);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'StringTypeWithType'," +
                " found 'string'", offset += 9, 28);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'StringTypeWithoutType'," +
                " found 'string'", offset += 7, 31);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '123', found 'int'", offset += 5,
                                  31);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '123', found 'int'", offset += 1,
                                  31);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '-1', found 'int'", offset += 1,
                                  31);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'int', found 'string'",
                                  offset += 10, 9);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'int', found 'string'",
                                  offset += 1, 9);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected 'int', found 'string'",
                                  offset += 1, 9);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '123', found '-1'", offset += 4,
                                  80);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '123', found 'int'", offset, 103);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '123', found '-1'", offset += 1,
                                  80);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '123', found 'int'", offset, 103);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '(123|-1)', found 'int'",
                                  offset += 1, 81);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '-1', found '123'", offset += 1,
                                  34);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '-1', found '123'", offset, 57);
        BAssertUtil.validateError(compileResult, i++, "incompatible types: expected '-1', found 'int'", offset, 107);

        Assert.assertEquals(compileResult.getErrorCount(), i);
    }

    @AfterClass
    public void tearDown() {
        compileResult = null;
    }
}
