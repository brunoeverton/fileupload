$(document).ready(function () {
	//Inicia o componente de upload
	var uploader = new plupload.Uploader(
			{
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'bth-file', 
				container : document.getElementById('container'), 
				url : '/fileupload/uploadfile',
				chunk_size : '1mb',

				filters : {
					max_file_size : '15mb',
					mime_types : [ {
						title : "Image files",
						extensions : "jpg,gif,png"
					}, {
						title : "Zip files",
						extensions : "zip"
					} ]
				},


				init : {
					PostInit : function() {
						$('#filelist').html('');
						$('#bth-submit').click(submitFile);
					},

					FilesAdded : function(up, files) {
						plupload
								.each(
										files,
										function(file) {
											var htmlFileList = $('#filelist').html();
											htmlFileList += '<div id="' + file.id + '">'
													+ file.name
													+ ' ('
													+ plupload
															.formatSize(file.size)
													+ ') <b></b></div>';
											$('#filelist').html(htmlFileList);
										});
					},

					UploadProgress : function(up, file) {
						var selector = '#' + file.id + ' b';
						var percentual = '<span>' 	+ file.percent + '%</span>';
						$(selector).html(percentual)
					},

					Error : function(up, err) {
						showError('Problema ao enviar arquivo. ' + err.message);
					},
					UploadComplete: function(up, files,object) {						
						$('#table_id').DataTable().ajax.reload();
					},
					FileUploaded: function(up, file, info) {
						var data = JSON.parse(info.response)
						if(data.status==='ERROR'){
							for(var item in data.messages){
								showError(data.messages[item]);
							}
						}else{
							showSuccess("Arquivo enviado com sucesso.");
						}
						
						
		            },
				}
	});
	uploader.init();

	//Inicia dataTable
	$('#table_id').DataTable({
		paging: false,
		searching: false,
		info: false,
		
		ajax: {
	        url: '/fileupload/findFiles',
	        	dataSrc: 'response'
	    },
        columns: [
            { data: "userEmail" },
            { data: "fileName" },
            { data: "status" },
            { data: "uploadTime" },
            { data: "blocksAmount" },
            { defaultContent: "<button>Download</button>" },

        ]
		
	});
    $('#table_id tbody').on( 'click', 'button', function () {    
        var data = 	$('#table_id').DataTable().row( $(this).parents('tr') ).data();
        if(data.downloadLink!==null && data.downloadLink!==undefined && data.downloadLink!==''){        	
        		window.location.href = data.downloadLink;
        }else{
        		showWarning("Este arquivo ainda não está disponível");
        }
    } );
	
    //Botao para atualizar o arquivo
	$('#bth-search').click(function(){
		$('#table_id').DataTable().ajax.reload();
	});
	
	
	//valida e inicia o envio de arquivos
	function submitFile(){
		removeError();
		
		var userEmail = $('#userEmail').val();		
		if(!validateEmail(userEmail)){
			$('#userEmail-div').addClass('has-error');
			showError('E-mail inválido');
			return;
		}
		
		if(uploader.total.queued<=0){
			$('#bth-file-div').addClass('has-error');
			showError('Nenhum arquivo foi selecionado');
			return;
		}
		
		uploader.settings.multipart_params = {};	
		uploader.settings.multipart_params.userEmail = userEmail
		
		uploader.start();
		return false;
	}


});


function removeError(){
	$('#userEmail-div').removeClass('has-error');
	$('#bth-file-div').removeClass('has-error');
}


