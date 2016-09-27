<?php
require "init.php";

$PATIENT_ADM_NO = $_POST["PATIENT_ADM_NO"];

$sql_query="SELECT Name FROM `patient_details` WHERE _id LIKE '$PATIENT_ADM_NO';";


$result = mysqli_query($con,$sql_query);

if(mysqli_num_rows($result)>0)
{
	$row=mysqli_fetch_assoc($result);
	$Name = $row["Name"];
	echo "ADM NO EXIST";
}
else
{
	echo "ADM NO DOES NOT EXISTS";
}


?>