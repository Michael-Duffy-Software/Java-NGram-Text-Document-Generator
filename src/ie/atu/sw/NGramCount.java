package ie.atu.sw;

// NGRAM COUNT : This class is responsible for storing the frequency of each ngram
public class NGramCount {

	// String to store NGram
	private String nGram;
	// Integer to store NGram frequency count
	private int count;

	// NGRAM COUNT : This constructor method creates an ngramcount with an ngram
	// string and count value
	public NGramCount(String nGram, int count) {
		this.nGram = nGram;
		this.count = count;
	}

	// SETTERS

	// SET NGRAM : This method sets the value of the ngram string
	public void setNGram(String nGram) {
		this.nGram = nGram;
	}

	// SET COUNT : This method sets the value of the frequency count
	public void setCount(int count) {
		this.count = count;
	}

	// GETTERS

	// GET NGRAM : This method gets the value of the ngram string
	public String getNGram() {
		return this.nGram;
	}

	// GET COUNT : This method gets the value of the frequency count
	public int getCount() {
		return this.count;
	}

}
