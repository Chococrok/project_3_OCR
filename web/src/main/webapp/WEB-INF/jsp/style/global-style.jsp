<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<style>

body {
	font-family: Verdana, Geneva, sans-serif;
	min-height: 100vh;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: center;
    background-color: #f1f1f1;
}

header {
	width: 100%;
	background-color: #153718;
	color: #b7b7b7;
}

h1 {
    	font-weight: normal;
    font-size: 4em;
    -webkit-margin-after: 0;
    -webkit-margin-before: 0;
}

a {
    margin: 0.5em 1em 0 1em;
	color: #966969;
	text-decoration: none;
}

a:hover {
	color: #999999;
}

nav {
    display: flex;
    align-items: baseline;
    justify-content: flex-start;
}

button {
    display: flex;
    flex-direction: column;
    align-items: center;
    border: 0;
    border-radius: 5px;
    padding: 1em;
    background-color: #966969;
}
button:hover {
	background-color: #999999;
}
button:active {
  	transform: translateY(4px);
}
button:focus {
	background-color: #755252;
}


.margin {
	margin: 2.5em;
}

.site-list-container {
	width: 50vw;
	display: flex;
    flex-direction: column;
    box-shadow: 5px 5px 20px 0px #888888;
}
.site-container {
    padding: 1.5em;
    text-align: center;
    display: flex;
    background-color: white;
    border-bottom: 1px solid black;
    justify-content: space-between;
}

</style>