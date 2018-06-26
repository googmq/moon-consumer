package cn.minqi.consumer.repository;

import cn.minqi.consumer.entity.Picture;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface  PictureRepository extends MongoRepository<Picture, Long> {

    List<Picture> findByCategory(String category);

    Picture findById(String id);
}
