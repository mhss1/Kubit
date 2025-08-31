#!/bin/bash

primitives=("Int" "Long" "Float" "Double")
scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonMain/kotlin/kubit/collections/list/ext/GenericCompoundActionExt.kt"

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

# forEachFlattened, filterFlattened, mapFlattened for generic types (object/time-invariant)
cat >> "$targetFile" << EOF

/**
 * Iterates over each element in all nested lists and applies the given [action].
 */
fun <T> List<List<T>>.forEachFlattened(action: (T) -> Unit) {
    forEach { list ->
        list.forEach { item ->
            action(item)
        }
    }
}

/**
 * Returns a flattened list containing only the elements that match the given [predicate].
 */
fun <T> List<List<T>>.filterFlattened(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    forEach { list ->
        list.forEach { item ->
            if (predicate(item)) result.add(item)
        }
    }
    return result
}

/**
 * Returns a flattened list produced by applying [transform] to each element of the nested lists (generic version).
 */
@OverloadResolutionByLambdaReturnType
fun <T, E> List<List<T>>.mapFlattened(transform: (T) -> E): List<E> {
    val totalSize = sumOf { it.size }
    val result = ArrayList<E>(totalSize)
    forEach { list ->
        list.forEach { item ->
            result.add(transform(item))
        }
    }
    return result
}
EOF

# mapFlattened, filterFlattened for all primitives
for primitive in "${primitives[@]}"; do
cat >> "$targetFile" << EOF

/**
 * Returns a flattened ${primitive}List produced by applying [transform] to each element of the nested lists.
 */
 @OverloadResolutionByLambdaReturnType
fun <T> List<List<T>>.mapFlattened(transform: (T) -> $primitive): ${primitive}List {
    val totalSize = sumOf { it.size }
    val result = Mutable${primitive}List(totalSize)
    forEach { list ->
        list.forEach { item ->
            result.add(transform(item))
        }
    }
    return result
}

/**
 * Returns a flattened ${primitive}List containing only elements that match the given [predicate].
 */
fun List<List<$primitive>>.filterFlattened(predicate: ($primitive) -> Boolean): ${primitive}List {
    val result = Mutable${primitive}List()
    forEach { list ->
        list.forEach { item ->
            if (predicate(item)) result.add(item)
        }
    }
    return result
}
EOF
done

# findFlattened generic
cat >> "$targetFile" << EOF

/**
 * Finds the first element in the nested lists that matches [predicate], or returns null if none found (generic version).
 */
fun <T> List<List<T>>.findFlattened(predicate: (T) -> Boolean): T? {
    forEach { list ->
        list.forEach { item ->
            if (predicate(item)) return item
        }
    }
    return null
}
EOF

echo "Generated GenericCompoundActionExt extensions for primitive and object types"
