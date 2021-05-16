package com.mironov.image.studio.dao;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mironov.image.studio.dao")
@EntityScan("com.mironov.image.studio.entities")
public class DaoConfiguration {

}
