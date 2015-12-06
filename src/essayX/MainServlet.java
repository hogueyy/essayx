package essayX;

import essayX.Extender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class MainServlet extends HttpServlet{
	static {
       ObjectifyService.register(Extender.class);
       ObjectifyService.register(Word.class);
    }

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        String essay = req.getParameter("essay");
        String verbs = req.getParameter("verbs");
        String[] verbPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"}; //
        String adverbs = req.getParameter("adverbs");
        String[] adverbPOS = {"RB", "RBR", "RBS"};//
        String nouns = req.getParameter("nouns");
        String[] nounPOS = {"NN",  "NNP"};//"NNS",, "NNPS"
        String adjectives = req.getParameter("adjectives");
        String[] adjectivePOS = {"JJ", "JJR", "JJS"};//
        
        ArrayList<String> pos = new ArrayList<String>();
        if(verbs != null){for(String s : verbPOS){ pos.add(s);}}
        if(adverbs != null){for(String s : adverbPOS){ pos.add(s);}}
        if(nouns != null){for(String s : nounPOS){ pos.add(s);}}
        if(adjectives != null){for(String s : adjectivePOS){ pos.add(s);}}
        
        Extender ex = new Extender(new Text(essay), essay.length(), pos);
        extend(ex);
        ObjectifyService.ofy().save().entity(ex).now();
        resp.sendRedirect("/extendedEssay.jsp?id=" + ex.id);
    }
	
	public static Extender extend(Extender ex){
		POSTagger.tag(ex);
		ParseEssay p = new ParseEssay();
		ArrayList<Word> extendedWords = new ArrayList<Word>();
		int fromIndex = 0;
		int toIndex = 0;
		String punctuation = ".!?";
		for(Word w : ex.taggedWords){
			List<Word> sentence = new ArrayList<Word>();
			if(punctuation.contains(w.getOrigValue())){
				sentence = ex.taggedWords.subList(fromIndex, toIndex + 1);
				extendedWords.addAll(p.overhaulSentence(new ArrayList<Word>(sentence), ex.pos));
				fromIndex = toIndex + 1;
			}
			toIndex += 1;
		}
		ex.taggedWords = extendedWords;
		int count = 0;
		for(Word w : ex.taggedWords){ count = count + w.getLength() + 1;}
		ex.num_extended_chars = count;
		return ex;
	}
}