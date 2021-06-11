package com.example.myshopping;

public class PriceDataStruct {
    private int total;
    private int start;
    private int display;
    private String title;
    private String link;
    private String image;
    private int lprice;
    private int hprice;
    private String mallName;
    private long productId;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLprice() {
        return lprice;
    }

    public void setLprice(int lprice) {
        this.lprice = lprice;
    }


    public int getHprice() {
        return hprice;
    }

    public void setHprice(int hprice) {
        this.hprice = hprice;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
