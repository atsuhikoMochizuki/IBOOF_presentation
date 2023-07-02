package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
@Cacheable
public class Produit {
    // Attributs propres aux produits
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_produit;
    @Column
    private String nom_produit;
    @Column
    private double energiePour100g;
    @Column
    private double graisse100g;
    @Column
    private double sucres100g;
    @Column
    private double fibres100g;
    @Column
    private double proteines100g;
    @Column
    private double sel100g;
    @Column
    private double vita100g;
    @Column
    private double vitd100g;
    @Column
    private double vite100g;
    @Column
    private double vitk100g;
    @Column
    private double vitc100g;
    @Column
    private double vitb1100g;
    @Column
    private double vitb2100g;
    @Column
    private double vitpp100g;
    @Column
    private double vitb6100g;
    @Column
    private double vitb9100g;
    @Column
    private double vitb12100g;
    @Column
    private double calcium100g;
    @Column
    private double magnesium100g;
    @Column
    private double iron100g;
    @Column
    private double fer100g;
    @Column
    private double betacarotene100g;
    @Column
    private boolean presencehuilepalme;


    //Attributs relations 1-n
    @ManyToOne
    @JoinColumn(name = "CAT_ID")
    private Categorie categorie;
    @ManyToOne
    @JoinColumn(name = "NUTRISCORE_ID")
    private Nutriscore nutriscore;

