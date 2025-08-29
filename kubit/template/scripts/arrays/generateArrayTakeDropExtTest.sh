#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonTest/kotlin/kubit/collections/array/ArrayTakeDropExtTest.kt"

# Remove any old file and create a new empty file
rm -f "$targetFile"
mkdir -p "$(dirname "$targetFile")"
touch "$targetFile"

# Write package header and imports
cat >> "$targetFile" << EOF
package kubit.collections.array

import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class ArrayTakeDropExtTest {
EOF

# Generate tests for each primitive type
for primitive in "${primitives[@]}"; do
    suffix=""
    [ "$primitive" = "Long" ] && suffix="L"
    [ "$primitive" = "Float" ] && suffix="f"
    [ "$primitive" = "Double" ] && suffix=".0"

    cat >> "$targetFile" << EOF

    @Test
    fun test${primitive}ArrayTake() {
        val array = ${primitive}Array(5) { (it + 1${suffix}) }

        val result1 = array.take(3)
        assertEquals(3, result1.size)
        assertEquals(1${suffix}, result1[0])
        assertEquals(2${suffix}, result1[1])
        assertEquals(3${suffix}, result1[2])

        val result2 = array.take(0)
        assertEquals(0, result2.size)

        val result3 = array.take(10)
        assertEquals(5, result3.size)

        assertFailsWith<IllegalArgumentException> { array.take(-1) }
    }

    @Test
    fun test${primitive}ArrayTakeLast() {
        val array = ${primitive}Array(5) { (it + 1${suffix}) }

        val result1 = array.takeLast(3)
        assertEquals(3, result1.size)
        assertEquals(3${suffix}, result1[0])
        assertEquals(4${suffix}, result1[1])
        assertEquals(5${suffix}, result1[2])

        val result2 = array.takeLast(0)
        assertEquals(0, result2.size)

        val result3 = array.takeLast(10)
        assertEquals(5, result3.size)

        assertFailsWith<IllegalArgumentException> { array.takeLast(-1) }
    }

    @Test
    fun test${primitive}ArrayDrop() {
        val array = ${primitive}Array(5) { (it + 1${suffix}) }

        val result1 = array.drop(2)
        assertEquals(3, result1.size)
        assertEquals(3${suffix}, result1[0])
        assertEquals(4${suffix}, result1[1])
        assertEquals(5${suffix}, result1[2])

        val result2 = array.drop(0)
        assertEquals(5, result2.size)

        val result3 = array.drop(10)
        assertEquals(0, result3.size)

        assertFailsWith<IllegalArgumentException> { array.drop(-1) }
    }

    @Test
    fun test${primitive}ArrayDropLast() {
        val array = ${primitive}Array(5) { (it + 1${suffix}) }

        val result1 = array.dropLast(2)
        assertEquals(3, result1.size)
        assertEquals(1${suffix}, result1[0])
        assertEquals(2${suffix}, result1[1])
        assertEquals(3${suffix}, result1[2])

        val result2 = array.dropLast(0)
        assertEquals(5, result2.size)

        val result3 = array.dropLast(10)
        assertEquals(0, result3.size)

        assertFailsWith<IllegalArgumentException> { array.dropLast(-1) }
    }
EOF
done

# Close the class
echo "}" >> "$targetFile"

echo "Generated array take/drop function tests successfully"


