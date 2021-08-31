package com.example.bloggingapp.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bloggingapp.model.data.Photo
import com.example.bloggingapp.model.data.Post

@Dao
interface PhotoDao {
    // this is used to add all the photo tothe database

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun addAllPhoto(photos : List<Photo>)

        //this is used to retrive all the photo from database
        @Query("SELECT * FROM photo")
        fun getAllPhoto(): LiveData<List<Photo>>
}