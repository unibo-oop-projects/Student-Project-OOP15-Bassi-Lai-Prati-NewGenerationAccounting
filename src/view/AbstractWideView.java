/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.IViewObserver;

/**
 * classe astratta che estende da AbstractFrame creata per il menu principale e
 * le due situazioni aziendali
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractWideView extends AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8661356282182241245L;

	protected IViewObserver observer;

	/**
	 * @param title
	 * @param dimension
	 */
	public AbstractWideView(final String title, final Dimension dimension) {
		super(title, dimension);
		JButton chiudi = new JButton("Chiudi");
		JPanel footer = new JPanel(new FlowLayout());
		footer.add(chiudi);
		getMyFrame().getContentPane().add(footer, BorderLayout.SOUTH);
		chiudi.addActionListener(e -> {
			observer.chiusura();
		});
	}

	@Override
	protected void chiusura() {
		observer.chiusura();
	}

	/**
	 * @param observer
	 *            the observer to set
	 */
	public void setObserver(IViewObserver observer) {
		this.observer = observer;
	}
}
