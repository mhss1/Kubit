#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonTest/kotlin/kubit/collections/list/ext/GenericCompoundActionExtTest.kt"

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
class GenericCompoundActionExtTest {
EOF


cat >> "$targetFile" << EOF

    @Test
    fun \`forEachFlattened executes action for each element in nested lists\`() {
        val src = listOf(
            listOf("a", "b"),
            listOf("c"),
            listOf("d", "e")
        )
        val result = mutableListOf<String>()
        src.forEachFlattened { result.add(it) }
        assertEquals(listOf("a", "b", "c", "d", "e"), result)
    }

    @Test
    fun \`filterFlattened with generic lists\`() {
        val src = listOf(
            listOf("a", "b"),
            listOf("c", "d"),
            listOf("e")
        )
        val result = src.filterFlattened { it > "b" }
        assertEquals(listOf("c", "d", "e"), result)
    }

    @Test
    fun \`mapFlattened with generic lists\`() {
        val src = listOf(
            listOf("a", "b"),
            listOf("c")
        )
        val result = src.mapFlattened { it.length }
        assertEquals(intListOf(1, 1, 1), result)
    }
EOF

# --- Primitive variants ---
for primitive in "${primitives[@]}"; do
    suffix=""
    [ "$primitive" = "Long" ] && suffix="L"
    [ "$primitive" = "Float" ] && suffix="f"
    [ "$primitive" = "Double" ] && suffix=".0"
    value1="1${suffix}"
    value2="2${suffix}"
    value3="3${suffix}"
    value4="4${suffix}"

cat >> "$targetFile" << EOF

    @Test
    fun \`mapFlattened to ${primitive}List from nested lists\`() {
        val src = listOf(
            listOf("1", "2"),
            listOf("3")
        )
        val result = src.mapFlattened { it.to${primitive}() }
        assertTrue(result is ${primitive}List)
        assertEquals(3, result.size)
        assertEquals($value1, result[0])
        assertEquals($value2, result[1])
        assertEquals($value3, result[2])
    }

    @Test
    fun \`filterFlattened from nested ${primitive} lists\`() {
        val src = listOf(
            listOf($value1, $value2),
            listOf($value3, $value4)
        )
        val result = src.filterFlattened { it > $value2 }
        assertTrue(result is ${primitive}List)
        assertEquals(2, result.size)
        assertEquals($value3, result[0])
        assertEquals($value4, result[1])
    }

EOF
done

# --- findFlattened generic ---
cat >> "$targetFile" << EOF

    @Test
    fun \`findFlattened returns first matching element in nested list\`() {
        val src = listOf(
            listOf("z", "y"),
            listOf("match", "other")
        )
        val result = src.findFlattened { it.startsWith("m") }
        assertEquals("match", result)
    }

    @Test
    fun \`findFlattened returns null when not found\`() {
        val src = listOf(
            listOf("a"),
            listOf("b")
        )
        val result = src.findFlattened { it == "missing" }
        assertNull(result)
    }
EOF

echo '}' >> "$targetFile"
echo "Generated GenericCompoundActionExt test functions successfully"
