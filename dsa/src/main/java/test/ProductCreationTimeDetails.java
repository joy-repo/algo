package test;

public class ProductCreationTimeDetails implements Comparable<ProductCreationTimeDetails>{
    final String product;
    final Long date;

    public ProductCreationTimeDetails(String product, Long date){
        this.product = product;
        this.date = date;
    }

    public String getProduct() {
        return product;
    }

    public Long getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ProductCreationTimeDetails{" +
                "product='" + product + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public int compareTo(ProductCreationTimeDetails pctd) {
        return this.date.compareTo(pctd.date);
    }
}