package org.example.poolista6javafx;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;

public class AlunoBoundary extends Application {
    private TextField txtId = new TextField();
    private TextField txtRa = new TextField();
    private TextField txtNome = new TextField();
    private DatePicker txtNascimento = new DatePicker();

    Button btnAdicionar = new Button("Adicionar");
    Button btnPesquisar = new Button("Pesquisar");

    private TableView<Aluno> table = new TableView<>();
    private AlunoControl control = null;

    @Override
    public void start(Stage stage) {
        control = new AlunoControl();

        BorderPane panePrincipal = new BorderPane();
        Scene scene = new Scene(panePrincipal, 500, 300);
        GridPane grid = new GridPane();

        panePrincipal.setTop(grid);
        panePrincipal.setCenter(table);

        grid.add(new Label("ID"), 0, 0);
        grid.add(txtId, 1, 0);
        grid.add(new Label("RA"), 0, 1);
        grid.add(txtRa, 1, 1);
        grid.add(new Label("Nome"), 0, 2);
        grid.add(txtNome, 1, 2);
        grid.add(new Label("Nascimento"), 0, 3);
        grid.add(txtNascimento, 1, 3);
        grid.add(btnAdicionar, 0, 4);
        grid.add(btnPesquisar, 1, 4);

        //Acoes dos botoes
        btnAdicionar.setOnAction(e -> control.adicionar());
        btnPesquisar.setOnAction(e -> control.pesquisar());


        generateBindings();
        generateTable();

        stage.setScene(scene);
        stage.setTitle("Gestao de Alunos");
        stage.show();
    }

    private void generateBindings() {
        StringConverter<Number> conversor = new NumberStringConverter();
        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), conversor);
        Bindings.bindBidirectional(txtRa.textProperty(), control.raProperty());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtNascimento.valueProperty(), control.nascimentoProperty());
    }

    private void generateTable() {
        TableColumn<Aluno, Long> col1 = new TableColumn<>("ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Aluno, String> col2 = new TableColumn<>("RA");
        col2.setCellValueFactory(new PropertyValueFactory<>("ra"));
        TableColumn<Aluno, String> col3 = new TableColumn<>("NOME");
        col3.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<Aluno, LocalDate> col4 = new TableColumn<>("NASCIMENTO");
        col4.setCellValueFactory(new PropertyValueFactory<>("nascimento"));

        table
                .getSelectionModel()
                .selectedItemProperty().addListener(
                        (obs, oldSelection, newSelection) -> {
                            if (newSelection != null) {
                                System.out.println("Selecionado: " + newSelection);
                                control.setEntity(newSelection);
                            }
                        });

        table.setItems(control.getTable());

        table.getColumns().addAll(col1, col2, col3, col4);
    }

    public static void main(String[] args) {
        launch();
    }
}
