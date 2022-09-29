package heck.mhpizzeria.gui;


import heck.mhpizzeria.Main;
import heck.mhpizzeria.logic.BillHolder;
import heck.mhpizzeria.model.Bill;
import heck.mhpizzeria.model.Customer;
import heck.mhpizzeria.model.OrderItem;
import heck.mhpizzeria.settings.AppTexts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <h2>Singleton zum Verwalten der Scenes, die auf der Stage präsentiert werden sollen</h2>
 */
public class SceneManager {
    //region attributes
    static private SceneManager instance;
    static private Stage stage;
    //endregion

    //region setters & getters
    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setTitle(AppTexts.STAGE_TITLE_SCENE_MANAGE);
    }
    //endregion

    //region constructors
    private SceneManager() {
    }

    static synchronized public SceneManager getInstance() {
        if (instance == null) instance = new SceneManager();
        return instance;
    }
    //endregion

    //region methods & functions
    public void stageMasterScene() {
        try {
            switchScene(new Scene(new FXMLLoader(Main.class.getResource("masterViewLayout.fxml")).load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stageCustomerScene() {
        try {
            switchScene(new Scene(new FXMLLoader(Main.class.getResource("customerViewLayout.fxml")).load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stageCustomerDetailedScene(Customer customer) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("customerDetailedViewLayout.fxml"));
            switchScene(new Scene(fxmlLoader.load()));
            CustomerDetailedViewController customerDetailedViewController = fxmlLoader.getController();
            if (customer != null) {
                customerDetailedViewController.setCustomer(customer);
            } else {
                customerDetailedViewController.disableDeleteBtn();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stageOrderItemScene(Customer customer) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("orderItemViewLayout.fxml"));
            switchScene(new Scene(fxmlLoader.load()));
            OrderItemViewController orderItemViewController = fxmlLoader.getController();
            orderItemViewController.setCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stageOrderItemDetailedScene(OrderItem orderItem, Customer customer) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("orderItemDetailedViewLayout.fxml"));
            switchScene(new Scene(fxmlLoader.load()));
            OrderItemDetailedViewController orderItemDetailedViewController = fxmlLoader.getController();
            orderItemDetailedViewController.setOrderItem(orderItem);
            orderItemDetailedViewController.setCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stageBillDetailedScene(Bill bill) {
        try {
            BillHolder.getInstance().setCurrentBill(bill);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("billDetailedViewLayout.fxml"));
            switchScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }
    //endregion
}
