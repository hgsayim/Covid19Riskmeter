<?php

//getting the dboperation class
require_once '../includes/DbOperation.php';

//https://www.simplifiedcoding.net/Android-mysql-tutorial-to-perform-basic-crud-operation/

//function validating all the paramters are available
//we will pass the required parameters to this function
function isTheseParametersAvailable($params){
    //assuming all parameters are available
    $available = true;
    $missingparams = "";

    foreach($params as $param){
        if(!isset($_POST[$param]) || strlen($_POST[$param])<=0){
            $available = false;
            $missingparams = $missingparams . ", " . $param;
        }
    }

    //if parameters are missing
    if(!$available){
        $response = array();
        $response['error'] = true;
        $response['message'] = 'Parameters ' . substr($missingparams, 1, strlen($missingparams)) . ' missing';

        //displaying error
        echo json_encode($response);

        //stopping further execution
        die();
    }
}

//an array to display response
$response = array();

//if it is an api call
//that means a get parameter named api call is set in the URL
//and with this parameter we are concluding that it is an api call
if(isset($_GET['apicall'])){

    switch($_GET['apicall']){

        //the CREATE operation
        //if the api call value is 'createUser'
        //we will create a record in the database
        case 'createUser':
            //first check the parameters required for this request are available or not
            isTheseParametersAvailable(array('firstName','lastName','email','password'));

            //creating a new dboperation object
            $db = new DbOperation();

            //creating a new record in the database
            $result = $db->createUser(
                $_POST['firstName'],
                $_POST['lastName'],
                $_POST['email'],
                $_POST['password']
            );


            //if the record is created adding success to response
            if($result){
                //record is created means there is no error
                $response['error'] = false;

                //in message we have a success message
                $response['message'] = 'User addedd successfully';

                //and we are getting all the heroes from the database in the response
                // $response['heroes'] = $db->get();
            }else{

                //if record is not added that means there is an error
                $response['error'] = true;

                //and we have the error message
                $response['message'] = 'Some error occurred please try again';
            }

            break;

        //the READ operation
        //if the call is getheroes
        case 'getBlogs':
            $db = new DbOperation();
            $response['error'] = false;
            $response['message'] = 'Request successfully completed';
            $response['blogs'] = $db->getBlogs();
            break;

        case 'getLocations':
            $db = new DbOperation();
            $response['error'] = false;
            $response['message'] = 'Request successfully completed';
            $response['locations'] = $db->getLocations();
            break;



        //the UPDATE operation
        case 'updateProfile':
            isTheseParametersAvailable(array('firstName','lastName','email','password','age', 'covidStatusId'));
            $db = new DbOperation();
            $result = $db->updateProfile(
                $_POST['fistName'],
                $_POST['lastName'],
                $_POST['email'],
                $_POST['password'],
                $_POST['age'],
                $_POST['covidStatusId']
            );

            if($result){
                $response['error'] = false;
                $response['message'] = 'Hero updated successfully';

            }else{
                $response['error'] = true;
                $response['message'] = 'Some error occurred please try again';
            }
            break;

        //the UPDATE operation
        case 'changePassword':
            isTheseParametersAvailable(array('password'));
            $db = new DbOperation();
            $result = $db->changePassword(
                $_POST['password']
            );

            if($result){
                $response['error'] = false;
                $response['message'] = 'Password updated successfully';

            }else{
                $response['error'] = true;
                $response['message'] = 'Some error occurred please try again';
            }
            break;


        //the Complete Profile operation
        case 'completeProfile':
            isTheseParametersAvailable(array('bloodGroupId','gender','age','locationId','covidStatusId'));
            $db = new DbOperation();
            $result = $db->completeProfile(
                $_POST['bloodGroupId'],
                $_POST['gender'],
                $_POST['age'],
                $_POST['locationId'],
                $_POST['covidStatusId']
            );

            if($result){
                $response['error'] = false;
                $response['message'] = 'Profile completed successfully';

            }else{
                $response['error'] = true;
                $response['message'] = 'Some error occurred please try again';
            }
            break;


        //the delete operation
        case 'deletehero':

            //for the delete operation we are getting a GET parameter from the url having the id of the record to be deleted
            if(isset($_GET['id'])){
                $db = new DbOperation();
                if($db->deleteHero($_GET['id'])){
                    $response['error'] = false;
                    $response['message'] = 'Hero deleted successfully';
                    $response['heroes'] = $db->getHeroes();
                }else{
                    $response['error'] = true;
                    $response['message'] = 'Some error occurred please try again';
                }
            }else{
                $response['error'] = true;
                $response['message'] = 'Nothing to delete, provide an id please';
            }
            break;
    }

}else{
    //if it is not api call
    //pushing appropriate values to response array
    $response['error'] = true;
    $response['message'] = 'Invalid API Call';
}

//displaying the response in json structure
echo json_encode($response);