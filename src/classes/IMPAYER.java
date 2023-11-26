package classes;

public class IMPAYER extends MVT_CAISSE {

	private int NUM_IMP;

	private String TELE_PAT;

	private double MONTANT_SUIVIE;

	private double MONTANT_RESTE_SUIVIE;
	
	private int CONSULTATION_ID;

	public IMPAYER(int nUM_IMP, String tELE_PAT, double mONTANT_SUIVIE, double mONTANT_RESTE_SUIVIE,int consultation_id) {
		super();
		NUM_IMP = nUM_IMP;
		TELE_PAT = tELE_PAT;
		MONTANT_SUIVIE = mONTANT_SUIVIE;
		MONTANT_RESTE_SUIVIE = mONTANT_RESTE_SUIVIE;
		CONSULTATION_ID=consultation_id;
	}

	public int getNUM_IMP() {
		return NUM_IMP;
	}

	public void setNUM_IMP(int nUM_IMP) {
		NUM_IMP = nUM_IMP;
	}

	public String getTELE_PAT() {
		return TELE_PAT;
	}

	public void setTELE_PAT(String tELE_PAT) {
		TELE_PAT = tELE_PAT;
	}

	public double getMONTANT_SUIVIE() {
		return MONTANT_SUIVIE;
	}

	public void setMONTANT_SUIVIE(double mONTANT_SUIVIE) {
		MONTANT_SUIVIE = mONTANT_SUIVIE;
	}

	public double getMONTANT_RESTE_SUIVIE() {
		return MONTANT_RESTE_SUIVIE;
	}

	public void setMONTANT_RESTE_SUIVIE(double mONTANT_RESTE_SUIVIE) {
		MONTANT_RESTE_SUIVIE = mONTANT_RESTE_SUIVIE;
	}
	public int getCONSULTATION_ID() {
	    return CONSULTATION_ID;
	}

	public void setCONSULTATION_ID(int CONSULTATION_ID) {
	    this.CONSULTATION_ID = CONSULTATION_ID;
	}
}

