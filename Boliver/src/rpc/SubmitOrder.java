package rpc;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Order;
import entity.Order.OrderBuilder;

/**
 * Servlet implementation class submitOrder
 */
@WebServlet("/submit")
public class SubmitOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int RANDOM =0;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DBConnection connection = DBConnectionFactory.getConnection();
	   	try {
	   		 //acquire parameter from front end;
	   		 JSONObject input = RpcHelper.readJSONObject(request);
	   		 String userId = input.getString("user_id");
	   		 String robotType =input.getString("robot_type");
	   		 String cost =input.getString("cost");
	   		 String destination =input.getString("destination");
	   		 String origin =input.getString("origin");
	   		 String eArrival =input.getString("e_arrival");
	   		 //generate orderID
	   		
			 Date date = new Date();
			 SimpleDateFormat mf = new SimpleDateFormat("yyyyMMddhhmmss");
			 String createTime = mf.format(date);
			 String orderId = createTime + String.format("%05d", RANDOM++);
			 if (RANDOM >= 100) {
					RANDOM = 0;
				}
	   		
	   		 String robotId="10";
	   		
	   		 OrderBuilder builder=new OrderBuilder();
	   		 builder.setUserId(userId);
	   		 builder.setOrigin(origin);
	   		 builder.setRobotId(robotId);
	   		 builder.setDestination(destination);
	   		 builder.seteArrival(eArrival);
	   		 builder.setOrderId(orderId);
	   		 builder.setOrderStatus("1");
	   		 builder.setCreateTime(createTime);
	   		 builder.setCost(cost);
	   		 
	   		 
	   		 
	   		 Order order=builder.build();
	   		 JSONObject obj = new JSONObject();
	   		 if(connection.placeOrder(order)) {
	   			 obj.put("status", "sucess");
	   		 }else {
	   			 obj.put("status", "failed");
	   		 }
	   		RpcHelper.writeJsonObject(response, obj);
	   	 } catch (Exception e) {
	   		 e.printStackTrace();
	   	 } finally {
	   		 connection.close();
	   	 }
	}
}


