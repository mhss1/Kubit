#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonTest/kotlin/kubit/collections/list/ext/GenericListExtTest.kt"

rm -f "$targetFile"
mkdir -p "$(dirname "$targetFile")"
touch "$targetFile"

cat >> "$targetFile" << EOF
package kubit.collections.list.ext

import kotlin.test.*
import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

@Suppress("USELESS_IS_CHECK")
class GenericListExtTest {
EOF

for primitive in "${primitives[@]}"; do
    # value/nullable suffix logic
    value1="1"
    value2="2"
    value3="3"
    value4="4"
    value5="5"
    suffix=""
    [ "$primitive" = "Long" ] && suffix="L"
    [ "$primitive" = "Float" ] && suffix="f"
    [ "$primitive" = "Double" ] && suffix=".0"
    [ "$primitive" = "Long" ] && value1="1L" value2="2L" value3="3L" value4="4L" value5="5L"
    [ "$primitive" = "Float" ] && value1="1f" value2="2f" value3="3f" value4="4f" value5="5f"
    [ "$primitive" = "Double" ] && value1="1.0" value2="2.0" value3="3.0" value4="4.0" value5="5.0"

cat >> "$targetFile" << EOF

    @Test
    fun \`filter list of $primitive to ${primitive}List\`() {
        val list = listOf($value1, $value2, $value3, $value4, $value5)
        val result = list.filter { it > $value3 }
        assertEquals(2, result.size)
        assertEquals($value4, result[0])
        assertEquals($value5, result[1])
        assertTrue(result is ${primitive}List)
    }

    @Test
    fun \`filterNot list of $primitive to ${primitive}List\`() {
        val list = listOf($value1, $value2, $value3, $value4, $value5)
        val result = list.filterNot { it > $value3 }
        assertEquals(3, result.size)
        assertEquals($value1, result[0])
        assertEquals($value2, result[1])
        assertEquals($value3, result[2])
        assertTrue(result is ${primitive}List)
    }

    @Test
    fun \`filterNotNull list of $primitive to ${primitive}List\`() {
        val list = listOf($value1, null, $value3, null, $value5)
        val result = list.filterNotNull()
        assertEquals(3, result.size)
        assertEquals($value1, result[0])
        assertEquals($value3, result[1])
        assertEquals($value5, result[2])
        assertTrue(result is ${primitive}List)
    }
EOF
done

# Add atLeast and atMost tests, using Int for simplicity
cat >> "$targetFile" << EOF

    @Test
    fun \`atLeast returns true when count greater than or equal to n\`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.atLeast(2) { it > 3 }
        assertTrue(result)
    }

    @Test
    fun \`atLeast returns false when count less than n\`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.atLeast(3) { it > 3 }
        assertEquals(false, result)
    }

    @Test
    fun \`atLeast with n equals 0 returns true\`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.atLeast(0) { it > 100 }
        assertTrue(result)
    }

    @Test
    fun \`atMost returns true when count less than or equal to n\`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.atMost(2) { it > 3 }
        assertTrue(result)
    }

    @Test
    fun \`atMost returns false when count greater than n\`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.atMost(1) { it > 3 }
        assertEquals(false, result)
    }

    @Test
    fun \`atMost with n equals 0 and no matches returns true\`() {
        val list = listOf(1, 2, 3)
        val result = list.atMost(0) { it > 10 }
        assertTrue(result)
    }

    @Test
    fun \`atMost with n equals 0 and matches returns false\`() {
        val list = listOf(1, 2, 3)
        val result = list.atMost(0) { it > 1 }
        assertEquals(false, result)
    }

    @Test
    fun \`atLeast throws IllegalArgumentException when n less than 0\`() {
        val list = listOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            list.atLeast(-1) { it > 3 }
        }
    }

    @Test
    fun \`atMost throws IllegalArgumentException when n less than 0\`() {
        val list = listOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            list.atMost(-1) { it > 3 }
        }
    }
EOF

echo '}' >> "$targetFile"

echo "Generated generic filter/atLeast/atMost test functions successfully"
