/*
package com.hackathlon.exoplayertask.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;

import com.thundr.stb.model.Content;
import com.thundr.stb.model.Continuity;
import com.thundr.stb.model.Genre;
import com.thundr.stb.model.GenreDb;
import com.thundr.stb.model.PlayList;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

*/
/**
 * saurav sharma on 8/17/2017.
 *
 * <p>Database Access helper for all locally stored information
 *//*

public class DatabaseManager {

  final String OBJECT_ID = "objectid";
  private Realm realm;

  public DatabaseManager(Realm realm) {
    this.realm = realm;
  }

  */
/** Returns the realm database instance *//*

  public Realm getRealm() {
    return realm;
  }





  public void saveContinuity(List<Continuity> listener) {
    if (realm != null) {
      realm.beginTransaction();
      realm.copyToRealmOrUpdate(listener);
      realm.commitTransaction();
    }
  }








  public RealmResults<PlayList> getPlaylistList() {
    return realm.where(PlayList.class).findAll();
  }

  RealmResults<PlayList> getPlaylistObject(String objectid) {
    return realm.where(PlayList.class).equalTo(OBJECT_ID, objectid).findAll();
  }



  public <E extends RealmObject> List<E> copyFromRealm(RealmResults<E> realmResults) {
    return realm.copyFromRealm(realmResults);
  }

  public void deletePlayListObject(String objectid) {
    if (realm != null) {
      realm.beginTransaction();

      realm.commitTransaction();
    }
  }

  public void deleteContinuityObject(String objectid) {
    if (realm != null) {
      realm.beginTransaction();
      for (Continuity continuity : getContinuityObject(objectid)) {
        continuity.deleteFromRealm();
      }
      realm.commitTransaction();
    }
  }

  public void deletePlayList() {
    if (realm != null) {
      realm.beginTransaction();
      realm.delete(PlayList.class);
      realm.commitTransaction();
    }
  }

  */
/**
   * Saves the Sub genres of the primary genres in the table.<br>
   * Note: Sub genre will only be stored when the it is not previously saved.
   *
   * @param category Category like [TVSHOW/MUSIC]
   * @param primaryGenre Primary of the category [e.g., Entertainment]
   * @param subGenreList Sub {@link Genre} list from the API
   *//*

  public void saveSubGenres(
      @NonNull String category, @NonNull String primaryGenre, @NonNull List<Genre> subGenreList) {
    if (category.equalsIgnoreCase("tvchanel")) {
      RealmResults<GenreDb> result =
          realm.where(GenreDb.class).equalTo("category", category).findAll();
      if (result.size() == 0) {
        realm.beginTransaction();
        List<Genre> genreList = realm.copyToRealmOrUpdate(subGenreList);
        GenreDb genreDb =
            new GenreDb(category, new RealmList<>(genreList.toArray(new Genre[genreList.size()])));
        realm.copyToRealmOrUpdate(genreDb);
        realm.commitTransaction();
      } else {
        GenreDb genreDb = result.get(0);
        RealmList<Genre> genreList = genreDb.getGenreList();
        realm.beginTransaction();
        for (Genre genre : subGenreList) {
          RealmResults<GenreDb> res =
              result.where().equalTo("genreList.genre", genre.getGenre()).findAll();
          if (res.size() == 0) {
            genreList.add(realm.copyToRealm(genre));
          }
        }
        genreDb.setGenreList(genreList);
        realm.commitTransaction();
      }
    } else {
      RealmResults<GenreDb> result =
          realm
              .where(GenreDb.class)
              .equalTo("category", category)
              .findAll()
              .where()
              .equalTo("genreList.genre", primaryGenre)
              .findAll();
      if (result.size() > 0) {
        GenreDb genreDb = result.get(0);
        for (Genre subGenre : subGenreList) {
          RealmResults<GenreDb> res =
              result.where().equalTo("genreList.subGenre.genre", subGenre.getGenre()).findAll();
          if (res.size() == 0) {
            realm.beginTransaction();
            for (Genre genre : genreDb.getGenreList()) {
              if (primaryGenre.equalsIgnoreCase(genre.getGenre())) {
                List<Genre> sList = realm.copyToRealmOrUpdate(subGenreList);
                genre.setSubGenre(new RealmList<>(sList.toArray(new Genre[sList.size()])));
              }
            }
            realm.commitTransaction();
          }
        }
      }
    }
  }

  */
