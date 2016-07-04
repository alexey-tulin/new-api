import com.netrika.exceptions.ApplicationException

class UrlMappings {

    static mappings = {
        "/api/v1/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")
        "500"(controller: "errors", action: "unknownError",
                exception: Throwable)
        "500"(controller: "errors", action: "applicationException",
                exception: ApplicationException)
    }
}
