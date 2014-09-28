/**
 * Created by mary on 27.09.14.
 */

function currentSlide( current ) {
    $(".current_slide").text(current + " of " + $("#slides").slides("status","total") );
}

$(function(){
    $("#slides").slidesjs({
        width:900,
        height: 480,
        navigation: false,
        pagination: false,
        play: {
            auto: true,
            interval: 5000,
            effect:"fade"
        },
        effect: {
            fade: {
                speed: 400
            }
        }
    });
    $('.show-hide').click(function(){
        $('#slides').toggle();
        return false;
    });

});