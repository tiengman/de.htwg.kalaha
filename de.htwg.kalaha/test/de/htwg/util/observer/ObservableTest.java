package de.htwg.util.observer;

//Imported from htwg.se.sudoku by markoboger

import junit.framework.TestCase;

public class ObservableTest extends TestCase {
	private boolean ping=false;
	private TestObserver testObserver;
	private Observable testObservable;
	
	class TestObserver implements IObserver {
		//@Override
		public void update(Event e) {
			ping=true;
		}
		
	}

	
	public void setUp() throws Exception {
		testObserver = new TestObserver();
		testObservable = new Observable();
		testObservable.addObserver(testObserver);
	}

	
	public void testNotify() {
		assert(!ping);
		testObservable.notifyObservers();
		assert(ping);
	}
	
	
	public void testRemove() {
		assert(!ping);
		testObservable.removeObserver(testObserver);
		testObservable.notifyObservers();
		assert(!ping);
	}
	
	public void testRemoveAll() {
		assert(!ping);
		testObservable.removeAllObservers();
		testObservable.notifyObservers();
		assert(!ping);
	}

}