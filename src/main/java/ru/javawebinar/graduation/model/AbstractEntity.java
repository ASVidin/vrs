package ru.javawebinar.graduation.model;

import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Size;

@MappedSuperclass
@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public abstract class AbstractEntity implements Persistable<Integer> {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "global_seq")
    protected Integer id;

    @Column(name = "name")
    @Size(max = 128)
    private String name;

    @Override
    public boolean isNew() {
        return false;
    }

    public int id() {
        Assert.notNull(id, "Entity must have id");
        return id;
    }
}
