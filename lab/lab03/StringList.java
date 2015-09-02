import java.util.ArrayList;

public class StringList extends ArrayList<String> {
	
	public StringList() {
		String f = "Here, pretend - pretend that that's a seed.";
		String d = "That's a rock";
		f = "Oh, I know it's a rock, I know. But let's just pretend for a minute that it's a seed, alright? We'll just use our imaginations. Now, now do you see our tree? Everything that made that giant tree is already contained inside this tiny little seed. All it needs is some time, a little bit of sunshine and rain, and voila!";
		d = "That rock will be a tree?";
		f = "Seed to tree. You've gotta work with me, here. Alright? Okay. Now, y-you might not feel like you can do much now, but that's just because, well, you're not a tree yet. You just have to give yourself some time. You're still a seed.";
		d = "But it's a rock.";
		f = " I know it's a rock! Don't you think I know a rock when I see a rock? I've spent a lot of time around rocks!";
		d = "You're weird, but I like you.";
	}
	
	public boolean add(int i) {
		String s = i + "";
		super.add(s);
		return true;
	}

}
