terraform {
  required_providers {
    koyeb = {
      source = "koyeb/koyeb"      
    }
    random = {
      source = "hashicorp/random"      
    }
  }
}

provider "koyeb" {
  #
  # Use the KOYEB_TOKEN env variable to set your Koyeb API token.
  #
}

resource "random_id" "suffix" {
  byte_length = 4
}

resource "koyeb_app" "app" {
  name = "atl-demoday-${random_id.suffix.hex}"
}

# variable "koyeb_token" {}
# variable "docker_image_name" {
#   default = "nilsonmazurchi/atlantico-demoday"
# }
# variable "docker_image_tag" {
#   default = "latest"
# }

resource "koyeb_service" "service" {
  app_name = koyeb_app.app.name

  definition {
    name = "atlantico-demoday"
    instance_types{
        type = "free"
        }
    ports {
      protocol = "http"
      port = 8080
      }
    scalings {
      min = 0
      max = 1
      }
    env {
        key = "PORT"
        value = "8080"
        }
    routes {
      path = "/"
      port = 8080
      }
    regions = ["was"]
    docker {
      image = "${var.docker_image_name}:${var.docker_image_tag}"
      } 
    }

    depends_on = [
    koyeb_app.app
    ]
}
