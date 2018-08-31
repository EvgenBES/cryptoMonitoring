package com.test.data.db;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.test.data.entity.UserCoinResponse;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public class UserCoinDAO_Impl implements UserCoinDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUserCoinResponse;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfUserCoinResponse;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public UserCoinDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserCoinResponse = new EntityInsertionAdapter<UserCoinResponse>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `user`(`id`,`name`,`symbol`,`price`,`quantity`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserCoinResponse value) {
        stmt.bindLong(1, value.id);
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
        stmt.bindDouble(4, value.getPrice());
        stmt.bindDouble(5, value.getQuantity());
      }
    };
    this.__updateAdapterOfUserCoinResponse = new EntityDeletionOrUpdateAdapter<UserCoinResponse>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `user` SET `id` = ?,`name` = ?,`symbol` = ?,`price` = ?,`quantity` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserCoinResponse value) {
        stmt.bindLong(1, value.id);
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
        stmt.bindDouble(4, value.getPrice());
        stmt.bindDouble(5, value.getQuantity());
        stmt.bindLong(6, value.id);
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM user WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM user";
        return _query;
      }
    };
  }

  @Override
  public void insert(UserCoinResponse userCoinResponse) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserCoinResponse.insert(userCoinResponse);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(UserCoinResponse userCoinResponse) {
    __db.beginTransaction();
    try {
      __updateAdapterOfUserCoinResponse.handle(userCoinResponse);
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
  public Flowable<List<UserCoinResponse>> getAll() {
    final String _sql = "SELECT * FROM user INNER JOIN coins ON user.id = coins.id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"user","coins"}, new Callable<List<UserCoinResponse>>() {
      @Override
      public List<UserCoinResponse> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfSymbol = _cursor.getColumnIndexOrThrow("symbol");
          final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
          final int _cursorIndexOfQuantity = _cursor.getColumnIndexOrThrow("quantity");
          final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfName_1 = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfSymbol_1 = _cursor.getColumnIndexOrThrow("symbol");
          final int _cursorIndexOfPrice_1 = _cursor.getColumnIndexOrThrow("price");
          final List<UserCoinResponse> _result = new ArrayList<UserCoinResponse>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserCoinResponse _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSymbol;
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final long _tmpId_1;
            _tmpId_1 = _cursor.getLong(_cursorIndexOfId_1);
            final String _tmpName_1;
            _tmpName_1 = _cursor.getString(_cursorIndexOfName_1);
            final String _tmpSymbol_1;
            _tmpSymbol_1 = _cursor.getString(_cursorIndexOfSymbol_1);
            _item = new UserCoinResponse(_tmpId,_tmpName,_tmpSymbol,_tmpQuantity);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            _item.setPrice(_tmpPrice);
            final double _tmpPrice_1;
            _tmpPrice_1 = _cursor.getDouble(_cursorIndexOfPrice_1);
            _item.setPrice(_tmpPrice_1);
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
