package classes;

import java.util.Date;

public class DEPENSES extends MVT_CAISSE {

	private int NUM_DEP;

	private Date DATE_DEP;

	private String MOTIF_DEP;

	private double MONTANT_DEP;
	
	private int CONSULTATION_ID;

	public DEPENSES(int nUM_DEP, Date dATE_DEP, String mOTIF_DEP, double mONTANT_DEP,int consultation_id) {
		super();
		NUM_DEP = nUM_DEP;
		DATE_DEP = dATE_DEP;
		MOTIF_DEP = mOTIF_DEP;
		MONTANT_DEP = mONTANT_DEP;
		CONSULTATION_ID=consultation_id;
	}

	public int getNUM_DEP() {
		return NUM_DEP;
	}

	public void setNUM_DEP(int nUM_DEP) {
		NUM_DEP = nUM_DEP;
	}

	public Date getDATE_DEP() {
		return DATE_DEP;
	}

	public void setDATE_DEP(Date dATE_DEP) {
		DATE_DEP = dATE_DEP;
	}

	public String getMOTIF_DEP() {
		return MOTIF_DEP;
	}

	public void setMOTIF_DEP(String mOTIF_DEP) {
		MOTIF_DEP = mOTIF_DEP;
	}

	public double getMONTANT_DEP() {
		return MONTANT_DEP;
	}

	public void setMONTANT_DEP(double mONTANT_DEP) {
		MONTANT_DEP = mONTANT_DEP;
	}
	public int getCONSULTATION_ID() {
	    return CONSULTATION_ID;
	}

	public void setCONSULTATION_ID(int CONSULTATION_ID) {
	    this.CONSULTATION_ID = CONSULTATION_ID;
	}
}

