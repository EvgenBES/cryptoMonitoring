package com.test.data.db;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.test.data.entity.NotifCoinResponse;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public class NotifCoinDAO_Impl implements NotifCoinDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfNotifCoinResponse;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfNotifCoinResponse;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public NotifCoinDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNotifCoinResponse = new EntityInsertionAdapter<NotifCoinResponse>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `notif`(`idCoin`,`name`,`symbol`,`pricePosition`,`motionPrice`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotifCoinResponse value) {
        stmt.bindLong(1, value.getIdCoin());
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
        stmt.bindDouble(4, value.getPricePosition());
        final int _tmp;
        _tmp = value.getMotionPrice() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__updateAdapterOfNotifCoinResponse = new EntityDeletionOrUpdateAdapter<NotifCoinResponse>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `notif` SET `idCoin` = ?,`name` = ?,`symbol` = ?,`pricePosition` = ?,`motionPrice` = ? WHERE `idCoin` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotifCoinResponse value) {
        stmt.bindLong(1, value.getIdCoin());
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
        stmt.bindDouble(4, value.getPricePosition());
        final int _tmp;
        _tmp = value.getMotionPrice() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.getIdCoin());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM notif WHERE idCoin = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM notif";
        return _query;
      }
    };
  }

  @Override
  public void insert(NotifCoinResponse notifCoinResponse) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfNotifCoinResponse.insert(notifCoinResponse);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(NotifCoinResponse notifCoinResponse) {
    __db.beginTransaction();
    try {
      __updateAdapterOfNotifCoinResponse.handle(notifCoinResponse);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(long coinId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, coinId);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
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
  public Flowable<List<NotifCoinResponse>> getAllNotif() {
    final String _sql = "SELECT * FROM notif";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"notif"}, new Callable<List<NotifCoinResponse>>() {
      @Override
      public List<NotifCoinResponse> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfIdCoin = _cursor.getColumnIndexOrThrow("idCoin");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfSymbol = _cursor.getColumnIndexOrThrow("symbol");
          final int _cursorIndexOfPricePosition = _cursor.getColumnIndexOrThrow("pricePosition");
          final int _cursorIndexOfMotionPrice = _cursor.getColumnIndexOrThrow("motionPrice");
          final List<NotifCoinResponse> _result = new ArrayList<NotifCoinResponse>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final NotifCoinResponse _item;
            final int _tmpIdCoin;
            _tmpIdCoin = _cursor.getInt(_cursorIndexOfIdCoin);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSymbol;
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            final double _tmpPricePosition;
            _tmpPricePosition = _cursor.getDouble(_cursorIndexOfPricePosition);
            final boolean _tmpMotionPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfMotionPrice);
            _tmpMotionPrice = _tmp != 0;
            _item = new NotifCoinResponse(_tmpIdCoin,_tmpName,_tmpSymbol,_tmpPricePosition,_tmpMotionPrice);
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
