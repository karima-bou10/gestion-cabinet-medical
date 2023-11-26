package classes;

import java.util.Date;
import java.sql.Time;
import java.util.Collection;

public class RDV {

	private int NUM_RDV;

	private String DESCRIPTION;

	private Date DATE_RDV;

	private Time HEURE_RDV;

	public RDV(int nUM_RDV, String dESCRIPTION, Date dATE_RDV, Time hEURE_RDV) {
		super();
		NUM_RDV = nUM_RDV;
		DESCRIPTION = dESCRIPTION;
		DATE_RDV = dATE_RDV;
		HEURE_RDV = hEURE_RDV;
	}

	public int getNUM_RDV() {
		return NUM_RDV;
	}

	public void setNUM_RDV(int nUM_RDV) {
		NUM_RDV = nUM_RDV;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public Date getDATE_RDV() {
		return DATE_RDV;
	}

	public void setDATE_RDV(Date dATE_RDV) {
		DATE_RDV = dATE_RDV;
	}

	public Time getHEURE_RDV() {
		return HEURE_RDV;
	}

	public void setHEURE_RDV(Time hEURE_RDV) {
		HEURE_RDV = hEURE_RDV;
	}

}

