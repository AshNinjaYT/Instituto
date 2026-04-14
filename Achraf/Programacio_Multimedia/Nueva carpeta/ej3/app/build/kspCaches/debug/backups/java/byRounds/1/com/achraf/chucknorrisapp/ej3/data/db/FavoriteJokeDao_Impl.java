package com.achraf.chucknorrisapp.ej3.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FavoriteJokeDao_Impl implements FavoriteJokeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FavoriteJoke> __insertionAdapterOfFavoriteJoke;

  private final EntityDeletionOrUpdateAdapter<FavoriteJoke> __deletionAdapterOfFavoriteJoke;

  public FavoriteJokeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavoriteJoke = new EntityInsertionAdapter<FavoriteJoke>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `favorite_jokes` (`id`,`jokeApiId`,`jokeText`,`category`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FavoriteJoke entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getJokeApiId());
        statement.bindString(3, entity.getJokeText());
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategory());
        }
      }
    };
    this.__deletionAdapterOfFavoriteJoke = new EntityDeletionOrUpdateAdapter<FavoriteJoke>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `favorite_jokes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FavoriteJoke entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertFavorite(final FavoriteJoke joke,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFavoriteJoke.insert(joke);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFavorite(final FavoriteJoke joke,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFavoriteJoke.handle(joke);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<FavoriteJoke>> getAllFavorites() {
    final String _sql = "SELECT * FROM favorite_jokes ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"favorite_jokes"}, new Callable<List<FavoriteJoke>>() {
      @Override
      @NonNull
      public List<FavoriteJoke> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfJokeApiId = CursorUtil.getColumnIndexOrThrow(_cursor, "jokeApiId");
          final int _cursorIndexOfJokeText = CursorUtil.getColumnIndexOrThrow(_cursor, "jokeText");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final List<FavoriteJoke> _result = new ArrayList<FavoriteJoke>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FavoriteJoke _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpJokeApiId;
            _tmpJokeApiId = _cursor.getString(_cursorIndexOfJokeApiId);
            final String _tmpJokeText;
            _tmpJokeText = _cursor.getString(_cursorIndexOfJokeText);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item = new FavoriteJoke(_tmpId,_tmpJokeApiId,_tmpJokeText,_tmpCategory);
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
  public Object getFavoriteByApiId(final String apiId,
      final Continuation<? super FavoriteJoke> $completion) {
    final String _sql = "SELECT * FROM favorite_jokes WHERE jokeApiId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, apiId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FavoriteJoke>() {
      @Override
      @Nullable
      public FavoriteJoke call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfJokeApiId = CursorUtil.getColumnIndexOrThrow(_cursor, "jokeApiId");
          final int _cursorIndexOfJokeText = CursorUtil.getColumnIndexOrThrow(_cursor, "jokeText");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final FavoriteJoke _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpJokeApiId;
            _tmpJokeApiId = _cursor.getString(_cursorIndexOfJokeApiId);
            final String _tmpJokeText;
            _tmpJokeText = _cursor.getString(_cursorIndexOfJokeText);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _result = new FavoriteJoke(_tmpId,_tmpJokeApiId,_tmpJokeText,_tmpCategory);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
