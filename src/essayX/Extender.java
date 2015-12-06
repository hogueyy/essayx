package essayX;
import java.util.ArrayList;
import java.util.Random;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Extender
{
	@Id Long id;
    public Text originalText;
    public Text extendedText;
    public ArrayList<Word> taggedWords;
    public int num_original_chars;
    public int num_extended_chars;
    ArrayList<String> pos = new ArrayList<String>();
    
    private Extender() {}
    
    public Extender(Text orig, int num_chars)
    {
    	originalText = orig;
        extendedText = orig;
        num_original_chars = num_chars;
        pos.add("noun");
        pos.add("verb");
        pos.add("adjective");
        pos.add("adverb");
    }
    
    public Extender(Text orig, int num_chars, ArrayList<String> p)
    {
        originalText = orig;
        num_original_chars = num_chars;
        pos = p;
        Random r = new Random(); 
        id = (long) r.nextInt(1000000000);
    }
}
