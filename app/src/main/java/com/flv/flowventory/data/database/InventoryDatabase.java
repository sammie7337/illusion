package com.flv.flowventory.data.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;

import com.flv.flowventory.data.dao.*;
import com.flv.flowventory.data.model.*;

@Database(
    entities = {
        User.class,
        Product.class,
        StockTransaction.class,
        Supplier.class,
        Customer.class
    },
    version = 1,
    exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class InventoryDatabase extends RoomDatabase {
    
    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    public abstract StockTransactionDao stockTransactionDao();
    public abstract SupplierDao supplierDao();
    public abstract CustomerDao customerDao();
    
    private static volatile InventoryDatabase INSTANCE;
    
    public static InventoryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        InventoryDatabase.class,
                        "inventory_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
