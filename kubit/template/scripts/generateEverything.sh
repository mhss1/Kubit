#!/bin/bash
# Runs all scripts

set -euo pipefail

scriptDir=$(cd "$(dirname "$0")" && pwd)

# Lists
"${scriptDir}/list/generatePrimitiveLists.sh"
echo ""

# List Map
"${scriptDir}/list/ext/generateMapExt.sh"
"${scriptDir}/list/ext/generateMapExtTest.sh"
echo ""

# List MapIndexed
"${scriptDir}/list/ext/generateMapIndexedExt.sh"
"${scriptDir}/list/ext/generateMapIndexedExtTest.sh"
echo ""

# GenericListExt
"${scriptDir}/list/ext/generateGenericListExt.sh"
"${scriptDir}/list/ext/generateGenericListExtTest.sh"
echo ""

# GenericListExt
"${scriptDir}/list/ext/generateGenericCompoundActionExt.sh"
"${scriptDir}/list/ext/generateGenericCompoundActionTestExt.sh"
echo ""

# Flatten
"${scriptDir}/list/ext/generateFlattenExt.sh"
"${scriptDir}/list/ext/generateFlattenExtTest.sh"
echo ""

# FlatMap
"${scriptDir}/list/ext/generateFlatMapExt.sh"
"${scriptDir}/list/ext/generateFlatMapExtTest.sh"
echo ""

# Array.map
"${scriptDir}/arrays/generateArrayMapExt.sh"
"${scriptDir}/arrays/generateArrayMapExtTest.sh"
echo ""

# Array.filter
"${scriptDir}/arrays/generateArrayFilterExt.sh"
"${scriptDir}/arrays/generateArrayFilterExtTest.sh"
echo ""

# Array Take & Drop
"${scriptDir}/arrays/generateArrayTakeDropExt.sh"
"${scriptDir}/arrays/generateArrayTakeDropExtTest.sh"
echo ""

# InPlace primitives (windowed + sublist generation)
"${scriptDir}/inplace/generateWindowedPrimitiveList.sh"
echo ""

# InPlace tests
"${scriptDir}/inplace/generateWindowedPrimitiveListTest.sh"
"${scriptDir}/inplace/generatePrimitiveWindowedChunkedTest.sh"

# InPlace primitive extensions (windowed + chunked ext)
"${scriptDir}/inplace/generatePrimitiveInPlaceExt.sh"