    //Attributs relations n-n
    @ManyToMany
    @JoinTable(name = "JOIN_TABLE_MARQUE_PRODUIT",
            joinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "id_produit"),
            inverseJoinColumns = @JoinColumn(name = "ID_MARQUE", referencedColumnName = "id_marque")
    )
    private Set<Marque> marques;

    @ManyToMany
    @JoinTable(name = "JOIN_TABLE_ALLERGENE_PRODUIT",
            joinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "id_produit"),
            inverseJoinColumns = @JoinColumn(name = "ID_ALLERGENE", referencedColumnName = "id_allergene")
    )
    private Set<Allergene> allergenes;

    @ManyToMany
    @JoinTable(name = "JOIN_TABLE_ADDITIF_PRODUIT",
            joinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "id_produit"),
            inverseJoinColumns = @JoinColumn(name = "ID_ADDITIF", referencedColumnName = "id_additif")
    )
    private Set<Additif> additifs;

    @ManyToMany
    @JoinTable(name = "JOIN_TABLE_INGREDIENT_PRODUIT",
            joinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "id_produit"),
            inverseJoinColumns = @JoinColumn(name = "ID_INGREDIENT", referencedColumnName = "id_ingredient")
    )
    private Set<Ingredient> ingredients;

    //Constructeurs
    public Produit() {
    }

    public Produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public Integer getId_produit() {
        return id_produit;
    }

    public void setId_produit(Integer id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public double getEnergiePour100g() {
        return energiePour100g;
    }

    public void setEnergiePour100g(double energiePour100g) {
        this.energiePour100g = energiePour100g;
    }

    public double getGraisse100g() {
        return graisse100g;
    }

    public void setGraisse100g(double graisse100g) {
        this.graisse100g = graisse100g;
    }

    public double getSucres100g() {
        return sucres100g;
    }

    public void setSucres100g(double sucres100g) {
        this.sucres100g = sucres100g;
    }

    public double getFibres100g() {
        return fibres100g;
    }

    public void setFibres100g(double fibres100g) {
        this.fibres100g = fibres100g;
    }

    public double getProteines100g() {
        return proteines100g;
    }

    public void setProteines100g(double proteines100g) {
        this.proteines100g = proteines100g;
    }

    public double getSel100g() {
        return sel100g;
    }

    public void setSel100g(double sel100g) {
        this.sel100g = sel100g;
    }

    public double getVita100g() {
        return vita100g;
    }

    public void setVita100g(double vita100g) {
        this.vita100g = vita100g;
    }

    public double getVitd100g() {
        return vitd100g;
    }

    public void setVitd100g(double vitd100g) {
        this.vitd100g = vitd100g;
    }

    public double getVite100g() {
        return vite100g;
    }

    public void setVite100g(double vite100g) {
        this.vite100g = vite100g;
    }

    public double getVitk100g() {
        return vitk100g;
    }

    public void setVitk100g(double vitk100g) {
        this.vitk100g = vitk100g;
    }

    public double getVitc100g() {
        return vitc100g;
    }

    public void setVitc100g(double vitc100g) {
        this.vitc100g = vitc100g;
    }

    public double getVitb1100g() {
        return vitb1100g;
    }

    public void setVitb1100g(double vitb1100g) {
        this.vitb1100g = vitb1100g;
    }

    public double getVitb2100g() {
        return vitb2100g;
    }

    public void setVitb2100g(double vitb2100g) {
        this.vitb2100g = vitb2100g;
    }

    public double getVitpp100g() {
        return vitpp100g;
    }

    public void setVitpp100g(double vitpp100g) {
        this.vitpp100g = vitpp100g;
    }

    public double getVitb6100g() {
        return vitb6100g;
    }

    public void setVitb6100g(double vitb6100g) {
        this.vitb6100g = vitb6100g;
    }

    public double getVitb9100g() {
        return vitb9100g;
    }

    public void setVitb9100g(double vitb9100g) {
        this.vitb9100g = vitb9100g;
    }

    public double getVitb12100g() {
        return vitb12100g;
    }

    public void setVitb12100g(double vitb12100g) {
        this.vitb12100g = vitb12100g;
    }

    public double getCalcium100g() {
        return calcium100g;
    }

    public void setCalcium100g(double calcium100g) {
        this.calcium100g = calcium100g;
    }

    public double getMagnesium100g() {
        return magnesium100g;
    }

    public void setMagnesium100g(double magnesium100g) {
        this.magnesium100g = magnesium100g;
    }

    public double getIron100g() {
        return iron100g;
    }

    public void setIron100g(double iron100g) {
        this.iron100g = iron100g;
    }

    public double getFer100g() {
        return fer100g;
    }

    public void setFer100g(double fer100g) {
        this.fer100g = fer100g;
    }

    public double getBetacarotene100g() {
        return betacarotene100g;
    }

    public void setBetacarotene100g(double betacarotene100g) {
        this.betacarotene100g = betacarotene100g;
    }

    public boolean isPresencehuilepalme() {
        return presencehuilepalme;
    }

    public void setPresencehuilepalme(boolean presencehuilepalme) {
        this.presencehuilepalme = presencehuilepalme;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Nutriscore getNutriscore() {
        return nutriscore;
    }

    public void setNutriscore(Nutriscore nutriscore) {
        this.nutriscore = nutriscore;
    }

    public Set<Marque> getMarques() {
        return marques;
    }

    public void setMarques(Set<Marque> marques) {
        this.marques = marques;
    }

    public Set<Allergene> getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(Set<Allergene> allergenes) {
        this.allergenes = allergenes;
    }

    public Set<Additif> getAdditifs() {
        return additifs;
    }

    public void setAdditifs(Set<Additif> additifs) {
        this.additifs = additifs;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", nom_produit='" + nom_produit + '\'' +
                ", energiePour100g=" + energiePour100g +
                ", energiePour100g=" + energiePour100g +
                ", graisse100g=" + graisse100g +
                ", sucres100g=" + sucres100g +
                ", fibres100g=" + fibres100g +
                ", proteines100g=" + proteines100g +
                ", sel100g=" + sel100g +
                ", vita100g=" + vita100g +
                ", vitd100g=" + vitd100g +
                ", vite100g=" + vite100g +
                ", vitk100g=" + vitk100g +
                ", vitc100g=" + vitc100g +
                ", vitb1100g=" + vitb1100g +
                ", vitb2100g=" + vitb2100g +
                ", vitpp100g=" + vitpp100g +
                ", vitb6100g=" + vitb6100g +
                ", vitb9100g=" + vitb9100g +
                ", vitb12100g=" + vitb12100g +
                ", calcium100g=" + calcium100g +
                ", magnesium100g=" + magnesium100g +
                ", iron100g=" + iron100g +
                ", fer100g=" + fer100g +
                ", betacarotene100g=" + betacarotene100g +
                ", presencehuilepalme=" + presencehuilepalme +
                ", categorie=" + categorie +
                ", nutriscore=" + nutriscore +
                ", marques=" + marques +
                ", allergenes=" + allergenes +
                ", additifs=" + additifs +
                ", ingredients=" + ingredients +
                '}';
    }
}
