public class ExposureTools {
	String[] shutterSpeedStrings, isoStrings, apertureStrings;
	float[] isoEv, shutterSpeedEv, apertureEv;

	static int defaultIsoSteps = 12;
	static int defaultBaseIso = 50;
	
	public ExposureTools() {
		this(defaultIsoSteps, defaultBaseIso);
	}
	
	public ExposureTools(int isoSteps, int baseIso) {
		shutterSpeedStrings = createShutterSpeedStrings();
		isoStrings = createIsoStrings(isoSteps, baseIso);
		apertureStrings = createApertureStrings();
		initArrays();
	}
		
	public float getEv(String[] labels, float[] values, String matchString) {
		for(int n=0; n<labels.length; n++) {
			if(labels[n].equals(matchString)) {
				return values[n];
			}
		}
		return 0;
	}
	
	public String getString(String[] labels, float[] values, float value) {
		for(int n=0; n<labels.length; n++) {
			if(values[n] == value) {
				return labels[n];
			}
		}
		return "";
	}
	
	private void initArrays() {	
		isoEv = new float[getIsoStrings().length];
		shutterSpeedEv = new float[getShutterSpeedStrings().length];
		apertureEv = new float[getApertureStrings().length];
			
		isoEv[0] = 0.5f;
		shutterSpeedEv[0] = 1;
		apertureEv[0] = 1;
					
		for (int n=1; n<getIsoStrings().length; n++)	{
			isoEv[n] = isoEv[n-1]*2;
		}
		
		for (int n=1; n<getShutterSpeedStrings().length; n++)	{
			shutterSpeedEv[n] = shutterSpeedEv[n-1]*2;
		}
		
		for (int n=1; n<getApertureStrings().length; n++)	{
			apertureEv[n] = apertureEv[n-1]*2;
		}
	}
	
	private String[] createApertureStrings() {
		int steps = 10;	
		String[] temp = new String[steps];
		Float lastOdd = 2f;
		Float lastEven = 1.4f;
				
		for (int n = steps; n > 0; n--) {
			if ( (n & 1) == 0 ) {  
				if(lastEven < 10) {
					temp[n-1] = "f/" + String.format("%.1f", lastEven); // lastEven; //String.format(Locale.ENGLISH, "%s", lastEven);				
					lastEven = lastEven * 2;
				}
				else {
					int temp2 = Math.round(lastEven);
					temp[n-1] = "f/" + temp2;
					lastEven = lastEven * 2; 
				}
			}
			else {
				if(lastOdd < 10) {
					temp[n-1] = "f/" + lastOdd; //String.format(Locale.ENGLISH, "%d", lastOdd);
					lastOdd = lastOdd * 2;
				}
				else {
					int temp2 = Math.round(lastOdd);
					temp[n-1] = "f/" + temp2;
					lastOdd = lastOdd * 2; 		
				}	
			}
		}
		return temp;
	}
	
	private String[] createShutterSpeedStrings() {
		String [] staticInitilializer = { 	"1/8000 with 2 stop ND", 
											"1/8000 with 1 stop ND", 
											"1/8000", 
											"1/4000", 
											"1/2000", 
											"1/1000", 
											"1/500", 
											"1/250", 
											"1/125", 
											"1/60",
											"1/30", 
											"1/15", 
											"1/8", 
											"1/4", 
											"1/2", 
											"1\"", 
											"2\"", 
											"4\"", 
											"8\"", 
											"15\"", 
											"30\"", 
											"1\'", 
											"2\'", 
											"4\'",
											"8\'", 
											"16\'", 
											"32\'", 
											"1h", 
											"2h", 
											"4h", 
											"8h" };
		return staticInitilializer;  		
	}

	private String[] createIsoStrings(int i, int baseIso) {
	 	String[] temp = new String[i];	 	
	 	temp[0] = String.valueOf(baseIso);
	 	
	 	for(int n = 1; n<i; n++) {
	 		String previousString = temp[n-1];
	 		int previousValue = Integer.parseInt(previousString);
	 		int currentValue = previousValue * 2;
	 		temp[n] = String.valueOf(currentValue);	 	
	 	}
	 	return temp;
	}
	
	public String[] getShutterSpeedStrings() {
		return shutterSpeedStrings;
	}
	
	public String[] getIsoStrings() {
		return isoStrings;
	}

	public String[] getApertureStrings() {
		return apertureStrings;
	}

	public float getIsoEv(String iso) {
		return this.getEv(this.isoStrings, this.isoEv, iso);		
	}

	public float getShutterEv(String shutter) {
		return this.getEv(this.shutterSpeedStrings, this.shutterSpeedEv, shutter);
	}

	public float getApertureEv(String aperture) {
		return this.getEv(this.apertureStrings, this.apertureEv, aperture);
	}
}
