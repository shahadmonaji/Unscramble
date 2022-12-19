package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    //save data if ui is changed
    private var _score = 0

    private var _count = 0

    private var _currentScrambledWord = "test"
    private var _currentWordCount = 0


     val score :Int
         get() = _score


//    private var _count = 0
//    private var _currentScrambledWord = "test"
    val count: Int
        get() = _count

    val currentScrambledWord:String
        get() = _currentScrambledWord

    val currentWordCount: Int
        get() = _currentWordCount


    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String


    init {
        getNextWord()
    }

    private fun getNextWord() {


        currentWord = allWordsList.random()


        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            // if true
            // keep shuffle()
            tempWord.shuffle()
        }


        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }

    } // End getNextWord()


    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }else
        return false
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
        getNextWord()
    }




}