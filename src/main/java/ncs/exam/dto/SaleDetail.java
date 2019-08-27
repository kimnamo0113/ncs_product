package ncs.exam.dto;

public class SaleDetail {
	private int no;
	private int salePrice;
	private int addTax;
	private int supplyPrice;
	private int marginPrice;
	
	public SaleDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public SaleDetail(int salePrice, int addTax, int supplyPrice, int marginPrice) {
		super();
		this.salePrice = salePrice;
		this.addTax = addTax;
		this.supplyPrice = supplyPrice;
		this.marginPrice = marginPrice;
	}



	public SaleDetail(int no, int salePrice, int addTax, int supplyPrice, int marginPrice) {
		super();
		this.no = no;
		this.salePrice = salePrice;
		this.addTax = addTax;
		this.supplyPrice = supplyPrice;
		this.marginPrice = marginPrice;
	}



	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getAddTax() {
		return addTax;
	}
	public void setAddTax(int addTax) {
		this.addTax = addTax;
	}
	public int getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(int supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public int getMarginPrice() {
		return marginPrice;
	}
	public void setMarginPrice(int marginPrice) {
		this.marginPrice = marginPrice;
	}



	@Override
	public String toString() {
		return "SaleDetail [no=" + no + ", salePrice=" + salePrice + ", addTax=" + addTax + ", supplyPrice="
				+ supplyPrice + ", marginPrice=" + marginPrice + "]";
	}
	
	
	
}
