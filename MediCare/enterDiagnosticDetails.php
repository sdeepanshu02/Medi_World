<?php
require "init.php";

$PATIENT_MEDICAL_HISTORY_ADM_NO = $_POST["PATIENT_MEDICAL_HISTORY_ADM_NO"];
$PATIENT_MEDICAL_HISTORY_SYMPTOMS = $_POST["PATIENT_MEDICAL_HISTORY_SYMPTOMS"];
$PATIENT_MEDICAL_HISTORY_MEDICAL_TEST = $_POST["PATIENT_MEDICAL_HISTORY_MEDICAL_TEST"];
$PATIENT_MEDICAL_HISTORY_MEDICINES_PRESCRIBED = $_POST["PATIENT_MEDICAL_HISTORY_MEDICINES_PRESCRIBED"];
$PATIENT_MEDICAL_HISTORY_DATE_TIME_SHOW=$_POST["PATIENT_MEDICAL_HISTORY_DATE_TIME_SHOW"];
$PATIENT_MEDICAL_HISTORY_DATE_TIME_STORE=$_POST["PATIENT_MEDICAL_HISTORY_DATE_TIME_STORE"];
$sql_query = "INSERT INTO patient_medical_history(`Adm_No`, `Symptoms`, `Medical_Test`, `Medicines_Prescribed`, `Date_Time_Show`, `Date_Time_Store`) VALUES('$PATIENT_MEDICAL_HISTORY_ADM_NO','$PATIENT_MEDICAL_HISTORY_SYMPTOMS','$PATIENT_MEDICAL_HISTORY_MEDICAL_TEST','$PATIENT_MEDICAL_HISTORY_MEDICINES_PRESCRIBED','$PATIENT_MEDICAL_HISTORY_DATE_TIME_SHOW','$PATIENT_MEDICAL_HISTORY_DATE_TIME_STORE');";
if(mysqli_query($con,$sql_query))
{
	echo "Data Inserted";
}
else
{
	echo "Insertion Failed!!".mysqli_error($con);
}


?>