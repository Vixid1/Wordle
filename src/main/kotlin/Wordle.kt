fun main(args: Array<String>) {

    // Check if any arguments are supplied and if so, play that provided game
    // Whilst also setting the game object
    val game: Game = if (args.size > 0) {
        Game(Integer.parseInt(args[0]), "src\\main\\resources\\words.txt")
    } else {
        Game("src\\main\\resources\\words.txt")
    }

    // Play the game and when finished save the game in a text file
    game.play()
    game.save("src\\main\\resources\\lastgame.txt")
}