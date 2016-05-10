package dataModel;

/**
 * classe per la gestione della singola azienda
 * 
 * @author Pentolo
 *
 */

public class Company implements IDataTableModel {

	private static final String[] intestazione = { "Rag. Soc", "P.iva" };

	public static String[] getIntestazione() {
		return intestazione;
	}

	private int cap;

	private String citta;
	private int codice_azienda;
	private String indirizzo;
	private long partita_iva;
	private String password;
	private String provincia;
	private String ragione_sociale;

	private String tel;

	public Company(int codice_azienda, String password, String ragione_sociale, long partita_iva, String indirizzo,
			String citta, int cap, String provincia, String tel) {
		this.codice_azienda = codice_azienda;
		this.password = password;
		this.ragione_sociale = ragione_sociale;
		this.partita_iva = partita_iva;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.cap = cap;
		this.provincia = provincia;
		this.tel = tel;

	}

	public int getCap() {
		return cap;
	}

	public String getCitta() {
		return citta;
	}

	public int getCodice_azienda() {
		return codice_azienda;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public long getPartita_iva() {
		return partita_iva;
	}

	public String getPassword() {
		return password;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public String getTel() {
		return tel;
	}

	@Override
	public String getValueAt(int column) {
		switch (column) {
		case 0:
			return getRagione_sociale();
		case 1:
			return Long.toString(getPartita_iva());
		default:
			return "";
		}
	}

	/**
	 * Semplice controllo che confronta la password memorizzata nel database con
	 * quella inserita volta per volta dall'utente.
	 *
	 * @param password
	 * @return boolean
	 */
	public boolean ispasswordcorrect(String password) {
		if (this.getPassword().equals(password)) {
			return true;
		} else
			return false;

	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setCodice_azienda(int codice_azienda) {
		this.codice_azienda = codice_azienda;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setPartita_iva(long partita_iva) {
		this.partita_iva = partita_iva;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
