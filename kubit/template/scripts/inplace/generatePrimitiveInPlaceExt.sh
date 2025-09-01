#!/bin/bash

set -euo pipefail

primitives=("Int" "Long" "Float" "Double")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

outDir="${projectRoot}/src/commonMain/kotlin/kubit/collections/inplace/primitives"
mkdir -p "$outDir"

# Generate WindowedInPlacePrimitiveExt.kt
winFile="${outDir}/WindowedInPlacePrimitiveExt.kt"
rm -f "$winFile"
cat > "$winFile" << 'EOF'
package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.math.min

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
EOF

for primitive in "${primitives[@]}"; do
  cat >> "$winFile" << EOF

/**
 * Returns a Windowed${primitive}List with the given windowSize sliding by step.
 * Each window is a view into the original list.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 * @return A [Windowed${primitive}List] containing the windows.
 */
fun ${primitive}List.windowedInPlace(windowSize: Int, step: Int = 1, partialWindows: Boolean = false): Windowed${primitive}List {
    return Windowed${primitive}List(this, windowSize, step, partialWindows)
}

/**
 * Iterates through the windows of the list and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0. Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @param action The function to be executed for each window.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun ${primitive}List.forEachWindow(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (InPlace${primitive}SubList) -> Unit,
) {
    if (isEmpty()) return
    require(windowSize > 0) { "Window size must be greater than 0" }
    require(step > 0) { "Step must be greater than 0" }
    if (size < windowSize && !partialWindows) return

    val current = InPlace${primitive}SubList(this, 0, min(windowSize, size))

    while (current.start < size) {
        action(current)
        current.start += step
        val newEnd = current.start + windowSize
        if (newEnd > size && !partialWindows) break
        current.end = newEnd.coerceAtMost(size)
    }
}

/**
 * Iterates through the windows of the list with their indices and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0, Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @param action The function to be executed for each window and its index.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun ${primitive}List.forEachWindowIndexed(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (index: Int, InPlace${primitive}SubList) -> Unit,
) {
    var index = 0
    forEachWindow(windowSize, step, partialWindows) {
        action(index++, it)
    }
}
EOF
done

echo "Generated WindowedInPlacePrimitiveExt.kt"

# Generate InPlacePrimitiveChunkedExt.kt
chunkFile="${outDir}/ChunkedInPlacePrimitiveExt.kt"
rm -f "$chunkFile"
cat > "$chunkFile" << 'EOF'
package kubit.collections.inplace.primitives

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
EOF

for primitive in "${primitives[@]}"; do
  cat >> "$chunkFile" << EOF

/**
 * Splits this list into a list of [InPlace${primitive}SubList] each not exceeding the given [chunkSize].
 * The last list may have less elements than the given [chunkSize].
 *
 * Each chunk is a view into the original list. Changes in the original list will be reflected in the chunks.
 *
 * @param chunkSize the number of elements to take in each window.
 */
fun ${primitive}List.chunkedInPlace(chunkSize: Int): Windowed${primitive}List {
    return windowedInPlace(chunkSize, chunkSize, true)
}

/**
 * Iterates through the windowed chunks of this collection with given [chunkSize],
 * where window step is equals to [chunkSize] and calls [action] on each chunk.
 *
 * The returned chunk is a view into the original list. Changes in the original list will be reflected in the chunk.
 *
 * @param chunkSize the number of elements to take in each window.
 * @param action function that takes the chunk as an argument.
 */
inline fun ${primitive}List.forEachChunk(chunkSize: Int, action: (InPlace${primitive}SubList) -> Unit) {
    forEachWindow(chunkSize, chunkSize, true, action)
}

/**
 * Iterates through the windowed chunks of this collection with given [chunkSize],
 * where window step is equals to [chunkSize] and calls [action] on each chunk with its index.
 *
 * The returned chunk is a view into the original list. Changes in the original list will be reflected in the chunk.
 *
 * @param chunkSize the number of elements to take in each window.
 * @param action function that takes the index of a chunk and the chunk itself as arguments.
 */
inline fun ${primitive}List.forEachChunkIndexed(chunkSize: Int, action: (index: Int, InPlace${primitive}SubList) -> Unit) {
    var index = 0
    forEachChunk(chunkSize) { chunk ->
        action(index++, chunk)
    }
}
EOF
done

echo "Generated InPlacePrimitiveChunkedExt.kt"