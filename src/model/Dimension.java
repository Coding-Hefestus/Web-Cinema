package model;

public enum Dimension {
	UNSPECIFIED,
	IID,
	IIID,
	IVD;
	
	private String text;
	
	static {
		UNSPECIFIED.text = "Unspecified";
		IID.text = "2D";
		IIID.text = "3D";
		IVD.text = "4D";
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	
}
