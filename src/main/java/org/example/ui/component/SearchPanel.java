package org.example.ui.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SearchPanel extends JPanel {

    private final RoundedTextField searchField;

    private final PrimaryButton searchButton;

    public SearchPanel() {

        setLayout(new MigLayout(
                "fillx,insets 10",
                "[grow][120!]"
        ));

        searchField = new RoundedTextField(20);

        searchField.putClientProperty(
                "JTextField.placeholderText",
                "Search..."
        );

        searchButton = new PrimaryButton("Search");

        add(searchField, "growx");

        add(searchButton);

    }

    public String getSearchText(){

        return searchField.getText().trim();

    }

    public JButton getSearchButton(){

        return searchButton;

    }

}