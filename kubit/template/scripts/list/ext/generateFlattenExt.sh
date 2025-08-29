#!/bin/bash

# Define the set of primitives we want to target
primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/../.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

targetFile="${projectRoot}/src/commonMain/kotlin/kubit/collections/list/ext/FlattenExt.kt"

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

# Generate flatten for List<List<T>> and List<TList>
for primitive in "${primitives[@]}"; do
  echo "Generating flatten functions for List<List<${primitive}>> and List<${primitive}List>..."

  cat >> "$targetFile" << EOF

/**
 * Flattens a List of List<$primitive> into a single ${primitive}List.
 */
@OverloadResolutionByLambdaReturnType
@JvmName("flattenList${primitive}")
inline fun List<List<$primitive>>.flatten(): ${primitive}List {
    val size = sumOf { it.size }
    val result = Mutable${primitive}List(size)
    fastForEach { list ->
        result.addAll(list)
    }
    return result
}

/**
 * Flattens a List of ${primitive}List into a single ${primitive}List.
 */
@OverloadResolutionByLambdaReturnType
inline fun List<${primitive}List>.flatten(): ${primitive}List {
    val size = sumOf { it.size }
    val result = Mutable${primitive}List(size)
    fastForEach { list ->
        result.addAll(list)
    }
    return result
}
EOF
done


echo "Generated flatten extension functions successfully"


