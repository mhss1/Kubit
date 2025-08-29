#!/bin/bash

primitives=("Double" "Float" "Long" "Int")
suffixes=(".0" "f" "L" "")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

tplDir="${templateRoot}/templates/inplace_primitives"

# Function to generate files
generate_files() {
    local template="$1"
    local target_path_base="$2"
    local target_path_file="$3"
    shift 3
    local substitutions=("$@")
    local sed_commands=""
    
    for substitution in "${substitutions[@]}"; do
        sed_commands+=" -e \"s/${substitution}/g\""
    done

    target_path="${target_path_base}/commonMain/kotlin/kubit/collections/inplace/primitives/${target_path_file}"

    # Create the target directory if it doesn't exist
    mkdir -p "$(dirname "${projectRoot}/${target_path}")"

    eval "sed${sed_commands} \"${tplDir}/${template}\" > \"${projectRoot}/${target_path}\""
}

# Loop through primitives
# shellcheck disable=SC2068
for index in ${!primitives[@]}
do
    primitive=${primitives[$index]}
    firstLower=$(echo "${primitive:0:1}" | tr '[:upper:]' '[:lower:]')
    lower="${firstLower}${primitive:1}"
    suffix=${suffixes[$index]}

    echo "generating Windowed${primitive}List.kt"
    generate_files "WindowedPKeyList.kt.template" "src" "Windowed${primitive}List.kt" "PKey/${primitive}" "pKey/${lower}" "KeySuffix/${suffix}"

    echo "generating InPlace${primitive}SubList.kt"
    generate_files "InPlacePKeySubList.kt.template" "src" "InPlace${primitive}SubList.kt" "PKey/${primitive}" "pKey/${lower}" "KeySuffix/${suffix}"
done

echo "Lists generated successfully"


