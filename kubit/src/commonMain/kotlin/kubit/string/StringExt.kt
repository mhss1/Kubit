package kubit.string


/**
 * Returns the number of words in the string without creating intermediate objects.
 *
 * @param wordSeparator a function that returns true if the character is a word separator
 */
inline fun CharSequence.countWords(
    wordSeparator: (Char) -> Boolean = { it == ' ' }
): Int {
    var count = 0
    var inWord = false

    for (char in this) {
        if (wordSeparator(char)) {
            inWord = false
        } else if (!inWord) {
            count++
            inWord = true
        }
    }

    return count
}

/**
 * Executes the given [action] for each segment of the CharSequence split by the [delimiter].
 * This function avoids creating intermediate collections, making it more memory-efficient
 * for large strings or frequent calls compared to `String.split().forEach()`.
 *
 * Behavior notes:
 * - Empty input invokes no callbacks (produces zero segments).
 * - Leading/trailing/consecutive delimiters produce empty segments accordingly.
 * - The segment passed to [action] is a mutable, reused view over the original input.
 *   If you need to retain a segment after the callback returns, call `segment.toString()`.
 *
 * @param delimiter The character used to split the string.
 * @param action The action to perform on each segment. The segment is provided as a [CharSequence],
 */
inline fun CharSequence.forEachSplit(
    delimiter: Char,
    action: (segment: CharSequence) -> Unit
) {
    if (isEmpty()) return

    var startIndex = 0
    val segment = InPlaceCharSequence(this, 0,0)

    for (index in indices) {
        if (this[index] == delimiter) {
            segment.start = startIndex
            segment.end = index
            action(segment)
            startIndex = index + 1
        }
    }
    if (startIndex <= length) {
        segment.start = startIndex
        segment.end = length
        action(segment)
    }
}