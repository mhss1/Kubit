#!/bin/bash

# Define the set of primitives we want to target
primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonMain/kotlin/kubit/collections/list/ext/FlatMapExt.kt"

# Remove any old file and create a new empty file
rm -f "$targetFile"
mkdir -p "$(dirname "$targetFile")"
touch "$targetFile"

# Write package header and imports
cat >> "$targetFile" << EOF
package kubit.collections.list.ext

import kubit.collections.list.*
import kotlin.jvm.JvmName

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
EOF

# Generate flatMap for List<T> to List<R> and TList
for primitive in "${primitives[@]}"; do
  echo "Generating flatMap functions for List<T> to List<${primitive}> and ${primitive}List..."

  cat >> "$targetFile" << EOF

/**
 * Returns a single ${primitive}List of all elements yielded from results of transform function being invoked on each element of original collection.
 *
 * @param computeSize whether to precompute the size of the resulting list
 * @param transform the function to transform an element to a list of elements
 */
@OverloadResolutionByLambdaReturnType
@JvmName("flatMapList")
inline fun <T> List<T>.flatMap(computeSize: Boolean = false, transform: (T) -> List<$primitive>): ${primitive}List {
    val size = if (computeSize) sumOf { transform(it).size } else 16
    val result = Mutable${primitive}List(size)
    fastForEach { element ->
        result.addAll(transform(element))
    }
    return result
}

/**
 * Returns a single ${primitive}List of all elements yielded from results of transform function being invoked on each element of original collection.
 *
 * @param computeSize whether to precompute the size of the resulting list
 * @param transform the function to transform an element to a list of elements
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.flatMap(computeSize: Boolean = false, transform: (T) -> ${primitive}List): ${primitive}List {
    val size = if (computeSize) sumOf { transform(it).size } else 16
    val result = Mutable${primitive}List(size)
    fastForEach { element ->
        result.addAll(transform(element))
    }
    return result
}
EOF
done

echo "Generated flatMap functions successfully at FlatMapExt.kt"


