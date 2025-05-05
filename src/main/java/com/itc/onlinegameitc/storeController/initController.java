/**
 * Simulacion de tienda de un juego RPG en linea
 * @author Andres Herrera Oettinghaus
 */

package com.itc.onlinegameitc.storeController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    protected Button allAccessoryBtn, allEquipmentBtn, allOtherBtn,
            allConsumableBtn;
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
    protected ComboBox<String> classCombobox, levelComboBox, rarityComboBox;
    //String usados para ser usados en las clases DAO
    protected String classFilter ="All", levelFilter ="All", rarityFilter ="All";

    /** Para mostrar u ocultar Filtros*/
    @FXML //Boton para mostrar/esconder filtros
    protected Button accessoryBtn, equipmentBtn, consumibleBtn, otherBtn;
    @FXML //Contenedores de los filtros
    protected VBox accessoryVBox, equipmentVBox, otherVBox, consumableVbox;

    /** Muestra Informacion de la base de datos*/
    @FXML
    TableColumn<Item, Image> iconColumn;
    @FXML//TableView para mostrar los articulos
    protected TableView<Item> marketTableView;
    @FXML
    protected TableColumn<Item, String> nameColumn;
    @FXML
    protected TableColumn<Item, Integer> levelColumn;
    @FXML
    protected TableColumn<Item, String> categoryColumn;
    @FXML
    protected TableColumn<Item, Double> priceColumn;
    @FXML
    protected TableColumn<Item, Double> avgPriceColumn;
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
    protected Button weaponsBtn, bootsBtn, chestPiecesBtn,
            shieldBtn, glovesBtn, equipmentOthersBtn,
            necklacesBtn, earingsBtn, accessoryOtherBtn,
            maskBtn;

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

        // Configurar cómo cada columna obtiene sus datos
        iconColumn.setCellValueFactory(new PropertyValueFactory<>("icon"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        avgPriceColumn.setCellValueFactory(new PropertyValueFactory<>("avgPrice"));

        // Configurar cómo mostrar la imagen
        iconColumn.setCellFactory(column -> new TableCell<Item, Image>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);

                if (empty || image == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    imageView.setFitWidth(30);  // Ajustar tamaño
                    imageView.setFitHeight(30);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });

        // Crear datos de ejemplo
        ObservableList<Item> items = FXCollections.observableArrayList(
                new Item(loadImage("/com/itc/onlinegameitc/icons/Equipment/Weapons/OoT_Master_Sword_Icon.png"),"Espada", 5, "Armas", 120.50, 115.75),
                new Item(loadImage("/com/itc/onlinegameitc/icons/Consumable/OoT_Red_Potion_Icon.png"),"Poción", 1, "Consumibles", 25.0, 22.5),
                new Item(loadImage("/com/itc/onlinegameitc/icons/Equipment/Shield/OoT_Hylian_Shield_Icon.png"),"Escudo", 3, "Armaduras", 85.0, 90.25),
                new Item(loadImage("/com/itc/onlinegameitc/icons/Equipment/Weapons/OoT_Master_Sword_Icon.png"),"Espada", 5, "Armas", 120.50, 115.75),
                new Item(loadImage("/com/itc/onlinegameitc/icons/Consumable/OoT_Red_Potion_Icon.png"),"Poción", 1, "Consumibles", 25.0, 22.5),
                new Item(loadImage("/com/itc/onlinegameitc/icons/Equipment/Shield/OoT_Hylian_Shield_Icon.png"),"Escudo", 3, "Armaduras", 85.0, 90.25)
        );

        // Asignar los datos al TableView
        marketTableView.setItems(items);
    }
    /** Inicializa el estado de escondido de los contenedores de seleccion de filtros
     * TERMINADO*/

    protected void initVBoxesVisibility(){
        equipmentVBox.setVisible(false);
        accessoryVBox.setVisible(false);
        otherVBox.setVisible(false);
        equipmentVBox.setManaged(false);
        accessoryVBox.setManaged(false);
        consumableVbox.setVisible(false);
        consumableVbox.setManaged(false);
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
            consumableVbox.setVisible(false);
            consumableVbox.setManaged(false);
        } else if (clickedButton == accessoryBtn) {
            equipmentVBox.setManaged(false);
            accessoryVBox.setManaged(!accessoryVBox.isVisible());
            otherVBox.setManaged(false);
            equipmentVBox.setVisible(false);
            accessoryVBox.setVisible(!accessoryVBox.isVisible());
            otherVBox.setVisible(false);
            consumableVbox.setVisible(false);
            consumableVbox.setManaged(false);
        } else if (clickedButton == otherBtn) {
            equipmentVBox.setManaged(false);
            accessoryVBox.setManaged(false);
            otherVBox.setManaged(!otherVBox.isVisible());
            equipmentVBox.setVisible(false);
            accessoryVBox.setVisible(false);
            otherVBox.setVisible(!otherVBox.isVisible());
            consumableVbox.setVisible(false);
            consumableVbox.setManaged(false);
        }else if (clickedButton == consumibleBtn) {
            equipmentVBox.setManaged(false);
            accessoryVBox.setManaged(false);
            otherVBox.setManaged(false);
            equipmentVBox.setVisible(false);
            accessoryVBox.setVisible(false);
            otherVBox.setVisible(false);
            consumableVbox.setManaged(!consumableVbox.isVisible());
            consumableVbox.setVisible(!consumableVbox.isVisible());
        }else if (clickedButton == allItemsBtn) {
            equipmentVBox.setManaged(false);
            accessoryVBox.setManaged(false);
            otherVBox.setManaged(false);
            equipmentVBox.setVisible(false);
            accessoryVBox.setVisible(false);
            otherVBox.setVisible(false);
            consumableVbox.setVisible(false);
            consumableVbox.setManaged(false);
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
        rarityComboBox.getItems().add("All Rarities");
        rarityComboBox.getItems().add("Common");
        rarityComboBox.getItems().add("Rare");
        rarityComboBox.getItems().add("Arcane");
        rarityComboBox.getItems().add("Legendary");
    }
    protected void setItemsTierComboBox(){
        levelComboBox.getItems().add("All Levels");
        levelComboBox.getItems().add("Levels 0-20");
        levelComboBox.getItems().add("Levels 21-50");
        levelComboBox.getItems().add("Levels 51-100");
    }
    /** Asignacion de valores a los filtros string de botones presionados
     * TERMINADO*/

    @FXML
    void selectedComboBox(ActionEvent event) {
        ComboBox<String> clickedComboBox = (ComboBox<String>) event.getSource();
        String selected = clickedComboBox.getSelectionModel().getSelectedItem();
        if(clickedComboBox == rarityComboBox){
            rarityFilter =selected;
        }else if(clickedComboBox == levelComboBox){
            levelFilter =selected;
        }else if(clickedComboBox == classCombobox){
            classFilter =selected;
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

    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            return null;
        }
    }
}
