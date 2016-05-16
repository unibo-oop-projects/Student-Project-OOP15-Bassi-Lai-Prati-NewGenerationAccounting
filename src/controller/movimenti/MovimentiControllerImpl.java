/**
 * 
 */
package controller.movimenti;

import controller.IAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import model.MovementsModel;
import view.movimenti.MovimentiView;

/**
 * @author Pentolo
 *
 */
public class MovimentiControllerImpl implements IAnagraficaViewObserver {

	private final MovimentiView view;
	private final MovementsModel model;

	/**
	 * @param view
	 */
	public MovimentiControllerImpl(final String title) {
		this.model = new MovementsModel(null);
		this.view = new MovimentiView(model.load(), title);
		this.view.setObserver(this);
		view.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#chiusura()
	 */
	@Override
	public void chiusura() {
		view.close();
		new MainControllerImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto0()
	 */
	@Override
	public void tasto0() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto1()
	 */
	@Override
	public void tasto1() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto2()
	 */
	@Override
	public void tasto2() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto3()
	 */
	@Override
	public void tasto3() {
		// TODO Auto-generated method stub

	}
}
