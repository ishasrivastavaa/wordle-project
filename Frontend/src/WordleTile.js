import React from 'react';

/**
 * Cr
 * @param {*} param0 
 * @returns 
 * 
 */
const WordleTile = ({ letter, correctWord, index, styles }) => {
  let tileStyle = styles.tile;

  if (correctWord[index] === letter) {
    tileStyle = styles.correct;
  } else if (correctWord.includes(letter)) {
    tileStyle = styles.present;
  } else if (letter === " "){
    tileStyle = styles.notLetter;
  } else {
    tileStyle = styles.absent;
  }

  return <div className={tileStyle}>{letter}</div>;
};

export default WordleTile;
