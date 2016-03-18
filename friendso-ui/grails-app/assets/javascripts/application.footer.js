/*anything after html load*/

//= require jquery-2.1.3.js
//= require_tree .
//= require ../bootstrap/js/bootstrap.min
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
            $(this).fadeOut();
        });
    })(jQuery);
}
