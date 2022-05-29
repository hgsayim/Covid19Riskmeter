<?php
 
class DbOperation
{
    //Database connection link
    private $con;

    //Class constructor
    function __construct()
    {
        //Getting the DbConnect.php file
        require_once dirname(__FILE__) . '/DbConnect.php';

        //Creating a DbConnect object to connect to the database
        $db = new DbConnect();

        //Initializing our connection link of this class
        //by calling the method connect of DbConnect class
        $this->con = $db->connect();
    }

    /*
    * The create operation
    * When this method is called a new user record is created in the database
    */
    function createUser($firstName, $lastName, $email, $password){
        $stmt = $this->con->prepare("INSERT INTO user (firstName, lastName, email, password) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("ssis", $firstName, $lastName, $email, $password);
        if($stmt->execute())
            return true;
        return false;
    }

    /*
    * The read operation
    * When this method is called it is returning all the existing record of the database
    */
    function getBlogs(){

        // Get blogs


        $stmt = $this->con->prepare("SELECT id, authorId, createdAt, title, content, coverPhoto, isPublished FROM blog");
        $stmt->execute();
        $stmt->bind_result($id, $authorId, $createdAt, $title, $content,  $coverPhoto, $isPublished);

        $blogs = array();

        while($stmt->fetch()){
            $blog  = array();
            $blog['id'] = $id;
            $blog['authorId'] = $authorId;
            $blog['createdAt'] = $createdAt;
            $blog['title'] = $title;
            $blog['content'] = $content;
            $blog['coverPhoto'] = $coverPhoto;
            $blog['isPublished'] = $isPublished;


            array_push($blogs, $blog);
        }

        return $blogs;
    }


    function getLocations(){

        // Get locations


        $stmt = $this->con->prepare("SELECT id, latitude, longitude FROM location");
        $stmt->execute();
        $stmt->bind_result($id, $latitude, $longitude);

        $locations = array();

        while($stmt->fetch()){
            $location  = array();
            $location['id'] = $id;
            $location['latitude'] = $latitude;
            $location['longitude'] = $longitude;

            array_push($locations, $location);
        }

        return $locations;
    }


    //Complete Profile

    /*
    * The complete profile operation
    * When this method is called the record with the given id is updated with the missing values
    */
    function completeProfile($bloodGroupId, $gender, $age, $locationId, $covidStatusId){
        $stmt = $this->con->prepare("UPDATE user SET bloodGroupId = ?, gender = ?, age = ?, locationId = ?, covidStatusId = ? WHERE id = ?");
        $stmt->bind_param("ssisi", $bloodGroupId, $gender, $age, $locationId, $covidStatusId);
        if($stmt->execute())
            return true;
        return false;
    }

    function updateProfile($firstName, $lastName, $email, $password, $age, $covidStatusId){
        $stmt = $this->con->prepare("UPDATE user SET firstName = ?, lastName = ?, email = ?, password = ?, age = ?, covidStatusId = ? WHERE id = ?");
        $stmt->bind_param("ssisi", $firstName, $lastName, $email, $password, $age, $covidStatusId);
        if($stmt->execute())
            return true;
        return false;
    }

    function changePassword($password){
        $stmt = $this->con->prepare("UPDATE user SET $password = ? WHERE id = ?");
        $stmt->bind_param("ssisi", $password);
        if($stmt->execute())
            return true;
        return false;
    }



    /*
    * The delete operation
    * When this method is called record is deleted for the given id
    */
    function deleteHero($id){
        $stmt = $this->con->prepare("DELETE FROM heroes WHERE id = ? ");
        $stmt->bind_param("i", $id);
        if($stmt->execute())
            return true;

        return false;
    }
}
