#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

# Path to the output file (all code will be accumulated here)
targetFile="${projectRoot}/src/commonMain/kotlin/kubit/collections/list/ext/MapExt.kt"

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

# List map functions
for primitive in "${primitives[@]}"; do
  echo "Generating List<T>.map for $primitive..."
  cat >> "$targetFile" << EOF

/**
 * Returns a ${primitive}List containing the results of applying the given transform function to each element in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.map(transform: (T) -> $primitive): ${primitive}List {
    return Mutable${primitive}List(size).also { result ->
        fastForEach { element ->
            result.content[result._size++] = transform(element)
        }
    }
}

/**
 * Returns a list containing only the non-null results of applying the given transform function to each element in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.mapNotNull(transform: (T) -> $primitive?): ${primitive}List {
    val result = Mutable${primitive}List()
    fastForEach { element ->
        transform(element)?.let { result.add(it) }
    }
    return result
}
EOF
done

# primitive to primitive map
for primitive1 in "${primitives[@]}"; do
    for primitive2 in "${primitives[@]}"; do
        echo "Generating ${primitive1}List to ${primitive2}List map..."
        cat >> "$targetFile" << EOF

/**
 * Returns a ${primitive2}List containing the results of applying the given transform function to each element in the original ${primitive1}List.
 */
@OverloadResolutionByLambdaReturnType
inline fun ${primitive1}List.map(transform: (${primitive1}) -> ${primitive2}): ${primitive2}List {
    return Mutable${primitive2}List(size) { i -> transform(content[i]) }
}
EOF
    done
done

# primitive to Generic map
for primitive in "${primitives[@]}"; do
    echo "Generating ${primitive}List to List<T> map..."
    cat >> "$targetFile" << EOF

/**
 * Returns an List<R> containing the results of applying the given transform function to each element in the original ${primitive}List.
 */
@OverloadResolutionByLambdaReturnType
inline fun <R> ${primitive}List.map(transform: (${primitive}) -> R): List<R> {
    return MutableList(size) { i -> transform(content[i]) }
}
EOF
done

echo "Generated map functions successfully"


