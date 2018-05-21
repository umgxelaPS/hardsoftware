package Facturacion.pagos;

/*import com.rafsan.inventory.HibernateUtil;
import com.rafsan.inventory.dao.ProductDao;
import com.rafsan.inventory.entity.Product;
import com.rafsan.inventory.entity.Supplier;*/
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/*import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;*/

public class modeloProducto /*implements ProductDao*/ {

    //private static Session session;


    /*public ObservableList<Product> getProducts() {
    
    ObservableList<Product> list = FXCollections.observableArrayList();
    session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    List<Product> products = session.createQuery("from Product").list();
    session.beginTransaction().commit();
    products.stream().forEach(list::add);
    
    return list;
    }
    
    public Product getProduct(long id) {
    
    session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Product product = session.get(Product.class, id);
    session.getTransaction().commit();
    
    return product;
    }
    
    
    public Product getProductByName(String productName) {
    
    session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Query query = session.createQuery("from Product where productName=:name");
    query.setParameter("name", productName);
    Product product = (Product) query.uniqueResult();
    
    return product;
    }
    
    
    public void saveProduct(Product product) {
    session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    session.save(product);
    session.getTransaction().commit();
    }
    
    
    public void updateProduct(Product product) {
    
    session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Product p = session.get(Product.class, product.getId());
    p.setProductName(product.getProductName());
    p.setCategory(product.getCategory());
    p.setQuantity(product.getQuantity());
    p.setPrice(product.getPrice());
    p.setDescription(product.getDescription());
    session.getTransaction().commit();
    }
    
    
    public void increaseProduct(Product product){
    
    session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Product p = session.get(Product.class, product.getId());
    p.setQuantity(product.getQuantity());
    session.getTransaction().commit();
    }
    
    @Override
    public void decreaseProduct(Product product){
    
    session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Product p = session.get(Product.class, product.getId());
    p.setQuantity(product.getQuantity());
    session.getTransaction().commit();
    }
    
    
    public void deleteProduct(Product product) {
    session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Product p = session.get(Product.class, product.getId());
    session.delete(p);
    session.getTransaction().commit();
    }
    
    
    public ObservableList<String> getProductNames(){
    
    session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Criteria criteria = session.createCriteria(Product.class);
    criteria.setProjection(Projections.property("productName"));
    ObservableList<String> list = FXCollections.observableArrayList(criteria.list());
    session.getTransaction().commit();
    
    return list;
    }*/
}
