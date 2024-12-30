import React, { useState } from 'react';
import styles from './WordleUI.module.css';
import WordleTile from './WordleTile';

const WORD_LENGTH = 5;
const MAX_ATTEMPTS = 6;
const correctWord = 'react'; // Example word, replace with backend word from API

/**
 * Handles Running the UI for Wordle
 * @returns The UI for the game
 */
const WordleGame = () => {
  const [attempts, setAttempts] = useState([]);
  const [currentGuess, setCurrentGuess] = useState('');

  const handleInputChange = (e) => {
    const guess = e.target.value.toLowerCase();
    if (guess.length <= WORD_LENGTH) {
      setCurrentGuess(guess);
    }
  };

  const handleSubmitGuess = () => {
    if (currentGuess.length === WORD_LENGTH) {
      setAttempts([...attempts, currentGuess]);
      setCurrentGuess('');
    }
  };

  //I made a function because it was simple enough to determine the correct tiles for testing but 
  //we can switch it to backend later if we wish.
  const renderTiles = (word, index) => (
    <div key={index} className={styles.row}>
      {[...word].map((letter, i) => (
        <WordleTile key={i} letter={letter} correctWord={correctWord} index={i} />
      ))}
    </div>
  );

  const renderEmptyTiles = () => {
    const emptyRows = Array.from({ length: MAX_ATTEMPTS - attempts.length }, (_, index) => (
      <div key={`empty-${index}`} className={styles.row}>
        {Array.from({ length: WORD_LENGTH }, (_, i) => (
          <WordleTile key={i} letter="" correctWord={correctWord} index={i} />
        ))}
      </div>
    ));
    return emptyRows;
  };

  return (
    <div className={styles.gameContainer}>
      <h1>Wordle Game</h1>
      {attempts.map((attempt, index) => renderTiles(attempt, index))}
      {renderEmptyTiles()}
      {attempts.length < MAX_ATTEMPTS && (
        <input
          type='text'
          value={currentGuess}
          onChange={handleInputChange}
          className={styles.input}
        />
      )}
      <button onClick={handleSubmitGuess} className={styles.submitButton}>
        Submit Guess
      </button>
    </div>
  );
};

export default WordleGame;
