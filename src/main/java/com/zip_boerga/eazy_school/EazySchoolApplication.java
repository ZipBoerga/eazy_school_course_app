package com.zip_boerga.eazy_school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.zip_boerga.eazy_school.repository")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableAspectJAutoProxy
//@EntityScan("com.zip_boerga.eazy_school.repository.jpa.entities")
public class EazySchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazySchoolApplication.class, args);
	}

}
