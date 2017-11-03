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

.header-content {
	margin: 0 5em;
}

h1 {
	font-weight: normal;
	font-size: 4em;
	margin-bottom: 0.25em;
}

h2 {
	font-weight: normal;
	text-decoration: underline;
}

h3 {
	margin-top: 2.5em;
}

h5 {
	font-weight: normal;
	font-size: 1em;
	text-decoration: underline;
	margin-bottom: 0;
}

a {
	margin: 1em 1em 0 1em;
	color: #966969;
	text-decoration: none;
}

a:hover {
	color: #999999;
}

nav {
	display: flex;
	align-items: baseline;
	justify-content: space-between;
}

.sub-nav {
	display: flex;
	align-items: baseline;
}

i {
	font-size: 3em !important;
	align-self: center;
	color: #966969;
}

form {
	margin-top: 1em;
	display: flex;
	flex-direction: column;
}

.simpleForm {
	margin: 0;
	display: initial;
}
form:first-child {
	margin-top: 0;
}

form>button {
	margin-top: 1em;
}

form>input {
	margin-top: 1em;
}

form>span {
	align-self: center;
}

td {
	text-align: left;
	padding: 0 5em 1em 0;
}

button {
	margin: auto;
}

.true {
	color: green;
}

.false {
	color: red;
}

.margin {
	margin: 2.5em;
}

.card-container {
	display: flex;
	flex-wrap: wrap;
	flex-direction: row;
	justify-content: center;
	align-items: center;
}

.card {
	margin: 1em;
	min-width: 50%;
	max-width: 85%;
	display: flex;
	flex-direction: column;
	box-shadow: 5px 5px 20px 0px #888888;
	background-color: white;
}

.card-header {
	padding: 2em 3em;
	padding-bottom: 0;
}

.card-content {
	padding: 2.5em 5em;
	display: flex;
	flex-direction: column;
}

.card-content>*:first-child {
	margin-top: 0;
}

.topo {
	min-width: unset;
	width: 25%;
	margin: 2em;
}

.description {
	font-style: italic;
	color: #888888;
	font-size: 0.9em;
}

.list>* {
	padding: 1em;
	text-align: center;
	display: flex;
	border-bottom: 1px solid black;
	justify-content: space-between;
}

.list>*:last-child {
	border-bottom: none;
}

.list-item-column {
	flex-direction: column;
	text-align: start;
}

.date {
	text-align: end;
	font-size: 0.75em;
	color: #888888;;
	margin: 0;
}

.input {
	display: flex;
	flex-direction: column;
	margin: 1.5em;
}

.input>textarea {
	height: 5em;
}

.input>input {
	width: 5em;
}

.clickable {
	display: flex;
	justify-content: space-between;
}

.clickable:hover {
	background-color: #f0f0f0;
}

.clickable:active {
	transform: translateY(4px);
}

.clickable:hover>i {
	color: #999999;
}

.editable {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}
</style>