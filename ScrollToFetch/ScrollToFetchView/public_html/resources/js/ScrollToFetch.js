var isLoading = false;
var loadSource = null;

function attachScrollEvent(loadEvent) {
    loadSource = loadEvent.getSource();
    alert()
    $(window).scroll(function () {
        var totalHeight = window.innerHeight;
        var scrollPosition = $(selector).scrollTop();
        scrollPosition = scrollPosition / totalHeight;
        if (scrollPosition > 0.95) {
            isLoading = true;
            var customEvent = new AdfCustomEvent(loadSource, "scrollToFetchListener", 
            {
            },
true);
            customEvent.preventUserInput();
            customEvent.queue();
        }
    });
}