package classes;

import java.sql.Date;

public class CERTIFICAT {

	private String COMMENTAIRE_CERT;
	
	private Date DATE_CERT;

	public CERTIFICAT(String cOMMENTAIRE_CERT, Date dATE_CERT) {
		super();
		COMMENTAIRE_CERT = cOMMENTAIRE_CERT;
		DATE_CERT = dATE_CERT;
	}

	public String getCOMMENTAIRE_CERT() {
		return COMMENTAIRE_CERT;
	}

	public void setCOMMENTAIRE_CERT(String cOMMENTAIRE_CERT) {
		COMMENTAIRE_CERT = cOMMENTAIRE_CERT;
	}

	public Date getDATE_CERT() {
		return DATE_CERT;
	}

	public void setDATE_CERT(Date dATE_NAIS) {
		DATE_CERT = dATE_NAIS;
	}
}

