package essayX;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.appengine.api.datastore.Text;

public class ExtendTest
{
	protected Text essay = new Text("Throughout this class, we have watched many movies that I have thoroughly enjoyed. However, of the films that we have watched, I feel that Andrew Niccol�s film Gattaca, has probably been one of my favorite. Not only did it have, in my opinion, a great story, but it also had several interesting themes and ideas. One of the things that I felt was the most important was how, even if they distrusted him or thought he was a bad person at first, most people who actually got to know the main character, Vincent, eventually accepted him for who he was. I feel that this says a lot about human nature because, even though their society seems obsessed with hating the �others�, and discriminating against them as much as possible, morality wins out in the end when, as they get to know them, people realize that the �others� are actually just like them. This can be seen most obviously in Jerome. Even he, the one who voluntarily offers his identity for sale, doesn�t like Vincent at first. In fact, he almost seems to despise him. It�s as though he feels that Vincent is stealing his life, even though, because of his accident, his life was already sort of stolen from him. He constantly questions whether Vincent will be able to impersonate him successfully because to him, since Vincent is not of superior genetic stock and the odds aren�t in his favor, he can�t possibly pull off the deception necessary to be accepted as a genetically perfect human being. As Jerome himself says, �with all I had going for me, I was still second best� so how do you expect to pull this off?� As the story progresses however, and Vincent spends more time living with Jerome and �borrowing� his identity, they two go from simply being business partners of sorts to being very close friends. Jerome even risks his own freedom and physical safety to protect Vincent from the authorities, who are hot on the scent of their deception. In the end, he takes his own life without regret and leaves Vincent the means to live out the rest of his days as Jerome because he knows that he helped Vincent achieve his dream, and he is at peace with the situation.	When she first sees the evidence of Vincent�s deception upon meeting the real Jerome, Irene, the girl who Vincent loves, is also almost horrified by who he really is. Even though she loves him, and even though she already knew that Vincent wasn�t who he said he was, it is tough for her to actually see it with her own eyes because the prejudice in their society is so strong. Before she leaves however, Vincent tries to make her understand that genetics aren�t everything and shows her that how her society is flawed in its obsession with genetic perfection because, even though the odds say that he should be miserably unhealthy or even dead, he is actually able to perform as well and even better than many of his genetically �perfect� counterparts. When she thinks about it, she realizes that he is right and that, just because he is a �borrowed ladder�, he isn�t any less of a human being, and he deserves everything that he has earned. Even the investigator who is chasing Vincent ends up accepting him. At first, he is obviously out to stop Vincent because what Vincent is doing is considered one of the worst crimes in their society. However, when it turns out that the investigator is really Anton, Vincent�s long lost, genetically crafted brother, and when Anton discovers that the man he knew as �Jerome� is really his brother, he is very conflicted. He wants to believe that Vincent is inferior, but he remembers the incident in their childhood when Vincent beat him at a competition of strength and endurance. When Anton realizes what Vincent has achieved, and when he sees that despite his �inferior� genes he is still a superior human being, he ends up deciding not to arrest Vincent. The final act of acceptance which, to me, was probably one of the most powerful in the film is at the very end, right before Vincent gets on the space ship to go on his mission. There is a mandatory, surprise gene test before the flight as one last security precaution and Vincent, not expecting this to occur, didn�t prepare for it as he normally would have. He realizes that he will be caught out at the absolute last moment, right before he is about to reach his dream and the viewer can tell from his actions and his words that he realizes he will be caught and seems to give up. Vincent is friends with the doctor, and he basically reveals that he is not who he has been pretending to be when he takes the test. The doctor, however, reveals that he has actually known that Vincent was not �Jerome� the whole time, and he lets him go on the mission despite his test revealing his false identity. This was so powerful to me because it shows how, despite all of his deception, his secret was always known and could have been revealed at any time, but that, even an average person, who didn�t gain anything by keeping his secret, decided to let him be because it was the morally right thing to do. Out of all of the films we watched, I enjoyed Gattaca the most, and indeed it has probably become one of my all-time favorite films. I love how it explores the basic morality of what makes a person a person, and how it concludes that, just because something like someone�s genetics say a certain thing about them, that isn�t the bottom line of who they are. Instead, the movie shows the viewer how a person is defined by much more than simple variables such as genetics (or by extension, race, sex, religious belief, sexual preference, etc.), and how every person should be treated the same way and given the same opportunities regardless of these things because they could just go and completely disprove whatever stereotypes or prejudices have been applied to them, just as Vincent did in the movie.");
	protected Extender ex;

	@Test
	public void testTiming()
	{
		ex = new Extender(essay,essay.getValue().length());
		long time = System.nanoTime();
		MainServlet.extend(ex);
		long finalTime = System.nanoTime() - time;
		long maxTime = (long) (Math.pow(2, 63)-1);
		assertTrue(finalTime < maxTime);
		for(Word s : ex.taggedWords)
		{
			System.out.print(s.getPOS() + " ");
		}
	}
	@Test
	public void testTagCompleteness()
	{
		int expected = 1192;
		ex = new Extender(essay, essay.getValue().length());
		MainServlet.extend(ex);
		int actual = ex.taggedWords.size();
		assertTrue(expected == actual);
	}
	
	@Test
	public void testLongerLength()
	{
		int originalLength = essay.getValue().length();
		ex = new Extender(essay, essay.getValue().length());
		MainServlet.extend(ex);
		int newLength = ex.extendedText.getValue().length();
		assertTrue(newLength > originalLength);
	}
}
