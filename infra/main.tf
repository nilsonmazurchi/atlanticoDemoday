# terraform {
#   required_providers {
#     koyeb = {
#       source = "koyeb/koyeb"
#       version = "~> 1.0"
#     }
#   }
# }

# provider "koyeb" {
  
# }

# variable "koyeb_token" {}
# variable "docker_image_name" {
#   default = "nilsonmazurchi/atlantico-demoday"
# }
# variable "docker_image_tag" {
#   default = "latest"
# }

# resource "koyeb_app" "demoday" {
#   name = "atlantico-demoday"

#   service {
#     name = "web"
#     type = "web"
#     image {
#       registry = "docker.io"
#       name     = var.docker_image_name
#       tag      = var.docker_image_tag
#     }
#     ports {
#       protocol = "HTTP"
#       internal_port = 8080
#     }
#   }
# }
