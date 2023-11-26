package classes;

import java.sql.Date;

public class CONSULTATION {

	private int NUM_CONS;

	private Date DATE_CONS;

	private String MOTIF;

	private String DIAGNOSTIC;
	
	private int PATIENT_ID;

	public CONSULTATION(int nUM_CONS, Date dATE_CONS, String mOTIF, String dIAGNOSTIC,int pATIENT_ID) {
		super();
		NUM_CONS = nUM_CONS;
		DATE_CONS = dATE_CONS;
		MOTIF = mOTIF;
		DIAGNOSTIC = dIAGNOSTIC;
		setPATIENT_ID(pATIENT_ID);
	}


	public int getNUM_CONS() {
		return NUM_CONS;
	}

	public void setNUM_CONS(int nUM_CONS) {
		NUM_CONS = nUM_CONS;
	}

	public Date getDATE_CONS() {
		return DATE_CONS;
	}

	public void setDATE_CONS(Date dATE_CONS) {
		DATE_CONS = dATE_CONS;
	}

	public String getMOTIF() {
		return MOTIF;
	}

	public void setMOTIF(String mOTIF) {
		MOTIF = mOTIF;
	}

	public String getDIAGNOSTIC() {
		return DIAGNOSTIC;
	}

	public void setDIAGNOSTIC(String dIAGNOSTIC) {
		DIAGNOSTIC = dIAGNOSTIC;
	}


	public int getPATIENT_ID() {
		return PATIENT_ID;
	}


	public void setPATIENT_ID(int pATIENT_ID) {
		PATIENT_ID = pATIENT_ID;
	}   
}
