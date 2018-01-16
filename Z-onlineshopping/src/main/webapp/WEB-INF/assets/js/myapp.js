$(function(){
	// solving the active menu problem
	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Product':
		$('#listProduct').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProduct').addClass('active');
		break;
	default:
		$('#listProduct').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
});