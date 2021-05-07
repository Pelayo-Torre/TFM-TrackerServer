<?php
    session_start();
?>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello jQuery</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="js/scriptTest.js"></script>        
		<script>
            var session_id = '<?php echo ( session_id() );?>';
			window.onload=initTracking(55);
		</script>
    </head>

    <body>
        <h1>Testing</h1>
        <a href="testTracking.php" onclick="finishTracking();">Mueve el ratón por la página en blanco sin correr demasiado y luego pincha aquí!</a>
        <div id="sent" name="sent">
			...
		</div>
		
		<div id="result" name="result">
			...
		</div>
    </body>
</html>