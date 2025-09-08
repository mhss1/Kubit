#!/bin/bash

primitives=("Double" "Float" "Long" "Int")
suffixes=(".0" "f" "L" "")

scriptDir=$(cd "$(dirname "$0")" && pwd)
scriptsRoot=$(cd "${scriptDir}/.." && pwd)
templateRoot=$(cd "${scriptsRoot}/.." && pwd)
projectRoot=$(cd "${templateRoot}/.." && pwd)

tplDir="${templateRoot}/templates/deque"

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

    # Determine the full target path based on file type
    if [[ "$target_path_file" == *"Test"* ]]; then
        target_path="${target_path_base}/commonTest/kotlin/kubit/collections/deque/${target_path_file}"
    else
        target_path="${target_path_base}/commonMain/kotlin/kubit/collections/deque/${target_path_file}"
    fi

    # Create the target directory if it doesn't exist
    mkdir -p "$(dirname "${projectRoot}/${target_path}")"

    eval "sed${sed_commands} \"${tplDir}/${template}\" > \"${projectRoot}/${target_path}\""
}

# Loop through primitives
for index in ${!primitives[@]}
do
    primitive=${primitives[$index]}
    firstLower=$(echo "${primitive:0:1}" | tr '[:upper:]' '[:lower:]')
    lower="${firstLower}${primitive:1}"
    suffix=${suffixes[$index]}

    echo "generating ${primitive}Deque.kt"
    generate_files "PKeyDeque.kt.template" "src" "${primitive}Deque.kt" "PKeyArray/${primitive}Array" "PKey/${primitive}" "pKey/${lower}" "KeySuffix/${suffix}"

    echo "generating ${primitive}DequeTest.kt"
    generate_files "PKeyDequeTest.kt.template" "src" "${primitive}DequeTest.kt" "PKey/${primitive}" "pKey/${lower}" "KeySuffix/${suffix}"
done

echo "Deques generated successfully"


