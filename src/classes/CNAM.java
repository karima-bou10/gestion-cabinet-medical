package classes;

import java.sql.Date;

public class CNAM {

	private int NUM_CNAM;

	private Date DATE_VAL_CNAM;

	private String TYPE_CNAM;


	public CNAM(int nUM_CNAM, Date dATE_VAL_CNAM, String tYPE_CNAM) {
		super();
		NUM_CNAM = nUM_CNAM;
		DATE_VAL_CNAM = dATE_VAL_CNAM;
		TYPE_CNAM = tYPE_CNAM;
	}
	public CNAM(Date dATE_VAL_CNAM, String tYPE_CNAM) {
		super();
		DATE_VAL_CNAM = dATE_VAL_CNAM;
		TYPE_CNAM = tYPE_CNAM;
	}
	public int getNUM_CNAM() {
		return NUM_CNAM;
	}

	public void setNUM_CNAM(int nUM_CNAM) {
		NUM_CNAM = nUM_CNAM;
	}

	public Date getDATE_VAL_CNAM() {
		return DATE_VAL_CNAM;
	}

	public void setDATE_VAL_CNAM(Date dATE_VAL_CNAM) {
		DATE_VAL_CNAM = dATE_VAL_CNAM;
	}

	public String getTYPE_CNAM() {
		return TYPE_CNAM;
	}

	public void setTYPE_CNAM(String tYPE_CNAM) {
		TYPE_CNAM = tYPE_CNAM;
	}
    
}

