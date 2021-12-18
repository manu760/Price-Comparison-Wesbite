//Import the mysql module and create a connection pool with user details
const mysql = require('mysql');
const response = require("express");
const express = require("express");
const connectionPool = mysql.createPool({
    connectionLimit: 1,
    host: "localhost",
    user: "root",

    password: "manu",
    database: "Coursework3",
    debug: false
});


module.exports.getTotalProductsCount =  (response)=>{
    let sql = "SELECT COUNT(*) FROM products";
    //Execute the query and call the anonymous callback function.
    connectionPool.query(sql, function (err, result) {

        if(err){
        let errMsg = "{Error: " + err + "}";
        console.error(errMsg);
        response.status(400).json(errMsg);

    }else{
        response.send(JSON.stringify(result));
        }
    });
};

module.exports.getProductName = (response) => {
    var sql = "SELECT products.name FROM  products";

    connectionPool.query(sql, function (err, result) {

        if(err){
            let errMsg = "{Error: " + err + "}";
            console.error(errMsg);
            response.status(400).json(errMsg);

        }else{
            response.send(JSON.stringify(result));
        }
    });

};

module.exports.getProductID = (response) => {
    var sql = "SELECT products.id FROM  products";

    connectionPool.query(sql, function (err, result) {

        if (err) {
            let errMsg = "{Error: " + err + "}";
            console.error(errMsg);
            response.status(400).json(errMsg);

        } else {
            response.send(JSON.stringify(result));
        }
    });
}

    module.exports.getProductPrice = (response) => {
        var sql = "SELECT products.price FROM  products";

        connectionPool.query(sql, function (err, result) {

            if (err) {
                let errMsg = "{Error: " + err + "}";
                console.error(errMsg);
                response.status(400).json(errMsg);

            } else {
                response.send(JSON.stringify(result));
            }
        });


}


module.exports.getAllProducts = (response) => {
    var sql = "SELECT products.store FROM  products";

    connectionPool.query(sql, function (err, result) {

        if (err) {
            let errMsg = "{Error: " + err + "}";
            console.error(errMsg);
            response.status(400).json(errMsg);

        } else {
            response.send(JSON.stringify(result));
        }
    });




}