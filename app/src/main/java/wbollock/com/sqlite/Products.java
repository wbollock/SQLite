package wbollock.com.sqlite;


public class Products {
    private int _id;
    private String _productname;

    public Products(){
        // empty consturctor, in case we want to make an empty object and give it a name later
    }

    public Products(String productname) { // so user can type in product name and assigned ID #
        this._productname = productname; // productname being passed in so don't need underscore

    }

    public void set_id(int _id) { // set id for new product
        this._id = _id;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }

    public int get_id() { // retrive/print id
        return _id;
    }

    public String get_productname() {
        return _productname;
    }
}
