package heck.mhpizzeria.logic;

import heck.mhpizzeria.logic.db.DbManager;
import heck.mhpizzeria.model.Bill;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Comparator;

/**
 * <h2>Singleton zum Verwalten von {@link Bill}</h2>
 * Bills werden in einer ObservableList gespeichert, die es erlaubt mit Hilfe von einem Listener Änderungen an die
 * Datenbank weiterzugeben. Die Liste kann and die Views weitergegeben werden für die Darstellung in der GUI.
 *
 * Die currentBill ermöglicht eine Rechnung auszuwählen, die dann bereits bei der Initialisierung der View zur Verfügung
 * stehen kann.
 */
public class BillHolder {
    //region attributes
    private static BillHolder instance;
    private static ObservableList<Bill> bills;
    private static Bill currentBill;
    //endregion

    //region setters&getters
    public static Bill getCurrentBill() {
        return currentBill;
    }

    public static void setCurrentBill(Bill currentBill) {
        BillHolder.currentBill = currentBill;
    }
    //endregion

    //region constructors
    private BillHolder() {
        bills = FXCollections.observableArrayList(bill -> new Observable[]{
                bill.getCustomer().nameProperty(), bill.getCustomer().tableNumberProperty(), bill.sumProperty()
        });
        //Rechnungen aus der Datenbank hinzufügen
        bills.addAll(DbManager.getInstance().readAllBillRecords());


        bills.addListener((ListChangeListener<Bill>) change -> {
            while (change.next()){
                if (change.wasAdded()) {
                    Bill bill = change.getAddedSubList().get(0);
                    DbManager.getInstance().insertBill(bill);
                } else if (change.wasRemoved()) {
                    Bill bill = change.getRemoved().get(0);
                    DbManager.getInstance().removeBill(bill);
                }
            }
        });
    }

    public static synchronized BillHolder getInstance() {
        if (instance == null) instance = new BillHolder();
        return instance;
    }
    //endregion

    //region methods&functions
    public void saveBill(Bill bill) {
        bills.add(bill);
    }

    public void deleteBill(Bill bill) {
        bills.remove(bill);
    }

    public ObservableList<Bill> getBills() {
        bills.sort(Comparator.comparingInt(Bill::getPk_id).reversed());
        return bills;
    }
    //endregion
}