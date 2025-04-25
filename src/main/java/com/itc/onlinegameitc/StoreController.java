/**
 * Simulacion de tienda de un juego RPG en linea
 * @author Andres Herrera Oettinghaus
 */

package com.itc.onlinegameitc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StoreController implements Initializable {
    /** Elementos graficos del programa:

    /** Barra de titulo*/
    @FXML //Boton cerrar el stage
    private Button closeBtn;
    @FXML //Barra de la ventana, maneja el movimiento de la ventana
    private StackPane titlebarStackPane;

    /** Filtros para categorias generales*/
    @FXML //Botones para mostrar toda una categoria
    private Button allAccessoryBtn, allEquipmentBtn, allOtherBtn;
    @FXML //Boton para mostrar todas las categorias
    private Button allItemsBtn;

    /** Filtros para lo particular*/
    @FXML //TextField para filtrar por nombre del articulo
    private TextField searchTextField;
    @FXML //Botones busqueda
    private Button searchBtn;
    private String searchString = "";

    /** Filtro extras para clase, tier y rareza*/
    @FXML //ComboBox para agregar filtro particulares extras
    private ComboBox<String> rankComboBox, tierComboBox, classCombobox;
    //String usados para ser usados en las clases DAO
    private String classFilter ="All", tierFilter="All", rankFilter="All";

    /** Para mostrar u ocultar Filtros*/
    @FXML //Boton para mostrar/esconder filtros
    private Button accessoryBtn, equipmentBtn, otherBtn;
    @FXML //Contenedores de los filtros
    private VBox accessoryVBox, equipmentVBox, otherVBox;

    /** Muestra Informacion de la base de datos*/
    @FXML//TableView para mostrar los articulos
    private TableView<?> marketTableView;
    @FXML //Columnas informacion articulo
    private TableColumn<?, ?>
            rankColumn, recentColumn, avgColumn,
            cheapestColumn, lowestColumn;
    @FXML //Mostrar las paginas del TableView
    private Label numberPagesLabel;
    @FXML //Mostrar articulos siguiente y anterior
    private Button nextPageBtn, backPageBtn;

    /** Mostrar Informacion del player*/
    @FXML //Label para mostrar el saldo del jugador
    private Label moneyLabel;
    @FXML //Boton que lanza un nuevo Stage para mostrar las transcciones
    private Button transactionsLabel;

    /** Acceden a las clases DAO*/
    private String generalCategoryFilter = "All",
                    specificCategoryFilter = "none";
    @FXML //Botones Filtro
    private Button weaponsBtn, helmetsBtn, chestPiecesBtn,
            pantsBtn, glovesBtn, shouldersBtn,
            necklacesBtn, earingsBtn, ringsBtn;

    /** Modificar base de datos atravez de clases DAO*/
    @FXML //Boton agregar articulo
    private Button addBtn;
    @FXML //Boton comprar articulo
    private Button buyBtn;

    /** inicializador de elementos de la GUI*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initVBoxesVisibility();
        setItemsClassCombobox();
        setItemsRankComboBox();
        setItemsTierComboBox();
        titlebar();
    }

    /** Metodo para acceder a la clase DAO */
    void searchDatabase(){
        /**Elementos String que se pueden utilizan para el select en la base de datos:
         * searchString
         * classFilter
         * tierFilter
         * rankFilter
         * categoryFilter
         * */

        System.out.printf("search String: %s\n",searchString);
        System.out.printf("classFilter: %s\n",classFilter);
        System.out.printf("tierFilter: %s\n",tierFilter);
        System.out.printf("rankFilter: %s\n",rankFilter);
        System.out.printf("categoryFilter: %s\n\n", generalCategoryFilter);

        //Realizar diferente tipo de seleccion dependiendo si esta vacio o no el campo de busqueda
        boolean searchFlag = searchString.isBlank();
        if(searchFlag)
        {
            //Llamar a la clase DAO
            //Utilizar los valores de los filtros para filtrar la seleccion de elementos
        }
        else
        {
            //Llamar a la clase DAO
            //Utilizar los valores de los filtros para filtrar la seleccion de elementos
        }
    }

    /** Eventos de click y seleccion de la GUI */
    @FXML
    void addIteamNewStage(ActionEvent event) {

    }

    @FXML
    void buyItem(ActionEvent event) {

    }

    @FXML
    void readSearchField(ActionEvent event) {
        if(!searchTextField.getText().isEmpty())
        {
            searchString = searchTextField.getText();
            searchDatabase();
        }
    }

    @FXML
    void filterSearch(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        //Asigna un valor al string para utilizar en la seleccion de datos en la base de datos;
        if (clickedButton == allItemsBtn) generalCategoryFilter = "All";
        else if (clickedButton == allEquipmentBtn)generalCategoryFilter = "All Equipment";
        else if (clickedButton == allAccessoryBtn)generalCategoryFilter = "All Accessory";
        else if (clickedButton == allOtherBtn)generalCategoryFilter = "All Other";
        else if (clickedButton == weaponsBtn)generalCategoryFilter = "Weapons";
        else if (clickedButton == helmetsBtn)generalCategoryFilter = "Helmets";
        else if (clickedButton == chestPiecesBtn)generalCategoryFilter = "Chest Pieces";
        else if (clickedButton == pantsBtn)generalCategoryFilter = "Pants";
        else if (clickedButton == glovesBtn)generalCategoryFilter = "Gloves";
        else if (clickedButton == shouldersBtn)generalCategoryFilter = "Shoulders";
        else if (clickedButton == necklacesBtn)generalCategoryFilter = "Necklaces";
        else if (clickedButton == earingsBtn)generalCategoryFilter = "Earings";
        else if (clickedButton == ringsBtn)generalCategoryFilter = "Rings";

        //Llamar a la clase DAO y mostrar elementos
        searchDatabase();
    }

    /** Muestra todos los elementos
     * Opcional: asignar todos los filtros a All para mostrar todos los elementos
     *           borrar textfield, seleccionar elementos all en combobox
     * SIN IMPLEMENTAR LOGICA DE LA CLASE DAO*/

    @FXML
    void showAllItems(ActionEvent event) {
        generalCategoryFilter = "All";
        classFilter ="All";
        tierFilter="All";
        rankFilter="All";
        searchString = "";

        classCombobox.getSelectionModel().selectFirst();
        rankComboBox.getSelectionModel().selectFirst();
        tierComboBox.getSelectionModel().selectFirst();

        searchTextField.setText("");

        searchDatabase();
        showCategories(event);
        //Llamar a la clase DAO y mostrar elementos
    }

    /** Inicializa el estado de escondido de los contenedores de seleccion de filtros
     * SIN IMPLEMENTAR LOGICA DE LA CLASE DAO*/

    /** OPCIONAL:
     * Mostrar todas las operaciones realizadas por el usuario
     * es necesario agregar una nueva tupla a una la tabla transacciones
     * por cada transaccion realizada
     *  Trabajar en esto despues de terminar lo basico del programa
     * */

    @FXML
    void showTransactions(ActionEvent event) {

    }

    /** OPCIONAL:
     *  Muestran la busqueda de la base de datos en partes
     *  Cargar n elementos a los tableview de una busqueda dada
     *  Trabajar en esto despues de terminar lo basico del programa
     * SIN IMPLEMENTAR LOGICA DE LA CLASE DAO*/
    @FXML
    void loadBackitems(ActionEvent event) {

    }
    @FXML
    void loadNextItems(ActionEvent event) {

    }
    /** Logica de mostrado y ocultamiento de filtros segun categoria seleccionada
     * Muestra y oculta VBoxes segun si esta esta visible o no
     * TERMINADO*/

    @FXML
    void showCategories(ActionEvent event) {
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

    /** Inicializa el estado de escondido de los contenedores de seleccion de filtros
     * TERMINADO*/

    private void initVBoxesVisibility(){
        equipmentVBox.setVisible(false);
        accessoryVBox.setVisible(false);
        otherVBox.setVisible(false);
        equipmentVBox.setManaged(false);
        accessoryVBox.setManaged(false);
        otherVBox.setManaged(false);
    }

    /** Inicializacion de los valores del ComboBox
     * TERMINADO*/

    private void setItemsClassCombobox(){
        classCombobox.getItems().add("All Classes");
        classCombobox.getItems().add("Warrior");
        classCombobox.getItems().add("Mage");
        classCombobox.getItems().add("Druid");
        classCombobox.getItems().add("Rogue");
    }
    private void setItemsRankComboBox(){
        rankComboBox.getItems().add("All Rarities");
        rankComboBox.getItems().add("Common");
        rankComboBox.getItems().add("Rare");
        rankComboBox.getItems().add("Arcane");
        rankComboBox.getItems().add("Legendary");
    }
    private void setItemsTierComboBox(){
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

    /** Logica de barra de ventana
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