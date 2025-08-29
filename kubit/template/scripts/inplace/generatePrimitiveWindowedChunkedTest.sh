#!/bin/bash

set -euo pipefail

primitives=("Double" "Float" "Long" "Int")
suffixes=(".0" "f" "L" "")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

testsDir="${projectRoot}/src/commonTest/kotlin/kubit/collections/inplace/primitives"

srcWindowedTpl="${templateRoot}/templates/inplace_primitives/WindowedInPlacePrimitiveExtTest.kt.template"
srcChunkedTpl="${templateRoot}/templates/inplace_primitives/InPlacePrimitiveChunkedExtTest.kt.template"

mkdir -p "$testsDir"

for index in ${!primitives[@]}; do
  primitive=${primitives[$index]}
  suffix=${suffixes[$index]}
  firstLower=$(echo "${primitive:0:1}" | tr '[:upper:]' '[:lower:]')
  lower="${firstLower}${primitive:1}"

  # Windowed test
  destWindowed="$testsDir/WindowedInPlace${primitive}ExtTest.kt"
  sed -e "s/PKey/${primitive}/g" \
      -e "s/pKey/${lower}/g" \
      -e "s/KeySuffix/${suffix}/g" \
      "$srcWindowedTpl" > "$destWindowed"

  # Chunked test
  destChunked="$testsDir/ChunkedInPlace${primitive}ExtTest.kt"
  sed -e "s/PKey/${primitive}/g" \
      -e "s/pKey/${lower}/g" \
      -e "s/KeySuffix/${suffix}/g" \
      "$srcChunkedTpl" > "$destChunked"
done

echo "Generated primitive in-place windowed/chunked tests from templates"


