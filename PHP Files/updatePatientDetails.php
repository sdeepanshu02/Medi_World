<?php
require "init.php";

$PATIENT_ADM_NO = $_POST["PATIENT_ADM_NO"];
$PATIENT_PASSWORD = $_POST["PATIENT_PASSWORD"];
$PATIENT_CONTACT_NO = $_POST["PATIENT_CONTACT_NO"];
$PATIENT_EMAIL = $_POST["PATIENT_EMAIL"];
$PATIENT_HEIGHT = $_POST["PATIENT_HEIGHT"];
$PATIENT_WEIGHT = $_POST["PATIENT_WEIGHT"];
$PATIENT_METABOLIC_DISORDERS = $_POST["PATIENT_METABOLIC_DISORDERS"];

$sql_query = "UPDATE `patient_details` SET `Password`='$PATIENT_PASSWORD',`Contact_No`='$PATIENT_CONTACT_NO',`Email`='$PATIENT_EMAIL',`Height`='$PATIENT_HEIGHT',`Weight`='$PATIENT_WEIGHT',`Metabolic_Disorders`='$PATIENT_METABOLIC_DISORDERS' WHERE _id='$PATIENT_ADM_NO';";
if(mysqli_query($con,$sql_query))
{
	echo "Update Sucessful....";
}
else
{
	echo "Updation Failed!!".mysqli_error($con);
}


?>