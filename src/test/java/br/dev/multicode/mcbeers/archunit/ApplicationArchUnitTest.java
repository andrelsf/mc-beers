package br.dev.multicode.mcbeers.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AnalyzeClasses(packages = "br.dev.multicode.mcbeers")
public class ApplicationArchUnitTest {

  @ArchTest
  static final ArchRule app_class_name_should_be_application = ArchRuleDefinition.classes()
      .that().resideInAPackage("br.dev.multicode.mcbeers")
      .and().areAnnotatedWith(SpringBootApplication.class)
      .should().haveSimpleNameEndingWith("Application");

}
