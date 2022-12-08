import java.io.File
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.regex.Pattern

class Game {
    private var gameNumber: Int = 0
    private val target: String
    private val output: ArrayList<String> = ArrayList()
    private val ansiCodes: Pattern = Pattern.compile("(\u001B\\[30;10\\dm)|(\u001B\\[0m)")

    constructor(filename: String) {
        // Wordle start date and today's date
        val startDate: LocalDate = LocalDate.of(2021, 6, 19)
        val localDate: LocalDate = LocalDate.now()

        // Calculate difference in days to find today's word
        gameNumber = ChronoUnit.DAYS.between(startDate, localDate).toInt()

        // Get today's word from the words text file
        val list = WordList(filename)
        target = list.getWord(gameNumber)

        println("WORDLE #${gameNumber}")
    }

    constructor(gameNumber: Int, filename: String) {
        // Get the word from the given Wordle game number
        val list = WordList(filename)
        target = list.getWord(gameNumber)

        println("WORDLE #${gameNumber}")
    }

    // Main function to play the game and iterate guesses
    fun play() {

        // Give the user 6 guesses
        for (i in 1..6) {

            // Take an input from user
            val input = Scanner(System.`in`)
            print("Enter guess ($i/6): ")
            if (input.hasNext()) {
                val guess = input.next()

                // Create a guess object
                val guessObj = Guess(i, guess)
                // Compare guess with target word
                output.add(guessObj.compareWith(target))
                // Print the guess with ANSI codes to
                // signify correct, incorrect and misplaced letters
                println(output[i-1])

                // If all guesses failed
                if (!guessObj.matches(target) && i == 6) {
                    println("Better luck next time!")
                    break
                }

                // If guess was correct give a different congratulation
                // message based on how many guesses it took
                if (guessObj.matches(target)) {
                    if (i == 1) {
                        println("Got it in one!")
                        break
                    } else if (i < 6) {
                        println("Well done")
                        break
                    } else {
                        println("That was close!")
                        break
                    }
                }
            }
        }
    }

    // Function to save the game to a text file
    fun save(filename: String) {
        try {
            // Use buffered writer to write to file
            File(filename).bufferedWriter().use { out ->
                // Iterate over the guesses
                for (s: String in output) {
                    // Strip the whitespace and ANSI codes
                    var clean = s.replace(" ", "")
                    clean = clean.replace(ansiCodes.toRegex(), "")
                    // Write to file
                    out.write(clean + "\n")
                }
            }
        // Catch exceptions i.e. IOExecptions
        } catch (e: Exception) {
            println("Writing game to save file failed!")
        }
    }
}