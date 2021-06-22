/*
 * Target list parameters: 
 *  0: sceneId
 *  [
 * 	1: id: target identifier in the scene
 *  2: X (from left upper corner)
 *  3: Y
 *  4: radius
 *  ]
 * */

//OJO! Cuando registres el elemento en la bbdd regístralo con las coordenadas reales corregidas.
/* The first target must always be absolute, so we can force the user to start from a known position */
var t1=30;
var t2=40;
var t3=50;

var targetList = [
		[ 1001, [ [ 0, 60, 540, t2, 'A' ] ] ],
		//[ 1002, [ [ 0, 400, -400,t1, 'R' ] ] ],
		//[ 1003, [ [ 0, -400, 400, t2, 'R' ] ] ],
		[ 1004, [ [ 0, 740, 540, t2, 'A' ] ] ],
		//[ 1005, [ [ 0, -400, -400, t3, 'R' ] ] ],
		//[ 1006, [ [ 0, 400, 400, t2, 'R' ] ] ],
		//[ 1007, [ [ 0, -400, -400, t2, 'R' ] ] ],
		//[ 1008, [ [ 0, 400, 400, t3, 'R' ] ] ],
		//[ 1009, [ [ 0, -400, -400, t1, 'R' ] ] ],
		[ 1010, [ [ 0, 60, 540, t2, 'A' ] ] ],
		//[ 1011, [ [ 0, 400, -400, t3, 'R' ] ] ],
		//[ 1012, [ [ 0, -400, 400, t3, 'R' ] ] ],
		//[ 1013, [ [ 0, 400, -400, t2, 'R' ] ] ],
		//[ 1014, [ [ 0, 400, 60, t2, 'A' ] ] ],
		[ 1015, [ [ 0, 60, 540, t2, 'A' ],[ 1, 740, 540, t2, 'A' ] ]],
		[ 1016, [ [ 0, 400, 60, t2, 'A' ] ]],
		[ 1017, [ [ 0, 60, 540, t1, 'A' ],[ 1, 740, 540, t1, 'A' ] ]],
		[ 1018, [ [ 0, 400, 570, t2, 'A' ] ]],
		[ 1019, [ [ 0, 60, 60, t1, 'A' ],[ 1, 740, 60, t1, 'A' ] ]],
		[ 1020, [ [ 0, 400, 540, t2, 'A' ] ]],
	    [ 1021, [ [ 0, 60, 60, t2, 'A' ],[ 1, 740, 60, t2, 'A' ] ]]
	    ];


var targetIndex = 0;
var currentTargets = [];

var minWidth = 800;
var minHeigth = 600;
var canvasBorderSize = 5;

var proportion = 4 / 3;

var canvas;
var ctx;

var lastX = 0;
var lastY = 0;

function getCanvasPosition() {
	var element = document.getElementById('canvas');
	// alert(getComputedStyle(element).getPropertyValue('border-left-width'));
	// canvasBorderSize=getComputedStyle(element).getPropertyValue('border-left-width').replace('px','');
	return element.getBoundingClientRect();
}

function getCanvasX() {
	// We add the canvas border size
	return getCanvasPosition().left + canvasBorderSize;
}

function getCanvasY() {
	return getCanvasPosition().top + canvasBorderSize;
}

function getLastX() {
	return lastX - getCanvasX();
}
function getLastY() {
	return lastY - getCanvasY();
}

var iBody = new Image();
iBody.src = 'img/Fence/fence_112x56.png';
var background;

var hit = new Audio();
hit.src = 'assets/hits/hit.ogg';
var dropSound = new Audio();
dropSound.src = 'assets/drop.ogg';

// var target=new Target(120,120,50);

window.addEventListener('load', init, false);
window.addEventListener('load', paintBackground, false);

parent.addEventListener('click', function() {
	found = false;
	currentTargets.forEach(function(item, index) {
		if (item.hitted(window.event.clientX
				- canvas.getBoundingClientRect().left, window.event.clientY
				- canvas.getBoundingClientRect().top) == true) {
			
			hit.play();
			
			processHit();
			
			found = true;
		}
	});
	if (!found) {
		dropSound.play();
	}
});


function initFittsTracking() {
	// alert('init tracking for '+targetList[targetIndex][0]);
	initFittsSceneTracking(0);
}
function initFittsSceneTracking(sceneIndex) {
	
	// We register the components in the scene with their absolute positions
	targetList[sceneIndex][1].forEach(function(item, index) {
		var temp;
		if (item[4] == 'A') {
			temp = new Target(targetList[sceneIndex][0], item[0], item[1],
					item[2], item[3]);
		} else {
			temp = new Target(targetList[sceneIndex][0], item[0], getLastX()
					+ item[1], getLastY() + item[2], item[3]);
		}
		// WE register the target
		registerComponent(targetList[sceneIndex][0], targetList[sceneIndex][0] +"-"+item[0], 
				item[1]+getCanvasX()-item[3]/2,
				item[2]+getCanvasY()-item[3]/2,
				item[1]+getCanvasX()+item[3]/2,
				item[2]+getCanvasY()+item[3]/2, null, null);
	});
	
	// We initialize the Tracking
	initTracking(targetList[sceneIndex][0]);
}

