#!/bin/bash

primitives=("Int" "Long" "Float" "Double")
scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonMain/kotlin/kubit/collections/list/ext/GenericListExt.kt"

# Remove old and create new file
rm -f "$targetFile"
mkdir -p "$(dirname "$targetFile")"
touch "$targetFile"

cat >> "$targetFile" << EOF
package kubit.collections.list.ext

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
EOF

# Write fastForEach and fastForEachIndexed once
cat >> "$targetFile" << EOF

/**
 * A fast forEach implementation that uses a for-loop for RandomAccess Lists
 * and falls back to forEach for other List types.
 */
inline fun <T> List<T>.fastForEach(action: (T) -> Unit) {
    if (this is RandomAccess) for (i in indices) action(this[i])
    else forEach(action)
}

/**
 * A fast forEachIndexed implementation that uses a for-loop for RandomAccess Lists
 * and falls back to forEachIndexed for other List types.
 */
inline fun <T> List<T>.fastForEachIndexed(action: (Int, T) -> Unit) {
    if (this is RandomAccess) for (i in indices) action(i, this[i])
    else forEachIndexed(action)
}
EOF

# Generate filter and filterNot and filterNotNull for each primitive type
for primitive in "${primitives[@]}"; do
    listType="${primitive}List"
    mutableType="Mutable${primitive}List"
    nullablePrimitive="${primitive}?"
cat >> "$targetFile" << EOF

/**
 * Returns a $listType containing only elements matching the given predicate.
 */
inline fun List<$primitive>.filter(predicate: ($primitive) -> Boolean): $listType {
    val result = $mutableType()
    fastForEach { element ->
        if (predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a $listType containing only elements NOT matching the given predicate.
 */
inline fun List<$primitive>.filterNot(predicate: ($primitive) -> Boolean): $listType {
    val result = $mutableType()
    fastForEach { element ->
        if (!predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a $listType containing all non-null elements.
 */
fun List<$nullablePrimitive>.filterNotNull(): $listType {
    val result = $mutableType()
    fastForEach { element ->
        element?.let { result.add(it) }
    }
    return result
}
EOF
done
echo "Generated generic extensions for primitive lists"
