package br.dev.multicode.mcbeers.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import javax.persistence.Entity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "br.dev.multicode.mcbeers")
public class ArchUnitTest {

  @ArchTest
  static final ArchRule app_class_name_should_have_ending_with_application = ArchRuleDefinition.classes()
      .that().areAnnotatedWith(SpringBootApplication.class)
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

  @ArchTest
  static final ArchRule requests_should_reside_in_a_requests_package = ArchRuleDefinition.classes()
      .that().resideInAPackage("..api.http.requests..")
      .should().haveSimpleNameEndingWith("Request")
      .orShould().haveSimpleNameEndingWith("Builder")
      .as("Requests should reside in package '..api.http.requests..'")
      .allowEmptyShould(false);

  @ArchTest
  static final ArchRule responses_should_reside_in_a_response_packages = ArchRuleDefinition.classes()
      .that().resideInAPackage("..api.http.responses..")
      .should().haveSimpleNameEndingWith("Response")
      .orShould().haveSimpleNameEndingWith("Builder")
      .as("Responses should reside in package '..api.http.requests..'")
      .allowEmptyShould(false);

  @ArchTest
  static final ArchRule controllers_should_be_suffixed_with_resources = ArchRuleDefinition.classes()
      .that().resideInAPackage("..api.resources..")
      .and().areAnnotatedWith(RestController.class)
      .and().areAnnotatedWith(RequestMapping.class)
      .should().haveSimpleNameEndingWith("Resource")
      .allowEmptyShould(false);

  @ArchTest
  static final ArchRule service_classes_should_reside_in_a_impl_package = ArchRuleDefinition.classes()
      .that().resideInAPackage("..impl..")
      .should().haveSimpleNameEndingWith("Impl")
      .orShould().haveSimpleNameEndingWith("ImplTest")
      .andShould().notBeInterfaces();

  @ArchTest
  static final ArchRule interfaces_should_not_have_names_ending_with_word_interface = ArchRuleDefinition.noClasses()
      .that().areInterfaces()
      .should().haveNameMatching(".*Interface");

  @ArchTest
  static final ArchRule services_should_not_depend_on_controllers = ArchRuleDefinition.noClasses()
      .that().resideInAPackage("..services..")
      .should().dependOnClassesThat().resideInAPackage("..api.resources..");

  @ArchTest
  static final ArchRule repositories_should_reside_in_a_package = ArchRuleDefinition.classes()
      .that().resideInAPackage("..repositories..")
      .and().areAnnotatedWith(Repository.class)
      .should().haveSimpleNameEndingWith("Repository")
      .allowEmptyShould(false);

  @ArchTest
  static final ArchRule entities_should_reside_in_a_entities_package = ArchRuleDefinition.classes()
      .that().areAnnotatedWith(Entity.class)
      .should().resideInAPackage("..entities..")
      .as("Entities should reside in a package '..entities..'")
      .allowEmptyShould(false);
}
