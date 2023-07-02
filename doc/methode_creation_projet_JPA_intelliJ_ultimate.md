## Méthode de création de projet JPA-(Persistance) sous IntelliJ (Ultimate)

- Créer un projet Maven
- File/Project Structure/Facets
	- Ajouter un Facet
	- Choisir un descriptor persistence.xml et le mettre dans le projet
	- Vérifier que persistence.xml va se générer dans dans le dossier /src/main/resources/META-INF/
	- Choisir le JPA provider Hibernate
	- Prendre la dernière version
	- Si il manque les libraires javaa, faire fix ou OK
	- OK
- Configurer le projet Maven dans le pom.xml
	- Ajouter hibernate dans les dépendances
	  <dependency>
	  <groupId>org.hibernate</groupId>
	  <artifactId>hibernate-core</artifactId>
	  <version>6.2.4.Final</version>
	  </dependency>
	- Ajouter le driver de la base de données
	  <dependency>
	  <groupId>mysql</groupId>
	  <artifactId>mysql-connector-java</artifactId>
	  <version>8.0.33</version>
	  </dependency>
- Configurer le fichier de configuration persistence.xml
	- la base de données va être définie par un persistence-unit
	  -donner un nom
	  <persistence-unit name="DEMO-JPA">
		- Définir le fournisseur de services JPA (ici Hibernate)
		  <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
		- Définir les propriétés minimale pour pouvoir se connecter à une base de données
		  <properties>
		  <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/bibliotheque"/>
		  <property name="jakarta.persistence.jdbc.user" value="root"/>
		  <property name="jakarta.persistence.jdbc.password" value="root"/>

		        <property name="hibernate.show_sql" value="true"/>
		        <property name="hibernate.format_sql" value="true"/>
		  </properties>

		- Vérifier la connection à la base de données
			- Créer l'objet qui va représenter la connection
			  EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEMO-JPA");
			- Créer le gestionnaire d'entités
			- Ce qui donne :
			  public static void main(String[] args) {
			  try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEMO-JPA");
			  EntityManager em = emf.createEntityManager();)
			  {
			  System.out.println(em);
			  }
			  }			
