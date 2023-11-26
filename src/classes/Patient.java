package classes;

import java.sql.Date;

public class Patient {

	private int NUM_FIC;

	private String CIN;

	private Date DAT_FIC;

	private String NOM;

	private String PRENOM;

	private Date DAT_NAI;

	private String PROF;

	private String ADRESSE;

	private String PHONE;

	private String SEXE;
	
	public Patient(String cIN, Date dAT_FIC, String nOM, String pRENOM, Date dAT_NAI, String pROF, String aDRESSE, String pHONE, String sEXE) {
		super();
		CIN = cIN;
		DAT_FIC = dAT_FIC;
		NOM = nOM;
		PRENOM = pRENOM;
		DAT_NAI = dAT_NAI;
		PROF = pROF;
		ADRESSE = aDRESSE;
		PHONE = pHONE;
		SEXE = sEXE;
	}


	public Patient(int nUM_FIC, String cIN, String nOM, String pRENOM, String sEXE) {
		super();
		NUM_FIC = nUM_FIC;
		CIN = cIN;
		NOM = nOM;
		PRENOM = pRENOM;
		SEXE = sEXE;
	}


	public int getNUM_FIC() {
		return NUM_FIC;
	}

	public void setNUM_FIC(int nUM_FIC) {
		NUM_FIC = nUM_FIC;
	}

	public String getCIN() {
		return CIN;
	}

	public void setCIN(String cIN) {
		CIN = cIN;
	}

	public Date getDAT_FIC() {
		return DAT_FIC;
	}

	public void setDAT_FIC(Date dAT_FIC) {
		DAT_FIC = dAT_FIC;
	}

	public String getNOM() {
		return NOM;
	}

	public void setNOM(String nOM) {
		NOM = nOM;
	}

	public String getPRENOM() {
		return PRENOM;
	}

	public void setPRENOM(String pRENOM) {
		PRENOM = pRENOM;
	}

	public Date getDAT_NAI() {
		return DAT_NAI;
	}

	public void setDAT_NAI(Date dAT_NAI) {
		DAT_NAI = dAT_NAI;
	}

	public String getPROF() {
		return PROF;
	}

	public void setPROF(String pROF) {
		PROF = pROF;
	}

	public String getADRESSE() {
		return ADRESSE;
	}

	public void setADRESSE(String aDRESSE) {
		ADRESSE = aDRESSE;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}

	public String getSEXE() {
		return SEXE;
	}

	public void setSEXE(String sEXE) {
		SEXE = sEXE;
	}
 

}

