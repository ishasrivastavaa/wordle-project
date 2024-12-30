# Writes to the second column in comb_valid_guesses.csv
import pandas as pd

# skip the first row (header)
valid_solutions = pd.read_csv("valid_solutions.csv", header=None)

# Extract all rows starting from the second row
valid_solutions_set = set(valid_solutions.iloc[1:, 0])

# didnt skip first row
comb_valid_guesses = pd.read_csv("comb_valid_guesses.csv", header=None)

result_df = pd.DataFrame()
# copy first column
result_df['word'] = comb_valid_guesses[0]
# fill second column with boolean and convert to string
result_df['can_be_daily'] = result_df['word'].isin(valid_solutions_set).replace({True: 'True', False: 'False'})

# Write back to comb_valid_guesses.csv
result_df.to_csv("comb_valid_guesses.csv", index=False, header=True)