package classes;

public class MEDICAMENT {

	private int NUM_MED;

	private String NOM_MED;

	private String DOSAGE;

	public MEDICAMENT(int nUM_MED, String nOM_MED, String dOSAGE) {
		super();
		NUM_MED = nUM_MED;
		NOM_MED = nOM_MED;
		DOSAGE = dOSAGE;
	}
	public MEDICAMENT(String nOM_MED, String dOSAGE) {
		super();
		NOM_MED = nOM_MED;
		DOSAGE = dOSAGE;
	}

	public int getNUM_MED() {
		return NUM_MED;
	}

	public void setNUM_MED(int nUM_MED) {
		NUM_MED = nUM_MED;
	}

	public String getNOM_MED() {
		return NOM_MED;
	}

	public void setNOM_MED(String nOM_MED) {
		NOM_MED = nOM_MED;
	}

	public String getDOSAGE() {
		return DOSAGE;
	}

	public void setDOSAGE(String dOSAGE) {
		DOSAGE = dOSAGE;
	}
}

