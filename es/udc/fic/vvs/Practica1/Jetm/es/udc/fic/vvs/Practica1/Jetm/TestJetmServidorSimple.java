package es.udc.fic.vvs.Practica1.Jetm;

import etm.core.configuration.BasicEtmConfigurator;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.renderer.SimpleTextRenderer;

public class TestJetmServidorSimple {

	private static EtmMonitor monitor;


	  public static void main(String[] args) {
	    // configure measurement framework
	    setup();

	    // instantiate business service
	    BusinessService service = new BusinessService();

	    // execute business logic

	    for(int n=1;n<10;n++){
	    	service.buscarServidorSimple();
	    	service.agregarServidorSimple();
	    	service.altaServidorSimple();
	    	service.bajaServidorSimple();
	    	service.eliminarServidorSimple();
	    	service.insertarAnunciosServidorSimple();
	    	service.buscarTokenVacioTest();
	    }
	    service.nestedMethod();

	    // visualize results
	    monitor.render(new SimpleTextRenderer());

	    // shutdown measurement framework
	    tearDown();
	  }


	  private static void setup() {
	    BasicEtmConfigurator.configure();
	    monitor = EtmManager.getEtmMonitor();
	    monitor.start();
	  }

	  private static void tearDown() {
	    monitor.stop();
	  }

}
