import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ExposureCalculator extends HttpServlet {	
	ExposureTools exp = new ExposureTools();
	Exposure exposure;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 	
		exposure = createExposureObject(req);
		renderHtml(req, resp);
	}
	
	private void renderHtml(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("shutter", 	selectCustom(req, exposure.shutter, exp.getShutterSpeedStrings(), "shutter"));
		req.setAttribute("iso", 		selectCustom(req, exposure.iso, exp.getIsoStrings(), "iso"));
		req.setAttribute("aperture", 	selectCustom(req, exposure.aperture, exp.getApertureStrings(), "aperture"));
		req.setAttribute("iso2", 		selectCustom(req, exposure.iso2, exp.getIsoStrings(), "iso2"));
		req.setAttribute("aperture2", 	selectCustom(req, exposure.aperture2, exp.getApertureStrings(), "aperture2"));
		
		req.setAttribute("shutter2", exp.getString(exp.getShutterSpeedStrings(), exp.shutterSpeedEv, exposure.getNewShutterSpeed()));	
		
		req.setAttribute("result", "<p> Same exposure, aperture "+ exposure.aperture2 +", "
				+ "and adjusting shutterspeed for iso "+ exposure.iso2 +" : " 
				+ exp.getString(exp.getShutterSpeedStrings(), exp.shutterSpeedEv, exposure.getNewShutterSpeed()));	
		
		req.getRequestDispatcher("/indexexposure.jsp").forward(req, resp);
	}

	private Exposure createExposureObject(HttpServletRequest req) {
		Exposure tempExposure = new Exposure(req.getParameter("iso"), 
								req.getParameter("shutter"), 
								req.getParameter("aperture"), 
								req.getParameter("iso2"), 
								req.getParameter("aperture2"));
								
		if(!tempExposure.inputCheck()) {
			tempExposure = new Exposure("6400", "1/250", "f/2.8", "100", "f/5.6");
		}
		return tempExposure;
	}

	private String selectCustom(HttpServletRequest req, String currentValue, String[] values, String name) {
		String temp;
		temp = "<SELECT NAME='"+ name +"'>";
		for(int n = values.length; n>0; n--){
			if(values[n-1].equals(currentValue)) {
				temp += ("<option selected>" + values[n-1] + "</option>\n");
			}
			else {
				temp += ("<option>" + values[n-1] + "</option>\n");
			}
		} 
		temp+="</SELECT>";
		return temp;
	}
}

