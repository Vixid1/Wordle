import java.util.*

class Guess(num: Int, word: String) {
    private val guessNumber: Int = num
    private var chosenWord: String

    init {
        chosenWord = word.uppercase()
        // Check guessNumber is valid
        if (guessNumber < 1 || guessNumber > 6) {
            throw Exception("guessNumber out of range (Double parameter constructor)")
        }
        // Check chosenWord is valid
        if (!chosenWord.matches("^[a-zA-Z]{5}$".toRegex())) {
            throw Exception("chosenWord has invalid length or type")
        }
    }

    // Compare guess with target word and return the result
    // with correct, incorrect and misplaced letters represented
    // with ANSI colour codes
    fun compareWith(target: String): String {
        val result = StringBuilder()

        for (i in 0..4) {
            if (target.toCharArray()[i] == chosenWord.toCharArray()[i]) {
                result.append("\u001b[30;102m ").append(chosenWord.toCharArray()[i]).append(" \u001b[0m")
            } else if (target.contains(chosenWord.toCharArray()[i]) && !result.toString().contains(chosenWord.toCharArray()[i])) {
                result.append("\u001b[30;103m ").append(chosenWord.toCharArray()[i]).append(" \u001b[0m")
            } else {
                result.append("\u001b[30;107m ").append(chosenWord.toCharArray()[i]).append(" \u001b[0m")
            }
        }
        return result.toString()
    }

    // Check if guess is correct with target
    fun matches(target: String): Boolean {
        return Objects.equals(target, chosenWord)
    }
}