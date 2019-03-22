package entity;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Order {
	private String userId;
	private String orderId;
	private String robotId;
	private String orderStatus;
	private String origin;
	private String destination;
	private String eArrival;
	private String aArrival;
	private String createTime;
	private String cost;
	private String robotType;

	public String getRobotType() {
		return robotType;
	}
	private Order(OrderBuilder builder) {
		this.orderId = builder.orderId;
		this.robotId = builder.robotId;
		this.userId = builder.userId;
		this.orderStatus = builder.orderStatus;
		this.origin = builder.origin;
		this.destination = builder.destination;
		this.aArrival = builder.aArrival;
		this.eArrival = builder.eArrival;
		this.createTime = builder.createTime;
		this.cost=builder.cost;
		this.robotType=builder.robotType;

	}
	public String getCost() {
		return cost;
	}

	public String getUserId() {
		return userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getRobotId() {
		return robotId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String geteArrival() {
		return eArrival;
	}

	public String getaArrival() {
		return aArrival;
	}

	public String getCreateTime() {
		return createTime;
	}

	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("order_id", orderId);
			obj.put("robot_id", robotId);
			obj.put("user_id", userId);
			obj.put("orderStatus", orderStatus);
			obj.put("origin", origin);
			obj.put("destination", destination);
			obj.put("aArrival", aArrival);
			obj.put("eArrival", eArrival);
			obj.put("createTime", createTime);
			obj.put("cost", cost);
			obj.put("robotType", robotType);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return obj;
	}

	public static class OrderBuilder {

		private String orderId;
		private String robotId;
		private String userId;
		private String orderStatus;
		private String origin;
		private String destination;
		private String aArrival;
		private String eArrival;
		private String createTime;
        private String cost;
        private String robotType;
        
        public void setRobotType(String robotType) {
			this.robotType = robotType;
		}

		public void setCost(String cost) {
			this.cost = cost;
		}
        
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public void setRobotId(String robotId) {
			this.robotId = robotId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public void setOrigin(String origin) {
			this.origin = origin;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public void setaArrival(String aArrival) {
			this.aArrival = aArrival;
		}

		public void seteArrival(String eArrival) {
			this.eArrival = eArrival;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public Order build() {
			return new Order(this);
		}
	}
}
