class MyUrlMappings {
  static mappings = { 
    "/product/$id" {
       controller = "product"
       action = "show"
    }
  }
}