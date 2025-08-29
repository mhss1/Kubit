#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonTest/kotlin/kubit/collections/list/ext/MapExtTest.kt"

# Remove any old file and create a new empty file
rm -f "$targetFile"
mkdir -p "$(dirname "$targetFile")"
touch "$targetFile"

# Write header
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
class ListMapExtTest {
EOF

# Generate map tests for List to primitive
for primitive in "${primitives[@]}"; do
    # Set up test values based on primitive type
    if [ "$primitive" = "Int" ] || [ "$primitive" = "Long" ]; then
        transform="it * 2"
        [ "$primitive" = "Long" ] && transform="it * 2L"
        expected1="2"; expected2="4"; expected3="6"
        [ "$primitive" = "Long" ] && expected1="2L" expected2="4L" expected3="6L"
    else
        transform="it * 1.5"
        [ "$primitive" = "Float" ] && transform="it * 1.5f"
        expected1="1.5"; expected2="3.0"; expected3="4.5"
        [ "$primitive" = "Float" ] && expected1="1.5f" expected2="3.0f" expected3="4.5f"
    fi

    cat >> "$targetFile" << EOF

    @Test
    fun \`map List to ${primitive}List\`() {
        val input = listOf(1, 2, 3)
        val result = input.map { $transform }

        assertTrue(result is ${primitive}List)
        assertEquals(3, result.size)
        assertEquals($expected1, result[0])
        assertEquals($expected2, result[1])
        assertEquals($expected3, result[2])
    }
    
    @Test
    fun \`mapNotNull List to ${primitive}List\`() {
        val input = listOf(1, null, 2, null, 3)
        val result = input.mapNotNull { it?.let { ${transform} } }

        assertTrue(result is ${primitive}List)
        assertEquals(3, result.size)
        assertEquals(${expected1}, result[0])
        assertEquals(${expected2}, result[1])
        assertEquals(${expected3}, result[2])
    }
EOF
done

#### Generate primitive to primitive map tests
for primitive1 in "${primitives[@]}"; do
    for primitive2 in "${primitives[@]}"; do
        # Set up test values based on primitive types
        suffix1=""
        [ "$primitive1" = "Long" ] && suffix1="L"
        [ "$primitive1" = "Float" ] && suffix1="f"
        [ "$primitive1" = "Double" ] && suffix1=".0"
        
        if [ "$primitive2" = "Int" ] || [ "$primitive2" = "Long" ]; then
            transform="it * 2"
            if [ "$primitive2" = "Int" ] && [ "$primitive1" != "Int" ]; then
                transform="it.toInt() * 2"
            elif [ "$primitive2" = "Long" ] && [ "$primitive1" != "Long" ]; then
                transform="it.toLong() * 2"
            fi
            expected1="2"; expected2="4"; expected3="6"
            [ "$primitive2" = "Long" ] && expected1="2L" expected2="4L" expected3="6L"
        else
            transform="it * 1.5"
            [ "$primitive2" = "Float" ] && transform="it * 1.5f"
            if [ "$primitive2" = "Float" ] && [ "$primitive1" = "Double" ]; then
                transform="(it * 1.5).toFloat()"
            fi
            expected1="1.5"; expected2="3.0"; expected3="4.5"
            [ "$primitive2" = "Float" ] && expected1="1.5f" expected2="3.0f" expected3="4.5f"
        fi
        cat >> "$targetFile" << EOF

    @Test
    fun \`map ${primitive1}List to ${primitive2}List\`() {
        val input = mutable${primitive1}ListOf(1${suffix1}, 2${suffix1}, 3${suffix1})
        val result = input.map { $transform }

        assertTrue(result is ${primitive2}List)
        assertEquals(3, result.size)
        assertEquals($expected1, result[0])
        assertEquals($expected2, result[1])
        assertEquals($expected3, result[2])
    }
EOF
    done
done

# Generate map tests for primitive to Generic
for primitive in "${primitives[@]}"; do
    suffix=""
    [ "$primitive" = "Float" ] && suffix="f"
    [ "$primitive" = "Double" ] && suffix=".0"

    value_suffix=""
    [ "$primitive" = "Float" ] && value_suffix=".0"
    [ "$primitive" = "Double" ] && value_suffix=".0"

    cat >> "$targetFile" << EOF

    @Test
    fun \`map ${primitive}List to Generic List\`() {
        val input = mutable${primitive}ListOf(1${suffix}, 2${suffix}, 3${suffix})
        val result = input.map { "Number \$it" }

        assertTrue(result is List<*>)
        assertEquals(3, result.size)
        assertEquals("Number 1${value_suffix}", result[0])
        assertEquals("Number 2${value_suffix}", result[1])
        assertEquals("Number 3${value_suffix}", result[2])
    }
EOF
done

# Close the class
echo "}" >> "$targetFile"

echo "Generated map test functions successfully"


