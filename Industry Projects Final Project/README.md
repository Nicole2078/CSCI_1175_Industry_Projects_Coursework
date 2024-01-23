"Industry Projects Final Projet". The final project I designed is a receipt expense calculater program. The final porject has a user interface requiring user imput and button use.
```
        menuItemClose.setOnAction(e -> System.exit(0));

        //Handle button action "Delete last Receipt"
        btDelete.setOnAction(e -> {
            //checking if there are no receipts to delete
            if (listR.isEmpty()){
                taDisplay.setText("All Receipts Deleted.\nPlease Enter Receipt values");
            }
            else {
                listR.remove(listR.size() - 1);
                taDisplay.setText("Last entered Receipt Deleted");
                tfHousing.requestFocus(); //Requesting focus back to the housing text area for user ease
            }
        });

        //Handle button action "Enter Receipt"
        btEnterReceipt.setOnAction(e -> {
            Receipt receipt = new Receipt(Double.parseDouble(tfHousing.getText().trim()),
                    Double.parseDouble(tfUtilities.getText().trim()),
                    Double.parseDouble(tfTransportGas.getText().trim()),
                    Double.parseDouble(tfGroceries.getText().trim()),
                    Double.parseDouble(tfInternetCellphone.getText().trim()),
                    Double.parseDouble(tfEntertainment.getText().trim()),
                    Double.parseDouble(tfBirthHoliday.getText().trim()),
                    Double.parseDouble(tfHealthCare.getText().trim())); //Creating a new receipt object
            listR.add(receipt);
            //Setting Text Fields back to a default
            tfHousing.setText("0.00");
            tfUtilities.setText("0.00");
            tfTransportGas.setText("0.00");
            tfGroceries.setText("0.00");
            tfInternetCellphone.setText("0.00");
            tfEntertainment.setText("0.00");
            tfBirthHoliday.setText("0.00");
            tfHealthCare.setText("0.00");
            tfHousing.requestFocus(); //Requesting focus back to the housing text area for user ease
        });
```