/**
   * Saves the contents based on the Primary genre and/or Sub-Genre
   *
   * @param category Category like [TVSHOW/MUSIC]
   * @param primaryGenre Primary of the category [e.g., Entertainment]
   * @param subGenre Sub genre based on the primary genre [e.g., Action]
   * @param list {@link Content } list to be stored based on Primary genre and/or Sub genre
   *//*

  public void saveContents(
      @NonNull String category,
      @NonNull String primaryGenre,
      @Nullable String subGenre,
      @NonNull List<Content> list) {
    boolean isTvChannel = category.equalsIgnoreCase("tvchanel");
    RealmResults<GenreDb> result =
        realm
            .where(GenreDb.class)
            .equalTo("category", category)
            .findAll()
            .where()
            .equalTo("genreList.genre", isTvChannel ? subGenre : primaryGenre)
            .findAll();

    if (result.size() > 0) {
      GenreDb genreDb = result.get(0);
      realm.beginTransaction();
      for (Genre genre : genreDb.getGenreList()) {
        List<Content> mList = realm.copyToRealmOrUpdate(list);
        if (isTvChannel) {
          if (!TextUtils.isEmpty(subGenre)
              && !TextUtils.isEmpty(genre.getGenre())
              && genre.getGenre().equalsIgnoreCase(subGenre))
            genre.setContentList(new RealmList<>(mList.toArray(new Content[mList.size()])));
        } else {
          if (TextUtils.isEmpty(subGenre)) {
            if (primaryGenre.equalsIgnoreCase(genre.getGenre())) {
              genre.setContentList(new RealmList<>(mList.toArray(new Content[mList.size()])));
            }
          } else if (!TextUtils.isEmpty(subGenre)) {
            for (Genre sGenre : genre.getSubGenre()) {
              if (subGenre.equalsIgnoreCase(sGenre.getGenre())) {
                sGenre.setContentList(new RealmList<>(mList.toArray(new Content[mList.size()])));
              }
            }
          }
        }
      }
      realm.commitTransaction();
    }
  }

  public List<Pair<String, List<Content>>> getContents(
      @NonNull String category, @Nullable String primaryGenre) {
    List<Pair<String, List<Content>>> list = new ArrayList<>();
    RealmResults<GenreDb> result;
    if (category.equalsIgnoreCase("home")) {
      result = realm.where(GenreDb.class).notEqualTo("category", "TVCHANEL").findAll();
    } else if (category.equalsIgnoreCase("TVCHANEL")) {
      result = realm.where(GenreDb.class).equalTo("category", category).findAll();
    } else {
      result = realm.where(GenreDb.class).equalTo("category", category).findAll();
    }
    if (category.equalsIgnoreCase("Home")) {
      for (GenreDb genreDb : result) {
        if (genreDb.getCategory().equalsIgnoreCase("TVSHOW")
            || (genreDb.getCategory().equalsIgnoreCase("MOVIE")))
          for (Genre genre : genreDb.getGenreList()) {
            if (genre.getContentList().size() > 0)
              list.add(
                  Pair.create(
                      realm.copyFromRealm(genre).getGenre(),
                      realm.copyFromRealm(genre.getContentList())));
          }
      }
    } else if (category.equalsIgnoreCase("TVCHANEL")) {
      for (GenreDb genreDb : result)
        if (genreDb.getCategory().equalsIgnoreCase("TVCHANEL")) {
          for (Genre genre : genreDb.getGenreList()) {
            if (genre.getContentList().size() > 0)
              list.add(
                  Pair.create(
                      realm.copyFromRealm(genre).getGenre(),
                      realm.copyFromRealm(genre.getContentList())));
          }
        }
    } else {
      for (GenreDb genreDb : result) {
        if (genreDb.getCategory().equalsIgnoreCase(category))
          for (Genre genre : genreDb.getGenreList()) {
            if (primaryGenre != null && primaryGenre.equalsIgnoreCase(genre.getGenre())) {
              for (Genre subGenre : genre.getSubGenre()) {
                if (subGenre.getContentList().size() > 0)
                  list.add(
                      Pair.create(
                          realm.copyFromRealm(subGenre).getGenre(),
                          realm.copyFromRealm(subGenre.getContentList())));
              }
            }
          }
      }
    }
    return list;
  }
}
*/
