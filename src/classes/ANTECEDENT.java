package classes;

public class ANTECEDENT {

	private int NUM_ANT;

	private String CATEGORIE;

	private String DESCRIPTION;
	
	public ANTECEDENT(String cATEGORIE, String dESCRIPTION) {
		super();
		CATEGORIE = cATEGORIE;
		DESCRIPTION = dESCRIPTION;
	}

	public int getNUM_ANT() {
		return NUM_ANT;
	}

	public void setNUM_ANT(int nUM_ANT) {
		NUM_ANT = nUM_ANT;
	}

	public String getCATEGORIE() {
		return CATEGORIE;
	}

	public void setCATEGORIE(String cATEGORIE) {
		CATEGORIE = cATEGORIE;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
}
