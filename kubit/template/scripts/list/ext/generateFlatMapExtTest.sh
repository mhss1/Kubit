#!/bin/bash

# Define the set of primitives we want to target
primitives=("Int" "Long" "Float" "Double")
lowerPrimitives=("int" "long" "float" "double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonTest/kotlin/kubit/collections/list/ext/FlatMapExtTest.kt"

# Remove any old file and create a new empty file
rm -f "$targetFile"
mkdir -p "$(dirname "$targetFile")"
touch "$targetFile"

# Write package header and imports
cat >> "$targetFile" << EOF
package kubit.collections.list.ext

import kotlin.test.Test
import kotlin.test.assertEquals
import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class FlatMapExtTest {
EOF

# Generate tests for List<T> to List<R> and TList
for i in "${!primitives[@]}"; do
    primitive=${primitives[$i]}
    lowerPrimitive=${lowerPrimitives[$i]}
    
    # Add suffix logic for float, double and long
    suffix=""
    [ "$lowerPrimitive" = "float" ] && suffix="f"
    [ "$lowerPrimitive" = "double" ] && suffix=".0"
    [ "$lowerPrimitive" = "long" ] && suffix="L"

    cat >> "$targetFile" << EOF

    @Test
    fun \`flat map list with ${primitive}List\`() {
        val input = listOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix}, 5${suffix}, 6${suffix}, 7${suffix}, 8${suffix}, 9${suffix})
        val expected = ${lowerPrimitive}ListOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix}, 5${suffix}, 6${suffix}, 7${suffix}, 8${suffix}, 9${suffix})
        val result = input.flatMap { ${lowerPrimitive}ListOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun \`flat map list with ${primitive}List and empty lists\`() {
        val input = listOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix})
        val expected = ${lowerPrimitive}ListOf(3${suffix}, 4${suffix})
        val result = input.flatMap { if (it > 2${suffix}) ${lowerPrimitive}ListOf(it) else ${lowerPrimitive}ListOf() }
        assertEquals(expected, result)
    }
    
    @Test
    fun \`flat map list with List of ${primitive}\`() {
        val input = listOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix}, 5${suffix}, 6${suffix}, 7${suffix}, 8${suffix}, 9${suffix})
        val expected = ${lowerPrimitive}ListOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix}, 5${suffix}, 6${suffix}, 7${suffix}, 8${suffix}, 9${suffix})
        val result = input.flatMap { listOf(it) }
        assertEquals(expected, result)
    }
EOF
done

# Generate tests for List<T> to List<R> and TList with computeSize = true
for i in "${!primitives[@]}"; do
    primitive=${primitives[$i]}
    lowerPrimitive=${lowerPrimitives[$i]}
    
    # Add suffix logic for float, double and long
    suffix=""
    [ "$lowerPrimitive" = "float" ] && suffix="f"
    [ "$lowerPrimitive" = "double" ] && suffix=".0"
    [ "$lowerPrimitive" = "long" ] && suffix="L"

    cat >> "$targetFile" << EOF

    @Test
    fun \`flat map list with ${primitive}List computeSize true\`() {
        val input = listOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix}, 5${suffix}, 6${suffix}, 7${suffix}, 8${suffix}, 9${suffix})
        val expected = ${lowerPrimitive}ListOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix}, 5${suffix}, 6${suffix}, 7${suffix}, 8${suffix}, 9${suffix})
        val result = input.flatMap(computeSize = true) { ${lowerPrimitive}ListOf(it) }
        assertEquals(expected, result)
    }
    
    @Test
    fun \`flat map list with List of ${primitive} computeSize true\`() {
        val input = listOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix}, 5${suffix}, 6${suffix}, 7${suffix}, 8${suffix}, 9${suffix})
        val expected = ${lowerPrimitive}ListOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix}, 5${suffix}, 6${suffix}, 7${suffix}, 8${suffix}, 9${suffix})
        val result = input.flatMap(computeSize = true) { listOf(it) }
        assertEquals(expected, result)
    }
EOF
done

# Close the class
echo "}" >> "$targetFile"

echo "Generated flatMap test cases successfully at FlatMapExtTest.kt"


