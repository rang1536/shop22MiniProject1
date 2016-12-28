package shop.order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.goods.db.Goods;
import shop.goods.db.GoodsDao;
import shop.order.db.Order;
import shop.order.db.OrderDao;

public class OUpdateForm implements Action {

	@Override
	public OActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Order order = new Order();
		OrderDao orderDao = new OrderDao();
		
		//거래처리상태가 넘어왔는지 확인하여 넘어왔다면 주문내용을 업데이트처리 해준다.
		if(request.getParameter("tradeState") != null){
			
		}
		
		//주문번호 확인하고 주문번호로 주문정보를 불러온다.	
		System.out.println("주문번호 확인 : "+request.getParameter("orderNum"));
		int orderNum = Integer.parseInt(request.getParameter("orderNum"));
		
		
		
		order = orderDao.oSelectOrderByOrderNum(orderNum);
		
		//상품정보 불러오기~
		Goods goods = new Goods();
		GoodsDao goodsDao = new GoodsDao();
		goods = goodsDao.gSelectOne(order.getOrderGoodsNum());
		
		
		//리퀘스트에 order, 상품정보세팅
		request.setAttribute("order", order);
		request.setAttribute("goods", goods);
		
		//view화면으로 포워드한다.
		OActionForward oforward = new OActionForward();
		oforward.setRedirect(false);
		oforward.setPath("/order/oUpdateForm.jsp");
		return oforward;
	}

}
