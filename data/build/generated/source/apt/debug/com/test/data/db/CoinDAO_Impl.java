package com.test.data.db;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.test.data.entity.CoinResponces;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public class CoinDAO_Impl implements CoinDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCoinResponces;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfCoinResponces;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfCoinResponces;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public CoinDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCoinResponces = new EntityInsertionAdapter<CoinResponces>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `coins`(`id`,`name`,`symbol`,`rank`,`price`,`marketCapUsd`,`availableSupply`,`totalSupply`,`percentChange1h`,`percentChange24h`,`percentChange7d`,`lastUpdated`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CoinResponces value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getSymbol() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSymbol());
        }
        stmt.bindLong(4, value.getRank());
        stmt.bindDouble(5, value.getPrice());
        stmt.bindLong(6, value.getMarketCapUsd());
        stmt.bindLong(7, value.getAvailableSupply());
        stmt.bindLong(8, value.getTotalSupply());
        stmt.bindDouble(9, value.getPercentChange1h());
        stmt.bindDouble(10, value.getPercentChange24h());
        stmt.bindDouble(11, value.getPercentChange7d());
        stmt.bindLong(12, value.getLastUpdated());
      }
    };
    this.__deletionAdapterOfCoinResponces = new EntityDeletionOrUpdateAdapter<CoinResponces>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `coins` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CoinResponces value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfCoinResponces = new EntityDeletionOrUpdateAdapter<CoinResponces>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `coins` SET `id` = ?,`name` = ?,`symbol` = ?,`rank` = ?,`price` = ?,`marketCapUsd` = ?,`availableSupply` = ?,`totalSupply` = ?,`percentChange1h` = ?,`percentChange24h` = ?,`percentChange7d` = ?,`lastUpdated` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CoinResponces value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getSymbol() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSymbol());
        }
        stmt.bindLong(4, value.getRank());
        stmt.bindDouble(5, value.getPrice());
        stmt.bindLong(6, value.getMarketCapUsd());
        stmt.bindLong(7, value.getAvailableSupply());
        stmt.bindLong(8, value.getTotalSupply());
        stmt.bindDouble(9, value.getPercentChange1h());
        stmt.bindDouble(10, value.getPercentChange24h());
        stmt.bindDouble(11, value.getPercentChange7d());
        stmt.bindLong(12, value.getLastUpdated());
        stmt.bindLong(13, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM coins";
        return _query;
      }
    };
  }

  @Override
  public void insert(CoinResponces coinResponces) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCoinResponces.insert(coinResponces);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(CoinResponces coinResponces) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfCoinResponces.handle(coinResponces);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(CoinResponces coinResponces) {
    __db.beginTransaction();
    try {
      __updateAdapterOfCoinResponces.handle(coinResponces);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public Flowable<List<CoinResponces>> searchName(String search) {
    final String _sql = "SELECT * FROM coins WHERE name LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (search == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, search);
    }
    return RxRoom.createFlowable(__db, new String[]{"coins"}, new Callable<List<CoinResponces>>() {
      @Override
      public List<CoinResponces> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfSymbol = _cursor.getColumnIndexOrThrow("symbol");
          final int _cursorIndexOfRank = _cursor.getColumnIndexOrThrow("rank");
          final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
          final int _cursorIndexOfMarketCapUsd = _cursor.getColumnIndexOrThrow("marketCapUsd");
          final int _cursorIndexOfAvailableSupply = _cursor.getColumnIndexOrThrow("availableSupply");
          final int _cursorIndexOfTotalSupply = _cursor.getColumnIndexOrThrow("totalSupply");
          final int _cursorIndexOfPercentChange1h = _cursor.getColumnIndexOrThrow("percentChange1h");
          final int _cursorIndexOfPercentChange24h = _cursor.getColumnIndexOrThrow("percentChange24h");
          final int _cursorIndexOfPercentChange7d = _cursor.getColumnIndexOrThrow("percentChange7d");
          final int _cursorIndexOfLastUpdated = _cursor.getColumnIndexOrThrow("lastUpdated");
          final List<CoinResponces> _result = new ArrayList<CoinResponces>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CoinResponces _item;
            _item = new CoinResponces();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpSymbol;
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            _item.setSymbol(_tmpSymbol);
            final int _tmpRank;
            _tmpRank = _cursor.getInt(_cursorIndexOfRank);
            _item.setRank(_tmpRank);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            _item.setPrice(_tmpPrice);
            final long _tmpMarketCapUsd;
            _tmpMarketCapUsd = _cursor.getLong(_cursorIndexOfMarketCapUsd);
            _item.setMarketCapUsd(_tmpMarketCapUsd);
            final long _tmpAvailableSupply;
            _tmpAvailableSupply = _cursor.getLong(_cursorIndexOfAvailableSupply);
            _item.setAvailableSupply(_tmpAvailableSupply);
            final long _tmpTotalSupply;
            _tmpTotalSupply = _cursor.getLong(_cursorIndexOfTotalSupply);
            _item.setTotalSupply(_tmpTotalSupply);
            final double _tmpPercentChange1h;
            _tmpPercentChange1h = _cursor.getDouble(_cursorIndexOfPercentChange1h);
            _item.setPercentChange1h(_tmpPercentChange1h);
            final double _tmpPercentChange24h;
            _tmpPercentChange24h = _cursor.getDouble(_cursorIndexOfPercentChange24h);
            _item.setPercentChange24h(_tmpPercentChange24h);
            final double _tmpPercentChange7d;
            _tmpPercentChange7d = _cursor.getDouble(_cursorIndexOfPercentChange7d);
            _item.setPercentChange7d(_tmpPercentChange7d);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item.setLastUpdated(_tmpLastUpdated);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<List<CoinResponces>> searchCoinName(String search) {
    final String _sql = "SELECT * FROM coins WHERE name LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (search == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, search);
    }
    return RxRoom.createFlowable(__db, new String[]{"coins"}, new Callable<List<CoinResponces>>() {
      @Override
      public List<CoinResponces> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfSymbol = _cursor.getColumnIndexOrThrow("symbol");
          final int _cursorIndexOfRank = _cursor.getColumnIndexOrThrow("rank");
          final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
          final int _cursorIndexOfMarketCapUsd = _cursor.getColumnIndexOrThrow("marketCapUsd");
          final int _cursorIndexOfAvailableSupply = _cursor.getColumnIndexOrThrow("availableSupply");
          final int _cursorIndexOfTotalSupply = _cursor.getColumnIndexOrThrow("totalSupply");
          final int _cursorIndexOfPercentChange1h = _cursor.getColumnIndexOrThrow("percentChange1h");
          final int _cursorIndexOfPercentChange24h = _cursor.getColumnIndexOrThrow("percentChange24h");
          final int _cursorIndexOfPercentChange7d = _cursor.getColumnIndexOrThrow("percentChange7d");
          final int _cursorIndexOfLastUpdated = _cursor.getColumnIndexOrThrow("lastUpdated");
          final List<CoinResponces> _result = new ArrayList<CoinResponces>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CoinResponces _item;
            _item = new CoinResponces();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpSymbol;
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            _item.setSymbol(_tmpSymbol);
            final int _tmpRank;
            _tmpRank = _cursor.getInt(_cursorIndexOfRank);
            _item.setRank(_tmpRank);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            _item.setPrice(_tmpPrice);
            final long _tmpMarketCapUsd;
            _tmpMarketCapUsd = _cursor.getLong(_cursorIndexOfMarketCapUsd);
            _item.setMarketCapUsd(_tmpMarketCapUsd);
            final long _tmpAvailableSupply;
            _tmpAvailableSupply = _cursor.getLong(_cursorIndexOfAvailableSupply);
            _item.setAvailableSupply(_tmpAvailableSupply);
            final long _tmpTotalSupply;
            _tmpTotalSupply = _cursor.getLong(_cursorIndexOfTotalSupply);
            _item.setTotalSupply(_tmpTotalSupply);
            final double _tmpPercentChange1h;
            _tmpPercentChange1h = _cursor.getDouble(_cursorIndexOfPercentChange1h);
            _item.setPercentChange1h(_tmpPercentChange1h);
            final double _tmpPercentChange24h;
            _tmpPercentChange24h = _cursor.getDouble(_cursorIndexOfPercentChange24h);
            _item.setPercentChange24h(_tmpPercentChange24h);
            final double _tmpPercentChange7d;
            _tmpPercentChange7d = _cursor.getDouble(_cursorIndexOfPercentChange7d);
            _item.setPercentChange7d(_tmpPercentChange7d);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item.setLastUpdated(_tmpLastUpdated);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<List<CoinResponces>> getAll() {
    final String _sql = "SELECT * FROM coins";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"coins"}, new Callable<List<CoinResponces>>() {
      @Override
      public List<CoinResponces> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfSymbol = _cursor.getColumnIndexOrThrow("symbol");
          final int _cursorIndexOfRank = _cursor.getColumnIndexOrThrow("rank");
          final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
          final int _cursorIndexOfMarketCapUsd = _cursor.getColumnIndexOrThrow("marketCapUsd");
          final int _cursorIndexOfAvailableSupply = _cursor.getColumnIndexOrThrow("availableSupply");
          final int _cursorIndexOfTotalSupply = _cursor.getColumnIndexOrThrow("totalSupply");
          final int _cursorIndexOfPercentChange1h = _cursor.getColumnIndexOrThrow("percentChange1h");
          final int _cursorIndexOfPercentChange24h = _cursor.getColumnIndexOrThrow("percentChange24h");
          final int _cursorIndexOfPercentChange7d = _cursor.getColumnIndexOrThrow("percentChange7d");
          final int _cursorIndexOfLastUpdated = _cursor.getColumnIndexOrThrow("lastUpdated");
          final List<CoinResponces> _result = new ArrayList<CoinResponces>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CoinResponces _item;
            _item = new CoinResponces();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpSymbol;
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            _item.setSymbol(_tmpSymbol);
            final int _tmpRank;
            _tmpRank = _cursor.getInt(_cursorIndexOfRank);
            _item.setRank(_tmpRank);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            _item.setPrice(_tmpPrice);
            final long _tmpMarketCapUsd;
            _tmpMarketCapUsd = _cursor.getLong(_cursorIndexOfMarketCapUsd);
            _item.setMarketCapUsd(_tmpMarketCapUsd);
            final long _tmpAvailableSupply;
            _tmpAvailableSupply = _cursor.getLong(_cursorIndexOfAvailableSupply);
            _item.setAvailableSupply(_tmpAvailableSupply);
            final long _tmpTotalSupply;
            _tmpTotalSupply = _cursor.getLong(_cursorIndexOfTotalSupply);
            _item.setTotalSupply(_tmpTotalSupply);
            final double _tmpPercentChange1h;
            _tmpPercentChange1h = _cursor.getDouble(_cursorIndexOfPercentChange1h);
            _item.setPercentChange1h(_tmpPercentChange1h);
            final double _tmpPercentChange24h;
            _tmpPercentChange24h = _cursor.getDouble(_cursorIndexOfPercentChange24h);
            _item.setPercentChange24h(_tmpPercentChange24h);
            final double _tmpPercentChange7d;
            _tmpPercentChange7d = _cursor.getDouble(_cursorIndexOfPercentChange7d);
            _item.setPercentChange7d(_tmpPercentChange7d);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item.setLastUpdated(_tmpLastUpdated);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
