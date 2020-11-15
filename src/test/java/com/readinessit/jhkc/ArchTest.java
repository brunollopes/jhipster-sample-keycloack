package com.readinessit.jhkc;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.readinessit.jhkc");

        noClasses()
            .that()
            .resideInAnyPackage("com.readinessit.jhkc.service..")
            .or()
            .resideInAnyPackage("com.readinessit.jhkc.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.readinessit.jhkc.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
