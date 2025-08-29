#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonMain/kotlin/kubit/collections/array/ArrayFilterExt.kt"

# Remove any old file and create a new empty file
rm -f "$targetFile"
mkdir -p "$(dirname "$targetFile")"
touch "$targetFile"

# Write package header and imports
cat >> "$targetFile" << EOF
package kubit.collections.array

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
EOF

# Primitive array filter functions
for primitive in "${primitives[@]}"; do
  echo "Generating ${primitive}Array.filter to List and Array..."
  cat >> "$targetFile" << EOF

/**
 * Returns a ${primitive}List containing only elements matching the given predicate.
 */
inline fun ${primitive}Array.filter(predicate: (${primitive}) -> Boolean): ${primitive}List {
    val list = Mutable${primitive}List()
    for (element in this) if (predicate(element)) list.add(element)
    return list
}

/**
 * Returns a ${primitive}Array containing only elements matching the given predicate.
 */
inline fun ${primitive}Array.filterArray(predicate: (${primitive}) -> Boolean): ${primitive}Array {
    return this.filter(predicate).to${primitive}Array()
}
EOF
done

# Generic Array<T> filter to primitive List/Array
for primitive in "${primitives[@]}"; do
  echo "Generating Array<T>.filter to ${primitive}List/Array..."
  cat >> "$targetFile" << EOF

/**
 * Returns a ${primitive}List containing only elements that, when transformed, match the predicate.
 */
inline fun Array<${primitive}>.filter(predicate: (${primitive}) -> Boolean): ${primitive}List {
    val list = Mutable${primitive}List()
    for (element in this) if (predicate(element)) list.add(element)
    return list
}

/**
 * Returns a ${primitive}Array containing only elements that, when transformed, match the predicate.
 */
inline fun Array<${primitive}>.filterArray(predicate: (${primitive}) -> Boolean): ${primitive}Array {
    return this.filter(predicate).to${primitive}Array()
}
EOF
done

echo "Generated array filter functions successfully"
