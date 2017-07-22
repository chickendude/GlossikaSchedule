package ch.ralena.glossikaschedule.data;

public class LanguageType {
	String name;
	int drawable;

	public LanguageType(String name, int drawable) {
		this.name = name;
		this.drawable = drawable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDrawable() {
		return drawable;
	}

	public void setDrawable(int drawable) {
		this.drawable = drawable;
	}
}
