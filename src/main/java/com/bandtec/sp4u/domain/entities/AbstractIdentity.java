package com.bandtec.sp4u.domain.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@MappedSuperclass
@Getter
@Setter

public abstract class AbstractIdentity<id extends Serializable> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private id id;

}
