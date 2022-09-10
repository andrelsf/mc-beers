package br.dev.multicode.mcbeers.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@AnalyzeClasses(packages = "br.dev.multicode.mcbeers")
public class ApplicationArchUnitTest {

  @ArchTest
  static final ArchRule app_class_name_should_be_application = ArchRuleDefinition.classes()
      .that().resideInAPackage("br.dev.multicode.mcbeers")
      .and().areAnnotatedWith(SpringBootApplication.class)
      .should().haveSimpleNameEndingWith("Application");

  @ArchTest
  static final ArchRule configs_should_reside_in_a_configs_package = ArchRuleDefinition.classes()
      .that().areAnnotatedWith(Configuration.class)
      .or().areNotNestedClasses()
      .and().areAnnotatedWith(ConfigurationProperties.class)
      .should().resideInAPackage("..configs..")
      .andShould().haveSimpleNameEndingWith("Config")
      .as("Configs should reside in a package '..configs..'")
      .allowEmptyShould(true);

}
