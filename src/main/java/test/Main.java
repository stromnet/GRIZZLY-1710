package test;

import java.io.IOException;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Main class.
 */
public class Main implements BundleActivator
{
	private HttpServer server;

	public void start(BundleContext bc) throws Exception {
		Logger.getAnonymousLogger().info("Starting simple server");
		server = HttpServer.createSimpleServer();
		try {
			server.getServerConfiguration()
				.addHttpHandler(
					new CLStaticHttpHandler(Main.class.getClassLoader(), "test/web/"),
					"/"
				);
			server.start();
		}catch(IOException ex ) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	public void stop(BundleContext bc) throws Exception {
		Logger.getAnonymousLogger().info("Stopping simple server");
		if(server != null) {
			server.shutdownNow();
		}
		server = null;
	}
}

