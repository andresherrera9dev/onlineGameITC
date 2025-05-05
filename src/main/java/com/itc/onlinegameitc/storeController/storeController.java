package com.itc.onlinegameitc.storeController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class storeController extends initController {
    /** Metodo para acceder a la clase DAO */
    void searchDatabase(){
        /**Elementos String que se pueden utilizan para el select en la base de datos:
         * searchString
         * classFilter
         * tierFilter
         * levelFilter
         * categoryFilter
         * */

        System.out.printf("search String: %s\n",searchString);
        System.out.printf("classFilter: %s\n",classFilter);
        System.out.printf("tierFilter: %s\n", levelFilter);
        System.out.printf("rankFilter: %s\n", rarityFilter);
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
        else if (clickedButton == allConsumableBtn)generalCategoryFilter = "All Consumable";
        else if (clickedButton == weaponsBtn)generalCategoryFilter = "Weapons";
        else if (clickedButton == bootsBtn)generalCategoryFilter = "Boots";
        else if (clickedButton == chestPiecesBtn)generalCategoryFilter = "Chest Pieces";
        else if (clickedButton == shieldBtn)generalCategoryFilter = "Shield";
        else if (clickedButton == glovesBtn)generalCategoryFilter = "Gloves";
        else if (clickedButton == equipmentOthersBtn)generalCategoryFilter = "Other Equipment";
        else if (clickedButton == necklacesBtn)generalCategoryFilter = "Necklaces";
        else if (clickedButton == earingsBtn)generalCategoryFilter = "Earings";
        else if (clickedButton == maskBtn)generalCategoryFilter = "Masks";
        else if (clickedButton == accessoryOtherBtn)generalCategoryFilter = "Other accessory";

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
        levelFilter ="All";
        rarityFilter ="All";
        searchString = "";

        classCombobox.getSelectionModel().selectFirst();
        rarityComboBox.getSelectionModel().selectFirst();
        levelComboBox.getSelectionModel().selectFirst();

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
}
