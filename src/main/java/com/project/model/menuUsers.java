package com.project.model;

public class menuUsers {
	private boolean flagLogin;
	/*
	 * @Pages
	 */
	private boolean homePage;
	private boolean productsPage;
	private boolean invoicePage;
	private boolean cartPage;
	private boolean blogPage;
	private boolean watchlistPage;
	
	public boolean isHomePage() {
		return homePage;
	}
	public void setHomePage(boolean homePage) {
		this.homePage = homePage;
	}
	public boolean isProductsPage() {
		return productsPage;
	}
	public void setProductsPage(boolean productsPage) {
		this.productsPage = productsPage;
	}
	public boolean isInvoicePage() {
		return invoicePage;
	}
	public void setInvoicePage(boolean invoicePage) {
		this.invoicePage = invoicePage;
	}
	public boolean isCartPage() {
		return cartPage;
	}
	public void setCartPage(boolean cartPage) {
		this.cartPage = cartPage;
	}
	public boolean isBlogPage() {
		return blogPage;
	}
	public void setBlogPage(boolean blogPage) {
		this.blogPage = blogPage;
	}
	public boolean isWatchlistPage() {
		return watchlistPage;
	}
	public void setWatchlistPage(boolean watchlistPage) {
		this.watchlistPage = watchlistPage;
	}
	public boolean isFlagLogin() {
		return flagLogin;
	}
	public void setFlagLogin(boolean flagLogin) {
		this.flagLogin = flagLogin;
	}
	
	
}
