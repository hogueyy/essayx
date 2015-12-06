package essayX;

import java.util.ArrayList;

import com.google.appengine.api.datastore.Text;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class POSTagger {
	
	public static void tag(Extender ex){
		ex.taggedWords = processTagging(ex.originalText.getValue());
	}
	
	public static ArrayList<Word> processTagging(String s){
		MaxentTagger tagger = new MaxentTagger("english-left3words-distsim.tagger");
		ArrayList<Word> wordList = new ArrayList<Word>();
		
		String tagged = tagger.tagString(s);
		String[] words = tagged.split(" ");
		String value, pos;
		int index;
		for(String value_pos : words){
			index  = value_pos.indexOf('_');
			value = value_pos.substring(0, index);
			pos = value_pos.substring(index+1);
			wordList.add(new Word(value, pos));
		}
		return wordList;
	}
}
