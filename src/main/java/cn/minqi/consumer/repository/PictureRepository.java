package cn.minqi.consumer.repository;

import cn.minqi.consumer.entity.Picture;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface  PictureRepository extends MongoRepository<Picture, ObjectId> {

    List<Picture> findByCategory(String category);

    Picture findById(String id);
}
