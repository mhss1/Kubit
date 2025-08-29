#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonMain/kotlin/kubit/collections/list/ext/MapIndexedExt.kt"

# Remove any old file and create a new empty file
rm -f "$targetFile"
mkdir -p "$(dirname "$targetFile")"
touch "$targetFile"

# Write a package header and imports using heredoc
cat >> "$targetFile" << EOF
package kubit.collections.list.ext

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
EOF

# List mapIndexed functions
for primitive in "${primitives[@]}"; do
  echo "Generating List<T>.mapIndexed for $primitive..."
  cat >> "$targetFile" << EOF

/**
 * Returns a ${primitive}List containing the results of applying the given transform function to each element and its index in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.mapIndexed(transform: (Int, T) -> $primitive): ${primitive}List {
    return Mutable${primitive}List(size).also { result ->
        fastForEachIndexed { index, element ->
            result.content[result._size++] = transform(index, element)
        }
    }
}
EOF
done

# primitive to primitive mapIndexed
for primitive1 in "${primitives[@]}"; do
    for primitive2 in "${primitives[@]}"; do
        echo "Generating ${primitive1}List to ${primitive2}List mapIndexed..."
        cat >> "$targetFile" << EOF

/**
 * Returns a ${primitive2}List containing the results of applying the given transform function to each element and its index in the original ${primitive1}List.
 */
@OverloadResolutionByLambdaReturnType
inline fun ${primitive1}List.mapIndexed(transform: (Int, ${primitive1}) -> ${primitive2}): ${primitive2}List {
    return Mutable${primitive2}List(size) { i -> transform(i, content[i]) }
}
EOF
    done
done

# primitive to List mapIndexed
for primitive in "${primitives[@]}"; do
    echo "Generating ${primitive}List to List<R> mapIndexed..."
    cat >> "$targetFile" << EOF

/**
 * Returns a List containing the results of applying the given transform function to each element and its index in the original ${primitive}List.
 */
@OverloadResolutionByLambdaReturnType
inline fun <R> ${primitive}List.mapIndexed(transform: (Int, ${primitive}) -> R): List<R> {
    return MutableList(size) { i -> transform(i, content[i]) }
}
EOF
done

echo "Generated mapIndexed functions successfully"


