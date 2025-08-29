#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonTest/kotlin/kubit/collections/array/ArrayMapExtTest.kt"

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

class ArrayMapExtTest {
EOF

# Array to List/Array map function tests
for primitive in "${primitives[@]}"; do
    suffix=""
    [ "$primitive" = "Long" ] && suffix="L"
    [ "$primitive" = "Float" ] && suffix="f"
    [ "$primitive" = "Double" ] && suffix=".0"

    cat >> "$targetFile" << EOF

    @Test
    fun test${primitive}ArrayMapToList() {
        val array = ${primitive}Array(3) { (it + 1${suffix}) }
        val result = array.map { it * 2 }
        
        assertEquals(3, result.size)
        assertEquals(2${suffix}, result[0])
        assertEquals(4${suffix}, result[1])
        assertEquals(6${suffix}, result[2])
    }

    @Test
    fun test${primitive}ArrayMapToArray() {
        val array = ${primitive}Array(3) { (it + 1${suffix}) }
        val result = array.mapArray { it * 2 }
        
        assertEquals(3, result.size)
        assertEquals(2${suffix}, result[0])
        assertEquals(4${suffix}, result[1])
        assertEquals(6${suffix}, result[2])
    }
EOF
done

# Array primitive to primitive map tests
for primitive1 in "${primitives[@]}"; do
    for primitive2 in "${primitives[@]}"; do
        if [ "$primitive1" != "$primitive2" ]; then
            suffix1=""
            [ "$primitive1" = "Long" ] && suffix1="L"
            [ "$primitive1" = "Float" ] && suffix1="f"
            [ "$primitive1" = "Double" ] && suffix1=".0"

            suffix2=""
            [ "$primitive2" = "Long" ] && suffix2="L"
            [ "$primitive2" = "Float" ] && suffix2="f"
            [ "$primitive2" = "Double" ] && suffix2=".0"

            cat >> "$targetFile" << EOF

    @Test
    fun test${primitive1}ArrayMapTo${primitive2}List() {
        val array = ${primitive1}Array(3) { (it + 1${suffix1}) }
        val result = array.map { it.to${primitive2}() }
        
        assertEquals(3, result.size)
        assertEquals(1${suffix2}, result[0])
        assertEquals(2${suffix2}, result[1])
        assertEquals(3${suffix2}, result[2])
    }

    @Test
    fun test${primitive1}ArrayMapTo${primitive2}Array() {
        val array = ${primitive1}Array(3) { (it + 1${suffix1}) }
        val result = array.mapArray { it.to${primitive2}() }
        
        assertEquals(3, result.size)
        assertEquals(1${suffix2}, result[0])
        assertEquals(2${suffix2}, result[1])
        assertEquals(3${suffix2}, result[2])
    }
EOF
        fi
    done
done

# Generic Array<T> to primitive List/Array map tests
for primitive in "${primitives[@]}"; do
    suffix=""
    [ "$primitive" = "Long" ] && suffix="L"
    [ "$primitive" = "Float" ] && suffix="f"
    [ "$primitive" = "Double" ] && suffix=".0"

    cat >> "$targetFile" << EOF

    @Test
    fun testGenericArrayMapTo${primitive}List() {
        val array = Array(3) { (it + 1).toString() }
        val result = array.map { it.to${primitive}() }
        
        assertEquals(3, result.size)
        assertEquals(1${suffix}, result[0])
        assertEquals(2${suffix}, result[1])
        assertEquals(3${suffix}, result[2])
    }

    @Test
    fun testGenericArrayMapTo${primitive}Array() {
        val array = Array(3) { (it + 1).toString() }
        val result = array.mapArray { it.to${primitive}() }
        
        assertEquals(3, result.size)
        assertEquals(1${suffix}, result[0])
        assertEquals(2${suffix}, result[1])
        assertEquals(3${suffix}, result[2])
    }
EOF
done

# Close the class
echo "}" >> "$targetFile"

echo "Generated array map function tests successfully"


