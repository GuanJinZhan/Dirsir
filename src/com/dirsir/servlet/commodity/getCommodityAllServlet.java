package com.dirsir.servlet.commodity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dirsir.dao.entities.Commodity;
import com.dirsir.dao.entities.Merchant;
import com.dirsir.service.commodity.CommodityService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class getCommodityAllServlet
 */
@WebServlet("/commodity/getCommodityAllServlet")
public class getCommodityAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chartset=utf-8");
		HttpSession session =request.getSession();
		PrintWriter out=response.getWriter();
		Merchant merchant=(Merchant)session.getAttribute("merchant");
		int merchantId =merchant.getMerchantId();
		CommodityService service=new CommodityService();
		int pages=Integer.parseInt(request.getParameter("pages"));
		String commodityName=request.getParameter("commodityName");
		List<Commodity> list=service.getCommoditysByCommodityNameLimitAndMerchantId(commodityName, merchantId, pages);
		JSONArray json=new JSONArray();
		json.add(list);
		out.print(json);
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
