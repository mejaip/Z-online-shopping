$(function() {
	// solving the active menu problem
	switch (menu) {
	case 'Home':
		$('#home').addClass('active');
		break;
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Product':
		$('#listProduct').addClass('active');
		break;
	case 'Manage Product':
		$('#manageProducts').addClass('active');
		break;
	case 'User Cart':
		$('#userCart').addClass('active');
		break;
	default:
		$('#listProduct').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
	// To Tackle CSRF Token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');

	if ((token != undefined && header != undefined)
			&& (token.length > 0 && header.length > 0)) {
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	}

	// code for jquery dataTable

	var $table = $('#producstListTable');

	// executes the below code only where we have this table

	if ($table.length) {

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products'
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products'
		}

		$table
				.DataTable({

					lengthMenu : [
							[ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records',
									'All Records' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';

								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377;' + data
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}
									return data;
								}

							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160';

									if (userRole == 'ADMIN') {

										str += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';

									} else {

										if (row.quantity < 1) {
											str += '<a href="javascript.void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										} else {

											str += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ data
													+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';

										}
									}

									return str;
								}

							} ]

				});
	}

	// Dismissing the Alert
	var $alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)
	}

	// ------------------------

	// Data Table for Admin

	var $adminProductsTable = $('#adminProductsTable');

	if ($adminProductsTable.length) {
		// console.log('Inside the table!');

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		$adminProductsTable
				.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},

							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="adminDataTableImg"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;

								}
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									if (data) {
										str += '<label class="switch"> <input type="checkbox" value="'
												+ row.id
												+ '" checked="checked">  <div class="slider round"> </div></label>';

									} else {
										str += '<label class="switch"> <input type="checkbox" value="'
												+ row.id
												+ '">  <div class="slider round"> </div></label>';
									}

									return str;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

									return str;
								}
							} ],

					initComplete : function() {
						var api = this.api();
						api
								.$('.switch input[type="checkbox"]')
								.on(
										'change',
										function() {

											var checkbox = $(this);
											var checked = checkbox
													.prop('checked');
											var dMsg = (checked) ? 'You want activate the product?'
													: 'You want to De activate the product?';
											var value = checkbox.prop('value');

											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation and Deactivation',
														message : dMsg,
														callback : function(
																confirmed) {
															if (confirmed) {
																console
																		.log(value);
																var activationUrl = window.contextRoot
																		+ '/manage/product/'
																		+ value
																		+ '/activation';
																$
																		.post(
																				activationUrl,
																				function(
																						data) {

																					bootbox
																							.alert({
																								size : 'medium',
																								title : 'Information',
																								message : data
																							});
																				});
															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}
													});

										});
					}
				});

	}
	// Catgory validation
	$categoryForm = $('#categoryForm');

	if ($categoryForm.length) {

		$categoryForm.validate({
			rules : {
				name : {
					required : true,
					minlength : 3
				},
				description : {
					required : true,
					minlength : 3
				}
			},
			messages : {
				name : {
					required : 'Please enter product name!',
					minlength : 'Please enter atleast five characters'
				},
				description : {
					required : 'Please enter product name!',
					minlength : 'Please enter atleast five characters'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				error.addClass('help-block');
				error.insertAfter(element);
			}
		});

	}
	// -----------------
	// Login validation
	$loginForm = $('#loginForm');

	if ($loginForm.length) {

		$loginForm.validate({
			rules : {
				username : {
					required : true,
					email : true
				},
				password : {
					required : true
				}
			},
			messages : {
				username : {
					required : 'Please enter The Username!',
					email : 'Please enter valid Email address'
				},
				password : {
					required : 'Please enter the Password!'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				error.addClass('help-block');
				error.insertAfter(element);
			}
		});

	}
	// -------------------

	// Click Event of refresh cart button

	$('button[name = "refreshCart"]').click(function() {

		// Fetch the cart line id
		var cartLineId = $(this).attr('value');
		var countElement = $('#count_' + cartLineId);

		var originalCount = countElement.attr('value');
		var currentCount = countElement.val();

		// Work only when the count has changed

		if (currentCount !== originalCount) {
			if (currentCount < 1 || currentCount > 3) {
				countElement.val(originalCount);
				bootbox.alert({
					size: 'medium',
					title: 'Error',
					message: 'Product count should be 1 minimum and 3 Maximum'
				});
			}
			
			else{
				var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count='+ currentCount;
				
				// forward to controller
				
				window.location.href = updateUrl;
				
			}
			
		}

	});

	//
});