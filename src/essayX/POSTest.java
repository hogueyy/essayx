package essayX;

import static org.junit.Assert.*;
import org.junit.Test;

import com.google.appengine.api.datastore.Text;

public class POSTest
{
	protected Text testEssay = new Text("The quick brown fox jumped.");
	protected Extender ex;
	
	@Test
	public void testNoun()
	{
		ex = new Extender(testEssay, testEssay.getValue().length());
		POSTagger.tag(ex);
		
		for(int i = 0; i < ex.taggedWords.size(); i++)
		{
			if(ex.taggedWords.get(i).getPOS().equals("NN"))
			{
				assertTrue(ex.taggedWords.get(i).getOrigValue().equals("fox"));
			}
		}
	}
	
	@Test
	public void testPastVerb()
	{
		ex = new Extender(testEssay, testEssay.getValue().length());
		POSTagger.tag(ex);
		
		for(int i = 0; i < ex.taggedWords.size(); i++)
		{
			if(ex.taggedWords.get(i).getPOS().equals("VBD"))
			{
				assertTrue(ex.taggedWords.get(i).getOrigValue().equals("jumped"));
			}
		}
	}
	
	@Test
	public void testAdjective()
	{
		ex = new Extender(testEssay, testEssay.getValue().length());
		POSTagger.tag(ex);
		
		for(int i = 0; i < ex.taggedWords.size(); i++)
		{
			if(ex.taggedWords.get(i).getPOS().equals("JJ"))
			{
				assertTrue((ex.taggedWords.get(i).getOrigValue().equals("quick")) || (ex.taggedWords.get(i).getOrigValue().equals("brown")));
			}
		}
	}
}
