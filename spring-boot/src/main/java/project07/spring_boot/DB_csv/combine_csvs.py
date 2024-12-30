# This code will combine the words in wordle.csv and valid_guesses.csv as valid guesses
import csv

# Read the first column of wordle.csv
with open('wordle.csv', 'r', newline='') as wordle_file:
    wordle_reader = csv.reader(wordle_file)
    wordle_words = {row[0] for row in wordle_reader if row}  # Store words in a set

# Read the first column of valid_guesses.csv
with open('valid_guesses.csv', 'r', newline='') as guess_file:
    guess_reader = csv.reader(guess_file)
    guess_words = {row[0] for row in guess_reader if row}  # Store words in a set

# Find words in wordle.csv that are not in valid_guess.csv
unique_words = wordle_words.difference(guess_words)

# Union the words from valid_guess.csv and the unique words
combined_words = guess_words.union(unique_words)

# Write the result to a new comb_valid_guess.csv file
with open('comb_valid_guesses.csv', 'w', newline='') as output_file:
    writer = csv.writer(output_file)
    for word in sorted(combined_words):  # Sorted comb_valid_guess.csv
        writer.writerow([word])