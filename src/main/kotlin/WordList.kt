import java.nio.file.Files
import java.nio.file.Paths

class WordList(filename: String) {
    private val words: ArrayList<String>

    init {
        // Get all words from the words.txt file
        val path = Paths.get(filename)
        words = Files.readAllLines(path) as ArrayList<String>
    }

    // Get size of words array
    fun size(): Int {
        return words.toArray().size
    }

    // Get the word given the Wordle game number
    fun getWord(n: Int): String {
        if (n < 0 || n > words.size - 1) {
            throw Exception("gameNumber out of range")
        }
        return words[n]
    }
}