import com.netrika.exceptions.ApplicationException

class UrlMappings {

    static mappings = {
        "/api/v1/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/api/v1/$action?/$id?(.${format})?"(controller: 'dictionary')
        "/api/v1/disability-types/$id?(.${format})?"(controller: 'dictionary', action: "disabilityTypes")
        "/api/v1/ownership-types/$id?(.${format})?"(controller: 'dictionary', action: "ownershipTypes")
        "/api/v1/data-directions/$id?(.${format})?"(controller: 'data', action: "dataDirections")
        "/api/v1/data-sources/$id?(.${format})?"(controller: 'data', action: "dataSources")


        "/"(view: "/index")
        "500"(controller: "errors", action: "unknownError",
                exception: Throwable)
        "500"(controller: "errors", action: "applicationException",
                exception: ApplicationException)
    }
}
