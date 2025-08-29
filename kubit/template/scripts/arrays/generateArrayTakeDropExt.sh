#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonMain/kotlin/kubit/collections/array/ArrayTakeDropExt.kt"

# Remove any old file and create a new empty file
rm -f "$targetFile"
mkdir -p "$(dirname "$targetFile")"
touch "$targetFile"

# Write package header and imports
cat >> "$targetFile" << EOF
package kubit.collections.array

import kubit.collections.list.*
import kubit.collections.list.internal.requirePrecondition

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
EOF

# Generate take/drop functions for each primitive type
for primitive in "${primitives[@]}"; do
    echo "Generating ${primitive}Array take/drop functions..."
    cat >> "$targetFile" << EOF

/**
 * Returns a ${primitive}List containing first [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun ${primitive}Array.take(n: Int): ${primitive}List {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    val listSize = n.coerceAtMost(size)
    return Mutable${primitive}List(listSize).also { list ->
        copyInto(list.content, 0, 0, listSize)
        list._size = listSize
    }
}

/**
 * Returns a ${primitive}List containing last [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun ${primitive}Array.takeLast(n: Int): ${primitive}List {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    val listSize = n.coerceAtMost(size)
    return Mutable${primitive}List(listSize).also { list ->
        copyInto(list.content, 0, size - listSize, size)
        list._size = listSize
    }
}

/**
 * Returns a ${primitive}List containing all elements except first [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun ${primitive}Array.drop(n: Int): ${primitive}List {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    return takeLast(size - n.coerceAtMost(size))
}

/**
 * Returns a ${primitive}List containing all elements except last [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun ${primitive}Array.dropLast(n: Int): ${primitive}List {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    return take(size - n.coerceAtMost(size))
}
EOF
done

echo "Generated array take/drop functions successfully"


