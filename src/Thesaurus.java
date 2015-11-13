import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class Thesaurus {
	
	Map<String,List<String>> thesaurus = new HashMap<String,List<String>>();
	
	
	public Thesaurus()
	{
		
	}
	
	void initalize()
	{
		
		thesaurus.put("and",Arrays.asList("along with"));
		thesaurus.put("are",Arrays.asList("are to be"));		
		thesaurus.put("is",Arrays.asList("is to be"	));	
		thesaurus.put("to",Arrays.asList("towards"));
		thesaurus.put("see",Arrays.asList("recognize"));
		thesaurus.put("seen",Arrays.asList("recognized"));	
		thesaurus.put("any",Arrays.asList("several"));		
		thesaurus.put("other",Arrays.asList( "form"));		
		thesaurus.put("as",Arrays.asList("just as"));		
		thesaurus.put("but",Arrays.asList("on the other hand"));	
		thesaurus.put("instead",Arrays.asList("as a substitute"));
		thesaurus.put("some",Arrays.asList("numerous"));
		thesaurus.put("use",Arrays.asList("use to their advantage"));		
		thesaurus.put("eat",Arrays.asList("consume"));	
		thesaurus.put("like",Arrays.asList("along the lines of"));		
		thesaurus.put("in fact",Arrays.asList("as a matter of fact"));
		thesaurus.put("always",Arrays.asList("at all times"));	
		thesaurus.put("now",Arrays.asList("at the present time"));	
		thesaurus.put("currently",Arrays.asList("at this point of time"));	
		thesaurus.put("because",Arrays.asList("because of the fact that","in light of the fact that","due to the fact that","for the reason that"));
		thesaurus.put("by",Arrays.asList("by means of"));	
		thesaurus.put("point out",Arrays.asList("draw to your attention"));	
		thesaurus.put("show",Arrays.asList("establish"));
		thesaurus.put("for",Arrays.asList("for the purpose of"));	
		thesaurus.put("be able to",Arrays.asList("have the ability to"));		
		thesaurus.put("can",Arrays.asList("have the ability to"));	
		thesaurus.put("to",Arrays.asList("in order to"));	
		thesaurus.put("about",Arrays.asList("in regards to","with reference to"));		
		thesaurus.put("although",Arrays.asList("in spite of the fact that"));
		thesaurus.put("though",Arrays.asList("in spite of the fact that"));	
		thesaurus.put("if",Arrays.asList("in the event that"));
		thesaurus.put("finally",Arrays.asList("in the final analysis"));	
		thesaurus.put("like",Arrays.asList("in the nature of"));
		thesaurus.put("decide on",Arrays.asList("make decisions about"));	
		thesaurus.put("when",Arrays.asList("on the occasion of"));
		thesaurus.put("twice",Arrays.asList("two separate occasions"));	
		thesaurus.put("the water rose",Arrays.asList("the level of the water rose"));	
		thesaurus.put("most",Arrays.asList("the majority of"));	
		thesaurus.put("the people in",Arrays.asList("the people who are located in"));	
		thesaurus.put("the pie in",Arrays.asList("the pie that is included in"));	
		thesaurus.put("until",Arrays.asList("until such time as"));

	}

	public String extendSentence(String sentence)
	{
		Random rand = new Random();
		Set<String> keys = thesaurus.keySet();
		String result = sentence;
		int synonymSize;
				
		for(String s : keys)
		{			
			if(sentence.contains(s))
			{				
		    	List<String> synonym = thesaurus.get(s);
		    	synonymSize = synonym.size();
				result = result.replaceAll("\\b" + s + "\\b", synonym.get(rand.nextInt(synonymSize)));
			}
		}
		
		return result;
	}
	
}
