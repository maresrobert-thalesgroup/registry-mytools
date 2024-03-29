package ro.thales.mytools.registryapp.entities;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        ),
        @TypeDef(
                name = "int-array",
                typeClass = IntArrayType.class
        )
})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Templates")
@Entity
@Builder
@EqualsAndHashCode
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private int id;

    @ManyToOne
    @JoinColumn(name = "request_by_id")
    private AppUser requestBy;

    @ManyToOne
    @JoinColumn(name = "request_for_id")
    private AppUser requestFor;

    @Type(type = "int-array")
    @Column(
            name = "floor_access",
            columnDefinition = "integer[]"
    )
    private int[] floorAccess;
    private String kitRequired;

    public void checkForSimilar(List<Template> templateList){
        if(!templateList.isEmpty()){
            for(Template t : templateList){
                if(this.equals(t)){
                    throw new IllegalStateException("You already created a similar template");
                }
            }
        }
    }

}
