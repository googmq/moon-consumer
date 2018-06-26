package cn.minqi.consumer.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="sites")
@Getter
@Setter
@AllArgsConstructor
public class Picture {

    @Id
    private ObjectId id;

    private String title;
    private String raw_text;
    private String url;
    private String desc;
    private String category;

}
