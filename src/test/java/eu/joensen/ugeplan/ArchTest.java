package eu.joensen.ugeplan;

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
            .importPackages("eu.joensen.ugeplan");

        noClasses()
            .that()
            .resideInAnyPackage("eu.joensen.ugeplan.service..")
            .or()
            .resideInAnyPackage("eu.joensen.ugeplan.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..eu.joensen.ugeplan.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
