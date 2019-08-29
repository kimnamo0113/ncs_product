package ncs.exam.dto;

import java.text.DecimalFormat;
import java.util.List;

public class Sale {
	private int no;
	private String productCode;
	private int price;
	private int saleCnt;
	private int marginrate;
	private SaleDetail saleDetail;
	private Product product;
	
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Sale() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sale(int no, String productCode, int price, int saleCnt, int marginrate, SaleDetail saleDetail) {
		super();
		this.no = no;
		this.productCode = productCode;
		this.price = price;
		this.saleCnt = saleCnt;
		this.marginrate = marginrate;
		this.saleDetail = saleDetail;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSaleCnt() {
		return saleCnt;
	}
	public void setSaleCnt(int saleCnt) {
		this.saleCnt = saleCnt;
	}
	public int getMarginrate() {
		return marginrate;
	}
	public void setMarginrate(int marginrate) {
		this.marginrate = marginrate;
	}
	public SaleDetail getSaleDetail() {
		return saleDetail;
	}
	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}
	
	
@Override
	public String toString() {
		return "Sale [no=" + no + ", productCode=" + productCode + ", price=" + price + ", saleCnt=" + saleCnt
				+ ", marginrate=" + marginrate + ", saleDetail=" + saleDetail + ", product=" + product + "]";
	}
	public Object[] toArray() {
		DecimalFormat df=new DecimalFormat("###,###");
		return new Object[] { no,productCode,product.getProductName(),df.format(price),df.format(saleCnt),df.format(saleDetail.getSupplyPrice()),df.format(saleDetail.getAddTax()),df.format(saleDetail.getSalePrice()),df.format(marginrate),df.format(saleDetail.getMarginPrice())};
	}
	
}
