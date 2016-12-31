package ch.ralena.glossikaschedule.data;

/**
 * Created by crater-windoze on 12/31/2016.
 */

public class LanguageType {
	String mName;
	int mDrawable;

	public LanguageType(String name, int drawable) {
		mName = name;
		mDrawable = drawable;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public int getDrawable() {
		return mDrawable;
	}

	public void setDrawable(int drawable) {
		mDrawable = drawable;
	}
}
