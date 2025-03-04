/*
 *   Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ballerinalang.test.expressions.access;

import org.ballerinalang.test.BCompileUtil;
import org.ballerinalang.test.BRunUtil;
import org.ballerinalang.test.CompileResult;
import org.ballerinalang.test.exceptions.BLangTestException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.ballerinalang.test.BAssertUtil.validateError;

/**
 * Test cases for member access.
 *
 * @since 1.0
 */
public class MemberAccessTest {

    private CompileResult result;
    private CompileResult negativeResult;

    @BeforeClass
    public void setup() {
        result = BCompileUtil.compile("test-src/expressions/access/member_access.bal");
        negativeResult = BCompileUtil.compile("test-src/expressions/access/member_access_negative.bal");
    }

    @Test
    public void testNegativeCases() {
        int i = 0;
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'", 33, 12);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'", 34, 12);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'ALL_STRINGS'", 35, 12);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'", 37, 12);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'", 38, 12);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'ALL_STRINGS'", 39, 12);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'", 41, 12);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'", 42, 12);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'ALL_STRINGS'", 43, 12);
        validateError(negativeResult, i++, "invalid operation: type 'int[]?' does not support member access", 53, 9);
        validateError(negativeResult, i++, "invalid operation: type 'Employee[3]?' does not support " +
                "member access", 54, 9);
        validateError(negativeResult, i++, "invalid operation: type '(int[]|Employee[3])?' does not " +
                        "support member access", 55, 9);
        validateError(negativeResult, i++, "list index out of range: index: '4'", 60, 12);
        validateError(negativeResult, i++, "list index out of range: index: '5'", 65, 12);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'boolean?'", 74, 19);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'int?'", 75, 14);
        validateError(negativeResult, i++, "undefined field 'names' in 'Employee'", 76, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found '(string|boolean|int)?'", 77
                , 17);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found '(int|string)?'", 86, 19);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found '(int|string)?'", 87, 14);
        validateError(negativeResult, i++, "incompatible types: expected 'string?', found '(int|string)?'", 88, 18);
        validateError(negativeResult, i++, "incompatible types: expected '(int|string)', found '(int|string)?'", 89,
                      21);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'string?'", 95, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'string?'", 96, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'map<int>', found 'map<int>?'", 99, 20);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'int?'", 100, 15);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'int?'", 101, 15);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'string?'", 104, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found '(string|boolean|int)?'",
                      105, 18);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'", 124, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found '" +
                "(string|int|anydata|boolean|float)'", 125, 14);
        validateError(negativeResult, i++, "incompatible types: expected '(int|boolean)', found '(int|boolean)?'",
                      126, 21);
        validateError(negativeResult, i++, "incompatible types: expected 'float', found '(anydata|float)'", 127, 15);
        validateError(negativeResult, i++, "invalid operation: type '(string|int|anydata|boolean|float)' does not " +
                "support member access", 128, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found '(string|map<float>|xml)?'",
                      136, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found '(string|map<float>|xml)?'",
                      137, 17);
        validateError(negativeResult, i++, "invalid operation: type '(string|map<float>|xml)?' does not support " +
                "member access", 138, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found '(string|int|map<float>)?'",
                      146, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found '" +
                "(string|int|anydata|map<float>)'", 147, 14);
        validateError(negativeResult, i++, "incompatible types: expected 'map<float>?', found '(int|map<float>)?'",
                      148, 21);
        validateError(negativeResult, i++, "invalid operation: type '(anydata|int|map<float>)' does not support " +
                "member access", 149, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string:Char'", 156, 15);
        validateError(negativeResult, i++, "incompatible types: expected 'float', found 'string:Char'", 157, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'", 158, 21);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'", 159, 21);
        validateError(negativeResult, i++, "invalid operation: type 'StrOrInt' does not support member access",
                169, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'StrOrInt'", 170, 20);
        validateError(negativeResult, i++, "invalid operation: type 'string' does not support member access for " +
                "assignment", 175, 5);
        validateError(negativeResult, i++, "list index out of range: index: '5'", 182, 12);
        validateError(negativeResult, i++, "list index out of range: index: '5'", 187, 12);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'", 195, 14);
        validateError(negativeResult, i++, "undefined field 'age' in 'Employee'", 196, 14);
        validateError(negativeResult, i++, "missing key expr in member access expr", 201, 26);
        validateError(negativeResult, i++, "invalid expression statement", 202, 5);
        validateError(negativeResult, i++, "missing key expr in member access expr", 202, 14);
        validateError(negativeResult, i++, "missing semicolon token", 203, 1);
        validateError(negativeResult, i++, "invalid operation: type '((Grault|int[]) & readonly)?' does " +
                "not support member access", 222, 14);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int?'", 225, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'byte', found 'int'", 231, 14);
        validateError(negativeResult, i++, "incompatible types: expected 'int:Signed32', found 'int'", 234, 22);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'int?'", 244, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                258, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'",
                259, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found " +
                "'(int|string|boolean)'", 261, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found " +
                "'(int|string|boolean)'", 262, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                265, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'",
                268, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found " +
                "'(int|string|boolean)'", 271, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found " +
                "'(int|string|boolean)'", 274, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                281, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'int'",
                282, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'boolean'",
                283, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                285, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found " +
                "'(int|string|boolean)'", 286, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found " +
                "'(int|string|boolean)'", 287, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                290, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'int'",
                293, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'boolean'",
                296, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                299, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found " +
                "'(int|string|boolean)'", 302, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found " +
                "'(int|string|boolean)'", 305, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                317, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'",
                318, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                319, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'int'",
                320, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'boolean'",
                321, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                322, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'int'",
                323, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                324, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'int'",
                325, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found " +
                "'(int|string|boolean)'", 327, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found " +
                "'(int|string|boolean)'", 328, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                329, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found " +
                "'(int|string|boolean)'", 330, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found " +
                "'(int|string|boolean)'", 331, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                332, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'int'",
                333, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                334, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'int'",
                335, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                338, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'string'",
                341, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                344, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'int'",
                347, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found 'boolean'",
                350, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found " +
                "'(int|string|boolean)'", 353, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found " +
                "'(int|string|boolean)'", 356, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                359, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found " +
                "'(int|string|boolean)'", 362, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'int', found " +
                "'(int|string|boolean)'", 365, 13);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                368, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'int'",
                371, 17);
        validateError(negativeResult, i++, "incompatible types: expected 'string', found 'int'",
                374, 16);
        validateError(negativeResult, i++, "incompatible types: expected 'boolean', found 'int'",
                377, 17);
        Assert.assertEquals(negativeResult.getErrorCount(), i);
    }

    @Test(dataProvider = "listMemberAccessFunctions")
    public void testListMemberAccess(String function) {
        Object returns = BRunUtil.invoke(result, function);
        Assert.assertTrue((Boolean) returns);
    }

    @DataProvider(name = "listMemberAccessFunctions")
    public Object[][] listMemberAccessFunctions() {
        return new Object[][] {
            { "testOpenArrayMemberAccessByLiteralPositive" },
            { "testOpenArrayMemberAccessByConstPositive" },
            { "testOpenArrayMemberAccessByVariablePositive" },
            { "testOpenArrayMemberAccessByFiniteTypeVariablePositive" },
            { "testClosedArrayMemberAccessByLiteralPositive" },
            { "testClosedArrayMemberAccessByConstPositive" },
            { "testClosedArrayMemberAccessByVariablePositive" },
            { "testClosedArrayMemberAccessByFiniteTypeVariablePositive" },
            { "testTupleMemberAccessByLiteralPositive" },
            { "testTupleMemberAccessByConstPositive" },
            { "testTupleMemberAccessByVariablePositive" },
            { "testTupleMemberAccessByFiniteTypeVariablePositive" }
        };
    }

    @Test(dataProvider = "listMemberAccessTestFunctions")
    public void testListMemberAccess2(String function) {
        BRunUtil.invoke(result, function);
    }

    @DataProvider
    public Object[][] listMemberAccessTestFunctions() {
        return new Object[][] {
                { "testValidArrayMemberAccessWithBuiltInIntSubTypeKeyExpr" },
                { "testOutOfRangeArrayMemberAccessWithBuiltInIntSubTypeKeyExpr" },
                { "testValidTupleMemberAccessWithBuiltInIntSubTypeKeyExpr" },
                { "testOutOfRangeTupleMemberAccessWithBuiltInIntSubTypeKeyExpr" }
        };
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.array\\}IndexOutOfRange \\{\"message\":\"array " +
                    "index out of range: index: 4, size: 3\"\\}.*")
    public void testOpenArrayMemberAccessByLiteralIndexOutOfRange() {
        BRunUtil.invoke(result, "testOpenArrayMemberAccessByLiteralIndexOutOfRange");
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.array\\}IndexOutOfRange \\{\"message\":\"array " +
                    "index out of range: index: 6, size: 2\"\\}.*")
    public void testOpenArrayMemberAccessByConstIndexOutOfRange() {
        BRunUtil.invoke(result, "testOpenArrayMemberAccessByConstIndexOutOfRange");
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.array\\}IndexOutOfRange \\{\"message\":\"array " +
                    "index out of range: index: 5, size: 2\"\\}.*")
    public void testOpenArrayMemberAccessByVariableIndexOutOfRange() {
        BRunUtil.invoke(result, "testOpenArrayMemberAccessByVariableIndexOutOfRange");
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.array\\}IndexOutOfRange \\{\"message\":\"array " +
                    "index out of range: index: 9, size: 2\"\\}.*")
    public void testOpenArrayMemberAccessByFiniteTypeVariableIndexOutOfRange() {
        BRunUtil.invoke(result, "testOpenArrayMemberAccessByFiniteTypeVariableIndexOutOfRange");
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.array\\}IndexOutOfRange \\{\"message\":\"array " +
                    "index out of range: index: 5, size: 2\"\\}.*")
    public void testClosedArrayMemberAccessByVariableIndexOutOfRange() {
        BRunUtil.invoke(result, "testClosedArrayMemberAccessByVariableIndexOutOfRange");
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.array\\}IndexOutOfRange \\{\"message\":\"array " +
                    "index out of range: index: 9, size: 4\"\\}.*")
    public void testClosedArrayMemberAccessByFiniteTypeVariableIndexOutOfRange() {
        BRunUtil.invoke(result, "testClosedArrayMemberAccessByFiniteTypeVariableIndexOutOfRange");
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.array\\}IndexOutOfRange \\{\"message\":\"tuple " +
                    "index out of range: index: 3, size: 2\"\\}.*")
    public void testTupleMemberAccessByVariableIndexOutOfRange() {
        BRunUtil.invoke(result, "testTupleMemberAccessByVariableIndexOutOfRange");
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.array\\}IndexOutOfRange \\{\"message\":\"tuple " +
                    "index out of range: index: 9, size: 3\"\\}.*")
    public void testTupleMemberAccessByFiniteTypeVariableIndexOutOfRange() {
        BRunUtil.invoke(result, "testTupleMemberAccessByFiniteTypeVariableIndexOutOfRange");
    }

    @Test(dataProvider = "recordMemberAccessFunctions")
    public void testRecordMemberAccess(String function) {
        Object returns = BRunUtil.invoke(result, function);
        Assert.assertTrue((Boolean) returns);
    }

    @DataProvider(name = "recordMemberAccessFunctions")
    public Object[][] recordMemberAccessFunctions() {
        return new Object[][] {
            { "testRecordMemberAccessByLiteral" },
            { "testRecordMemberAccessByConstant" },
            { "testRecordMemberAccessByVariable" },
            { "testRecordMemberAccessForNonExistingKey" },
            { "testRestFieldAccessOnNilableRecordUnion" },
            { "testAccessOnNilableMapUnion" },
            { "testAccessOnNilableRecordMapUnion" },
            { "testNestedAccessOnNilableUnion" }
        };
    }

    @Test(dataProvider = "recordMemberAccessTestFunctions")
    public void testRecordMemberAccess2(String function) {
        BRunUtil.invoke(result, function);
    }

    @DataProvider
    public Object[][] recordMemberAccessTestFunctions() {
        return new Object[][] {
                { "testValidRecordMemberAccessWithStringCharKeyExpr" },
                { "testUnspecifiedFieldRecordMemberAccessWithStringCharKeyExpr" }
        };
    }

    @Test(dataProvider = "mapMemberAccessFunctions")
    public void testMapMemberAccess(String function) {
        Object returns = BRunUtil.invoke(result, function);
        Assert.assertTrue((Boolean) returns);
    }

    @DataProvider(name = "mapMemberAccessFunctions")
    public Object[][] mapMemberAccessFunctions() {
        return new Object[][] {
            { "testMapMemberAccessByLiteral" },
            { "testMapMemberAccessByConstant" },
            { "testMapMemberAccessByVariable" },
            { "testMapAccessForNonExistingKey" }
        };
    }

    @Test(dataProvider = "optionalMappingMemberAccessFunctions")
    public void testOptionalMappingMemberAccess(String function) {
        Object returns = BRunUtil.invoke(result, function);
        Assert.assertTrue((Boolean) returns);
    }

    @DataProvider(name = "optionalMappingMemberAccessFunctions")
    public Object[][] optionalMappingMemberAccessFunctions() {
        return new Object[][] {
            { "testMemberAccessOnNillableMap1" },
            { "testMemberAccessOnNillableMap2" },
            { "testMemberAccessNilLiftingOnNillableMap1" },
            { "testMemberAccessNilLiftingOnNillableMap2" },
            { "testMemberAccessOnNillableRecord1" },
            { "testMemberAccessOnNillableRecord2" },
            { "testMemberAccessNilLiftingOnNillableRecord1" },
            { "testMemberAccessNilLiftingOnNillableRecord2" },
        };
    }

    @Test(dataProvider = "optionalMappingMemberAccessFunctions2")
    public void testOptionalMappingMemberAccess2(String function) {
        BRunUtil.invoke(result, function);
    }

    @DataProvider(name = "optionalMappingMemberAccessFunctions2")
    public Object[][] optionalMappingMemberAccessFunctions2() {
        return new Object[][] {
                { "testUnavailableFinalAccessInNestedAccess" },
                { "testAvailableFinalAccessInNestedAccess" },
                { "testUnavailableIntermediateAccessInNestedAccess" },
                { "testNilValuedFinalAccessInNestedAccess" }
        };
    }

    @Test(dataProvider = "mappingUnionMemberAccessFunctions")
    public void testMappingUnionMemberAccess(String function) {
        Object returns = BRunUtil.invoke(result, function);
        Assert.assertTrue((Boolean) returns);
    }

    @DataProvider(name = "mappingUnionMemberAccessFunctions")
    public Object[][] mappingUnionMemberAccessFunctions() {
        return new Object[][] {
            { "testMemberAccessOnRecordUnion" },
            { "testMemberAccessOnMapUnion" }
        };
    }

    @Test(dataProvider = "stringMemberAccessFunctions")
    public void testStringMemberAccess(String function) {
        Object returns = BRunUtil.invoke(result, function);
        Assert.assertTrue((Boolean) returns);
    }

    @DataProvider(name = "stringMemberAccessFunctions")
    public Object[][] stringMemberAccessFunctions() {
        return new Object[][] {
                { "testVariableStringMemberAccess" },
                { "testConstStringMemberAccess1" },
                { "testConstStringMemberAccess2" },
                { "testFiniteTypeStringMemberAccess" }
        };
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.string\\}IndexOutOfRange \\{\"message\":\"string "
                    + "index out of range: index: -1, size: 5\"\\}.*")
    public void testOutOfRangeStringMemberAccess1() {
        Object returns = BRunUtil.invoke(result, "testOutOfRangeStringMemberAccess1");
        Assert.assertTrue((Boolean) returns);
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.string\\}IndexOutOfRange \\{\"message\":\"string "
                    + "index out of range: index: 11, size: 11\"\\}.*")
    public void testOutOfRangeStringMemberAccess2() {
        Object returns = BRunUtil.invoke(result, "testOutOfRangeStringMemberAccess2");
        Assert.assertTrue((Boolean) returns);
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.string\\}IndexOutOfRange \\{\"message\":\"string "
                    + "index out of range: index: 25, size: 12\"\\}.*")
    public void testOutOfRangeStringMemberAccess3() {
        Object returns = BRunUtil.invoke(result, "testOutOfRangeStringMemberAccess3");
        Assert.assertTrue((Boolean) returns);
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.string\\}IndexOutOfRange \\{\"message\":\"string "
                    + "index out of range: index: 4, size: 3\"\\}.*")
    public void testOutOfRangeFiniteTypeStringMemberAccess() {
        Object returns = BRunUtil.invoke(result, "testOutOfRangeFiniteTypeStringMemberAccess");
        Assert.assertTrue((Boolean) returns);
    }

    @Test
    public void testMemberAccessInUnionType() {
        BRunUtil.invoke(result, "testMemberAccessInUnionType");
    }

    @Test
    public void testMemberAccessOnStructuralConstructs() {
        BRunUtil.invoke(result, "testMemberAccessOnStructuralConstructs");
    }

    @Test
    public void testMemberAccessOnStrings() {
        BRunUtil.invoke(result, "testMemberAccessOnStrings");
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.string\\}IndexOutOfRange \\{\"message\":\"string "
                    + "index out of range: index: 5, size: 3\"\\}.*")
    public void testInvalidMemberAccessOnStrings1() {
        BRunUtil.invoke(result, "testInvalidMemberAccessOnStrings1");
    }

    @Test(expectedExceptions = BLangTestException.class,
            expectedExceptionsMessageRegExp = ".*\\{ballerina/lang.string\\}IndexOutOfRange \\{\"message\":\"string "
                    + "index out of range: index: 3, size: 1\"\\}.*")
    public void testInvalidMemberAccessOnStrings2() {
        BRunUtil.invoke(result, "testInvalidMemberAccessOnStrings2");
    }

    @Test(dataProvider = "memberAccessWithBinaryExprAsIndex")
    public void testMemberAccessWithBinaryExprAsIndex(String functionName) {
        BRunUtil.invoke(result, functionName);
    }

    @DataProvider(name = "memberAccessWithBinaryExprAsIndex")
    public Object[][] memberAccessWithBinaryExprAsIndex() {
        return new Object[][] {
                { "testMemberAccessWithBinaryExprAsIndex" },
                { "testMemberAccessWithGroupExprAsIndex" },
                { "testMemberAccessOutOfRangeWithBinaryExpr1" },
                { "testMemberAccessOutOfRangeWithBinaryExpr2" },
                { "testMemberAccessOutOfRangeWithBinaryExpr3" },
                { "testMemberAccessOutOfRangeWithBinaryExpr4" }
        };
    }

    @Test
    public void testMemberAccessInInferTypeCtxWithTypeRef() {
        BRunUtil.invoke(result, "testMemberAccessInInferTypeCtxWithTypeRef");
    }
    
    @Test
    public void testNestedMemberAccessOnIntersectionTypes() {
        BRunUtil.invoke(result, "testNestedMemberAccessOnIntersectionTypes");
    }

    @AfterClass
    public void tearDown() {
        result = null;
        negativeResult = null;
    }
}
