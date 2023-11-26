package classes;

import java.util.Date;

public class RECETTE extends MVT_CAISSE {

	private int NUM_REC;

	private Date DATE_REC;

	private double TARIF_CONS;

	private String MODE_PAIEMENT;
	
	private int CONSULTATION_ID;

	public RECETTE(int nUM_REC, Date dATE_REC, double tARIF_CONS, String mODE_PAIEMENT,int consultation_id) {
		super();
		NUM_REC = nUM_REC;
		DATE_REC = dATE_REC;
		TARIF_CONS = tARIF_CONS;
		MODE_PAIEMENT = mODE_PAIEMENT;
		CONSULTATION_ID=consultation_id;
	}

	public int getNUM_REC() {
		return NUM_REC;
	}

	public void setNUM_REC(int nUM_REC) {
		NUM_REC = nUM_REC;
	}

	public Date getDATE_REC() {
		return DATE_REC;
	}

	public void setDATE_REC(Date dATE_REC) {
		DATE_REC = dATE_REC;
	}

	public double getTARIF_CONS() {
		return TARIF_CONS;
	}

	public void setTARIF_CONS(double tARIF_CONS) {
		TARIF_CONS = tARIF_CONS;
	}

	public String getMODE_PAIEMENT() {
		return MODE_PAIEMENT;
	}

	public void setMODE_PAIEMENT(String mODE_PAIEMENT) {
		MODE_PAIEMENT = mODE_PAIEMENT;
	}
	public int getCONSULTATION_ID() {
	    return CONSULTATION_ID;
	}

	public void setCONSULTATION_ID(int CONSULTATION_ID) {
	    this.CONSULTATION_ID = CONSULTATION_ID;
	}
}

