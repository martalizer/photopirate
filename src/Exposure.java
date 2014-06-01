public class Exposure {
	ExposureTools exp = new ExposureTools();
	private float fromIso, fromShutterSpd, fromAperture, toIso, toAperture, ev;	
	String iso, shutter, aperture, iso2, aperture2;
	
	public Exposure(String iso, String shutter, String aperture, String iso2, String aperture2) {
		this.iso = iso;
		this.shutter = shutter;
		this.aperture = aperture;
		this.iso2 = iso2;
		this.aperture2 = aperture2;
		fromIso = exp.getIsoEv(iso);
		fromShutterSpd = exp.getShutterEv(shutter);
		fromAperture = exp.getApertureEv(aperture);	
		toIso = exp.getIsoEv(iso2);
		toAperture = exp.getApertureEv(aperture2);
	}

	public float getEv() {
		return fromIso*fromShutterSpd*fromAperture;
	}

	public boolean inputCheck() {
		return (this.fromIso!=0)&&(this.fromShutterSpd!=0)&&(this.fromAperture!=0);
	}

	public float getNewShutterSpeed() {  
		ev = getEv();
		return ev/(this.toAperture*this.toIso);
	}
}
