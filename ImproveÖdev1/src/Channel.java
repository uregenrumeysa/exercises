
public class Channel {
	/* Burda main olmaz.
	static public void main(String[] args) {
		String name;
		
		
	}*/
	// Buda constructor ve getter / settter mthodları oluşturuyoruz. 
	private int id;
	private String name;
	private int station_id;
	
	public Channel(int id, String name, int station_id) {
		this.id = id;
		this.name = name;
		this.station_id = station_id;
        }

        public int getId() {
	   return id;
        }

        public void setId(int id) {
	   this.id = id;
        }

        public String getName() {
	   return name;
        }

        public void setName(String name) {
	    this.name = name;
  	}

        public int getStation_id() {
	   return station_id;
        }

        public void setStation_id(int station_id) {
	   this.station_id = station_id;
        }
	
	
}
