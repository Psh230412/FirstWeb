package gmap;

import object.Location;

public class Distance {
	
	private int distance;
	private Location destination;
	
	public Distance( int distance,Location destination) {
		
		this.distance = distance;
		this.destination = destination;
	}

	public Location getdestination() {
		return destination;
	}

	public void setdestination(Location destination) {
		this.destination = destination;
	}

	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + distance;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distance other = (Distance) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (distance != other.distance)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Distance [distance=" + distance + ", destination=" + destination + "]";
	}

	
	
	
	
	
	
}
