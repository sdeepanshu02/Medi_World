<?php
require "init.php";

$PATIENT_ADM_NO = $_POST["PATIENT_ADM_NO"];
$PATIENT_PASSWORD = $_POST["PATIENT_PASSWORD"];

$sql_query="SELECT Name FROM `patient_details` WHERE _id LIKE '$PATIENT_ADM_NO' AND Password LIKE '$PATIENT_PASSWORD';";


$result = mysqli_query($con,$sql_query);

if(mysqli_num_rows($result)>0)
{
	$row=mysqli_fetch_assoc($result);
	$Name = $row["Name"];
	echo "Login Sucessful";
}
else
{
	echo "Invalid Admission No or Password";
}


?>