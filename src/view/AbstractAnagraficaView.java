/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.AbstractAnagraficaViewObserver;
import controller.AbstractViewObserver;
import dataModel.IDataTableModel;

/**
 * classe astratta per le view che rispettano il layout di anagrafica, con i 5
 * tasti nel footer.
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractAnagraficaView<dataModel extends IDataTableModel> extends AbstractFrame {

	private final static String DEFAULT_TASTO_0 = "Cerca";
	private final static String DEFAULT_TASTO_1 = "Aggiungi";
	private final static String DEFAULT_TASTO_2 = "Modifica";
	private final static String DEFAULT_TASTO_3 = "Cancella";
	private final static String DEFAULT_TASTO_4 = "Chiudi";

	private static final long serialVersionUID = -1706093338606827050L;

	private final JButton tasto0 = new JButton();
	private final JButton tasto1 = new JButton();
	private final JButton tasto2 = new JButton();
	private final JButton tasto3 = new JButton();
	private final JButton tasto4 = new JButton();

	protected final LinkedList<dataModel> lista = new LinkedList<dataModel>();
	private final JTable table = new JTable();
	private MyTableModel<dataModel> dataModel;
	protected AbstractAnagraficaViewObserver observer;

	/**
	 * @param title
	 * @param lm
	 * @param dimension
	 */
	public AbstractAnagraficaView(String intestazione[], String title) {
		this(intestazione, title, DEFAULT_TASTO_0, DEFAULT_TASTO_1, DEFAULT_TASTO_2, DEFAULT_TASTO_3, DEFAULT_TASTO_4);
	}

	public AbstractAnagraficaView(String intestazione[], String title, String testo0, String testo1, String testo2,
			String testo3, String testo4) {
		super(title, new Dimension(450, 550));
		tasto0.setText(testo0);
		tasto1.setText(testo1);
		tasto2.setText(testo2);
		tasto3.setText(testo3);
		tasto4.setText(testo4);
		final JPanel footer = new JPanel(new FlowLayout());
		footer.add(tasto0);
		footer.add(tasto1);
		footer.add(tasto2);
		footer.add(tasto3);
		footer.add(tasto4);
		this.dataModel = new MyTableModel<dataModel>(intestazione, lista);
		JScrollPane scrollPane = new JScrollPane(getTable());
		getTable().setModel(getModel());
		MyFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		MyFrame.getContentPane().add(footer, BorderLayout.SOUTH);
		addListeners();
	}

	private void addListeners() {
		tasto0.addActionListener(e -> {
			lista.remove(getModel().getObjectAt(getTable().getSelectedRow()));
			getModel().fireTableDataChanged();
		});
		tasto1.addActionListener(e -> {
			((AbstractAnagraficaViewObserver) observer).tasto1();
		});
		tasto2.addActionListener(e -> {
			((AbstractAnagraficaViewObserver) observer).tasto2();
		});
		tasto3.addActionListener(e -> {
			((AbstractAnagraficaViewObserver) observer).tasto3();
		});
		tasto4.addActionListener(e -> {
			((AbstractAnagraficaViewObserver) observer).tasto4();
		});
	}
	
	public void setObserver(AbstractAnagraficaViewObserver observer) {
		this.observer = observer;
	}

	protected MyTableModel<dataModel> getModel() {
		return dataModel;
	}

	protected JTable getTable() {
		return table;
	}
}
