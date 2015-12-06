package essayX;

import edu.smu.tspell.wordnet.*;

import java.util.Collection;
import java.util.List;
import java.io.StringReader;

import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;









import java.util.*;
import java.io.*;



public class ParseEssay {

ArrayList<String>/*Words?*/ synonyms;
lengthComparator myLengthComparator=new lengthComparator();
WordNetDatabase database;
ByteArrayOutputStream baos;
LexicalizedParser lp;


	public static void main(String[] args)
	{
		ParseEssay p = new ParseEssay();
		ArrayList<Word> sent=new ArrayList<Word>();
		sent.add(new Word("These","o"));//fake POS o and p
		sent.add(new Word("are", "o"));
		sent.add(new Word("balls","p"));
		ArrayList<String> pars=new ArrayList<String>();
		pars.add("p");
		System.out.println(p.overhaulSentence(sent, pars));
	}


	public ParseEssay()
	{
		System.setProperty("wordnet.database.dir", "../war/dict");
		synonyms=new ArrayList<String>();
		database = WordNetDatabase.getFileInstance();
		baos = new ByteArrayOutputStream();
		lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");

	//??	
	}
	
	/**
	 * overhaulSentence is the main method of this class, that will search all words, 
	 * and attempt to swap the words that have a  part of speech
	 * that we are swapping.
	 * 
	 * @param sentence
	 * @param partsSearching
	 * @return new sentence with words replaced
	 */
	public ArrayList<Word> overhaulSentence(ArrayList<Word> sentence, ArrayList<String> partsSearching)
	{
		int index=0;
		String k;
		for( Word w: sentence)//iterate through sentence, check words, set newVals to synonyms
		{
			for(String part: partsSearching)
			{
				if( w.getPOS().equals(part))
				{
					k=findSynonym(sentence,index);
					if(w.getNewLength()<=w.getOrigLength())
						w.setNewValue(null);
				}
			}
			index++;
		}
		
		return sentence;
	}
	
	
	private String findSynonym(ArrayList<Word> sentence, int indexToReplace/*<Word wordIn>*/ )
	{
		/**Takes in one String of a word, hopefully only one word(will truncate after space), and searches for a suitable synonym
		 * 
		 * **/
		String output="";
		//String word;
		//word=wordIn.getString();		
		String word=sentence.get(indexToReplace).getValue();
		Synset[] synsets = database.getSynsets(word);
		
		
		synonyms.clear();

		if (synsets.length > 0)
		{
			
			for (int i = 0; i < synsets.length; i++)
			{

				String[] wordForms = synsets[i].getWordForms();
				for (int j = 0; j < wordForms.length; j++)
				{
					if(wordForms[j].contains(" "))
					{System.out.println( wordForms[j]);}else
					if(!wordForms[j].equals(word))
					synonyms.add(wordForms[j]);
				}

				
				
			}
			
			//FIND IF PLURAL OR NO HERE			
			
			
///////////////////////////###########################////////////////////
			
			
			Collections.sort(synonyms,myLengthComparator);
			//synonyms is now sorted as longest first
			
			//now we have a list of all the terms that can replace the word. 
			//We need to check them in the lexical analyzer
//			System.out.println("kkkk");
			if(synonyms.size()==0)
				return word;
			while(!LexicalAnalyzer(sentence,indexToReplace,synonyms.get(0)))
			{
	//			System.out.println("1");
				synonyms.remove(0);
				if(synonyms.size()==0)
					return word;// did not find synonym
				
				if(synonyms.get(0).length()<=word.length())
				{
					return word;
					//do not replace word, synonyms are shorter
				}
			}
			
			
			//////////////############# PLACEHOLDER CODE
			if(synonyms.size()>0)
			output=synonyms.get(0);
			else
			output=word;
			/////////////################# PLACEHOLDER CODE
		}
		else
		{//There are no other known synonyms in our wordnet database.
			output=word;
		}
		
		
		return output;
	}
	
	private boolean LexicalAnalyzer(ArrayList<Word> words, int index, String newWord)
	{
		String[] sent=toSentence(words);
		///lexical analyzer
		List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);
	    Tree parse = lp.apply(rawWords);
	    
//		PrintStream outa = new PrintStream(new FileOutputStream("output1.txt"));
		
//	    System.setOut(outa);
//	    System.out.println("KKKKKKK");
//	    parse.pennPrint();
	    String oldTree=parse.toString();
//	    String oldTree=baos.toString();
//	    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
//	    System.out.println(oldTree);
	    

	    words.get(index).setNewValue(newWord);
	    sent=toSentence(words);
	    rawWords=Sentence.toCoreLabelList(sent);
	    parse=lp.apply(rawWords);
//	    PrintStream outb = new PrintStream(new FileOutputStream("output2.txt"));
//	    System.setOut(outb);
	    
//	    parse.pennPrint();
	    String newTree=parse.toString();
	    
	    oldTree=oldTree.replaceAll(words.get(index).getOrigValue()+"[)]", newWord+")");
//	    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	    System.out.println(oldTree+"\n"+newTree);

//	    	System.out.println(oldTree.equals(newTree));
	    	
	    if(oldTree.equals(newTree))
	    {	if(index==0)
	    	{	String str=words.get(index).getNewValue();
	    	String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
	    	words.get(index).setNewValue(cap);
	    	}
	    	return true;
	    }
	    else
	    {
	    	words.get(index).setNewValue(null);
	    	return false;
	    }
	    
	    
		/* catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}*/
		
//		return true;
	}
	private String[] toSentence(ArrayList<Word> words)
	{
		String[] sent=new String[words.size()];
		for(int k=0; k<words.size(); k++)
		{
			sent[k]=words.get(k).getValue();
		}
		return sent;
	}
}

class lengthComparator implements java.util.Comparator<String>
{
	public lengthComparator(){
		super();
	}
	
	public int compare(String a, String b)
	{
		return -a.length()+b.length();
	}
}