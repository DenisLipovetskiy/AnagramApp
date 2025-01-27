package com.example.anagramapp

object Anagram {

    fun build(input: String, filter: String): String {
        val words = input.split(" ")
        val result = words.map { word ->
            val chars = word.toCharArray()
            var left = 0
            var right = chars.size - 1

            while (left < right) {
                if (filter.contains(chars[left])) {
                    left++
                    continue
                }
                if (filter.contains(chars[right])) {
                    right--
                    continue
                }

                chars[left] = chars[right].also { chars[right] = chars[left] }
                left++
                right--
            }

            String(chars)
        }

        return result.joinToString(" ")
    }
}
