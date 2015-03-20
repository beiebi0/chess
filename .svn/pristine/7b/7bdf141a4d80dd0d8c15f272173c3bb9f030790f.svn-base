package org.kanwang.hw10;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.mgwt.linker.linker.ManifestWriter;
import com.googlecode.mgwt.linker.linker.PermutationMapLinker;
import com.googlecode.mgwt.linker.server.BindingProperty;
import com.googlecode.mgwt.linker.server.Html5ManifestServletBase;
import com.googlecode.mgwt.linker.server.propertyprovider.MgwtOsPropertyProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.MobileUserAgentProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.UserAgentPropertyProvider;

public class MyHtml5ManifestServlet extends Html5ManifestServletBase {
	/**
     * 
     */
	private static final long serialVersionUID = 3480215265307651028L;
	 @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    String moduleName = getModuleName(req);

	    String baseUrl = getBaseUrl(req);

	    Set<BindingProperty> computedBindings = calculateBindinPropertiesForClient(req);
	    
	    String strongName = getPermutationStrongName(baseUrl, moduleName, computedBindings);

	    if (strongName != null) {
	      String manifest = readManifest(baseUrl + moduleName + "/" + strongName + PermutationMapLinker.PERMUTATION_MANIFEST_FILE_ENDING);
	      serveStringManifest(req, resp, manifest);
	      return;
	    }

	    boolean isIPhoneWithoutCookie = isIphoneWithoutCookie(computedBindings);
	    boolean isIPadWithoutCookie = isIpadWithoutCookie(computedBindings);

	    if (isIPhoneWithoutCookie || isIPadWithoutCookie) {
	      Set<BindingProperty> nonRetinaMatch = new HashSet<BindingProperty>();
	      Set<BindingProperty> retinaMatch = new HashSet<BindingProperty>();

	      if (isIPhoneWithoutCookie) {
	        computedBindings.remove(MgwtOsPropertyProvider.iPhone_undefined);
	        nonRetinaMatch.add(MgwtOsPropertyProvider.iPhone);
	        retinaMatch.add(MgwtOsPropertyProvider.retina);
	      }

	      if (isIPadWithoutCookie) {
	        computedBindings.remove(MgwtOsPropertyProvider.iPad_undefined);
	        nonRetinaMatch.add(MgwtOsPropertyProvider.iPad);
	        retinaMatch.add(MgwtOsPropertyProvider.iPad_retina);
	      }

	      nonRetinaMatch.addAll(computedBindings);
	      retinaMatch.addAll(computedBindings);

	      String moduleNameNonRetina = getPermutationStrongName(baseUrl, moduleName, nonRetinaMatch);
	      String moduleNameRetina = getPermutationStrongName(baseUrl, moduleName, retinaMatch);

	      if (moduleNameNonRetina != null && moduleNameRetina != null) {

	        // load files for both permutations
	        Set<String> filesForPermutation = getFilesForPermutation(baseUrl, moduleName, moduleNameNonRetina);
	        filesForPermutation.addAll(getFilesForPermutation(baseUrl, moduleName, moduleNameRetina));

	        // dynamically write a new manifest..
	        ManifestWriter manifestWriter = new ManifestWriter();
	        String writeManifest = manifestWriter.writeManifest(new HashSet<String>(), filesForPermutation);
	        serveStringManifest(req, resp, writeManifest);
	        return;
	      }
	    }

	    // if we got here we just don`t know the device react with 500 -> no
	    // manifest...

	    throw new ServletException("unkown device");

	  }
	 
	public MyHtml5ManifestServlet() {
		addPropertyProvider(new MgwtOsPropertyProvider());
		addPropertyProvider(new UserAgentPropertyProvider());
		addPropertyProvider(new MyMobileUserAgentProvider());

	}

}
