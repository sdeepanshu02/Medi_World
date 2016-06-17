<?php
require "init.php";

$PATIENT_ADM_NO = $_POST["PATIENT_ADM_NO"];

$sql_query="SELECT * FROM `patient_details` WHERE `_id`='$PATIENT_ADM_NO';";


$result = mysqli_query($con,$sql_query);

$patient_Details = array();

while($row = mysqli_fetch_array($result))
{
	array_push($patient_Details,array("_id"=>$row[0],"Name"=>$row[1],"Password"=>$row[2],"Contact_No"=>$row[3],"Email"=>$row[4],"Gender"=>$row[5],"Dob"=>$row[6],"BloodGroup"=>$row[7],"Height"=>$row[8],"Weight"=>$row[9],"Metabolic_Disorders"=>$row[10]));
}

echo json_encode(array("Patient_Details_From_Server"=>$patient_Details));
mysqli_close($con);

?>