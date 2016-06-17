<?php

$db_name = "medi";
$mysql_user = "Medi";
$mysql_pass = "Medi2016";
$server_name = "mysql12.000webhost.com	";

$con = mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);

if(!$con)
{
	//echo "Connection Error.....".mysqli_connect_error();
}
else
{
	//echo "<h3>Database Connection Sucessful!!</h3>";
}
?>