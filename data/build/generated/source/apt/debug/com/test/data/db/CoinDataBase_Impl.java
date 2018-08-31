package com.test.data.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class CoinDataBase_Impl extends CoinDataBase {
  private volatile CoinDAO _coinDAO;

  private volatile UserCoinDAO _userCoinDAO;

  private volatile NotifCoinDAO _notifCoinDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `coins` (`id` INTEGER NOT NULL, `name` TEXT, `symbol` TEXT, `rank` INTEGER NOT NULL, `price` REAL NOT NULL, `marketCapUsd` INTEGER NOT NULL, `availableSupply` INTEGER NOT NULL, `totalSupply` INTEGER NOT NULL, `percentChange1h` REAL NOT NULL, `percentChange24h` REAL NOT NULL, `percentChange7d` REAL NOT NULL, `lastUpdated` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user` (`id` INTEGER NOT NULL, `name` TEXT, `symbol` TEXT, `price` REAL NOT NULL, `quantity` REAL NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `notif` (`idCoin` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `symbol` TEXT, `pricePosition` REAL NOT NULL, `motionPrice` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"11ad3e62a50ff5d51e1dd12a9b51ed21\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `coins`");
        _db.execSQL("DROP TABLE IF EXISTS `user`");
        _db.execSQL("DROP TABLE IF EXISTS `notif`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCoins = new HashMap<String, TableInfo.Column>(12);
        _columnsCoins.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCoins.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsCoins.put("symbol", new TableInfo.Column("symbol", "TEXT", false, 0));
        _columnsCoins.put("rank", new TableInfo.Column("rank", "INTEGER", true, 0));
        _columnsCoins.put("price", new TableInfo.Column("price", "REAL", true, 0));
        _columnsCoins.put("marketCapUsd", new TableInfo.Column("marketCapUsd", "INTEGER", true, 0));
        _columnsCoins.put("availableSupply", new TableInfo.Column("availableSupply", "INTEGER", true, 0));
        _columnsCoins.put("totalSupply", new TableInfo.Column("totalSupply", "INTEGER", true, 0));
        _columnsCoins.put("percentChange1h", new TableInfo.Column("percentChange1h", "REAL", true, 0));
        _columnsCoins.put("percentChange24h", new TableInfo.Column("percentChange24h", "REAL", true, 0));
        _columnsCoins.put("percentChange7d", new TableInfo.Column("percentChange7d", "REAL", true, 0));
        _columnsCoins.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCoins = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCoins = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCoins = new TableInfo("coins", _columnsCoins, _foreignKeysCoins, _indicesCoins);
        final TableInfo _existingCoins = TableInfo.read(_db, "coins");
        if (! _infoCoins.equals(_existingCoins)) {
          throw new IllegalStateException("Migration didn't properly handle coins(com.test.data.entity.CoinResponces).\n"
                  + " Expected:\n" + _infoCoins + "\n"
                  + " Found:\n" + _existingCoins);
        }
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(5);
        _columnsUser.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsUser.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsUser.put("symbol", new TableInfo.Column("symbol", "TEXT", false, 0));
        _columnsUser.put("price", new TableInfo.Column("price", "REAL", true, 0));
        _columnsUser.put("quantity", new TableInfo.Column("quantity", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("user", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "user");
        if (! _infoUser.equals(_existingUser)) {
          throw new IllegalStateException("Migration didn't properly handle user(com.test.data.entity.UserCoinResponse).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsNotif = new HashMap<String, TableInfo.Column>(5);
        _columnsNotif.put("idCoin", new TableInfo.Column("idCoin", "INTEGER", true, 1));
        _columnsNotif.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsNotif.put("symbol", new TableInfo.Column("symbol", "TEXT", false, 0));
        _columnsNotif.put("pricePosition", new TableInfo.Column("pricePosition", "REAL", true, 0));
        _columnsNotif.put("motionPrice", new TableInfo.Column("motionPrice", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotif = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotif = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotif = new TableInfo("notif", _columnsNotif, _foreignKeysNotif, _indicesNotif);
        final TableInfo _existingNotif = TableInfo.read(_db, "notif");
        if (! _infoNotif.equals(_existingNotif)) {
          throw new IllegalStateException("Migration didn't properly handle notif(com.test.data.entity.NotifCoinResponse).\n"
                  + " Expected:\n" + _infoNotif + "\n"
                  + " Found:\n" + _existingNotif);
        }
      }
    }, "11ad3e62a50ff5d51e1dd12a9b51ed21", "7ab9fb8300f6ead18db3705c22b02e72");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "coins","user","notif");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `coins`");
      _db.execSQL("DELETE FROM `user`");
      _db.execSQL("DELETE FROM `notif`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public CoinDAO getCoinDAO() {
    if (_coinDAO != null) {
      return _coinDAO;
    } else {
      synchronized(this) {
        if(_coinDAO == null) {
          _coinDAO = new CoinDAO_Impl(this);
        }
        return _coinDAO;
      }
    }
  }

  @Override
  public UserCoinDAO getUserDAO() {
    if (_userCoinDAO != null) {
      return _userCoinDAO;
    } else {
      synchronized(this) {
        if(_userCoinDAO == null) {
          _userCoinDAO = new UserCoinDAO_Impl(this);
        }
        return _userCoinDAO;
      }
    }
  }

  @Override
  public NotifCoinDAO getNotifDAO() {
    if (_notifCoinDAO != null) {
      return _notifCoinDAO;
    } else {
      synchronized(this) {
        if(_notifCoinDAO == null) {
          _notifCoinDAO = new NotifCoinDAO_Impl(this);
        }
        return _notifCoinDAO;
      }
    }
  }
}
