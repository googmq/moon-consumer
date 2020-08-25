package cn.minqi.consumer.repository;

import cn.minqi.consumer.entity.Picture;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PictureRepository extends MongoRepository<Picture, ObjectId> {

    List<Picture> findByCategory(String category);

    Picture findById(String id);
}
