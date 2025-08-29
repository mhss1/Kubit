#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonTest/kotlin/kubit/collections/list/ext/MapIndexedExtTest.kt"

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
class ListMapIndexedExtTest {
EOF

# Generate mapIndexed tests for List to primitive
for primitive in "${primitives[@]}"; do
    if [ "$primitive" = "Int" ] || [ "$primitive" = "Long" ]; then
        transform="it * 2 + index"
        [ "$primitive" = "Long" ] && transform="it * 2L + index"
        expected1="2"; expected2="5"; expected3="8"  # (1*2+0, 2*2+1, 3*2+2)
        [ "$primitive" = "Long" ] && expected1="2L" expected2="5L" expected3="8L"
    else
        transform="it * 1.5 + index"
        [ "$primitive" = "Float" ] && transform="it * 1.5f + index"
        expected1="1.5"; expected2="4.0"; expected3="6.5"  # (1*1.5+0, 2*1.5+1, 3*1.5+2)
        [ "$primitive" = "Float" ] && expected1="1.5f" expected2="4.0f" expected3="6.5f"
    fi

    cat >> "$targetFile" << EOF

    @Test
    fun \`mapIndexed List to ${primitive}List\`() {
        val input = listOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> $transform }

        assertTrue(result is ${primitive}List)
        assertEquals(3, result.size)
        assertEquals($expected1, result[0])
        assertEquals($expected2, result[1])
        assertEquals($expected3, result[2])
    }
EOF
done

# Generate primitive-to-primitive mapIndexed tests
for primitive1 in "${primitives[@]}"; do
    for primitive2 in "${primitives[@]}"; do
        suffix1=""
        [ "$primitive1" = "Long" ] && suffix1="L"
        [ "$primitive1" = "Float" ] && suffix1="f"
        [ "$primitive1" = "Double" ] && suffix1=".0"

        # Build transform and expected output
        if [ "$primitive2" = "Int" ]; then
            if [ "$primitive1" = "Int" ]; then
                transform="it * 2 + index"
            else
                transform="it.toInt() * 2 + index"
            fi
        elif [ "$primitive2" = "Long" ]; then
            if [ "$primitive1" = "Long" ]; then
                transform="it * 2 + index"
            else 
                transform="it.toLong() * 2 + index"
            fi
            expected1="2L"; expected2="5L"; expected3="8L"
        elif [ "$primitive2" = "Float" ]; then
            if [ "$primitive1" = "Float" ]; then
                transform="it * 1.5f + index"
            else
                transform="it.toFloat() * 1.5f + index"
            fi
            expected1="1.5f"; expected2="4.0f"; expected3="6.5f"
        else
            if [ "$primitive1" = "Double" ]; then
                transform="it * 1.5 + index"
            else
                transform="it.toDouble() * 1.5 + index"
            fi
            expected1="1.5"; expected2="4.0"; expected3="6.5"
        fi
        
        # Set default expected values for Int
        if [ "$primitive2" = "Int" ]; then
            expected1="2"; expected2="5"; expected3="8"
        fi

        cat >> "$targetFile" << EOF

    @Test
    fun \`mapIndexed ${primitive1}List to ${primitive2}List\`() {
        val input = mutable${primitive1}ListOf(1${suffix1}, 2${suffix1}, 3${suffix1})
        val result = input.mapIndexed { index, it -> $transform }

        assertTrue(result is ${primitive2}List)
        assertEquals(3, result.size)
        assertEquals($expected1, result[0])
        assertEquals($expected2, result[1])
        assertEquals($expected3, result[2])
    }
EOF
    done
done

# Generate primitive to List tests
for primitive in "${primitives[@]}"; do
    suffix=""
    [ "$primitive" = "Float" ] && suffix="f"
    [ "$primitive" = "Double" ] && suffix=".0"

    value_suffix=""
    [ "$primitive" = "Float" ] && value_suffix=".0"
    [ "$primitive" = "Double" ] && value_suffix=".0"

    cat >> "$targetFile" << EOF

    @Test
    fun \`mapIndexed ${primitive}List to List\`() {
        val input = mutable${primitive}ListOf(1${suffix}, 2${suffix}, 3${suffix})
        val result = input.mapIndexed { index, it -> "Number \$it at \$index" }

        assertTrue(result is List<*>)
        assertEquals(3, result.size)
        assertEquals("Number 1${value_suffix} at 0", result[0])
        assertEquals("Number 2${value_suffix} at 1", result[1])
        assertEquals("Number 3${value_suffix} at 2", result[2])
    }
EOF
done

# Close the class
echo "}" >> "$targetFile"

echo "Generated mapIndexed test functions successfully"


