#!/bin/bash

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonMain/kotlin/kubit/collections/array/ArrayMapExt.kt"

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

# Array to List map functions
for primitive in "${primitives[@]}"; do
  echo "Generating ${primitive}Array.map to List..."
  cat >> "$targetFile" << EOF

/**
 * Returns a ${primitive}List containing the results of applying the given transform function to each element in the original array.
 */
@OverloadResolutionByLambdaReturnType
inline fun ${primitive}Array.map(transform: (${primitive}) -> ${primitive}): ${primitive}List {
    return ${primitive}List(size){ index -> transform(this[index]) }
}

/**
 * Returns a ${primitive}Array containing the results of applying the given transform function to each element in the original array.
 */
@OverloadResolutionByLambdaReturnType
inline fun ${primitive}Array.mapArray(transform: (${primitive}) -> ${primitive}): ${primitive}Array {
    return ${primitive}Array(size) { index -> transform(this[index]) }
}
EOF
done

# Array primitive to primitive map
for primitive1 in "${primitives[@]}"; do
    for primitive2 in "${primitives[@]}"; do
        if [ "$primitive1" != "$primitive2" ]; then
            echo "Generating ${primitive1}Array to ${primitive2}List/Array map..."
            cat >> "$targetFile" << EOF

/**
 * Returns a ${primitive2}List containing the results of applying the given transform function to each element in the original ${primitive1}Array.
 */
@OverloadResolutionByLambdaReturnType
inline fun ${primitive1}Array.map(transform: (${primitive1}) -> ${primitive2}): ${primitive2}List {
    return ${primitive2}List(size){ index -> transform(this[index]) }
}

/**
 * Returns a ${primitive2}Array containing the results of applying the given transform function to each element in the original ${primitive1}Array.
 */
@OverloadResolutionByLambdaReturnType
inline fun ${primitive1}Array.mapArray(transform: (${primitive1}) -> ${primitive2}): ${primitive2}Array {
    return ${primitive2}Array(size) { index -> transform(this[index]) }
}
EOF
        fi
    done
done

# Generic Array<T> to primitive List/Array map with transform
for primitive in "${primitives[@]}"; do
    echo "Generating Array<T> to ${primitive}List/Array map..."
    cat >> "$targetFile" << EOF

/**
 * Returns a ${primitive}List containing the results of applying the given transform function to each element in the original Array<T>.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> Array<T>.map(transform: (T) -> ${primitive}): ${primitive}List {
    return Mutable${primitive}List(size){ index -> transform(this[index]) }
}

/**
 * Returns a ${primitive}Array containing the results of applying the given transform function to each element in the original Array<T>.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> Array<T>.mapArray(transform: (T) -> ${primitive}): ${primitive}Array {
    return ${primitive}Array(size) { index -> transform(this[index]) }
}
EOF
done

echo "Generated array map functions successfully"


