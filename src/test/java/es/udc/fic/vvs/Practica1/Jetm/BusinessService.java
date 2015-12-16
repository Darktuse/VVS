package es.udc.fic.vvs.Practica1.Jetm;

import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

public class BusinessService {

	private static final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

	  public void someMethod() {

	    EtmPoint point = etmMonitor.createPoint("BusinessService:someMethod");

	    try {

	      Thread.sleep((long) (10d * Math.random()));
	      nestedMethod();

	    } catch (InterruptedException e) {
	      // igored
	    } finally {
	      point.collect();
	    }
	  }
	  
	  public void buscarTokenVacioTest() {

		    EtmPoint point = etmMonitor.createPoint("BusinessService:buscarTokenVacioTest");

		    try {

		      Thread.sleep((long) (10d * Math.random()));
		      nestedMethod();

		    } catch (InterruptedException e) {
		      // igored
		    } finally {
		      point.collect();
		    }
		  }
	  
	  public void buscarServidorSimple() {

		    EtmPoint point = etmMonitor.createPoint("BusinessService:buscarServidorSimple");

		    try {

		      Thread.sleep((long) (10d * Math.random()));
		      nestedMethod();

		    } catch (InterruptedException e) {
		      // igored
		    } finally {
		      point.collect();
		    }
		  }
	  public void altaServidorSimple() {

		    EtmPoint point = etmMonitor.createPoint("BusinessService:altaServidorSimple");

		    try {

		      Thread.sleep((long) (10d * Math.random()));
		      nestedMethod();

		    } catch (InterruptedException e) {
		      // igored
		    } finally {
		      point.collect();
		    }
		  }
	  public void bajaServidorSimple() {

		    EtmPoint point = etmMonitor.createPoint("BusinessService:bajaServidorSimple");

		    try {

		      Thread.sleep((long) (10d * Math.random()));
		      nestedMethod();

		    } catch (InterruptedException e) {
		      // igored
		    } finally {
		      point.collect();
		    }
		  }
	  public void agregarServidorSimple() {

		    EtmPoint point = etmMonitor.createPoint("BusinessService:agregarServidorSimple");

		    try {

		      Thread.sleep((long) (10d * Math.random()));
		      nestedMethod();

		    } catch (InterruptedException e) {
		      // igored
		    } finally {
		      point.collect();
		    }
		  }
	  public void eliminarServidorSimple() {

		    EtmPoint point = etmMonitor.createPoint("BusinessService:eliminarServidorSimple");

		    try {

		      Thread.sleep((long) (10d * Math.random()));
		      nestedMethod();

		    } catch (InterruptedException e) {
		      // igored
		    } finally {
		      point.collect();
		    }
		  }
	  public void insertarAnunciosServidorSimple() {

		    EtmPoint point = etmMonitor.createPoint("BusinessService:insertarAnunciosServidorSimple");

		    try {

		      Thread.sleep((long) (10d * Math.random()));
		      nestedMethod();

		    } catch (InterruptedException e) {
		      // igored
		    } finally {
		      point.collect();
		    }
		  }

	  public void nestedMethod() {

	    EtmPoint point = etmMonitor.createPoint( "BusinessService:nestedMethod");

	    try {

	      Thread.sleep((long) (15d * Math.random()));

	    } catch (InterruptedException e) {
	      // ignored
	    } finally {
	      point.collect();
	    }
	  }
}
