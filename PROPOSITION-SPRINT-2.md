Proposition to merge WordOfTheDay.java and WordRepo.java

Currently: 

WordOfTheDay is a class that is constructed with or without a word parameter, which does not directly use the database to find the word. It can currently find a random word from a supplied array, or theoretically it will just call the WordRepo class to do any searching

WordRepo is a class that directly accesses the database, with a method to create a list/mapping of WordOfTheDay objects from every entry within the database. 


Things discussed by me and Mason:

Is it truly necessary to have an object version of the WordOfTheDay? 
We just need a singular command in the beginning of our controller class to create a daily word. (what would an object add to that versus just a string?)
We would like a second command to return a true/false if the input is a valid guess (in the guess class, there is no comparison to a WordOfTheDay object, just the literal char[] version of the word)
Possibly (future implementation is we have time, not necessary with current state of wordle) have the ability to look for previous words of the day. Would an object be necessary for this or would we just have a direct search from the DB of the previous dates of a certain time frame? (like past ____ days of type int as an input) 


Proposal:

Since we have DB connection and know how to use the spring boot implementation, we could instead have the following singular class to replace WordRepo and WordOfTheDay

(could still be named WordRepo, also discussed just naming it ‘Word’ for more clarity)

class Word {

//top part would have all the repo stuff from the current WordRepo class, lines 1-30 in current class

//string or char[] version, depending on which will work better with player/guess classes
public string/char[] getWord(){

//in this class, we can grab the local date with the LocalDateTime, and then search DB for existing word with given date. If not, we just search for a random word that has a null value for its date. We will need to both read and write in this method, so that will need to be discussed. When word is found, set the date in the DB and return the word.

}

//again, string/char[] depending on what group decides
public boolean isValid(string/char[]) {

	//this method just takes in a guess in string format and searches in DB if it exists. 
	If it does, true is returned. Otherwise, false. Only read is necessary here

}

//possible third method discussed in #3 in list above, where pastWordAmount is the
number of past words we want to get. Could also be just public String getWordAtDate(int
date), where the controller can create a loop of the specified date period to find the
words (example, finding the word of the day for the first week of october, since
getRecentWords only will find the recent words starting from the current date)
public list<String> getRecentWords(int pastWordAmount) {

//checks the database for the words of past dates. Likely will only need read,
however we may be looking into dates that haven’t been given words, so we
could either set the words in this method or just call getWord() in here on the
dates that have no declared word (which would keep this method just read, and
ensures that the only method that has write privileges is getWord). Would be
same idea if we want to have getWordAtDate instead.

}
}

Concluding Thoughts:

When I spoke with Scott, he said that removing past work does not affect whoever did that work (in the case for Isha and Mason who already worked hard with the implementation we currently have). Since I know both of you guys have already had a very stressful time with the DB work I could also just try and help with this to get it done, and either of you could step away from this if you want to work on something else. Overall, what’s most important is what the entire group thinks will be most beneficial and since it is also your work, it’s more important that you are okay with the idea. 

That all being said, I already did what I initially committed to this sprint, and have completely open availability today through Monday morning and could get it done quickly for those working on the Frontend/Backend connection.

