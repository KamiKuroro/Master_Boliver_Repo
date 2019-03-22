package entity;

import org.json.JSONObject;

public class TrackOrderEntity {
	private String robotId;
	private String currentLocation;
    private String robotType;

	public String getRobotType() {
		return robotType;
	}
	private TrackOrderEntity(TrackOrderBuilder builder) {
		this.currentLocation = builder.currentLocation;
		this.robotId = builder.robotId;
		this.robotType=builder.robotType;

	}


	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("currentLocation", currentLocation);
			obj.put("robot_id", robotId);
			obj.put("robot_type", robotType);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return obj;
	}

	public static class TrackOrderBuilder {
		private String robotId;
		private String currentLocation;
        private String robotType;
        

		public void setRobotId(String robotId) {
			this.robotId = robotId;
		}


		public void setCurrentLocation(String currentLocation) {
			this.currentLocation = currentLocation;
		}


		public void setRobotType(String robotType) {
			this.robotType = robotType;
		}


		public TrackOrderEntity build() {
			return new TrackOrderEntity(this);
		}
	}

}
