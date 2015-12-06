package essayX;

import java.util.ArrayList;
import java.util.Comparator;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Word {
	private String orig_value;
	private String new_value;
	private String pos;
	@Id Long id;
	
	private Word(){}
	
	public Word(String v, String p){
		orig_value = v;
		pos = p;
		//new_value = "";
	}
	
	public int getOrigLength(){
		return orig_value.length();
	}
	public String getValue(){
		if(new_value!=null)
			return new_value;
		else
			return orig_value;
	}
	
	public int getLength(){
		if(new_value!=null)
			return new_value.length();
		else
			return orig_value.length();
	}
	
	public int getNewLength(){
		if(new_value == null){ return -1;}
		return new_value.length();
	}
	
	public String getPOS(){
		return pos;
	}
	
	public String getOrigValue(){
		return orig_value;
	}
	
	public String getNewValue(){
		return new_value;
	}
	
	public void setOrigValue(String s){
		orig_value = s;
	}
	
	public void setNewValue(String s){
		new_value = s;
	}
	
	/* public static class CompareWords implements Comparator<Word>{
		public int compare(Word w1, Word w2){
			if(w1.getLength() > w2.getLength()){ return 1; }
			else if(w1.getLength() < w2.getLength()){ return -1; }
			else{ return 0; }
		}
	}
	*/
	
	public static void print(ArrayList<Word> wordList){
		for(Word w : wordList){
			System.out.print(w.getValue() + " ");
		}
		System.out.println();

	}
	public String toString()
	{
		return this.getValue();
	}
}
