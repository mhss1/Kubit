#!/bin/bash

primitives=("Int" "Long" "Float" "Double")
lowerPrimitives=("int" "long" "float" "double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

testFile="${projectRoot}/src/commonTest/kotlin/kubit/collections/array/ArrayFilterExtTest.kt"

echo "Generating ArrayFilterExtTest.kt in $testFileDir"

cat > "$testFile" << EOF
package kubit.collections.array

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertContentEquals
import kubit.collections.list.*

class ArrayFilterExtTest {
EOF

for i in "${!primitives[@]}"; do
primitive=${primitives[$i]}
lowerPrimitive=${lowerPrimitives[$i]}
# Set necessary suffixes
suffix=""
[ "$lowerPrimitive" = "float" ] && suffix="f"
[ "$lowerPrimitive" = "double" ] && suffix=".0"
[ "$lowerPrimitive" = "long" ] && suffix="L"

cat >> "$testFile" << EOF

    @Test
    fun \`${primitive}Array filter returns only matching elements as List\`() {
        val array = ${primitive}Array(5) { it + 1${suffix} }
        val result = array.filter { it % 2${suffix} == 0${suffix} }
        assertEquals(${lowerPrimitive}ListOf(2${suffix}, 4${suffix}), result)
    }

    @Test
    fun \`${primitive}Array filterArray returns only matching elements as Array\`() {
        val array = ${primitive}Array(5) { it + 1${suffix} }
        val result = array.filterArray { it > 3${suffix} }
        assertContentEquals(${lowerPrimitive}ArrayOf(4${suffix}, 5${suffix}), result)
    }

    @Test
    fun \`Array of ${primitive} filter returns only matching elements as List\`() {
        val array = arrayOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix}, 5${suffix})
        val result = array.filter { it < 3${suffix} }
        assertEquals(${lowerPrimitive}ListOf(1${suffix}, 2${suffix}), result)
    }

    @Test
    fun \`Array of ${primitive} filterArray returns only matching elements as Array\`() {
        val array = arrayOf(1${suffix}, 2${suffix}, 3${suffix}, 4${suffix}, 5${suffix})
        val result = array.filterArray { it == 5${suffix} }
        assertContentEquals(${lowerPrimitive}ArrayOf(5${suffix}), result)
    }
EOF
done

cat >> "$testFile" << EOF

}
EOF

echo "Test file generated at $testFile"
