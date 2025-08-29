package kubit.collections.inplace


/**
 * Returns a sublist of the first [n] elements.
 * The sublist is a view into the original list. Changes in the original list will be reflected in the sublist.
 *
 * @param n The number of elements to take.
 * @return A list containing the first [n] elements.
 */
fun <T> List<T>.takeInPlace(n: Int): List<T> {
    if (n == 0) return emptyList()
    return InPlaceSubList(this, 0, (n - 1).coerceAtMost(lastIndex))
}

/**
 * Returns a sublist of the last [n] elements.
 * The sublist is a view into the original list. Changes in the original list will be reflected in the sublist.
 *
 * @param n The number of elements to take from the end.
 * @return A list containing the last [n] elements.
 */
fun <T> List<T>.takeLastInPlace(n: Int): List<T> {
    if (n == 0) return emptyList()
    return InPlaceSubList(this, (size - n).coerceAtLeast(0), lastIndex)
}

/**
 * Returns a sublist containing all elements except first [n] elements.
 * The sublist is a view into the original list. Changes in the original list will be reflected in the sublist.
 *
 * @param n The number of elements to drop from the beginning.
 * @return A list containing all elements except first [n] elements.
 */
fun <T> List<T>.dropInPlace(n: Int): List<T> {
    if (n > size) return emptyList()
    return InPlaceSubList(this, n, lastIndex)
}

/**
 * Returns a sublist containing all elements except last [n] elements.
 * The sublist is a view into the original list. Changes in the original list will be reflected in the sublist.
 *
 * @param n The number of elements to drop from the end.
 * @return A list containing all elements except last [n] elements.
 */
fun <T> List<T>.dropLastInPlace(n: Int): List<T> {
    if (n > size) return emptyList()
    return InPlaceSubList(this, 0, lastIndex - n)
}
