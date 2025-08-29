#!/bin/bash

# Define the set of primitives and suffixes. Note that the order of suffixes
# matches the order of primitives.
primitives=("Double" "Float" "Long" "Int")
suffixes=(".0" "f" "L" "")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

testDir="${projectRoot}/src/commonTest/kotlin/kubit/collections/list/ext"
testFile="${testDir}/FlattenExtTest.kt"

# Remove any old test file and create a new empty file
rm -f "$testFile"
mkdir -p "$testDir"
touch "$testFile"

# Write package header and imports
cat >> "$testFile" << EOF
package kubit.collections.list.ext

import kotlin.test.Test
import kotlin.test.assertEquals
import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class FlattenExtTest {
EOF

# Generate tests for List<List<T>>.flatten() and List<TList>.flatten()
for i in "${!primitives[@]}"; do
  primitive="${primitives[$i]}"
  suffix="${suffixes[$i]}"

  cat >> "$testFile" << EOF

    @Test
    fun \`flatten list of ${primitive} list\`() {
        val input = listOf(listOf(), listOf(1${suffix}, 2${suffix}), listOf(3${suffix}))
        val result = input.flatten()

        val expected = Mutable${primitive}List(3).apply {
            addAll(listOf(1${suffix}, 2${suffix}, 3${suffix}))
        }

        assertEquals(expected.size, result.size)
        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }

    @Test
    fun \`flatten list of ${primitive}List\`() {
        val list1 = Mutable${primitive}List(2).apply { addAll(listOf(1${suffix}, 2${suffix})) }
        val list2 = Mutable${primitive}List(1).apply { add(3${suffix}) }
        val input = listOf(list1, list2)
        val result = input.flatten()

        val expected = Mutable${primitive}List(3).apply {
            addAll(listOf(1${suffix}, 2${suffix}, 3${suffix}))
        }

        assertEquals(expected.size, result.size)
        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }
EOF
done

# Close class definition
cat >> "$testFile" << EOF
}
EOF

echo "Generated flatten extension test functions successfully at FlattenExtTest"


