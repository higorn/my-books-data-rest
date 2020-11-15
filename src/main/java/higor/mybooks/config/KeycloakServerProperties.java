package higor.mybooks.config;

//@ConfigurationProperties(prefix = "keycloak.server")
public class KeycloakServerProperties {
  String contextPath = "/auth";
  String realmImportFile = "baeldung-realm.json";
  AdminUser adminUser = new AdminUser();

  // getters and setters

  public static class AdminUser {
    String username = "admin";
    String password = "admin";

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
}
