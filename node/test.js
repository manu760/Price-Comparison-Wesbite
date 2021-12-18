let server = require('./server.js');
module.exports = server;
let chai = require("chai");
let chaiHttp = require("chai-http");
//Built in Node.js assertions
const assert = require ("assert");
const response = require("express");


//Database code defined in external file
let db = require('./database');
module.exports = db;

//Assertion
chai.should();
chai.use(chaiHttp);


//Wrapper for all server tests
describe('Database', function() {

    //Mocha test for getTotalCerealsCount method in Database.
    describe('#getTotalProductsCount', function () {
        it('should return the count of products in the database', function (done) {
            //Data and dummy objects for test
            let response = {};
            response.status = (errorCode) => {//Empty function in case of error
                return {
                    json: (errorMessage) => {
                        console.log("Error code " + errorCode);
                        assert.fail("Error code" + errorCode);
                        done();
                    }
                }
            };

            response.send = (result) => {
                let resObj = JSON.parse(result);
                resObj.should.be.a('array');
                if (resObj.length > 1) {
                    resObj[0].should.have.property('name');
                    resObj[0].should.have.property('price');
                    resObj[0].should.have.property('store');

                }
                done();
            }
            db.getTotalProductsCount(response);

        });
    });

    //Mocha test for getProduct method in Database.
    describe('#getProductName', function () {
        it('should return the one product with specific name  in the database', function (done) {
            //Data and dummy objects for test
            let response = {};
            let id = {};
            response.status = (errorCode) => {//Empty function in case of error
                return {
                    json: (errorMessage) => {
                        console.log("Error code " + errorCode);
                        assert.fail("Error code" + errorCode);
                        done();
                    }
                }
            };

            response.send = (result) => {
                let resObj = JSON.parse(result);
                resObj.should.be.a('array');
                if (resObj.length > 1) {
                    resObj[0].should.have.property('name');
                }
                done();
            }
            db.getProductName(response);

        });
    });

    //Mocha test for getProduct method in Database.
    describe('#getProductID', function () {
        it('should return the products with only ids in the  database', function (done) {
            //Data and dummy objects for test
            let response = {};
            let id = {};
            response.status = (errorCode) => {//Empty function in case of error
                return {
                    json: (errorMessage) => {
                        console.log("Error code " + errorCode);
                        assert.fail("Error code" + errorCode);
                        done();
                    }
                }
            };

            response.send = (result) => {
                let resObj = JSON.parse(result);
                resObj.should.be.a('array');
                if (resObj.length > 1) {
                    resObj[0].should.have.property('id');
                }
                done();
            }
            db.getProductID(response);

        });
    });

    //Mocha test for getProduct method in Database.
    describe('#getProductPrice', function () {
        it('should return the all products with prices', function (done) {
            //Data and dummy objects for test
            let response = {};
            let id = {};
            response.status = (errorCode) => {//Empty function in case of error
                return {
                    json: (errorMessage) => {
                        console.log("Error code " + errorCode);
                        assert.fail("Error code" + errorCode);
                        done();
                    }
                }
            };

            response.send = (result) => {
                let resObj = JSON.parse(result);
                resObj.should.be.a('array');
                if (resObj.length > 1) {
                    resObj[0].should.have.property('price');
                }
                done();
            }
            db.getProductPrice(response);

        });
    });


    //Mocha test for getProduct method in Database.
    describe('#getAllProducts', function () {
        it('should return the all products', function (done) {
            //Data and dummy objects for test
            let response = {};
            let id = {};
            response.status = (errorCode) => {//Empty function in case of error
                return {
                    json: (errorMessage) => {
                        console.log("Error code " + errorCode);
                        assert.fail("Error code" + errorCode);
                        done();
                    }
                }
            };

            response.send = (result) => {
                let resObj = JSON.parse(result);
                resObj.should.be.a('array');
                if (resObj.length > 1) {
                    resObj[0].should.have.property('store');
                }
                done();
            }
            db.getAllProducts(response);

        });
    });

});