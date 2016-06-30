var isLoading = false;
var loadSource = null;

function attachScrollEvent(loadEvent) {
    loadSource = loadEvent.getSource();

    $(window).scroll(function () {
        scrollPosition = ( $(window).height() + $(window).scrollTop())  / $(document).height();
        console.log(scrollPosition);
        // Send a custom event only if current taskflow is not Loading.
        if (scrollPosition > 0.99 && !isLoading) {
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

function notifyLoadComplete(){
    isLoading = false;
}