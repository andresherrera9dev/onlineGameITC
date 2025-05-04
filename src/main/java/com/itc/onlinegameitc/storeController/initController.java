/**
 * Simulacion de tienda de un juego RPG en linea
 * @author Andres Herrera Oettinghaus
 */

package com.itc.onlinegameitc.storeController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class initController implements Initializable {
    /** Elementos graficos del programa:

    /** Barra de titulo*/
    @FXML //Boton cerrar el stage
    protected Button closeBtn;
    @FXML //Barra de la ventana, maneja el movimiento de la ventana
    protected StackPane titlebarStackPane;

    /** Filtros para categorias generales*/
    @FXML //Botones para mostrar toda una categoria
    protected Button allAccessoryBtn, allEquipmentBtn, allOtherBtn;
    @FXML //Boton para mostrar todas las categorias
    protected Button allItemsBtn;

    /** Filtros para lo particular*/
    @FXML //TextField para filtrar por nombre del articulo
    protected TextField searchTextField;
    @FXML //Botones busqueda
    protected Button searchBtn;
    protected String searchString = "";

    /** Filtro extras para clase, tier y rareza*/
    @FXML //ComboBox para agregar filtro particulares extras
    protected ComboBox<String> rankComboBox, tierComboBox, classCombobox;
    //String usados para ser usados en las clases DAO
    protected String classFilter ="All", tierFilter="All", rankFilter="All";

    /** Para mostrar u ocultar Filtros*/
    @FXML //Boton para mostrar/esconder filtros
    protected Button accessoryBtn, equipmentBtn, otherBtn;
    @FXML //Contenedores de los filtros
    protected VBox accessoryVBox, equipmentVBox, otherVBox;

    /** Muestra Informacion de la base de datos*/
    @FXML//TableView para mostrar los articulos
    protected TableView<?> marketTableView;
    @FXML //Columnas informacion articulo
    protected TableColumn<?, ?>
            rankColumn, recentColumn, avgColumn,
            cheapestColumn, lowestColumn;
    @FXML //Mostrar las paginas del TableView
    protected Label numberPagesLabel;
    @FXML //Mostrar articulos siguiente y anterior
    protected Button nextPageBtn, backPageBtn;

    /** Mostrar Informacion del player*/
    @FXML //Label para mostrar el saldo del jugador
    protected Label moneyLabel;
    @FXML //Boton que lanza un nuevo Stage para mostrar las transcciones
    protected Button transactionsLabel;

    /** Acceden a las clases DAO*/
    protected String generalCategoryFilter = "All",
                    specificCategoryFilter = "none";
    @FXML //Botones Filtro
    protected Button weaponsBtn, helmetsBtn, chestPiecesBtn,
            pantsBtn, glovesBtn, shouldersBtn,
            necklacesBtn, earingsBtn, ringsBtn;

    /** Modificar base de datos atravez de clases DAO*/
    @FXML //Boton agregar articulo
    protected Button addBtn;
    @FXML //Boton comprar articulo
    protected Button buyBtn;

    /** inicializador de elementos de la GUI*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initVBoxesVisibility();
        setItemsClassCombobox();
        setItemsRankComboBox();
        setItemsTierComboBox();
        titlebar();
    }
    /** Inicializa el estado de escondido de los contenedores de seleccion de filtros
     * TERMINADO*/

    protected void initVBoxesVisibility(){
        equipmentVBox.setVisible(false);
        accessoryVBox.setVisible(false);
        otherVBox.setVisible(false);
        equipmentVBox.setManaged(false);
        accessoryVBox.setManaged(false);
        otherVBox.setManaged(false);
    }

    /** Logica de mostrado y ocultamiento de filtros segun categoria seleccionada
     * Muestra y oculta VBoxes segun si esta esta visible o no
     * TERMINADO*/

    @FXML
    protected void showCategories(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton == equipmentBtn) {
            equipmentVBox.setManaged(!equipmentVBox.isVisible());
            accessoryVBox.setManaged(false);
            otherVBox.setManaged(false);
            equipmentVBox.setVisible(!equipmentVBox.isVisible());
            accessoryVBox.setVisible(false);
            otherVBox.setVisible(false);
        } else if (clickedButton == accessoryBtn) {
            equipmentVBox.setManaged(false);
            accessoryVBox.setManaged(!accessoryVBox.isVisible());
            otherVBox.setManaged(false);
            equipmentVBox.setVisible(false);
            accessoryVBox.setVisible(!accessoryVBox.isVisible());
            otherVBox.setVisible(false);
        } else if (clickedButton == otherBtn) {
            equipmentVBox.setManaged(false);
            accessoryVBox.setManaged(false);
            otherVBox.setManaged(!otherVBox.isVisible());
            equipmentVBox.setVisible(false);
            accessoryVBox.setVisible(false);
            otherVBox.setVisible(!otherVBox.isVisible());
        }else if (clickedButton == allItemsBtn) {
            equipmentVBox.setManaged(false);
            accessoryVBox.setManaged(false);
            otherVBox.setManaged(false);
            equipmentVBox.setVisible(false);
            accessoryVBox.setVisible(false);
            otherVBox.setVisible(false);
        }
    }

    /** Inicializacion de los valores del ComboBox
     * TERMINADO*/

    protected void setItemsClassCombobox(){
        classCombobox.getItems().add("All Classes");
        classCombobox.getItems().add("Warrior");
        classCombobox.getItems().add("Mage");
        classCombobox.getItems().add("Druid");
        classCombobox.getItems().add("Rogue");
    }
    protected void setItemsRankComboBox(){
        rankComboBox.getItems().add("All Rarities");
        rankComboBox.getItems().add("Common");
        rankComboBox.getItems().add("Rare");
        rankComboBox.getItems().add("Arcane");
        rankComboBox.getItems().add("Legendary");
    }
    protected void setItemsTierComboBox(){
        tierComboBox.getItems().add("All Tiers");
        tierComboBox.getItems().add("Tier 1");
        tierComboBox.getItems().add("Tier 2");
        tierComboBox.getItems().add("Tier 3");
    }
    /** Asignacion de valores a los filtros string de botones presionados
     * TERMINADO*/

    @FXML
    void selectedComboBox(ActionEvent event) {
        ComboBox<String> clickedComboBox = (ComboBox<String>) event.getSource();
        String selected = clickedComboBox.getSelectionModel().getSelectedItem();
        if(clickedComboBox == rankComboBox){
            if (selected.equals("Common")) {
                rankFilter="Common";
            } else if (selected.equals("Rare")) {
                rankFilter="Rare";
            } else if (selected.equals("Arcane")) {
                rankFilter="Arcane";
            } else if (selected.equals("Legendary")) {
                rankFilter="Legendary";
            } else if (selected.equals("All Rarities")) {
                rankFilter="All";
            }
        }else if(clickedComboBox == tierComboBox){
            if (selected.equals("Tier 1")) {
                tierFilter="Tier 1";
            } else if (selected.equals("Tier 2")) {
                tierFilter="Tier 2";
            } else if (selected.equals("Tier 3")) {
                tierFilter="Tier 3";
            } else if (selected.equals("All Tiers")) {
                tierFilter="All";
            }
        }else if(clickedComboBox == classCombobox){
            if (selected.equals("Warrior")) {
                classFilter ="Warrior";
            } else if (selected.equals("Mage")) {
                classFilter ="Mage";
            } else if (selected.equals("Druid")) {
                classFilter ="Druid";
            } else if (selected.equals("Rogue")) {
                classFilter ="Rogue";
            } else if (selected.equals("All Classes")) {
                classFilter ="All";
            }
        }
    }

    /** Logica de barra de titulo de la ventana
     * TERMINADO */

    private double xOffset = 0;
    private double yOffset = 0;
    private void titlebar(){
        titlebarStackPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titlebarStackPane.setOnMouseDragged(event -> {
            Stage stage = (Stage) titlebarStackPane.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}