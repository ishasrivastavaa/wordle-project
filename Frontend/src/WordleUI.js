import React, { useState, useEffect } from 'react';
import WordleTile from './WordleTile';
import Keypad from './Keypad'; // Adjust the path based on your folder structure

const WORD_LENGTH = 5;
const MAX_ATTEMPTS = 6;

/**
 * Handles Running the UI for Wordle
 * @returns The UI for the game
 */
const WordleGame = () => {
  const [attempts, setAttempts] = useState([]);
  const [currentGuess, setCurrentGuess] = useState('');
  const [correctWord, setCorrectWord] = useState('');
  const [gotWord, setGotWord] = useState(false);
  const [showNotification, setShowNotification] = useState(false);
  const [customTipMode, setCustomTipMode] = useState(false);
  const [customTip, setCustomTip] = useState('');
  const [theme, setTheme] = useState('Light');
  const [styles, setStyles] = useState(null);
  const [correctness, setCorrectness] = useState([]);
  const [letters, setLetters] = useState([]);
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const [refreshKeypad, setRefreshKeypad] = useState(false);

  // Track the last row index that was just submitted, to trigger animation
  const [lastSubmittedRow, setLastSubmittedRow] = useState(null);

  const toggleDropdown = () => {
    setDropdownOpen((prev) => !prev); // Toggle dropdown state
  };

  const handleOptionClick = (option) => {
    setTheme(option); // Set the selected theme
    setDropdownOpen(false); // Close the dropdown
  };
  
  // Dynamically load the correct stylesheet based on theme selection
  useEffect(() => {
    const loadStyles = async () => {
      let chosenStyles;
      switch (theme) {
        case 'Dark':
          chosenStyles = await import('./DarkTheme.module.css');
          break;
        case 'Dark with High Contrast':
          chosenStyles = await import('./DarkContrastTheme.module.css');
          break;
        case 'Light with High Contrast':
          chosenStyles = await import('./LightContrastTheme.module.css');
          break;
        case 'Potions':
          chosenStyles = await import('./PotionTheme.module.css');
          break;
        case 'Impossible':
          chosenStyles = await import('./ImpossibleTheme.module.css');
          break;
        default:
          chosenStyles = await import('./WordleUI.module.css'); // Default to Light theme
      }
      setStyles(chosenStyles.default);
    };
    loadStyles();
  }, [theme]);

  // Fetch the word of the day when the component mounts
  useEffect(() => {
    const fetchWordOfTheDay = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/dailyWord');
        const word = await response.text();
        setCorrectWord(word.toUpperCase().trim());  // Assuming the word is plain text
      } catch (error) {
        setCorrectWord("ERROR");
        console.error('Error fetching the word of the day:', error);
      }
    };
    fetchWordOfTheDay();
  }, []);

  const handleInputChange = (e) => {
    const guess = e.target.value.toUpperCase();
    // Use a regular expression to allow only alphabetical characters
    const filteredGuess = guess.replace(/[^A-Z]/g, '');
    if (filteredGuess.length <= WORD_LENGTH) {
      setCurrentGuess(filteredGuess);
    }
  };

  const handleSubmitGuess = async () => {
    if (currentGuess.length === WORD_LENGTH && (await checkIfWord()) && !gotWord) {
      const newAttempts = [...attempts, currentGuess];
      setAttempts(newAttempts);
      setCurrentGuess('');
      if (currentGuess === correctWord) {
        setGotWord(true);
      }
      await fetchUpdatedData();
      setRefreshKeypad((prev) => !prev);

      // Set the last submitted row index to trigger the flip-like animation
      setLastSubmittedRow(newAttempts.length - 1);
    }
  };

  const handleCustomTip = () => {
    setCustomTipMode(true);
  };

  const handleShowTipScreen = () => {
    setShowNotification(true);
  };

  const handleOtherButtonClick = () => {
    setShowNotification(false);
    setCustomTipMode(false);
  };

  const checkIfWord = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/validate?word=${currentGuess}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      });
      const isValid = await response.json();
      return isValid; // Return true if valid, false otherwise
    } catch (error) {
      console.error("Error validating word:", error);
      return false;
    }
  };

  useEffect(() => {
    const fetchInitialData = async () => {
      try {
        const correctnessResponse = await fetch('http://localhost:8080/api/correctness');
        const initialCorrectness = await correctnessResponse.json();
        setCorrectness(initialCorrectness);
    
        const lettersResponse = await fetch('http://localhost:8080/api/letters');
        const initialLetters = await lettersResponse.json();
        setLetters(initialLetters);
      } catch (error) {
        setCorrectness("ERROR");
        setLetters("ERROR");
        console.error('Error fetching initial data:', error);
      }
    };
    fetchInitialData();
  }, []);

  const fetchUpdatedData = async () => {
    try {
      const correctnessResponse = await fetch('http://localhost:8080/api/correctness');
      const newCorrectness = await correctnessResponse.json();
      console.log('Fetched correctness:', newCorrectness);
      setCorrectness(newCorrectness);
  
      const lettersResponse = await fetch('http://localhost:8080/api/letters');
      const newLetters = await lettersResponse.json();
      console.log('Fetched letters:', newLetters);
      setLetters(newLetters);
    } catch (error) {
      console.error('Error fetching updated data:', error);
    }
  };

  const renderTiles = (word, index) => {
    return (
      <div key={index} className={styles.row}>
        {[...word].map((letter, i) => (
          <WordleTile key={i} letter={letter} correctWord={correctWord} index={i}  styles={styles} />
        ))}
      </div>
    );
  };

  const renderEmptyTiles = () => {
    const emptyRows = Array.from({ length: MAX_ATTEMPTS - attempts.length }, (_, index) => (
      <div key={`empty-${index}`} className={styles.row}>
        {Array.from({ length: WORD_LENGTH }, (_, i) => (
          <WordleTile key={i} letter=" " correctWord={correctWord} index={i} styles={styles} />
        ))}
      </div>
    ));
    return emptyRows;
  };

  const renderTilesWithCorrectness = () => {
    let foundFirstNonNull = false; // Tracks when to start rendering tiles based on fetchLettersArray
  
    return Array.from({ length: MAX_ATTEMPTS }, (_, rowIndex) => {
      const word = letters[rowIndex]; // Get the word for the current row
      const correctnessRow = correctness[rowIndex] || []; // Get the correctness row
  
      if (!foundFirstNonNull && word) {
        foundFirstNonNull = true; // Mark the point where rendering begins
      }
  
      if (!foundFirstNonNull) {
        // Render initial empty tiles before any non-null row is found
        return (
          <div key={`empty-${rowIndex}`} className={styles.row}>
            {Array.from({ length: WORD_LENGTH }, (_, colIndex) => (
              <div key={colIndex} className={styles.absent}></div>
            ))}
          </div>
        );
      }
  
      // Apply the reveal animation class if this row was the last submitted
      const rowClass = rowIndex === lastSubmittedRow ? styles.revealRow : '';

      // Render tiles dynamically based on correctness once a non-null word is found
      return (
        <div key={rowIndex} className={`${styles.row} ${rowClass}`}>
          {Array.from({ length: WORD_LENGTH }, (_, colIndex) => {
            const letter = word ? word[colIndex] || ' ' : ' ';
            const correctnessValue = correctnessRow[colIndex] || 0;
  
            let tileStyle;
            switch (correctnessValue) {
              case 3:
                tileStyle = styles.correct;
                break;
              case 2:
                tileStyle = styles.present;
                break;
              case 1:
                tileStyle = styles.absent;
                break;
              default:
                tileStyle = styles.notLetter;
            }
  
            return (
              <div key={colIndex} className={tileStyle}>
                {letter}
              </div>
            );
          })}
        </div>
      );
    });
  };

  const handleChange = (event) => {
    setTheme(event.target.value);
  };

  useEffect(() => {
    console.log('Correctness updated:', correctness);
    console.log('Letters updated:', letters);
  }, [correctness, letters]);

  if (!styles) return null; // Wait for styles to load

  // If user exhausted all attempts without success, apply shake animation
  const gameContainerClass = (attempts.length === MAX_ATTEMPTS && !gotWord) ? styles.shake : '';

  return (
    <div className={`${styles.gameContainer} ${gameContainerClass}`}>
        <div className={styles.themeSelect}>
          <div className={`${styles.themeDropdown} ${dropdownOpen ? styles.open : ''}`}>
            <button onClick={toggleDropdown} className={styles.dropdownButton}>
              Change Theme
              <span className={styles.dropdownIcon}>▾</span>
            </button>
            {dropdownOpen && (
              <div className={styles.options}>
                {['Light', 'Light with High Contrast', 'Dark', 'Dark with High Contrast', 'Potions', 'Impossible'].map((option) => (
                  <div
                    key={option}
                    className={styles.option}
                    onClick={() => handleOptionClick(option)}
                  >
                    {theme === option ? `${option} (current)` : option}
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>

      <div className={styles.logo}></div>
      {renderTilesWithCorrectness()}
      {attempts.length < MAX_ATTEMPTS && !gotWord && (
        <>
          <input
            type='text'
            value={currentGuess}
            onChange={handleInputChange}
            className={styles.input}
          />
          <button onClick={handleSubmitGuess} className={styles.submitButton}>
            Submit Guess
          </button>
        </>
      )}
      {gotWord && <p>Congratulations! You guessed the correct word!</p>}
      {gotWord && <button className={styles.submitButton} onClick={handleShowTipScreen}>Yay!</button>}
      {!gotWord && <Keypad themeStyles={styles} refresh={refreshKeypad} />}
      {showNotification && (
        <div className={styles.notification}>
          <div className={styles.notificationBox}>
            {customTipMode ? (
              <div>
                <div className={styles.tipCustomDollarFormat}>
                  <h1>$</h1>
                  <input
                    type="text"
                    value={customTip}
                    onChange={(e) => {
                      const value = e.target.value;
                      // Regex to match valid monetary values: digits optionally followed by a period and up to 2 decimals
                      if (/^\d*(\.\d{0,2})?$/.test(value)) {
                        setCustomTip(value);
                      }
                    }}
                    placeholder="Enter your custom tip"
                    className={styles.customTipInput}
                  />
                </div>
                <div></div>
              <button className={styles.submitButton} onClick={handleOtherButtonClick}>Submit</button>
              </div>
            ) : (
              <>
                <div className={styles.tipTitle}>Add a tip?</div>
                <div className={styles.grid}>
                  <button onClick={handleOtherButtonClick} className={styles.gridItem}>$1.00</button>
                  <button onClick={handleOtherButtonClick} className={styles.gridItem}>$5.00</button>
                  <button onClick={handleOtherButtonClick} className={styles.gridItem}>$100.00</button>
                </div>
                <button onClick={handleCustomTip} className={styles.gridItem}>Custom Tip</button>
                <button onClick={handleOtherButtonClick} className={styles.gridItem}>No Tip :(</button>
              </>
            )}
          </div>
        </div>
      )}
    </div>
  );
};

export default WordleGame;
