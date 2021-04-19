package unitTests;

import events.IVenue;

public class VenueTestStub implements IVenue {
	
	int capacity;

	public void setCapacity(int venueCapacity) {
		capacity = venueCapacity;
		
	}

	public int getCapacity() {

		return capacity;
	}

}
