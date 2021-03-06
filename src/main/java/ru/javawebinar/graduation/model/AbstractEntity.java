package ru.javawebinar.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.util.ProxyUtils;
import org.springframework.util.Assert;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class AbstractEntity implements Persistable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return id == null;
    }

    public int id() {
        Assert.notNull(id, "Entity must have id");
        return id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(ProxyUtils.getUserClass(o))) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
