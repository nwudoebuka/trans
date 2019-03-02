package com.agonaika.data.test;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.agonaika.data.test.MoviesContract.AUTHORITY;
import static com.agonaika.data.test.MoviesContract.PATH_FAVORITE;


public class MoviesContentProvider extends ContentProvider {

    static final int FAVORITES = 5;

    private MoviesDbHelper mMoviesDbHelper;


    //Declare a static variable for the Uri matcher that you construct
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,PATH_FAVORITE, FAVORITES);
        return uriMatcher;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mMoviesDbHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)) {

            case FAVORITES:
                db.beginTransaction();
                int rowsInserted = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(MoviesContract.FavoriteEntry.TABLE_FAVOURITE, null, value);
                        if (_id != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                if (rowsInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }

                return rowsInserted;

            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mMoviesDbHelper = new MoviesDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch(sUriMatcher.match(uri)){
            case FAVORITES:
                return MoviesContract.FavoriteEntry.CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI: "+uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mMoviesDbHelper.getReadableDatabase();
        Cursor cursor;

        switch (sUriMatcher.match(uri)){
            case FAVORITES:

                cursor = mMoviesDbHelper.getReadableDatabase().query(
                        MoviesContract.FavoriteEntry.TABLE_FAVOURITE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mMoviesDbHelper.getWritableDatabase();
        Uri returnUri;
        long id;
        switch (sUriMatcher.match(uri)) {
            case FAVORITES:
                id = db.insert(MoviesContract.FavoriteEntry.TABLE_FAVOURITE, null, values);
                if (id > 0) {
                    returnUri = MoviesContract.FavoriteEntry.buildFavoriteUri(id);
                } else {
                    throw new android.database.SQLException("failed to insert into row" + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        /* Users of the delete method will expect the number of rows deleted to be returned. */
        int numRowsDeleted;

        /*
         * If we pass null as the selection to SQLiteDatabase#delete, our entire table will be
         * deleted. However, if we do pass null and delete all of the rows in the table, we won't
         * know how many rows were deleted. According to the documentation for SQLiteDatabase,
         * passing "1" for the selection will delete all rows and return the number of rows
         * deleted, which is what the caller of this method expects.
         */
        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)) {
            case FAVORITES:
                numRowsDeleted = mMoviesDbHelper.getWritableDatabase().delete(
                        MoviesContract.FavoriteEntry.TABLE_FAVOURITE,
                        selection,
                        selectionArgs);

                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        /* If we actually deleted any rows, notify that a change has occurred to this URI */
        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numRowsDeleted;
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    private Cursor getMoviesFromReferenceTable(String tableFavourite, String[] projection,
                                               String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        // tableName INNER JOIN movies ON tableName.movie_id = movies._id
//        sqLiteQueryBuilder.setTables(
//                tableFavourite + " INNER JOIN " + MoviesContract.MovieEntry.TABLE_DETAILS +
//                        " ON " + tableFavourite + "." + MoviesContract.FavoriteEntry.COLUMN_MOVIE_ID +
//                        " = " + MoviesContract.MovieEntry.TABLE_DETAILS+ "." + MoviesContract.MovieEntry._ID
//        );

        return sqLiteQueryBuilder.query(mMoviesDbHelper.getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }
}