import React from 'react';
import styles from './WordleUI.module.css';

/**
 * Cr
 * @param {*} param0 
 * @returns 
 */
const WordleTile = ({ letter, correctWord, index }) => {
  let tileStyle = styles.tile;

  if (correctWord[index] === letter) {
    tileStyle = styles.correct;
  } else if (correctWord.includes(letter)) {
    tileStyle = styles.present;
  } else {
    tileStyle = styles.absent;
  }

  return <div className={tileStyle}>{letter}</div>;
};

export default WordleTile;
