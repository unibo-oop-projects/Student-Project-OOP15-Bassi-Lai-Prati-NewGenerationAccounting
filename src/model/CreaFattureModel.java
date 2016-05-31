package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import dataEnum.KindPerson;
import dataModel.Customers_Suppliers;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Item;
import dataModel.Product;

/**
 * Classe implementativa per la gestione del carrello che permette l'acquisto di
 * prodotti, coinvolgendo i movimenti dei conti e delle scorte in magazzino.
 * 
 * @author Diego
 *
 */

public class CreaFattureModel implements ModelInterface {

	private final static String prodotto = "Prodotto";
	private final static String quantita = "Quantita'";
	private final static String scorta = "Scorta";

	private DBDataModel db;

	private final LinkedList<Item> listaCarrello = new LinkedList<Item>();

	public CreaFattureModel(DBDataModel db) {
		this.db = db;
	}

	/**
	 * Funzione per la creazione di un nuovo carrello , con conseguente
	 * possibilit� di acquistare prodotti.
	 * 
	 * 
	 */
	@Override
	public void add(Map<String, Object> elem) throws IllegalArgumentException {

		if (elem.get(prodotto).equals("")) {
			throw new IllegalArgumentException("Nome prodotto non valido. Riprovare.");
		}

		if (elem.get(quantita).equals("")) {
			throw new IllegalArgumentException("Quantita' non valida. Riprovare.");
		}

		if (listaCarrello.contains(elem)) {
			throw new IllegalArgumentException("Elemento gia' esistente!");
		} else {
			Item nuovocarrello = new Item((Product) elem.get(prodotto), (Integer) elem.get(quantita));
			listaCarrello.add(nuovocarrello);
		}
	}

	/**
	 * 
	 * Funzione per l'aggiornamento del DB dopo la creazione della fattura.
	 * 
	 * Crea un movimento, pi� debiti verso fornitori, meno scorte della merce
	 * richiesta.
	 * 
	 * 
	 */

	public DBDataModel create(Customers_Suppliers item) {

		int totale = 0;

		if (listaCarrello.isEmpty()) {
			throw new IllegalArgumentException("Acquisto non valido. Riprovare.");
		} else {

			for (Item riga : listaCarrello) {
				totale += riga.getProdotto().getPrezzovendita() * riga.getQuantita();
				riga.getProdotto().setScorta(riga.getProdotto().getScorta() - riga.getQuantita());

			}
		}
		item.setDebito(item.getDebito() + totale);// Addebito il totale al
													// debito pregresso

		LinkedList<Customers_Suppliers> cs = db.getCustomersSuppliers();
		cs.addLast(item);

		db.setCustomersSuppliers(cs);// aggiorno prodotti

		return db;
	}

	/**
	 * 
	 * Funzione per la modifica del carrello d'acquisto.
	 * 
	 * 
	 */

	@Override
	public void edit(IDataTableModel obj, Map<String, Object> infoDaModificare) {
		if ((Product) infoDaModificare.get(scorta) != null) {

			((Item) obj).setNome((Product) infoDaModificare.get(prodotto)); // vado
																			// a
																			// prendere
																			// il
																			// prodotto
																			// tramite
																			// il
																			// nome
			((Item) obj).setQuantita((Integer) infoDaModificare.get(quantita));

		}
	}

	/**
	 * Funzione per la creazione di un filtro per i prodotti.
	 * 
	 * 
	 */

	@Override
	public Map<String, Object> getFilterMap() {
		Map<String, Object> mappaFiltro = new HashMap<>();
		mappaFiltro.put(prodotto, new String(""));
		return mappaFiltro;
	}

	/**
	 * Funzione per ottenere tra tutte le persone, solo i clienti.
	 * 
	 * 
	 */

	public Customers_Suppliers[] getListaclienti() {
		final Customers_Suppliers listaClienti[] = {};
		int i = 0;
		for (Customers_Suppliers controlloCliente : db.getCustomersSuppliers()) {
			if (controlloCliente.getRuolo() == KindPerson.CLIENTE) {
				listaClienti[i] = controlloCliente;
				i++;
			}
		}
		return listaClienti;
	}

	/**
	 * Funzione per la creazione di una nuova mappa di classe, sia che sia
	 * ricevuta vuota o con valori al suo interno.
	 * 
	 * 
	 */

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {

		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();

			mappaVuota.put(prodotto, new String(""));
			mappaVuota.put(quantita, new Integer(0));

			return mappaVuota;

		} else {
			if (obj instanceof Item) {
				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(prodotto, ((Item) obj).getProdotto());
				mappaPiena.put(quantita, ((Item) obj).getQuantita());
				return mappaPiena;
			} else {
				throw new IllegalArgumentException("Valori non validi, riprovare.");
			}
		}

	}

	/**
	 * Funzione per la creazione di listaCarrello.
	 * 
	 * 
	 */

	@Override
	public LinkedList<Item> load() {
		return new LinkedList<Item>(listaCarrello);
	}

	/**
	 * Funzione per la creazione di un nuovo carrello , con conseguente
	 * possibilit� di acquistare prodotti.
	 * 
	 *
	 */

	@Override
	public LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro) {
		LinkedList<Item> listaFiltrata = new LinkedList<>();
		if (mappaFiltro.get(prodotto) != null) {
			for (Item controllofiltro : listaCarrello) {
				if (controllofiltro.getProdotto().getNome().equals(prodotto)) {
					listaFiltrata.add(controllofiltro);
				}
			}
		}
		return null;
	}

	/**
	 * Funzione per rimozione di un elemento dal carrello.
	 * 
	 * 
	 * 
	 */
	@Override
	public void remove(IDataTableModel elem) {
		if (listaCarrello.contains(elem)) {
			listaCarrello.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}

	/**
	 * Funzione per restituzione del DB.
	 * 
	 * 
	 */

	@Override
	public DBDataModel saveDBAndClose() {
		return db;
	}

}
