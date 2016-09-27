<?php
require "init.php";
$DOCTOR_USER_ID = $_POST["DOCTOR_USER_ID"];
$DOCTOR_PASSWORD = $_POST["DOCTOR_PASSWORD"];

$sql_query="SELECT Name FROM `doctor_details` WHERE _id LIKE '$DOCTOR_ADM_NO' AND Password LIKE '$DOCTOR_PASSWORD';";


$result = mysqli_query($con,$sql_query);

if(mysqli_num_rows($result)>0)
{
	$row=mysqli_fetch_assoc($result);
	$Name = $row["Name"];
	echo "Login Sucessful";
}
else
{
	echo "DOCTOR ID or Password";
}


?>