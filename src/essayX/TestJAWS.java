package essayX;

import edu.smu.tspell.wordnet.*;

/**
 * Displays word forms and definitions for synsets containing the word form
 * specified on the command line. To use this application, specify the word
 * form that you wish to view synsets for, as in the following example which
 * displays all synsets containing the word form "airplane":
 * <br>
 * java TestJAWS airplane
 */
public class TestJAWS
{

	/**
	 * Main entry point. The command-line arguments are concatenated together
	 * (separated by spaces) and used as the word form to look up.
	 */
	public static void main(String[] args)
	{

		if (args.length > -1)
		{
			
			System.setProperty("wordnet.database.dir", "C:\\Program Files (x86)\\WordNet\\2.1\\dict");// this points to your wordnet dictionary directory
			//  Concatenate the command-line arguments
			StringBuffer buffer = new StringBuffer();
			String[] keel={"walk"};
			for (int i = 0; i < keel.length; i++)
			{
				buffer.append((i > 0 ? " " : "") + keel[i]);
			}
			String wordForm = buffer.toString();
			//  Get the synsets containing the wrod form
			WordNetDatabase database = WordNetDatabase.getFileInstance();
			Synset[] synsets = database.getSynsets(wordForm);
			//  Display the word forms and definitions for synsets retrieved
			if (synsets.length > 0)
			{
				System.out.println("The following synsets contain '" +
						wordForm + "' or a possible base form " +
						"of that text:");
				for (int i = 0; i < synsets.length; i++)
				{
					System.out.println("");
					String[] wordForms = synsets[i].getWordForms();
					for (int j = 0; j < wordForms.length; j++)
					{
						System.out.print((j > 0 ? ", " : "") +
								wordForms[j]);
					}
					System.out.println(": " + synsets[i].getDefinition());
				}
			}
			else
			{
				System.err.println("No synsets exist that contain " +
						"the word form '" + wordForm + "'");
			}
		}
		else
		{
			System.err.println("You must specify " +
					"a word form for which to retrieve synsets.");
		}
	}

}