function processHit() {
	// We keep the current coordinates of the mouse to create relative
	// located targets
	
	if (window.event !== undefined) {
		lastX = window.event.clientX;
		lastY = window.event.clientY;
	}
	
	//Se añade pequeño timeOut debido a que el onClick se ejecuta 1º en fittsTest.js que en el script
	setTimeout(finish, 100);	
}

function finish(){
	//console.log("Stopping recording of scene " + targetList[targetIndex][0]);
	finishSubsceneTracking();
	targetIndex++;
	if (targetIndex < targetList.length) {
		//initTracking(targetList[targetIndex][0]);
		initFittsSceneTracking(targetIndex);
		//console.log("Initializing recording of scene "+ targetList[targetIndex][0]);
		paintBackground();
	} else {
		paintSendingMessage();
		// We close the fitts experiment and move to the next page
		finishTracking(nextPage);
	}
}

function updateView() {
	{
		paintBackground();
		paintTargets();
	}
}
function paintSendingMessage()
{
	ctx.font = "bold 30px Arial";
	ctx.fillText(sendingMessage, canvas.width/2-50 , canvas.height/2 );
}
function paintTargets() {
	// We reset CurrentTargets
	currentTargets = [];	
	
	// We paint the targets
	targetList[targetIndex][1].forEach(function(item, index) {
		var temp;
		if (item[4] == 'A') {
			temp = new Target(targetList[targetIndex][0], item[0], item[1],
					item[2], item[3]);
		} else {
			temp = new Target(targetList[targetIndex][0], item[0], getLastX()
					+ item[1], getLastY() + item[2], item[3]);
		}
		currentTargets.push(temp);
		temp.stroke(ctx);
	});
}
function paintBackground() {
	fence = new Image();
	background = new Image();
	background.onload = function() {
		// We paint the grass
		backWidth = background.width;
		backHeigth = background.height;
		for (i = 0; i <= canvas.width / backWidth; i++) {
			for (j = 0; j <= canvas.height / backHeigth; j++) {
				paintImage(background, i * backWidth, j * backHeigth);
			}
		}

		// We paint the fences
		backWidth = fence.width;
		backHeigth = fence.height;
		for (i = 0; i <= canvas.width / backWidth; i++) {
			paintImage(fence, i * backWidth, 10);
			paintImage(fence, i * backWidth, canvas.height - 10 - fence.height);
		}
		paintTargets();
	}
	background.src = 'img/backgrass.png';
	fence.src = 'img/Fence/fence_112x56.png';

}

function init() {
	canvas = document.getElementById('canvas');
	ctx = canvas.getContext('2d');
	targets = targetList[0];
	// paintBackground();
}

function paintImage(x, y, image) {
	ctx.drawImage(x, y, image);
}

function resizeCanvas() {
	if (canvas != null) {
		/*
		 * var w = (window.innerWidth -20) / canvas.width; var h =
		 * (window.innerHeight-20) / canvas.height; var scale = Math.min(h, w);
		 * canvas.width = (canvas.width * scale); canvas.height = (canvas.height *
		 * scale);
		 */
		var w = (window.innerWidth - 40);
		var h = (window.innerHeight - 40);
		if (w / h > proportion) {
			canvas.width = h * proportion;
			canvas.heigth = h;
		} else {
			canvas.width = w;
			canvas.heigth = w * proportion;
		}
		// paintTarget(100,100,50);
	}
}
/*
 * function paintTarget(x,y,size) { ctx.fillStyle = '#0f0';
 * ctx.fillRect(x-size/2, y-size/2, size, size); ctx.arc(x, y, size/2, 0,
 * Math.PI * 2, true); ctx.stroke(); }
 */

function Target(sceneId, targetId, x, y, radius) {
	this.sceneId = sceneId;
	this.targetId = targetId;
	this.x = (x == null) ? 0 : x;
	this.y = (y == null) ? 0 : y;
	this.radius = (radius == null) ? 0 : radius;
}

Target.prototype.stroke = function(ctx) {

	var rad = this.radius - 3;
	this.paintCircle(ctx, this.radius, "white");
	rad = rad / 3;
	this.paintCircle(ctx, rad * 3, "red");
	this.paintCircle(ctx, rad * 2, "white");
	this.paintCircle(ctx, rad, "red");

	//console.log("Painting target in " + this.x + "," + this.y + " radius "+ this.radius)
	//console.log("Painting target in abs " + (this.x + getCanvasX()) + ","	+ (getCanvasY() + this.y) + " radius " + this.radius)

}
Target.prototype.paintCircle = function(ctx, radius, color) {
	ctx.beginPath();
	ctx.arc(this.x, this.y, radius, 0, Math.PI * 2, true);
	// ctx.arc(50, 50, 50, 0, 2 * Math.PI, false);

	ctx.fillStyle = color;
	ctx.fill();
	ctx.stroke();
}

Target.prototype.hitted = function(x, y) {
	if (Target != null) {
		var dx = this.x - x;
		var dy = this.y - y;
		return (Math.sqrt(dx * dx + dy * dy) <= this.radius ? true : false);
	}
}
