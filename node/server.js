//Import the express,url and body-parser modules
const express = require('express');
const url = require("url");
const bodyParser = require("body-parser");
//Import the mysql module
const mysql = require('mysql');
//import the fs module
const fs = require('fs');

//Status codes defined in external file
require('./http_status');
let db = require('./database');


//The express module is a function. When it is executed it returns an app object
const app = express();
app.use(bodyParser.json());

//Set up express to serve static files from the directory called 'public'
app.use(express.static('public')); //use public folder to load files


//Start the app listening on port 8080
app.listen(8080);
//Create a connection object with the user details
const connectionPool = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'manu',
    database: 'coursework3',
    debug: false
});


//Set up the application to handle GET requests sent to the user path
app.get('/products/*', handleGetRequest);//Subfolders
app.get('/products', handleGetRequest);
app.get('/laptops/*', handleGetRequest);
app.get('/laptops', handleGetRequest);


/* Handles GET requests sent to web service.
   Processes path and query string and calls appropriate functions to
   return the data. */
async function handleGetRequest(request, response) {
    //Parse the URL
    var urlObj = url.parse(request.url, true);

    //Extract object containing queries from URL object.
    var queries = urlObj.query;

    //Get the pagination properties if they have been set. Will be undefined if not set.
    var numItems = queries['num_items'];
    var offset = queries['offset'];


    //

    //Split the path of the request into its components
    var pathArray = urlObj.pathname.split("/");

    //Get the last part of the path
    var pathEnd = pathArray[pathArray.length - 1];

    //If path ends with 'products' we return all products
    if (pathEnd === 'products') {
        getTotalProductsCount(response, numItems, offset);//This function calls the getAllProducts function in its callback
        return;
    }

    //If path ends with products/, we return all products
    if (pathEnd === '' && pathArray[pathArray.length - 2] === 'products') {
        getTotalProductsCount(response, numItems, offset);//This function calls the getAllCereals function in its callback
        return;
    }


    //If the last part of the path is a valid user id, return data about that user
    var regEx = new RegExp('^[0-9]+$');//RegEx returns true if string is all digits.
    if (regEx.test(pathEnd)) {
        getProduct(response, pathEnd);
        return;
    }
    //The path is not recognized. Return an error message
    response.status(HTTP_STATUS.NOT_FOUND);

    response.send("{error: 'Path not recognized', url: " + request.url + "}");

}

/** Returns all of the products, possibly with a limit on the total number of items returned and the offset (to
 *  enable pagination). This function should be called in the callback of getTotalProductCount  */
function getAllProducts(response, totNumItems, numItems, offset) {
    //Select the cereals data using JOIN to convert foreign keys into useful data.

    var sql = "SELECT products.name, products.url,products.store, products.price, laptops.model, laptops.color,laptops.url_image,laptops.storage, products.id, products.laptops_id FROM ( ( products INNER JOIN laptops ON laptops.id = products.laptops_id ) ) ";

    //Limit the number of results returned, if this has been specified in the query string
    if (numItems !== undefined && offset !== undefined) {
        sql += "ORDER BY products.id LIMIT " + numItems + " OFFSET " + offset;
    }


    //Execute the query
    connectionPool.query(sql, function (err, result) {

        //Check for errors
        if (err) {
            //Not an ideal error code, but we don't know what has gone wrong.
            response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
            response.json({'error': true, 'message': +err});
            console.log("Something went wrong");
            return;
        }

        //Create JavaScript object that combines total number of items with data
        var returnObj = {totNumItems: totNumItems};
        returnObj.products = result; //Array of data from database
        //Return results in JSON format
        response.json(returnObj);

    });

}


/** When retrieving all products we start by retrieving the total number of products
 The database callback function will then call the function to get the laptop data
 with pagination */
function getTotalProductsCount(response, numItems, offset) {


    var sql = "SELECT COUNT(*) FROM products";


    //Execute the query and call the anonymous callback function.
    connectionPool.query(sql, function (err, result) {

        //Check for errors
        if (err) {
            console.error("Error executing query: " + JSON.stringify(err)); // del?
            //Not an ideal error code, but we don't know what has gone wrong.
            response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
            response.json({'error': true, 'message': +err});
            console.log("Something went wrong");
            return;
        }

        //Get the total number of items from the result
        var totNumItems = result[0]['COUNT(*)'];
        //Call the function that retrieves all cereals
        getAllProducts(response, totNumItems, numItems, offset);
    });

}



/** Returns the one product */
function getProduct(response, id) {
    //Build SQL query to select cereal with specified id.

    var sql = "SELECT products.name, products.url,product.store, products.price, laptops.model, laptops.color, laptops.storage,laptops.url_image, products.id, products.laptops_id FROM ( ( products INNER JOIN laptops ON laptops.id = products.laptops_id  ) ) WHERE laptops.id=" + id;


    //Execute the query
    connectionPool.query(sql, function (err, result) {

        //Check for errors
        if (err) {
            //Not an ideal error code, but we don't know what has gone wrong.
            response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
            response.json({'error': true, 'message': +err});
            console.log("Something went wrong");
            return;
        }

        //Output results in JSON format
        response.json(result);
    });

}





