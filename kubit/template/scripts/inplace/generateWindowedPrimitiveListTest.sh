#!/bin/bash

set -euo pipefail

primitives=("Double" "Float" "Long" "Int")
suffixes=(".0" "f" "L" "")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

testsDir="${projectRoot}/src/commonTest/kotlin/kubit/collections/inplace/primitives"

mkdir -p "$testsDir"

windowedTemplate="${templateRoot}/templates/inplace_primitives/WindowedPKeyListTest.kt.template"
subListTemplate="${templateRoot}/templates/inplace_primitives/InPlacePKeySubListTest.kt.template"

for index in ${!primitives[@]}; do
  primitive=${primitives[$index]}
  suffix=${suffixes[$index]}
  firstLower=$(echo "${primitive:0:1}" | tr '[:upper:]' '[:lower:]')
  lower="${firstLower}${primitive:1}"

  # WindowedPKeyList test
  sed -e "s/PKey/${primitive}/g" \
      -e "s/pKey/${lower}/g" \
      -e "s/KeySuffix/${suffix}/g" \
      -e "s/topKey/to${primitive}/g" \
      "$windowedTemplate" > "${testsDir}/Windowed${primitive}ListTest.kt"

  # InPlacePKeySubList test
  sed -e "s/PKey/${primitive}/g" \
      -e "s/pKey/${lower}/g" \
      -e "s/KeySuffix/${suffix}/g" \
      -e "s/topKey/to${primitive}/g" \
      "$subListTemplate" > "${testsDir}/InPlace${primitive}SubListTest.kt"
done

echo "Generated Windowed and InPlace Primitive SubList tests"


