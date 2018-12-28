package sswag;

import java.util.ArrayList;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sswag.model.Pattern;
import sswag.model.PatternStatus;
import sswag.model.Role;
import sswag.model.User;
import sswag.service.PatternService;
import sswag.service.UserService;

@SpringBootApplication
public class SSWAGBackEndApp implements CommandLineRunner {

  @Autowired
  UserService userService;

  @Autowired
  PatternService patternService;

  public static void main(String[] args) {
    SpringApplication.run(SSWAGBackEndApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... params) throws Exception {
    User client = new User();
    client.setUsername("client");
    client.setPassword("client");
    client.setEmail("client@email.com");
    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

    userService.signup(client);

    User approver = new User();
    approver.setUsername("approver");
    approver.setPassword("approver");
    approver.setEmail("approver@email.com");
    approver.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT, Role.ROLE_APPROVER)));

    userService.signup(approver);


    User admin = new User();
    admin.setUsername("admin");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT, Role.ROLE_APPROVER, Role.ROLE_ADMIN)));

    userService.signup(admin);

    Pattern pat1 = new Pattern();
    pat1.setName("Pattern 1");
    pat1.setAuthor(client);
    pat1.setLanguages("Java and sother stuffzies.");
    pat1.setStatus(PatternStatus.STATUS_APPROVED);
    patternService.savePattern(pat1);

    Pattern pat2 = new Pattern();
    pat2.setName("Pattern 2");
    pat2.setAuthor(approver);
    pat2.setLanguages("C#.");
    pat2.setStatus(PatternStatus.STATUS_REJECTED);
    patternService.savePattern(pat2);

    Pattern pat3 = new Pattern();
    pat3.setName("Pattern 3");
    pat3.setAuthor(admin);
    pat3.setLanguages("Java and sother stuffzies.");
    pat3.setStatus(PatternStatus.STATUS_PENDING);
    patternService.savePattern(pat3);

    Pattern pat4 = new Pattern();
    pat4.setName("Pattern 4");
    pat4.setAuthor(client);
    pat4.setLanguages("Java and sother stuffzies.");
    pat4.setStatus(PatternStatus.STATUS_APPROVED);
    patternService.savePattern(pat4);

    Pattern pat5 = new Pattern();
    pat5.setName("Pattern 5");
    pat5.setAuthor(approver);
    pat5.setLanguages("C#.");
    pat5.setStatus(PatternStatus.STATUS_REJECTED);
    patternService.savePattern(pat5);

    Pattern pat6 = new Pattern();
    pat6.setName("Pattern 6");
    pat6.setAuthor(admin);
    pat6.setLanguages("Java and sother stuffzies.");
    pat6.setStatus(PatternStatus.STATUS_PENDING);
    patternService.savePattern(pat6);

    Pattern pat7 = new Pattern();
    pat7.setName("Pattern 7");
    pat7.setAuthor(approver);
    pat7.setLanguages("C#.");
    pat7.setStatus(PatternStatus.STATUS_APPROVED);
    patternService.savePattern(pat7);

    Pattern pat8 = new Pattern();
    pat8.setName("lololttern 8");
    pat8.setAuthor(admin);
    pat8.setLanguages("Java and sother stuffzies.");
    pat8.setStatus(PatternStatus.STATUS_APPROVED);
    patternService.savePattern(pat8);

    Pattern pat9 = new Pattern();
    pat9.setName("Patteffgrn 9");
    pat9.setAuthor(approver);
    pat9.setLanguages("C#.");
    pat9.setStatus(PatternStatus.STATUS_APPROVED);
    patternService.savePattern(pat9);

    Pattern pat10 = new Pattern();
    pat10.setName("Patternuu 10");
    pat10.setAuthor(admin);
    pat10.setLanguages("Java and sother stuffzies.");
    pat10.setStatus(PatternStatus.STATUS_APPROVED);
    patternService.savePattern(pat10);


  }

}
