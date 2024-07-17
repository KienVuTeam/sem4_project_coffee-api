/**
 * 
 */
//alert('aaa')
function uploadImage(){
	debugger
	const ref=firebase.storage().ref();
	const file =document.querySelector('#photo').files[0];
	if(file == null){
		alert("anh null");
		return; 
	}
	console.log('file: '+file);
	const metadata ={
		contentType: file.type
	};
	const name =file.name;
	const uploadImage = ref.child(name).put(file, metadata);
	uploadImage.then(snapshot =>snapshot.ref.getDownloadURL())
	.then(url=> {
		document.getElementById('hdImage').value=url;
		console.log(url);
		alert('success')
	})
	.catch(console.error)
	
}

function uploadImageForEdit(){
	debugger
	const ref=firebase.storage().ref();
	const file =document.querySelector('#edit_photo').files[0];
	if(file == null){
		alert("anh null");
		return; 
	}
	console.log('flie co gi '+file);
	const metadata ={
		contentType: file.type
	};
	const name =file.name;
	const uploadImage = ref.child(name).put(file, metadata);
	uploadImage.then(snapshot =>snapshot.ref.getDownloadURL())
	.then(url=> {
		document.getElementById('edit_hdImage').value=url;
		console.log(url);
		alert('success')
		
	})
	.catch(console.error)
	
}