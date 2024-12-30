import React, { useEffect, useState } from "react";

const Keypad = ({ themeStyles, refresh }) => {
  const [darkGrayKeys, setDarkGrayKeys] = useState([]); // Keys with color code 1 (dark gray)
  const [yellowKeys, setYellowKeys] = useState([]); // Keys with color code 2 (yellow)
  const [greenKeys, setGreenKeys] = useState([]); // Keys with color code 3 (green)


  const fetchKeys = async (value, setState) => {
    try {
      const response = await fetch(`http://localhost:8080/api/correctnessMap?value=${value}`);
      if (!response.ok) {
        throw new Error(`Server responded with status ${response.status}`);
      }
      const data = await response.json();
      setState(data); // Update the corresponding state with the fetched keys
    } catch (error) {
      console.error(`Error fetching keys for color code ${value}:`, error);
    }
  };

  const refreshKeys = () => {
    fetchKeys(1, setDarkGrayKeys);
    fetchKeys(2, setYellowKeys);
    fetchKeys(3, setGreenKeys);
  };

  useEffect(() => {
    refreshKeys(); // initial fetch
  }, []);

  useEffect(() => {
    refreshKeys(); // re-fetch for refresh changes
  }, [refresh]);

  const getKeyColor = (key) => {
    if (greenKeys.includes(key)) return themeStyles.green;
    if (yellowKeys.includes(key)) return themeStyles.yellow;
    if (darkGrayKeys.includes(key)) return themeStyles.darkgray;
    return themeStyles.key; // Default key style
  };

  const rows = ["QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"];

  return (
    <div className={themeStyles.keypad}>
      {rows.map((row, index) => (
        <div key={index} className={themeStyles.keyRow}>
          {row.split("").map((key) => (
            <button key={key} className={getKeyColor(key)}>
              {key}
            </button>
          ))}
        </div>
      ))}
    </div>
  );
};

export default Keypad;