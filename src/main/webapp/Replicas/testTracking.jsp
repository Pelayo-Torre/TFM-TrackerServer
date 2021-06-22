<?php
    session_start();
?>
<!DOCTYPE html>
<html>
    <head>
        <title>See my trace</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="js/scriptTest.js"></script>        
		<script>
            var session_id = '<?php echo ( session_id() );?>';
		</script>
    </head>

    <body onload="getTracking(session_id);">
        <h1></h1>
        
        <canvas id="myCanvas" width="1800" height="1000"></canvas>
        <!--
        <input type="submit" onclick="getTracking(session_id)"/>
        -->
        <div id="sent" name="sent">
			...
		</div>
		
		<div id="result" name="result">
			...
		</div>
    </body>
</html>